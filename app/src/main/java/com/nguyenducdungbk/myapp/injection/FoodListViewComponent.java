package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.FoodListFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FoodListViewModule.class)
public interface FoodListViewComponent {
    void inject(FoodListFragment fragment);
}