package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.ConfirmInfoUserInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.CustomerResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.RequestBody;

public final class ConfirmInfoUserInteractorImpl implements ConfirmInfoUserInteractor {
    private RestaurantData restaurantData;
    private Apis apis;
    private RxSchedulers rxSchedulers;

    @Inject
    public ConfirmInfoUserInteractorImpl(RestaurantData restaurantData, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
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

    @Override
    public Single<CustomerResponse> updateUser(RequestBody requestBody) {
        return apis.updateUser(getUser().getToken(), requestBody)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}