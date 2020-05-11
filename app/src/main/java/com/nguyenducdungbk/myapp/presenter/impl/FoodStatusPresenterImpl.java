package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.FoodStatusInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.FoodStatusPresenter;
import com.nguyenducdungbk.myapp.view.FoodStatusView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class FoodStatusPresenterImpl extends BasePresenterImpl<FoodStatusView> implements FoodStatusPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final FoodStatusInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public FoodStatusPresenterImpl(@NonNull FoodStatusInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            if (mInteractor.getUser() != null && mInteractor.getUser().getOrderOld() != null) {
                List<FoodResponse> foodResponses = new ArrayList<>();
                String[] foodOrder = mInteractor.getUser().getOrderOld().split(",");
                for (int i = 0; i < foodOrder.length ; i++) {
                    for (FoodResponse foodResponse : mInteractor.getListFood()) {
                        if (foodOrder[i].trim().equalsIgnoreCase(String.valueOf(foodResponse.getId()))) {
                            foodResponses.add(foodResponse);
                        }
                    }
                }
                mView.updateListFood(foodResponses);
            } else {
                mView.showNoData();
            }
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
}