package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.interactor.LoginInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;

import io.reactivex.Single;

public final class LoginInteractorImpl implements LoginInteractor {
    private Apis apis;
    private RxSchedulers rxSchedulers;
    @Inject
    public LoginInteractorImpl(Apis apis, RxSchedulers rxSchedulers) {
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public Single<User> getListUser() {
        return apis.getUserList()
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}