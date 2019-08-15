package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DungND on 8/14/2019.
 */
public class Contact {
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
