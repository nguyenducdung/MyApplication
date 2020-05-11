package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import java.util.List;

public interface FoodStatusInteractor extends BaseInteractor {
    UserResponse getUser();

    List<FoodResponse> getListFood();
}