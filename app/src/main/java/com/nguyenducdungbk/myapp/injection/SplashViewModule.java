package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.nguyenducdungbk.myapp.interactor.SplashInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.SplashInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.presenter.SplashPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.SplashPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class SplashViewModule {
    @Provides
    public SplashInteractor provideInteractor() {
        return new SplashInteractorImpl();
    }

    @Provides
    public PresenterFactory<SplashPresenter> providePresenterFactory(@NonNull final SplashInteractor interactor) {
        return new PresenterFactory<SplashPresenter>() {
            @NonNull
            @Override
            public SplashPresenter create() {
                return new SplashPresenterImpl(interactor);
            }
        };
    }
}
