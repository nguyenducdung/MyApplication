package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.ConfirmInfoUserFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ConfirmInfoUserViewModule.class)
public interface ConfirmInfoUserViewComponent {
    void inject(ConfirmInfoUserFragment fragment);
}