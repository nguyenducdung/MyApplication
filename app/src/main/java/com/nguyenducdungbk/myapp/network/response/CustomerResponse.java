package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

public class CustomerResponse {
    @SerializedName("customer")
    private UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
