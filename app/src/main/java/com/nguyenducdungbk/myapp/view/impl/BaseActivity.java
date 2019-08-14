package com.nguyenducdungbk.myapp.view.impl;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.presenter.BasePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterLoader;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.view.BaseView;
import com.nguyenducdungbk.myapp.view.dialog.MyDialog;
import com.nguyenducdungbk.myapp.view.dialog.MyLoading;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView, T extends ViewDataBinding> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P>, BaseView {
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);

    protected T binding;

    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean mFirstStart;

    private CompositeDisposable mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        binding = DataBindingUtil.setContentView(this, getLayoutResId());

        injectDependencies();
        setContentView(getLayoutResId());

        getSupportLoaderManager().initLoader(0, null, this).startLoading();
    }

    private void injectDependencies() {
        setupComponent(((MyApp) getApplication()).getAppComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();

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
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompositeSubscription.dispose();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterFactory());
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

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract int getRootViewId();

    public abstract ViewController getViewController();

    @Override
    public void onBackPressed() {
        if (getViewController() != null && getViewController().getCurrentFragment() != null) {
            if (getViewController().getCurrentFragment().backPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Avoid duplicate click listener at the same Time
     *
     * @return True if occurred duplicate click, else other wise
     */
    protected boolean avoidDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - BaseFragment.lastClickTime < Define.CLICK_TIME_INTERVAL) {
            return true;
        }
        BaseFragment.lastClickTime = now;
        return false;
    }

    @Override
    public void showErrorDialog(String messageRes) {
        new MyDialog(this)
                .setMessage(messageRes)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void showLoading() {
        MyLoading.getInstance(this).show();
    }

    @Override
    public void hiddenLoading() {
        MyLoading.getInstance(this).hidden();
    }

    @Override
    public void showErrorDialog(String title, String message) {
        new MyDialog(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void showErrorDialog(int messageRes) {
        new MyDialog(this)
                .setMessage(messageRes)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    protected void onDestroy() {
        MyLoading.getInstance(this).destroyLoadingDialog();
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
        }
    }

    @Override
    public void initView() {

    }
}
