package com.nguyenducdungbk.myapp.presenter.impl;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.google.firebase.database.FirebaseDatabase;
import com.nguyenducdungbk.myapp.interactor.LoginInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.presenter.LoginPresenter;
import com.nguyenducdungbk.myapp.view.LoginView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final LoginInteractor mInteractor;
    private LinkedHashMap<String, UserResponse> userList;

    // The view is available using the mView variable

    @Inject
    public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated) {
            mInteractor.deleteUser();
            getListUser();
            getListFood();
        }
    }

    private void getListFood() {
        compositeDisposable.add(mInteractor.getListFood()
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(response -> {
                    if (response != null && mView != null) {
                        List<FoodResponse> foodResponses = new ArrayList<>();
                        for (String keyset : response.getMap().keySet()) {
                            foodResponses.add(response.getMap().get(keyset));
                        }
                        mInteractor.saveFoodResponse(foodResponses);
                    }
                }, Throwable::printStackTrace));
    }

    private void getListUser() {
        compositeDisposable.add(mInteractor.getListUser()
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(response -> {
                    if (response != null && mView != null) {
                        this.userList = response.getUserList();
                    }
                }, Throwable::printStackTrace));
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }

    @Override
    public void login(String name, String phone) {
        if (mView == null) {
            return;
        }
        UserResponse userResponse = userList.get(phone);
        if (userResponse != null) {
            userResponse.setName(name);
            mInteractor.saveUser(userResponse);
            mView.loginSuccess();
        } else {
            UserResponse userResponseNew = new UserResponse(name, phone);
            FirebaseDatabase.getInstance().getReference().child("list_user").child("users").child(phone).setValue(userResponseNew);
            mInteractor.saveUser(userResponseNew);
            mView.loginSuccess();
        }
    }
}