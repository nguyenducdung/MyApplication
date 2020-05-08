package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

@UiThread
public interface HomepageView extends BaseView {

    void updateUserName(String name);
}