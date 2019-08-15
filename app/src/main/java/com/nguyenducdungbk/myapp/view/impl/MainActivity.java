package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ActivityMainBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerMainViewComponent;
import com.nguyenducdungbk.myapp.injection.MainViewModule;
import com.nguyenducdungbk.myapp.presenter.MainPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.permission.PermissionResultCallback;
import com.nguyenducdungbk.myapp.view.MainView;

import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainPresenter, MainView, ActivityMainBinding> implements MainView, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, PermissionResultCallback {

    private static final int LOCATION_PERMISSIONS_REQUEST_CODE = 1001;
    private static final int TURN_ON_GPS_REQUEST_CODE = 1002;
    private static final int LOCATION_SETTING_REQUEST_CODE = 1004;

    boolean isPermissionOk = false;

    private Locale myLocale;

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
        getViewController().addFragment(SplashFragment.class, new HashMap<>(), false, true);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void permissionGranted(int requestCode) {

    }

    @Override
    public void permissionDenied(int requestCode) {

    }

    @Override
    public void neverAskAgain(int requestCode) {

    }
}
