package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.UserFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = UserViewModule.class)
public interface UserViewComponent {
    void inject(UserFragment fragment);
}