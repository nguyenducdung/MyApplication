package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeFragment fragment);
}