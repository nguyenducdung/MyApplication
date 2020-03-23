package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ActivityMainBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerMainViewComponent;
import com.nguyenducdungbk.myapp.injection.MainViewModule;
import com.nguyenducdungbk.myapp.presenter.MainPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.MainView;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainPresenter, MainView, ActivityMainBinding> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @Inject
    ViewController mViewController;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule(this, getRootViewId()))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getRootViewId() {
        return R.id.rlMain;
    }

    @Override
    public ViewController getViewController() {
        return mViewController;
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp app = (MyApp) getApplication();
    }

    @Override
    public void initView() {
        super.initView();
        getViewController().addFragment(SplashFragment.class, null);
    }
}
