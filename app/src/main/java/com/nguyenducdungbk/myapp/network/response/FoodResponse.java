package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FoodResponse extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("food_type")
    private int foodType;
    @SerializedName("price")
    private int price;
    @SerializedName("image")
    private String image;
    @SerializedName("status")
    private String status;
    @SerializedName("time")
    private int time;
    @SerializedName("description")
    private String description;
    @SerializedName("number_order")
    private int numberOrder;
    @SerializedName("rate")
    private double rate;

    public FoodResponse() {
    }

    public FoodResponse(int id, String name, int foodType, int price, String image, String status, int time, String description, int numberOrder, double rate) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.price = price;
        this.image = image;
        this.status = status;
        this.time = time;
        this.description = description;
        this.numberOrder = numberOrder;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoodType() {
        return foodType;
    }

    public void setFoodType(Integer foodType) {
        this.foodType = foodType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Integer numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
