package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypeFoodResponse {
    @SerializedName("type")
    private List<FoodCategoryResponse> foodCategoryResponses;

    public List<FoodCategoryResponse> getFoodCategoryResponses() {
        return foodCategoryResponses;
    }

    public void setFoodCategoryResponses(List<FoodCategoryResponse> foodCategoryResponses) {
        this.foodCategoryResponses = foodCategoryResponses;
    }
}
