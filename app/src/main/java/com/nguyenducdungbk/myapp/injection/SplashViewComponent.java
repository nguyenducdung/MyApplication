package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.SplashFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashFragment fragment);
}