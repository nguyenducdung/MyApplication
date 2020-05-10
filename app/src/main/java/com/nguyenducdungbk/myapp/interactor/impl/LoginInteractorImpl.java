package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.LoginInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public final class LoginInteractorImpl implements LoginInteractor {
    private Apis apis;
    private RxSchedulers rxSchedulers;
    private RestaurantData realmManager;

    @Inject
    public LoginInteractorImpl(Apis apis, RxSchedulers rxSchedulers, RestaurantData realmManager) {
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
        this.realmManager = realmManager;
    }

    @Override
    public Single<User> getListUser() {
        return apis.getUserList()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }

    @Override
    public Single<FoodFirebase> getListFood() {
        return apis.getFoodList()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }

    @Override
    public void saveUser(UserResponse userResponse) {
        realmManager.saveUser(userResponse);
    }

    @Override
    public void deleteUser() {
        realmManager.deleteUser();
    }

    @Override
    public void saveFoodResponse(List<FoodResponse> foodResponse) {
        realmManager.saveFood(foodResponse);
    }
}