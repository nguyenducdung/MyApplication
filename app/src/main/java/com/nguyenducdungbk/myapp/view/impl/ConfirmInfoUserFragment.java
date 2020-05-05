package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentConfirmInfoUserBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.ConfirmInfoUserViewModule;
import com.nguyenducdungbk.myapp.injection.DaggerConfirmInfoUserViewComponent;
import com.nguyenducdungbk.myapp.presenter.ConfirmInfoUserPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.ConfirmInfoUserView;

import javax.inject.Inject;

public final class ConfirmInfoUserFragment extends BaseFragment<ConfirmInfoUserPresenter, ConfirmInfoUserView, FragmentConfirmInfoUserBinding> implements ConfirmInfoUserView {
    @Inject
    PresenterFactory<ConfirmInfoUserPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public ConfirmInfoUserFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerConfirmInfoUserViewComponent.builder()
                .appComponent(parentComponent)
                .confirmInfoUserViewModule(new ConfirmInfoUserViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_confirm_info_user;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<ConfirmInfoUserPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).hideIconOrder();
        }
    }
}
