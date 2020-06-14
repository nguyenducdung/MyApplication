package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.SearchFoodAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentListSearchFoodBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerListSearchFoodViewComponent;
import com.nguyenducdungbk.myapp.injection.ListSearchFoodViewModule;
import com.nguyenducdungbk.myapp.network.response.ChangeKeywordSearch;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.ListSearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.ListSearchFoodView;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

public final class ListSearchFoodFragment extends BaseFragment<ListSearchFoodPresenter, ListSearchFoodView, FragmentListSearchFoodBinding> implements ListSearchFoodView {
    public static final String ID_FOOD_CATEGORY = "ID_FOOD_CATEGORY";
    public static final String NAME_FOOD_CATEGORY = "NAME_FOOD_CATEGORY";
    @Inject
    PresenterFactory<ListSearchFoodPresenter> mPresenterFactory;
    private SearchFoodAdapter searchFoodAdapter;

    // Your presenter is available using the mPresenter variable

    public ListSearchFoodFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerListSearchFoodViewComponent.builder()
                .appComponent(parentComponent)
                .listSearchFoodViewModule(new ListSearchFoodViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list_search_food;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<ListSearchFoodPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        searchFoodAdapter = new SearchFoodAdapter(getContext());
        binding.rvFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvFood.setAdapter(searchFoodAdapter);
        searchFoodAdapter.setOnClickSearchFood(foodResponse -> new FoodDetailDialog(getContext(), foodResponse, foodResponse1 -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).addFoodOrder(foodResponse1);
            }
        }));
        binding.rvFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hideKeyboard();
            }
        });
    }

    @Override
    public Bundle getArgument() {
        return getArguments();
    }

    @Override
    public void updateListFood(List<FoodResponse> foodResponses) {
        hidenLoadingView();
        if (foodResponses != null && !foodResponses.isEmpty()) {
            binding.rvFood.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.GONE);
            searchFoodAdapter.setFoodResponses(foodResponses);
        } else {
            binding.rvFood.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingView() {
        binding.pbLoading.setVisibility(View.VISIBLE);
        binding.rvFood.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
    }

    @Override
    public void hidenLoadingView() {
        binding.pbLoading.setVisibility(View.GONE);
        binding.rvFood.setVisibility(View.GONE);
        binding.tvEmpty.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeKeyword(ChangeKeywordSearch event) {
        if (mPresenter != null && searchFoodAdapter != null) {
            searchFoodAdapter.clear();
            searchFoodAdapter.notifyDataSetChanged();
            mPresenter.setKeyword(event.getKeyword());
            mPresenter.loadData();
        }
    }
}
