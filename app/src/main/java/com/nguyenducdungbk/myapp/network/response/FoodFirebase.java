package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;

public class FoodFirebase {
    @SerializedName("foods")
    List<FoodResponse> foodResponses;

    public List<FoodResponse> getFoodResponses() {
        return foodResponses;
    }

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
    }
}
