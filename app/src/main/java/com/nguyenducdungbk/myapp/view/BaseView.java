package com.nguyenducdungbk.myapp.view;

import androidx.annotation.StringRes;

public interface BaseView {

    void showLoading();

    void hiddenLoading();

    void showErrorDialog(String title, String message);

    void showErrorDialog(String message);

    void showErrorDialog(@StringRes int messageRes);

    void initView();

}
