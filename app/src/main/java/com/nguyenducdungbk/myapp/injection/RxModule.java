package com.nguyenducdungbk.myapp.injection;

import com.nguyenducdungbk.myapp.utils.rx.AppRxSchedulers;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
