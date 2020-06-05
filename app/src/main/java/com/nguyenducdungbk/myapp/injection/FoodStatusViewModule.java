package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodStatusInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.FoodStatusInteractorImpl;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.presenter.FoodStatusPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.FoodStatusPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class FoodStatusViewModule {
    @Provides
    public FoodStatusInteractor provideInteractor(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        return new FoodStatusInteractorImpl(restaurantData, apis, rxSchedulers);
    }

    @Provides
    public PresenterFactory<FoodStatusPresenter> providePresenterFactory(@NonNull final FoodStatusInteractor interactor) {
        return new PresenterFactory<FoodStatusPresenter>() {
            @NonNull
            @Override
            public FoodStatusPresenter create() {
                return new FoodStatusPresenterImpl(interactor);
            }
        };
    }
}
