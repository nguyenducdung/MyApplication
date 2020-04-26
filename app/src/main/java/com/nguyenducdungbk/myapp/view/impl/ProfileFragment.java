package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentProfileBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerProfileViewComponent;
import com.nguyenducdungbk.myapp.injection.ProfileViewModule;
import com.nguyenducdungbk.myapp.presenter.ProfilePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.ProfileView;

import javax.inject.Inject;

public final class ProfileFragment extends BaseFragment<ProfilePresenter, ProfileView, FragmentProfileBinding> implements ProfileView {
    @Inject
    PresenterFactory<ProfilePresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerProfileViewComponent.builder()
                .appComponent(parentComponent)
                .profileViewModule(new ProfileViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<ProfilePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }
}
