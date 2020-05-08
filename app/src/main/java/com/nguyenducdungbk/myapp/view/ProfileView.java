package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

@UiThread
public interface ProfileView extends BaseView {

    void updateUser(String name);
}