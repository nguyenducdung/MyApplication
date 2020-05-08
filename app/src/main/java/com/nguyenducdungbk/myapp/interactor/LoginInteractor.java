package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import io.reactivex.Single;

public interface LoginInteractor extends BaseInteractor {
    Single<User> getListUser();

    void saveUser(UserResponse userResponse);

    void deleteUser();
}