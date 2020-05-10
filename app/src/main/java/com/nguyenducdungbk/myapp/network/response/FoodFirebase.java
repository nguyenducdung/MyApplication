package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class FoodFirebase {
    @SerializedName("foods")
    private LinkedHashMap<String, FoodResponse> map;

    public LinkedHashMap<String, FoodResponse> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, FoodResponse> map) {
        this.map = map;
    }
}
