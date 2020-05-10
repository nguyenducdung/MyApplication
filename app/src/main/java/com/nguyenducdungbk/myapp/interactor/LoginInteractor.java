package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.User;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import java.util.List;

import io.reactivex.Single;

public interface LoginInteractor extends BaseInteractor {
    Single<User> getListUser();

    Single<FoodFirebase> getListFood();

    void saveUser(UserResponse userResponse);

    void deleteUser();

    void saveFoodResponse(List<FoodResponse> foodResponse);
}