package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import java.util.List;

import io.reactivex.Single;

public interface FoodStatusInteractor extends BaseInteractor {
    UserResponse getUser();

    Single<FoodFirebase> getListFoodHistory();
}