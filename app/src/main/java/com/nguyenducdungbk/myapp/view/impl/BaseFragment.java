package com.nguyenducdungbk.myapp.view.impl;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.presenter.BasePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterLoader;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.utils.DeviceUtil;
import com.nguyenducdungbk.myapp.view.BaseView;
import com.nguyenducdungbk.myapp.view.dialog.MyDialog;
import com.nguyenducdungbk.myapp.view.dialog.MyLoading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView, T extends ViewDataBinding> extends Fragment implements LoaderManager.LoaderCallbacks<P>, BaseView {
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);
    public static long lastClickTime = System.currentTimeMillis();
    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;

    protected T binding;
    /**
     * Is this the first start of the fragment (after onCreate)
     */
    private boolean mFirstStart;

    private ViewController mViewController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(0, null, this).startLoading();
    }

    public void hideKeyboard() {
        if (getActivity() != null) {
            DeviceUtil.hideSoftKeyboard(getActivity());
        }
    }

    private void injectDependencies() {
        setupComponent(((MyApp) getActivity().getApplication()).getAppComponent());
    }

    @Override
    public void onStart() {
        super.onStart();
        lastClickTime = System.currentTimeMillis();
        if (mPresenter == null) {
            mNeedToCallStart.set(true);
        } else {
            doStart();
        }
    }

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart() {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getActivity(), getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        mPresenter = presenter;

        if (mNeedToCallStart.compareAndSet(true, false)) {
            doStart();
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    protected abstract void setupComponent(@NonNull AppComponent appComponent);

    //endregion
    @LayoutRes
    protected abstract int getLayoutResId();

    public void setData(HashMap<String, Object> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String) {
                bundle.putString(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                bundle.putDouble(entry.getKey(), ((Double) entry.getValue()));
            } else if (entry.getValue() instanceof Integer) {
                bundle.putInt(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Float) {
                bundle.putFloat(entry.getKey(), ((Float) entry.getValue()));
            } else if (entry.getValue() instanceof Boolean) {
                bundle.putBoolean(entry.getKey(), ((Boolean) entry.getValue()));
            } else if (entry.getValue() instanceof Parcelable) {
                bundle.putParcelable(entry.getKey(), ((Parcelable) entry.getValue()));
            } else if (entry.getValue() instanceof String[]) {
                bundle.putStringArray(entry.getKey(), (String[]) entry.getValue());
            } else if (entry.getValue() instanceof ArrayList) {
                if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof String) {
                    bundle.putStringArrayList(entry.getKey(), (ArrayList<String>) entry.getValue());
                } else if (((ArrayList) entry.getValue()).size() > 0 && ((ArrayList) entry.getValue()).get(0) instanceof Parcelable) {
                    bundle.putParcelableArrayList(entry.getKey(), (ArrayList<? extends Parcelable>) entry.getValue());
                }
            } else if (entry.getValue() instanceof Serializable) {
                bundle.putSerializable(entry.getKey(), ((Serializable) entry.getValue()));
            }
        }
        setArguments(bundle);
    }


    public void backFromAddFragment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            //back to over 2 screen
            String screenBefore = bundle.getString(Define.Shortcut.SCREEN_BEFORE, null);
            if (this.getClass().getSimpleName().equals(screenBefore)) {
                bundle.remove(Define.Shortcut.SCREEN_BEFORE);
                setArguments(bundle);
            } else if (screenBefore != null) {
                HashMap<String, String> data = new HashMap<>();
                data.put(Define.Shortcut.SCREEN_BEFORE, screenBefore);
                getViewController().backFromAddFragment(data);
            }
        }
    }

    public ViewController getViewController() {
        if (mViewController == null) {
            return ((BaseActivity) getActivity()).getViewController();
        } else {
            return mViewController;
        }
    }

    public void setViewController(ViewController viewController) {
        this.mViewController = viewController;
    }

    /**
     * This func has call when pressed back button on device.
     *
     * @return True if need destroy Activity
     */
    public abstract boolean backPressed();

    /**
     * Avoid duplicate click listener at the same Time
     *
     * @return True if occurred duplicate click, else other wise
     */
    protected boolean avoidDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < Define.CLICK_TIME_INTERVAL) {
            return true;
        }
        lastClickTime = now;
        return false;
    }


    @Override
    public void showErrorDialog(String message) {
        if (getUserVisibleHint())
            new MyDialog(getContext())
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, null)
                    .show();
    }


    @Override
    public void showErrorDialog(String title, String message) {
        if (getUserVisibleHint())
            new MyDialog(getContext())
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, null)
                    .show();
    }


    @Override
    public void showErrorDialog(int messageRes) {
        if (getUserVisibleHint())
            new MyDialog(getContext())
                    .setMessage(messageRes)
                    .setPositiveButton(R.string.ok, null)
                    .show();
    }

    @Override
    public void showLoading() {
        MyLoading.getInstance(getContext()).show();
    }

    @Override
    public void hiddenLoading() {
        MyLoading.getInstance(getContext()).hidden();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLoading.getInstance(getContext()).destroyLoadingDialog();
        if (binding != null) {
            binding.unbind();
        }
    }
}
