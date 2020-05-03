package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.StaffFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = StaffViewModule.class)
public interface StaffViewComponent {
    void inject(StaffFragment fragment);
}