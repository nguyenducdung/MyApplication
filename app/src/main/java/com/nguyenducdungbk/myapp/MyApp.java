package com.nguyenducdungbk.myapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.deploygate.sdk.DeployGate;
import com.facebook.stetho.Stetho;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.AppModule;
import com.nguyenducdungbk.myapp.injection.DaggerAppComponent;
import com.nguyenducdungbk.myapp.utils.Define;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by DungND on 8/14/2019.
 */
public final class MyApp extends Application {
    private AppComponent mAppComponent;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        context = mAppComponent.getAppContext();

        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Define.REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        // Debug tool
        Stetho.initializeWithDefaults(this);

        // Deploy tool
        DeployGate.install(this);
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getContext() {
        return context;
    }
}
