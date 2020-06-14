package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.ListSearchFoodFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = ListSearchFoodViewModule.class)
public interface ListSearchFoodViewComponent {
    void inject(ListSearchFoodFragment fragment);
}