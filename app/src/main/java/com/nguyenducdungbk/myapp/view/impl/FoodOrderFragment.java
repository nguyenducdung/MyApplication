package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodOrderAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentFoodOrderBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerFoodOrderViewComponent;
import com.nguyenducdungbk.myapp.injection.FoodOrderViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.FoodOrderPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.FoodOrderView;

import java.util.List;

import javax.inject.Inject;

public final class FoodOrderFragment extends BaseFragment<FoodOrderPresenter, FoodOrderView, FragmentFoodOrderBinding> implements FoodOrderView {
    @Inject
    PresenterFactory<FoodOrderPresenter> mPresenterFactory;
    private FoodOrderAdapter adapter;

    // Your presenter is available using the mPresenter variable

    public FoodOrderFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerFoodOrderViewComponent.builder()
                .appComponent(parentComponent)
                .foodOrderViewModule(new FoodOrderViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_food_order;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<FoodOrderPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).hideIconOrder();
        }
        adapter = new FoodOrderAdapter();
        binding.rvFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvFood.setAdapter(adapter);
        binding.toolbar.setOnBackClickListener(v -> backPressed());
    }

    @Override
    public void initData(List<FoodResponse> foodOrder) {
        if (adapter != null) {
            adapter.setFoodResponses(foodOrder);
        }
    }
}
