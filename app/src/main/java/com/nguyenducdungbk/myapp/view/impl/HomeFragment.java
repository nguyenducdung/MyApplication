package com.nguyenducdungbk.myapp.view.impl;

import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    public static final int POSITION_TAB_HOME = 0;
    public static final int POSITION_TAB_MAP = 1;
    public static final int POSITION_TAB_USER = 2;

    private List<BaseFragment> tabFragments = new ArrayList<>();

    private int currentPosition = -1;
    private int oldPosition = -1;

    @Inject
    PresenterFactory<HomePresenter> mPresenterFactory;

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
        binding.btnHome.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            openTab(POSITION_TAB_HOME, false);
        });
        binding.btnMap.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            openTab(POSITION_TAB_MAP, false);
        });
        binding.btnUser.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            openTab(POSITION_TAB_USER, false);
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void openTab(int position, boolean isReload) {


        if (!isReload && currentPosition == position) {
            return;
        }

        oldPosition = currentPosition;

        if (position == POSITION_TAB_USER) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            if (currentPosition != position) {
                if (tabFragments.get(position) instanceof MapFragment) {
                    currentPosition = position;
                }
            }
        }

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < tabFragments.size(); i++) {
            Fragment fragment = tabFragments.get(i);
            if (i == position) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_down);
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commitNowAllowingStateLoss();
        currentPosition = position;
    }

    private void addListFragment() {

        tabFragments.clear();
        tabFragments.add(new NewfeedFragment());
        tabFragments.add(new MapFragment());
        if (mPresenter != null ) {
            tabFragments.add(new UserFragment());
        } else {
//            tabFragments.add(new RequestLoginFragment());
        }

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        for (int i = tabFragments.size() - 1; i >= 0; i--) {
            Fragment fragment = tabFragments.get(i);
            fragmentTransaction.add(R.id.vpMain, fragment, fragment.getClass().getSimpleName());
            if (i != 0) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
        openTab(0, false);

    }
}
