package com.nguyenducdungbk.myapp.interactor.impl;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.LoginInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    public void saveUser(UserResponse userResponse) {
        realmManager.saveUser(userResponse);
    }

    @Override
    public void deleteUser() {
        realmManager.deleteUser();
    }

    @Override
    public Single<User> loginUser(RequestBody body) {
        return apis.loginUser(body)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}