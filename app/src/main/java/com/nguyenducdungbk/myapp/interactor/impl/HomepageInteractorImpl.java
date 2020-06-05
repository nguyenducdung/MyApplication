package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.HomepageInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Single;

public final class HomepageInteractorImpl implements HomepageInteractor {
    private RestaurantData restaurantData;
    private Apis apis;
    private RxSchedulers rxSchedulers;

    @Inject
    public HomepageInteractorImpl(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public UserResponse getUser() {
        return restaurantData.getUser();
    }

    @Override
    public Single<FoodFirebase> getListFoodSuggest() {
        return apis.getListFoodSuggest(getUser().getToken())
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }

    @Override
    public Single<FoodFirebase> getListFoodPromotion() {
        return apis.getListFoodPromotion(getUser().getToken())
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }

    @Override
    public Single<FoodFirebase> getListFoodHistory() {
        return apis.getListFoodHistory(getUser().getToken())
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}