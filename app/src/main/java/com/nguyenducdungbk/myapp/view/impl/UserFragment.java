package com.nguyenducdungbk.myapp.view.impl;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentUserBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerUserViewComponent;
import com.nguyenducdungbk.myapp.injection.UserViewModule;
import com.nguyenducdungbk.myapp.presenter.UserPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.UserView;

import javax.inject.Inject;

public final class UserFragment extends BaseFragment<UserPresenter, UserView, FragmentUserBinding> implements UserView {
    @Inject
    PresenterFactory<UserPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerUserViewComponent.builder()
                .appComponent(parentComponent)
                .userViewModule(new UserViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<UserPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }
}
