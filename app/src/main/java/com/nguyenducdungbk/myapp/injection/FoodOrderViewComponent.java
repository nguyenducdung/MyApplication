package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.FoodOrderFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FoodOrderViewModule.class)
public interface FoodOrderViewComponent {
    void inject(FoodOrderFragment fragment);
}