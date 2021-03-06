package com.nguyenducdungbk.myapp.presenter.loader;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
