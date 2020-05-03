package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.BasePagerAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentStaffListBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerStaffListViewComponent;
import com.nguyenducdungbk.myapp.injection.StaffListViewModule;
import com.nguyenducdungbk.myapp.network.response.TabFragment;
import com.nguyenducdungbk.myapp.presenter.StaffListPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.StaffListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class StaffListFragment extends BaseFragment<StaffListPresenter, StaffListView, FragmentStaffListBinding> implements StaffListView {
    @Inject
    PresenterFactory<StaffListPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public StaffListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerStaffListViewComponent.builder()
                .appComponent(parentComponent)
                .staffListViewModule(new StaffListViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_staff_list;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<StaffListPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        initTablayout();
    }

    private void initTablayout() {
        List<String> tabs = new ArrayList<>();
        List<TabFragment> lsFragment = new ArrayList<>();
        tabs.add("Quản lý");
        tabs.add("Nhân viên nhà bếp");
        tabs.add("Nhân viên phục vụ");
        tabs.add("Nhân viên thu ngân");
        for (int i = 0; i < tabs.size(); i++) {
            lsFragment.add(new TabFragment(tabs.get(i), new StaffFragment()));
        }
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(getContext(), getChildFragmentManager());
        pagerAdapter.setTabFragments(lsFragment);
        binding.vpStaff.setOffscreenPageLimit(lsFragment.size());
        binding.vpStaff.setAdapter(pagerAdapter);
        binding.tabStaff.setupWithViewPager(binding.vpStaff);
    }
}
