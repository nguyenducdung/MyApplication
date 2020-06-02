package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class User {
    @SerializedName("user")
    private UserResponse userResponse;
    @SerializedName("token")
    private String token;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
