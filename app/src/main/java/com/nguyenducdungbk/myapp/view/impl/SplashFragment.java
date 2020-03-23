package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentSplashBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerSplashViewComponent;
import com.nguyenducdungbk.myapp.injection.SplashViewModule;
import com.nguyenducdungbk.myapp.presenter.SplashPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.SplashView;

import javax.inject.Inject;

public final class SplashFragment extends BaseFragment<SplashPresenter, SplashView, FragmentSplashBinding> implements SplashView {
    @Inject
    PresenterFactory<SplashPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerSplashViewComponent.builder()
                .appComponent(parentComponent)
                .splashViewModule(new SplashViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<SplashPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
    }
}
