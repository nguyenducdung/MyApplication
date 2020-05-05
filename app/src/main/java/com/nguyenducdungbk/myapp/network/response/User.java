package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class User {
    @SerializedName("users")
    private LinkedHashMap<String, UserResponse> userList;

    public LinkedHashMap<String, UserResponse> getUserList() {
        return userList;
    }

    public void setUserList(LinkedHashMap<String, UserResponse> userList) {
        this.userList = userList;
    }
}
