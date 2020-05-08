package com.nguyenducdungbk.myapp.presenter;

import com.nguyenducdungbk.myapp.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void login(String name, String phone);
}