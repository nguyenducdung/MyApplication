package com.nguyenducdungbk.myapp.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodCategoryAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentHomepageBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerHomepageViewComponent;
import com.nguyenducdungbk.myapp.injection.HomepageViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.MonAnResponse;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.HomepageView;
import com.nguyenducdungbk.myapp.view.custom.FoodCategoryBottom;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import java.util.List;

import javax.inject.Inject;

public final class HomepageFragment extends BaseFragment<HomepagePresenter, HomepageView, FragmentHomepageBinding> implements HomepageView {
    @Inject
    PresenterFactory<HomepagePresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public HomepageFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomepageViewComponent.builder()
                .appComponent(parentComponent)
                .homepageViewModule(new HomepageViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<HomepagePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        binding.fcbOfferFood.setTitle(getString(R.string.de_xuat_cho_ban));
        binding.fcbOfferFood.setOnItemClickFood(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(FoodListFragment.TITLE_TOOLBAR, getString(R.string.de_xuat_cho_ban));
            getViewController().addFragment(FoodListFragment.class, bundle);
        });
        binding.fcbOfferFood.setOnItemClick(new FoodCategoryAdapter.OnClickFood() {
            @Override
            public void onClickFood(FoodResponse foodResponse) {
                new FoodDetailDialog(getContext(), foodResponse);
            }
        });
        binding.fcbAgainFood.setTitle(getString(R.string.dat_lai_lan_nua));
        binding.fcbAgainFood.setOnItemClickFood(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(FoodListFragment.TITLE_TOOLBAR, getString(R.string.dat_lai_lan_nua));
            getViewController().addFragment(FoodListFragment.class, bundle);
        });
        binding.fcbAgainFood.setOnItemClick(new FoodCategoryAdapter.OnClickFood() {
            @Override
            public void onClickFood(FoodResponse foodResponse) {
                new FoodDetailDialog(getContext(), foodResponse);
            }
        });
        binding.fcbPromotionFood.setTitle(getString(R.string.uu_dai_dac_biet));
        binding.fcbPromotionFood.setOnItemClickFood(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(FoodListFragment.TITLE_TOOLBAR, getString(R.string.uu_dai_dac_biet));
            getViewController().addFragment(FoodListFragment.class, bundle);
        });
        binding.fcbPromotionFood.setOnItemClick(new FoodCategoryAdapter.OnClickFood() {
            @Override
            public void onClickFood(FoodResponse foodResponse) {
                new FoodDetailDialog(getContext(), foodResponse);
            }
        });

        binding.tvDescription.setSelected(true);
        binding.rlSearch.setOnClickListener(view -> {
            if (!avoidDuplicateClick()) {
                getViewController().addFragment(SearchFoodFragment.class, null);
            }
        });
        binding.edtSearch.setOnClickListener(view -> {
            if (!avoidDuplicateClick()) {
                getViewController().addFragment(SearchFoodFragment.class, null);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateUserName(String name) {
        binding.tvNameUser.setText("Xin chào, " + name);
    }

    @Override
    public void updateListFood(List<FoodResponse> listFood) {
        binding.fcbOfferFood.setFoodList(listFood);
        binding.fcbAgainFood.setFoodList(listFood);
        binding.fcbPromotionFood.setFoodList(listFood);
    }
}
