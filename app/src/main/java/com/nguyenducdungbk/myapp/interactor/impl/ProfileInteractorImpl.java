package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ProfileInteractor;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

public final class ProfileInteractorImpl implements ProfileInteractor {
    private RestaurantData restaurantData;
    @Inject
    public ProfileInteractorImpl(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public UserResponse getUser() {
        return restaurantData.getUser();
    }
}