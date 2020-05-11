package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodCategoryAdapter;
import com.nguyenducdungbk.myapp.adapter.FoodListAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentFoodListBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerFoodListViewComponent;
import com.nguyenducdungbk.myapp.injection.FoodListViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.FoodListPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.FoodListView;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import java.util.List;

import javax.inject.Inject;

public final class FoodListFragment extends BaseFragment<FoodListPresenter, FoodListView, FragmentFoodListBinding> implements FoodListView {
    public static final String TITLE_TOOLBAR = "TITLE_TOOLBAR";
    @Inject
    PresenterFactory<FoodListPresenter> mPresenterFactory;
    private FoodListAdapter adapter;

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
        if (getArguments() != null) {
            binding.toolbar.setTextTitleToobar(getArguments().getString(TITLE_TOOLBAR, ""));
        }
        adapter = new FoodListAdapter(getContext());
        binding.rvFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvFood.setAdapter(adapter);
        adapter.setOnClickFood(new FoodCategoryAdapter.OnClickFood() {
            @Override
            public void onClickFood(FoodResponse foodResponse) {
                hideKeyboard();
                new FoodDetailDialog(getContext(), foodResponse);
            }
        });
        binding.toolbar.setOnBackClickListener(view -> backPressed());
    }

    @Override
    public void updateListFood(List<FoodResponse> listFood) {
        if (adapter != null) {
            adapter.setFoodResponses(listFood);
        }
    }
}
