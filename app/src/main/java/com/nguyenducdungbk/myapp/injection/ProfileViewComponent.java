package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.ProfileFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ProfileViewModule.class)
public interface ProfileViewComponent {
    void inject(ProfileFragment fragment);
}