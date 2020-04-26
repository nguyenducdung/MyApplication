package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.FoodStatusFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FoodStatusViewModule.class)
public interface FoodStatusViewComponent {
    void inject(FoodStatusFragment fragment);
}