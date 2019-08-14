package com.nguyenducdungbk.myapp;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.deploygate.sdk.DeployGate;
import com.facebook.stetho.Stetho;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.AppModule;
import com.nguyenducdungbk.myapp.injection.DaggerAppComponent;

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
