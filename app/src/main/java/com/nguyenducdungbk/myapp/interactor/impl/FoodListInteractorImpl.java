package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodListInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Single;

public final class FoodListInteractorImpl implements FoodListInteractor {
    private RestaurantData restaurantData;
    private Apis apis;
    private RxSchedulers rxSchedulers;
    @Inject
    public FoodListInteractorImpl(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public List<FoodResponse> getListFood() {
        return restaurantData.getFoodList();
    }

    private UserResponse getUser() {
        return restaurantData.getUser();
    }
}