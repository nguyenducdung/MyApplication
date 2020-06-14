package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ListSearchFoodInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.ListSearchFoodInteractorImpl;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.presenter.ListSearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.ListSearchFoodPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class ListSearchFoodViewModule {
    @Provides
    public ListSearchFoodInteractor provideInteractor(RestaurantData restaurantData, RxPreferenceHelper rxPreferenceHelper, Apis apis, RxSchedulers rxSchedulers) {
        return new ListSearchFoodInteractorImpl(restaurantData, rxPreferenceHelper, apis, rxSchedulers);
    }

    @Provides
    public PresenterFactory<ListSearchFoodPresenter> providePresenterFactory(@NonNull final ListSearchFoodInteractor interactor) {
        return new PresenterFactory<ListSearchFoodPresenter>() {
            @NonNull
            @Override
            public ListSearchFoodPresenter create() {
                return new ListSearchFoodPresenterImpl(interactor);
            }
        };
    }
}
