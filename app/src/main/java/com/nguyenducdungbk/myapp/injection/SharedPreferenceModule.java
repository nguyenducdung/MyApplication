package com.nguyenducdungbk.myapp.injection;

import android.content.Context;

import com.nguyenducdungbk.myapp.utils.sharedpreference.PreferencesHelper;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {
    @Provides
    @Singleton
    public RxPreferenceHelper providePreferencesHelper(Context context) {
        return PreferencesHelper.getInstance(context);
    }
}
