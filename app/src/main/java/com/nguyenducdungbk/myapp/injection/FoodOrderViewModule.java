package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodOrderInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.FoodOrderInteractorImpl;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.presenter.FoodOrderPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.FoodOrderPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class FoodOrderViewModule {
    @Provides
    public FoodOrderInteractor provideInteractor(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        return new FoodOrderInteractorImpl(restaurantData, apis, rxSchedulers);
    }

    @Provides
    public PresenterFactory<FoodOrderPresenter> providePresenterFactory(@NonNull final FoodOrderInteractor interactor) {
        return new PresenterFactory<FoodOrderPresenter>() {
            @NonNull
            @Override
            public FoodOrderPresenter create() {
                return new FoodOrderPresenterImpl(interactor);
            }
        };
    }
}
