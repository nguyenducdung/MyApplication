package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.StaffAdapter;
import com.nguyenducdungbk.myapp.databinding.FragmentStaffBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerStaffViewComponent;
import com.nguyenducdungbk.myapp.injection.StaffViewModule;
import com.nguyenducdungbk.myapp.presenter.StaffPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.StaffView;

import javax.inject.Inject;

public final class StaffFragment extends BaseFragment<StaffPresenter, StaffView, FragmentStaffBinding> implements StaffView {
    @Inject
    PresenterFactory<StaffPresenter> mPresenterFactory;

    private StaffAdapter staffAdapter;

    // Your presenter is available using the mPresenter variable

    public StaffFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerStaffViewComponent.builder()
                .appComponent(parentComponent)
                .staffViewModule(new StaffViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_staff;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<StaffPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        staffAdapter = new StaffAdapter();
        binding.rvStaff.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvStaff.setAdapter(staffAdapter);
    }
}
