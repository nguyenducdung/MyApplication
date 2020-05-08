package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

@UiThread
public interface ConfirmInfoUserView extends BaseView {

    void updateUser(UserResponse user);

    String getName();

    String getPhone();

    String getGender();

    String getDate();

    void showUpdateSuccess();
}