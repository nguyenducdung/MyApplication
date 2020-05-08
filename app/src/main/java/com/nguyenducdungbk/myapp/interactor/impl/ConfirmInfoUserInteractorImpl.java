package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ConfirmInfoUserInteractor;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import javax.inject.Inject;

public final class ConfirmInfoUserInteractorImpl implements ConfirmInfoUserInteractor {
    private RestaurantData restaurantData;

    @Inject
    public ConfirmInfoUserInteractorImpl(RestaurantData restaurantData) {
        this.restaurantData = restaurantData;
    }

    @Override
    public UserResponse getUser() {
        return restaurantData.getUser();
    }

    @Override
    public void deleteUser() {
        restaurantData.deleteUser();
    }

    @Override
    public void saveUser(UserResponse userResponse) {
        restaurantData.saveUser(userResponse);
    }
}