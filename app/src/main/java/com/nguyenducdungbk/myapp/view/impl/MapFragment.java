package com.nguyenducdungbk.myapp.view.impl;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentMapBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerMapViewComponent;
import com.nguyenducdungbk.myapp.injection.MapViewModule;
import com.nguyenducdungbk.myapp.presenter.MapPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.MapView;

import javax.inject.Inject;

public final class MapFragment extends BaseFragment<MapPresenter, MapView, FragmentMapBinding> implements MapView {
    @Inject
    PresenterFactory<MapPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMapViewComponent.builder()
                .appComponent(parentComponent)
                .mapViewModule(new MapViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_map;
    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<MapPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }
}
