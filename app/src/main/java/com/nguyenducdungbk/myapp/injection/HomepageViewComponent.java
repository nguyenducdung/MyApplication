package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.HomepageFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = HomepageViewModule.class)
public interface HomepageViewComponent {
    void inject(HomepageFragment fragment);
}