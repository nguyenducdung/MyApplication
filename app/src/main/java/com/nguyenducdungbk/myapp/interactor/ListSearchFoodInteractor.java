package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface ListSearchFoodInteractor extends BaseInteractor {

    Single<FoodFirebase> searchFood(RequestBody body);
}