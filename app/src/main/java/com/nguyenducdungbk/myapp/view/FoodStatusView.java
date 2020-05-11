package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface FoodStatusView extends BaseView {

    void showNoData();

    void updateListFood(List<FoodResponse> foodResponses);
}