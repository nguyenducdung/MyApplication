package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("num_of_order")
    @Expose
    private Object numOfOrder;
    @SerializedName("like_of_level")
    @Expose
    private Object likeOfLevel;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    private boolean isOrder;

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean order) {
        isOrder = order;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getNumOfOrder() {
        return numOfOrder;
    }

    public void setNumOfOrder(Object numOfOrder) {
        this.numOfOrder = numOfOrder;
    }

    public Object getLikeOfLevel() {
        return likeOfLevel;
    }

    public void setLikeOfLevel(Object likeOfLevel) {
        this.likeOfLevel = likeOfLevel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
