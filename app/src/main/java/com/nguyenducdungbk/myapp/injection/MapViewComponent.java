package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.MapFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = MapViewModule.class)
public interface MapViewComponent {
    void inject(MapFragment fragment);
}