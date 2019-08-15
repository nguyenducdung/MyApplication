package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.NewfeedFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = NewfeedViewModule.class)
public interface NewfeedViewComponent {
    void inject(NewfeedFragment fragment);
}