package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface HomepageView extends BaseView {

    void updateUserName(String name);

    void initFoodSuggest(List<FoodResponse> foodResponses);

    void initFoodPromotion(List<FoodResponse> foodResponses);

    void initFoodHistory(List<FoodResponse> foodResponses);
}