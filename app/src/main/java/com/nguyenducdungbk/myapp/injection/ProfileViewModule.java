package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ProfileInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.ProfileInteractorImpl;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.presenter.ProfilePresenter;
import com.nguyenducdungbk.myapp.presenter.impl.ProfilePresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class ProfileViewModule {
    @Provides
    public ProfileInteractor provideInteractor(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        return new ProfileInteractorImpl(restaurantData, apis, rxSchedulers);
    }

    @Provides
    public PresenterFactory<ProfilePresenter> providePresenterFactory(@NonNull final ProfileInteractor interactor) {
        return new PresenterFactory<ProfilePresenter>() {
            @NonNull
            @Override
            public ProfilePresenter create() {
                return new ProfilePresenterImpl(interactor);
            }
        };
    }
}
