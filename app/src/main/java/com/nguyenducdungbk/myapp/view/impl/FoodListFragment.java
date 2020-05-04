package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodListAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentFoodListBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerFoodListViewComponent;
import com.nguyenducdungbk.myapp.injection.FoodListViewModule;
import com.nguyenducdungbk.myapp.presenter.FoodListPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.FoodListView;

import javax.inject.Inject;

public final class FoodListFragment extends BaseFragment<FoodListPresenter, FoodListView, FragmentFoodListBinding> implements FoodListView {
    @Inject
    PresenterFactory<FoodListPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public FoodListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerFoodListViewComponent.builder()
                .appComponent(parentComponent)
                .foodListViewModule(new FoodListViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_food_list;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<FoodListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        FoodListAdapter adapter = new FoodListAdapter();
        binding.rvFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvFood.setAdapter(adapter);
        binding.toolbar.setOnBackClickListener(view -> backPressed());
    }
}
