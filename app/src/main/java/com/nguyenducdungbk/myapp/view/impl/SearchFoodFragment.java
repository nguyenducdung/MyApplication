package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.BasePagerAdapter;
import com.nguyenducdungbk.myapp.adapter.FoodListAdapter;
import com.nguyenducdungbk.myapp.adapter.RecentSearchAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentSearchFoodBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerSearchFoodViewComponent;
import com.nguyenducdungbk.myapp.injection.SearchFoodViewModule;
import com.nguyenducdungbk.myapp.network.response.ChangeKeywordSearch;
import com.nguyenducdungbk.myapp.network.response.FoodCategoryResponse;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.TabFragment;
import com.nguyenducdungbk.myapp.presenter.SearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.SearchFoodView;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class SearchFoodFragment extends BaseFragment<SearchFoodPresenter, SearchFoodView, FragmentSearchFoodBinding> implements SearchFoodView {
    @Inject
    PresenterFactory<SearchFoodPresenter> mPresenterFactory;

    private Handler delayOnEditSearchInputField = new Handler();

    private Runnable onReadyToSearchRunnable = () -> {
        String keyword = "";
        if (binding.etSearch != null) {
            keyword = binding.etSearch.getText().toString().trim();
        }
        EventBus.getDefault().post(new ChangeKeywordSearch(keyword));
    };

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
    public void initTab(List<FoodCategoryResponse> foodCategoryResponses) {
        List<String> tabs = new ArrayList<>();
        List<TabFragment> listFragment = new ArrayList<>();
        if (foodCategoryResponses != null && !foodCategoryResponses.isEmpty()) {
            for (FoodCategoryResponse foodCategory : foodCategoryResponses) {
                tabs.add(foodCategory.getName());
                Fragment fragment = new ListSearchFoodFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ListSearchFoodFragment.NAME_FOOD_CATEGORY, foodCategory.getName());
                bundle.putInt(ListSearchFoodFragment.ID_FOOD_CATEGORY, foodCategory.getId());
                fragment.setArguments(bundle);
                listFragment.add(new TabFragment(foodCategory.getName(), fragment));
            }
        }
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(getActivity(), getChildFragmentManager());
        pagerAdapter.setTabFragments(listFragment);
        binding.vpFood.setOffscreenPageLimit(listFragment.size());
        binding.vpFood.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.vpFood);
    }

    @Override
    public void initView() {
        binding.ivClose.setOnClickListener(v -> binding.etSearch.setText(""));
        binding.ivBackToolbar.setOnClickListener(v -> backPressed());
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                delayOnEditSearchInputField.removeCallbacks(onReadyToSearchRunnable);
                delayOnEditSearchInputField.postDelayed(onReadyToSearchRunnable, 1000);
                if (binding.etSearch.getText().toString().trim().isEmpty()) {
                    binding.ivClose.setVisibility(View.GONE);
                } else {
                    binding.ivClose.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
