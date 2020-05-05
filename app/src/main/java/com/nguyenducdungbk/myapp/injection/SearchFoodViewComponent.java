package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.SearchFoodFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = SearchFoodViewModule.class)
public interface SearchFoodViewComponent {
    void inject(SearchFoodFragment fragment);
}