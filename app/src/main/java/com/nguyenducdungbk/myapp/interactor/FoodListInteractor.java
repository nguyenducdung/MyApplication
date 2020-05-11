package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public interface FoodListInteractor extends BaseInteractor {
    List<FoodResponse> getListFood();
}