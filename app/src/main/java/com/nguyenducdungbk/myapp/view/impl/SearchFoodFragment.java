package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodCategoryAdapter;
import com.nguyenducdungbk.myapp.adapter.FoodListAdapter;
import com.nguyenducdungbk.myapp.adapter.RecentSearchAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentSearchFoodBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerSearchFoodViewComponent;
import com.nguyenducdungbk.myapp.injection.SearchFoodViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.SearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.SearchFoodView;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import java.util.List;

import javax.inject.Inject;

public final class SearchFoodFragment extends BaseFragment<SearchFoodPresenter, SearchFoodView, FragmentSearchFoodBinding> implements SearchFoodView {
    @Inject
    PresenterFactory<SearchFoodPresenter> mPresenterFactory;
    private RecentSearchAdapter recentAdapter;
    private FoodListAdapter adapter;
    private String searchQuery = "";

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
        initRecentRecycleView();
        initSearhResult();
        initToolbar();
        if (TextUtils.isEmpty(searchQuery)) {
            showOrHideLayoutRecycler(true, false);
        } else {
            showOrHideLayoutRecycler(false, true);
        }
    }

    private void initRecentRecycleView() {
        recentAdapter = new RecentSearchAdapter(getContext());
        recentAdapter.setOnItemClick(recentKey -> {
            binding.toolbar.setTextSearch(recentKey);
            showOrHideLayoutRecycler(false, true);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recentAdapter.setShowLastDivider(true);
        binding.rvRecentSearch.setAdapter(recentAdapter);
        binding.rvRecentSearch.setLayoutManager(layoutManager);
    }

    private void initSearhResult() {
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new FoodListAdapter(getContext());
        binding.rvSearchResult.setAdapter(adapter);
        adapter.setOnClickFood(new FoodCategoryAdapter.OnClickFood() {
            @Override
            public void onClickFood(FoodResponse foodResponse) {
                hideKeyboard();
                new FoodDetailDialog(getContext(), foodResponse);
            }
        });
        binding.rvSearchResult.setNestedScrollingEnabled(false);
    }

    private void initToolbar() {
        binding.toolbar
                .setOnBackClickListener(v -> backPressed())
                .showSearchLayout(keySearch -> {
                    searchQuery = keySearch != null ? keySearch.trim() : null;
                    if (mPresenter != null) {
                        mPresenter.searchData(searchQuery);
                    }
                    if (TextUtils.isEmpty(searchQuery)) {
                        showOrHideLayoutRecycler(true, false);
                    } else {
                        showOrHideLayoutRecycler(false, true);
                    }
                })
                .setImeActionSearch((v, actionId, event) -> {
                    String keySearch = binding.toolbar.getTextSearch() != null ? binding.toolbar.getTextSearch().trim() : null;
                    if (actionId == EditorInfo.IME_ACTION_SEARCH && mPresenter != null
                            && !TextUtils.isEmpty(keySearch)) {
                            mPresenter.saveResentSearch(keySearch);
                        return true;
                    }
                    return false;
                });
    }

    private void showOrHideLayoutRecycler(boolean isShowRecentSearch, boolean isShowResult) {
        binding.llRecentSearches.setVisibility(isShowRecentSearch && recentAdapter.isHaveData() ? View.VISIBLE : View.GONE);
        binding.rvSearchResult.setVisibility(isShowResult ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateListFood(List<FoodResponse> listFood) {
        if (adapter != null) {
            adapter.setFoodResponses(listFood);
        }
    }

    @Override
    public void setRecentSearchData(List<String> recentSearches) {
        if (recentSearches != null && !recentSearches.isEmpty()) {
            recentAdapter.setData(recentSearches);
            binding.llRecentSearches.setVisibility(View.VISIBLE);
        } else {
            binding.llRecentSearches.setVisibility(View.GONE);
        }
    }
}
