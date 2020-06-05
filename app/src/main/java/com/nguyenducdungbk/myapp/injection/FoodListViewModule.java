package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodListInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.FoodListInteractorImpl;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.presenter.FoodListPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.FoodListPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public final class FoodListViewModule {
    @Provides
    public FoodListInteractor provideInteractor(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        return new FoodListInteractorImpl(restaurantData, apis, rxSchedulers);
    }

    @Provides
    public PresenterFactory<FoodListPresenter> providePresenterFactory(@NonNull final FoodListInteractor interactor) {
        return new PresenterFactory<FoodListPresenter>() {
            @NonNull
            @Override
            public FoodListPresenter create() {
                return new FoodListPresenterImpl(interactor);
            }
        };
    }
}
