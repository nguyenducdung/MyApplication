package com.nguyenducdungbk.myapp.injection;

import android.content.Context;

import com.nguyenducdungbk.myapp.MyApp;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;

@Module
public final class AppModule {
    @NonNull
    private final MyApp mApp;

    public AppModule(@NonNull MyApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public MyApp provideApp() {
        return mApp;
    }
}
