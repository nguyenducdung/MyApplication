package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

@UiThread
public interface MainView extends BaseView{
    void showIconOrder();

    void hideIconOrder();

    void addFoodOrder(FoodResponse foodResponse);

    void removeFoodOrder(FoodResponse foodResponse);
}