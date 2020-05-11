package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodStatusInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.FoodStatusInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.FoodStatusPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.FoodStatusPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class FoodStatusViewModule {
    @Provides
    public FoodStatusInteractor provideInteractor(RestaurantData restaurantData) {
        return new FoodStatusInteractorImpl(restaurantData);
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
