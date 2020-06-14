package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.FoodOrderInteractor;
import com.nguyenducdungbk.myapp.presenter.FoodOrderPresenter;
import com.nguyenducdungbk.myapp.presenter.MainPresenter;
import com.nguyenducdungbk.myapp.view.FoodOrderView;

import javax.inject.Inject;

public final class FoodOrderPresenterImpl extends BasePresenterImpl<FoodOrderView> implements FoodOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final FoodOrderInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public FoodOrderPresenterImpl(@NonNull FoodOrderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            if (MainPresenterImpl.foodOrder != null) {
                mView.initData(MainPresenterImpl.foodOrder);
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