package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;

import io.reactivex.Single;

public interface FoodListInteractor extends BaseInteractor {

    Single<FoodFirebase> getListFoodSuggest();

    Single<FoodFirebase> getListFoodPromotion();

    Single<FoodFirebase> getListFoodHistory();
}