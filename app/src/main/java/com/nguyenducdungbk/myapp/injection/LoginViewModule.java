package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.LoginInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.LoginInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.LoginPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.LoginPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class LoginViewModule {
    @Provides
    public LoginInteractor provideInteractor() {
        return new LoginInteractorImpl();
    }

    @Provides
    public PresenterFactory<LoginPresenter> providePresenterFactory(@NonNull final LoginInteractor interactor) {
        return new PresenterFactory<LoginPresenter>() {
            @NonNull
            @Override
            public LoginPresenter create() {
                return new LoginPresenterImpl(interactor);
            }
        };
    }
}
