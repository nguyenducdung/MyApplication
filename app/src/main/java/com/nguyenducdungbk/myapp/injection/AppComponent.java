package com.nguyenducdungbk.myapp.injection;

import android.content.Context;

import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.data.RealmManager;
import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RxModule.class, SharedPreferenceModule.class, RealmModule.class})
public interface AppComponent {

    Context getAppContext();

    MyApp getApp();

    Apis appApis();

    RxSchedulers rxSchedulers();

    RxPreferenceHelper getPreference();

    RealmManager realmManager();
}