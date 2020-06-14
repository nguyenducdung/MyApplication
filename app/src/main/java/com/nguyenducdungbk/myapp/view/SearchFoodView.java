package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodCategoryResponse;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface SearchFoodView extends BaseView {
    void initTab(List<FoodCategoryResponse> foodCategoryResponses);
}