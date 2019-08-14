package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity activity);
}