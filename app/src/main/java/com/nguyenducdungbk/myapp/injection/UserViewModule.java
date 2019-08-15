package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.UserInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.UserInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.UserPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.UserPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class UserViewModule {
    @Provides
    public UserInteractor provideInteractor() {
        return new UserInteractorImpl();
    }

    @Provides
    public PresenterFactory<UserPresenter> providePresenterFactory(@NonNull final UserInteractor interactor) {
        return new PresenterFactory<UserPresenter>() {
            @NonNull
            @Override
            public UserPresenter create() {
                return new UserPresenterImpl(interactor);
            }
        };
    }
}
