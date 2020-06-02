package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface LoginInteractor extends BaseInteractor {

    void saveUser(UserResponse userResponse);

    void deleteUser();

    Single<User> loginUser(RequestBody body);
}