package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodOrderInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.Bill;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import io.reactivex.Single;
import okhttp3.RequestBody;

public final class FoodOrderInteractorImpl implements FoodOrderInteractor {
    private RestaurantData restaurantData;
    private Apis apis;
    private RxSchedulers rxSchedulers;

    @Inject
    public FoodOrderInteractorImpl(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<Bill> createBill(RequestBody body) {
        return apis.createBill(restaurantData.getUser().getToken(), body)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}