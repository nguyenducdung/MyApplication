package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentSearchFoodBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerSearchFoodViewComponent;
import com.nguyenducdungbk.myapp.injection.SearchFoodViewModule;
import com.nguyenducdungbk.myapp.presenter.SearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.SearchFoodView;

import javax.inject.Inject;

public final class SearchFoodFragment extends BaseFragment<SearchFoodPresenter, SearchFoodView, FragmentSearchFoodBinding> implements SearchFoodView {
    @Inject
    PresenterFactory<SearchFoodPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public SearchFoodFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerSearchFoodViewComponent.builder()
                .appComponent(parentComponent)
                .searchFoodViewModule(new SearchFoodViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_food;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<SearchFoodPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        binding.toolbar.setOnBackClickListener(v -> backPressed());
    }
}
