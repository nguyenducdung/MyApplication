package com.nguyenducdungbk.myapp.presenter;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void addFoodOrder(FoodResponse foodResponse);

    void removeFoodOrder(FoodResponse foodResponse);
}