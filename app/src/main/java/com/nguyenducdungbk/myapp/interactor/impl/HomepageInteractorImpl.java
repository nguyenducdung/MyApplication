package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.HomepageInteractor;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

public final class HomepageInteractorImpl implements HomepageInteractor {
    private RestaurantData restaurantData;
    @Inject
    public HomepageInteractorImpl(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public UserResponse getUser() {
        return restaurantData.getUser();
    }
}