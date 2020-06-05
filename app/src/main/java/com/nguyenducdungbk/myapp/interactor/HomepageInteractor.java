package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import io.reactivex.Single;

public interface HomepageInteractor extends BaseInteractor {
    UserResponse getUser();

    Single<FoodFirebase> getListFoodSuggest();

    Single<FoodFirebase> getListFoodPromotion();

    Single<FoodFirebase> getListFoodHistory();
}