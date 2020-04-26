package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.view.impl.StaffListFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = StaffListViewModule.class)
public interface StaffListViewComponent {
    void inject(StaffListFragment fragment);
}