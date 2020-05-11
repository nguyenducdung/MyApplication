package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.FoodStatusInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import java.util.List;

public final class FoodStatusInteractorImpl implements FoodStatusInteractor {
    private RestaurantData restaurantData;
    @Inject
    public FoodStatusInteractorImpl(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public UserResponse getUser() {
        return restaurantData.getUser();
    }

    @Override
    public List<FoodResponse> getListFood() {
        return restaurantData.getFoodList();
    }
}