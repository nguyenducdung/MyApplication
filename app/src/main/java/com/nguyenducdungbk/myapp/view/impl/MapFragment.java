package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentMapBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerMapViewComponent;
import com.nguyenducdungbk.myapp.injection.MapViewModule;
import com.nguyenducdungbk.myapp.presenter.MapPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.LocationUtil;
import com.nguyenducdungbk.myapp.view.MapView;

import javax.inject.Inject;

public final class MapFragment extends BaseFragment<MapPresenter, MapView, FragmentMapBinding> implements MapView, OnMapReadyCallback {
    @Inject
    PresenterFactory<MapPresenter> mPresenterFactory;

    private GoogleMap googleMap;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.mapView.getParent().getParent().requestDisallowInterceptTouchEvent(true);
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MapPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
        this.googleMap.setOnMapLoadedCallback(() -> {
            LocationUtil.getCurrentLocation(getActivity(), location -> {
                if (location != null) {
                    showMarker(new LatLng(location.getLatitude(), location.getLongitude()), R.drawable.ic_location_pin);
                }
            });
        });
    }

    private Marker showMarker(LatLng latLng, @DrawableRes int drawID) {
        if (googleMap != null) {
            return googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(drawID)));
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding.mapView != null) {
            binding.mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (binding.mapView != null) {
            binding.mapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding.mapView != null) {
            binding.mapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (binding.mapView != null) {
            binding.mapView.onLowMemory();
        }
    }
}
