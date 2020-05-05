package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentHomepageBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerHomepageViewComponent;
import com.nguyenducdungbk.myapp.injection.HomepageViewModule;
import com.nguyenducdungbk.myapp.network.response.MonAnResponse;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.HomepageView;
import com.nguyenducdungbk.myapp.view.custom.FoodCategoryBottom;

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
        binding.fcbOfferFood.setTitle("Đề xuất cho bạn");
        binding.fcbOfferFood.setOnItemClickFood(view -> getViewController().addFragment(FoodListFragment.class, null));
        binding.fcbAgainFood.setTitle("Đặt lại lần nữa");
        binding.fcbAgainFood.setOnItemClickFood(view -> getViewController().addFragment(FoodListFragment.class, null));
        binding.fcbPromotionFood.setTitle("Ưu đãi đặc biết");
        binding.fcbPromotionFood.setOnItemClickFood(view -> getViewController().addFragment(FoodListFragment.class, null));
        binding.tvDescription.setSelected(true);
        binding.rlSearch.setOnClickListener(view -> {
            if (!avoidDuplicateClick()) {
                getViewController().addFragment(SearchFoodFragment.class, null);
            }
        });
    }
}
