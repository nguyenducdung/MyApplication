package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentHomeBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerHomeViewComponent;
import com.nguyenducdungbk.myapp.injection.HomeViewModule;
import com.nguyenducdungbk.myapp.presenter.HomePresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class HomeFragment extends BaseFragment<HomePresenter, HomeView, FragmentHomeBinding> implements HomeView {
    public static final String NAME_USER = "NAME_USER";
    public static final String PHONE_USER = "PHONE_USER";

    public static final int POSITION_TAB_HOME = 0;
    public static final int POSITION_TAB_FOOD = 1;
    public static final int POSITION_TAB_STAFF = 2;
    public static final int POSITION_TAB_PERSONAL = 3;
    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

    public int currentPosition = -1;
    private List<BaseFragment> tabFragments = new ArrayList<>();
    private List<TextView> listTitleBottom = new ArrayList<>();
    private List<View> listIconBottom = new ArrayList<>();

    // Your presenter is available using the mPresenter variable

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerHomeViewComponent.builder()
                .appComponent(parentComponent)
                .homeViewModule(new HomeViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean backPressed() {
        if (currentPosition != POSITION_TAB_HOME) {
            openTab(POSITION_TAB_HOME, false);
            return false;
        }
        return true;
    }

    @NonNull
    @Override
    protected PresenterFactory<HomePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        addListFragment();
        binding.clHome.setOnClickListener(v -> openTab(POSITION_TAB_HOME, false));
        binding.clFood.setOnClickListener(v -> openTab(POSITION_TAB_FOOD, false));
        binding.clStaff.setOnClickListener(v -> openTab(POSITION_TAB_STAFF, false));
        binding.clMyPage.setOnClickListener(v -> openTab(POSITION_TAB_PERSONAL, false));
    }

    @Override
    public void onStart() {
        super.onStart();
        changeColorTab(currentPosition);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        changeColorTab(currentPosition);
    }

    private void addListFragment() {
        listIconBottom.add(binding.ivTabHome);
        listIconBottom.add(binding.ivFood);
        listIconBottom.add(binding.ivStaff);
        listIconBottom.add(binding.ivMyPage);

        listTitleBottom.add(binding.tvTitleHome);
        listTitleBottom.add(binding.tvTitleFood);
        listTitleBottom.add(binding.tvTitleStaff);
        listTitleBottom.add(binding.tvTitleStaff);


        tabFragments.clear();
        // Tab homepage
        HomepageFragment homepageFragment = new HomepageFragment();
        Bundle bundleHome = new Bundle();
        bundleHome.putString(NAME_USER, getArguments() != null ? getArguments().getString(NAME_USER, "") : "");
        bundleHome.putString(PHONE_USER, getArguments() != null ? getArguments().getString(PHONE_USER, "") : "");
        homepageFragment.setArguments(bundleHome);
        tabFragments.add(POSITION_TAB_HOME, homepageFragment);

        // Tab food
        tabFragments.add(POSITION_TAB_FOOD, new FoodStatusFragment());

        // Tab staff
        tabFragments.add(POSITION_TAB_STAFF, new StaffListFragment());

        // Tab personal
        tabFragments.add(POSITION_TAB_PERSONAL, new ProfileFragment());

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = tabFragments.size() - 1; i >= 0; i--) {
            Fragment fragment = tabFragments.get(i);
            fragmentTransaction.add(R.id.flMain, fragment, fragment.getClass().getSimpleName());
            if (i != 0) {
                fragmentTransaction.hide(fragment);
            } else {
                changeColorTab(i);
                listTitleBottom.get(i).setSelected(true);
                listIconBottom.get(i).setSelected(true);
                fragmentTransaction.show(fragment);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
        currentPosition = 0;
    }

    public void openTab(int position, boolean isReload) {

        changeColorTab(position);

        if (!isReload && currentPosition == position) {
            return;
        }

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < tabFragments.size(); i++) {
            Fragment fragment = tabFragments.get(i);
            if (i == position) {
                listTitleBottom.get(i).setSelected(true);
                listIconBottom.get(i).setSelected(true);
                fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
                fragmentTransaction.show(fragment);
            } else {
                listTitleBottom.get(i).setSelected(false);
                listIconBottom.get(i).setSelected(false);
                fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_down);
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commitNowAllowingStateLoss();

        currentPosition = position;
        if (currentPosition == POSITION_TAB_PERSONAL) {
//            RxBus.getInstance().publish(Define.Bus.UPDATE_TIMELINE);
        }
    }

    private void changeColorTab(int position) {
        switch (position) {
            case POSITION_TAB_HOME:
                binding.tvTitleHome.setTextColor(getResources().getColor(R.color.colorBlack));
                binding.tvTitleFood.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvTitleStaff.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvMyPage.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                break;
            case POSITION_TAB_FOOD:
                binding.tvTitleHome.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvTitleFood.setTextColor(getResources().getColor(R.color.colorBlack));
                binding.tvTitleStaff.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvMyPage.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                break;
            case POSITION_TAB_PERSONAL:
                binding.tvTitleHome.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvTitleStaff.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvTitleFood.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvMyPage.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case POSITION_TAB_STAFF:
                binding.tvTitleHome.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvTitleStaff.setTextColor(getResources().getColor(R.color.colorBlack));
                binding.tvTitleFood.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                binding.tvMyPage.setTextColor(getResources().getColor(R.color.com_facebook_picker_search_bar_text));
                break;
            default:
                break;
        }
    }
}
