package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ListSearchFoodInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.RequestBody;

public final class ListSearchFoodInteractorImpl implements ListSearchFoodInteractor {
    private RestaurantData restaurantData;
    private RxPreferenceHelper rxPreferenceHelper;
    private Apis apis;
    private RxSchedulers rxSchedulers;

    @Inject
    public ListSearchFoodInteractorImpl(RestaurantData restaurantData, RxPreferenceHelper rxPreferenceHelper, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.rxPreferenceHelper = rxPreferenceHelper;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<FoodFirebase> searchFood(RequestBody body) {
        return apis.searchFood(restaurantData.getUser().getToken(), body)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}