package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.data.RealmManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RealmModule {

    @Provides
    @Singleton
    RealmManager provideRealmManager() {
        return RealmManager.getInstance();
    }
}
