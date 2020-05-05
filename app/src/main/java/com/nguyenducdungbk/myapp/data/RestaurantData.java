package com.nguyenducdungbk.myapp.data;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

import javax.inject.Inject;

public class RestaurantData {

    private final RealmManager realmManager;

    @Inject
    public RestaurantData(RealmManager realmManager) {
        this.realmManager = realmManager;
    }
}
