package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentStaffListBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerStaffListViewComponent;
import com.nguyenducdungbk.myapp.injection.StaffListViewModule;
import com.nguyenducdungbk.myapp.presenter.StaffListPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.StaffListView;

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

    }
}
