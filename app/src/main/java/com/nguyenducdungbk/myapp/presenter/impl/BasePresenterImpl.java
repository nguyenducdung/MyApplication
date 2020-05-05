package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nguyenducdungbk.myapp.presenter.BasePresenter;
import com.nguyenducdungbk.myapp.view.BaseView;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link BasePresenter} method overriding.
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    /**
     * The view
     */
    @Nullable
    protected V mView;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onViewAttached(@NonNull V view) {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated) {
        if (viewCreated && mView != null) {
            mView.initView();
        }
    }

    @Override
    public void onStop() {

    }


    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onPresenterDestroyed() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

}
