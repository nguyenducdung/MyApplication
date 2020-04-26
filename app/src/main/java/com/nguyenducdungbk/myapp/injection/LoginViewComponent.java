package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.LoginFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
    void inject(LoginFragment fragment);
}