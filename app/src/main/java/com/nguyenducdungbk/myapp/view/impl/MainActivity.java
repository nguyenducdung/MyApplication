package com.nguyenducdungbk.myapp.view.impl;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ActivityMainBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerMainViewComponent;
import com.nguyenducdungbk.myapp.injection.MainViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
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
        getViewController().setOnFragmentChangedListener(() -> {
            if (getViewController().getCurrentFragment() instanceof HomeFragment) {
                new Handler().postDelayed(() -> {
                    if (((RelativeLayout) findViewById(R.id.rl_orderfood)) == null) {
                        return;
                    }
                    ((RelativeLayout) findViewById(R.id.rl_orderfood)).setVisibility(View.VISIBLE);
                }, 300);
            }
        });
        ((RelativeLayout) findViewById(R.id.rl_orderfood)).setOnClickListener(v -> {
            getViewController().addFragment(FoodOrderFragment.class, null);
        });
        getViewController().addFragment(LoginFragment.class, null);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String fcmToken = instanceIdResult.getToken();
            Log.e("FCM token 1", fcmToken);
        });
    }

    @Override
    public void showIconOrder() {
        ((RelativeLayout) findViewById(R.id.rl_orderfood)).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIconOrder() {
        ((RelativeLayout) findViewById(R.id.rl_orderfood)).setVisibility(View.GONE);
    }

    @Override
    public void addFoodOrder(FoodResponse foodResponse) {
        if (mPresenter != null) {
            mPresenter.addFoodOrder(foodResponse);
        }
    }

    @Override
    public void removeFoodOrder(FoodResponse foodResponse) {
        if (mPresenter != null) {
            mPresenter.removeFoodOrder(foodResponse);
        }
    }
}
