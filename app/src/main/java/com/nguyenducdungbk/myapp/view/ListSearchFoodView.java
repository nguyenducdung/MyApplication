package com.nguyenducdungbk.myapp.view;

import android.os.Bundle;
import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface ListSearchFoodView extends BaseView {
    Bundle getArgument();

    void updateListFood(List<FoodResponse> foodResponses);

    void showLoadingView();

    void hidenLoadingView();
}