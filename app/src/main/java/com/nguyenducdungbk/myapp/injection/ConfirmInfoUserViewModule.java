package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ConfirmInfoUserInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.ConfirmInfoUserInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.ConfirmInfoUserPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.ConfirmInfoUserPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class ConfirmInfoUserViewModule {
    @Provides
    public ConfirmInfoUserInteractor provideInteractor(RestaurantData restaurantData) {
        return new ConfirmInfoUserInteractorImpl(restaurantData);
    }

    @Provides
    public PresenterFactory<ConfirmInfoUserPresenter> providePresenterFactory(@NonNull final ConfirmInfoUserInteractor interactor) {
        return new PresenterFactory<ConfirmInfoUserPresenter>() {
            @NonNull
            @Override
            public ConfirmInfoUserPresenter create() {
                return new ConfirmInfoUserPresenterImpl(interactor);
            }
        };
    }
}
