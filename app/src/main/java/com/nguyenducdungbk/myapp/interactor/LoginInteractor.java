package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.User;

import io.reactivex.Single;

public interface LoginInteractor extends BaseInteractor {
    Single<User> getListUser();
}