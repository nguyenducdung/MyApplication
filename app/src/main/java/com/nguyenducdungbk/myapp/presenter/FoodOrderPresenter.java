package com.nguyenducdungbk.myapp.presenter;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.view.FoodOrderView;

import java.util.List;

public interface FoodOrderPresenter extends BasePresenter<FoodOrderView> {

    void order();

    List<FoodResponse> getListFood();
}