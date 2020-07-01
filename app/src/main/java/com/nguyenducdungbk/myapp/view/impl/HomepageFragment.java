package com.nguyenducdungbk.myapp.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentHomepageBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerHomepageViewComponent;
import com.nguyenducdungbk.myapp.injection.HomepageViewModule;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.HomepageView;
import com.nguyenducdungbk.myapp.view.dialog.FoodDetailDialog;

import java.util.List;

import javax.inject.Inject;

import static com.nguyenducdungbk.myapp.view.impl.HomeFragment.POSITION_TAB_FOOD;

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
        if (getContext() == null) {
            return;
        }
        binding.fcbOfferFood.setTitle(getString(R.string.de_xuat_cho_ban));
        binding.fcbOfferFood.setOnItemClickFood(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(FoodListFragment.TITLE_TOOLBAR, getString(R.string.de_xuat_cho_ban));
            bundle.putString(FoodListFragment.TYPE_SCREEN, FoodListFragment.TYPE_SUGGEST);
            getViewController().addFragment(FoodListFragment.class, bundle);
        });
        binding.fcbOfferFood.setOnItemClick(foodResponse -> new FoodDetailDialog(getContext(), foodResponse, foodResponse1 -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).addFoodOrder(foodResponse1);
            }
        }));
        binding.fcbAgainFood.setTitle(getString(R.string.dat_lai_lan_nua));
        binding.fcbAgainFood.setOnItemClickFood(view -> {
            if (getParentFragment() instanceof HomeFragment) {
                ((HomeFragment) getParentFragment()).openTab(POSITION_TAB_FOOD, false);
            }
        });
        binding.fcbAgainFood.setOnItemClick(foodResponse -> new FoodDetailDialog(getContext(), foodResponse, foodResponse1 -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).addFoodOrder(foodResponse1);
            }
        }));
        binding.fcbPromotionFood.setTitle(getString(R.string.uu_dai_dac_biet));
        binding.fcbPromotionFood.setOnItemClickFood(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(FoodListFragment.TITLE_TOOLBAR, getString(R.string.uu_dai_dac_biet));
            bundle.putString(FoodListFragment.TYPE_SCREEN, FoodListFragment.TYPE_PROMOTION);
            getViewController().addFragment(FoodListFragment.class, bundle);
        });
        binding.fcbPromotionFood.setOnItemClick(foodResponse -> new FoodDetailDialog(getContext(), foodResponse, foodResponse1 -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).addFoodOrder(foodResponse1);
            }
        }));

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
    public void initFoodSuggest(List<FoodResponse> foodResponses) {
        binding.fcbOfferFood.setFoodList(foodResponses);
        if (foodResponses != null && !foodResponses.isEmpty()) {
            binding.fcbOfferFood.setVisibility(View.VISIBLE);
        } else {
            binding.fcbOfferFood.setVisibility(View.GONE);
        }
    }

    @Override
    public void initFoodPromotion(List<FoodResponse> foodResponses) {
        binding.fcbPromotionFood.setFoodList(foodResponses);
        if (foodResponses != null && !foodResponses.isEmpty()) {
            binding.fcbPromotionFood.setVisibility(View.VISIBLE);
        } else {
            binding.fcbPromotionFood.setVisibility(View.GONE);
        }
    }

    @Override
    public void initFoodHistory(List<FoodResponse> foodResponses) {
        binding.fcbAgainFood.setFoodList(foodResponses);
        if (foodResponses != null && !foodResponses.isEmpty()) {
            binding.fcbAgainFood.setVisibility(View.VISIBLE);
        } else {
            binding.fcbAgainFood.setVisibility(View.GONE);
        }
    }
}
