package com.nguyenducdungbk.myapp.presenter.impl;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.MapInteractor;
import com.nguyenducdungbk.myapp.presenter.MapPresenter;
import com.nguyenducdungbk.myapp.view.MapView;

import javax.inject.Inject;

public final class MapPresenterImpl extends BasePresenterImpl<MapView> implements MapPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MapInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public MapPresenterImpl(@NonNull MapInteractor interactor) {
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
}