package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface FoodListView extends BaseView {

    void updateListFood(List<FoodResponse> listFood);
}