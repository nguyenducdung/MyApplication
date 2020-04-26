package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentFoodStatusBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerFoodStatusViewComponent;
import com.nguyenducdungbk.myapp.injection.FoodStatusViewModule;
import com.nguyenducdungbk.myapp.presenter.FoodStatusPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.FoodStatusView;

import javax.inject.Inject;

public final class FoodStatusFragment extends BaseFragment<FoodStatusPresenter, FoodStatusView, FragmentFoodStatusBinding> implements FoodStatusView {
    @Inject
    PresenterFactory<FoodStatusPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public FoodStatusFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerFoodStatusViewComponent.builder()
                .appComponent(parentComponent)
                .foodStatusViewModule(new FoodStatusViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_food_status;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<FoodStatusPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }
}
