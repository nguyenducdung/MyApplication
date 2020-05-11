package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodListInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public final class FoodListInteractorImpl implements FoodListInteractor {
    private RestaurantData restaurantData;
    @Inject
    public FoodListInteractorImpl(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public List<FoodResponse> getListFood() {
        return restaurantData.getFoodList();
    }
}