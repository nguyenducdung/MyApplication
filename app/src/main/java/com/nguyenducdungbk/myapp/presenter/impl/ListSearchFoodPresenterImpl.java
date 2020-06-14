package com.nguyenducdungbk.myapp.presenter.impl;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.ListSearchFoodInteractor;
import com.nguyenducdungbk.myapp.presenter.ListSearchFoodPresenter;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.view.ListSearchFoodView;
import com.nguyenducdungbk.myapp.view.impl.ListSearchFoodFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class ListSearchFoodPresenterImpl extends BasePresenterImpl<ListSearchFoodView> implements ListSearchFoodPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ListSearchFoodInteractor mInteractor;
    private String keyword = "";
    private String nameCategory = "";
    private int idCategory = 0;

    // The view is available using the mView variable

    @Inject
    public ListSearchFoodPresenterImpl(@NonNull ListSearchFoodInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            Bundle bundle = mView.getArgument();
            if (bundle != null) {
                nameCategory = bundle.getString(ListSearchFoodFragment.NAME_FOOD_CATEGORY, "");
                idCategory = bundle.getInt(ListSearchFoodFragment.ID_FOOD_CATEGORY, 0);
            }
            new Handler().postDelayed(this::loadData, 300);
        }
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
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void loadData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", keyword.isEmpty() ? "c" : keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), jsonObject.toString());
        compositeDisposable.add(mInteractor.searchFood(body)
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoadingView();
                    }
                })
                .subscribe(response -> {
                    if (response != null && mView != null) {
                        mView.updateListFood(response.getFoodResponses());
                    }
                }, Throwable::printStackTrace));
    }
}