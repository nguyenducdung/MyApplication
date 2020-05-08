package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.HomepageInteractor;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.view.HomepageView;

import javax.inject.Inject;

public final class HomepagePresenterImpl extends BasePresenterImpl<HomepageView> implements HomepagePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomepageInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public HomepagePresenterImpl(@NonNull HomepageInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            if (mInteractor.getUser() != null) {
                mView.updateUserName(mInteractor.getUser().getName());
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