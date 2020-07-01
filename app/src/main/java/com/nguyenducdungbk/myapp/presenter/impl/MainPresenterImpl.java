package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.MainInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.MainPresenter;
import com.nguyenducdungbk.myapp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MainInteractor mInteractor;

    public static List<FoodResponse> foodOrder = new ArrayList<>();

    // The view is available using the mView variable

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
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
    public void addFoodOrder(FoodResponse foodResponse) {
        for (FoodResponse foodResponse1 : foodOrder) {
            if (foodResponse1.getId().equals(foodResponse.getId())) {
                return;
            }
        }
        foodOrder.add(foodResponse);
    }

    @Override
    public void removeFoodOrder(FoodResponse foodResponse) {
        foodOrder.remove(foodResponse);
    }
}