package com.nguyenducdungbk.myapp.presenter.impl;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.FoodListInteractor;
import com.nguyenducdungbk.myapp.presenter.FoodListPresenter;
import com.nguyenducdungbk.myapp.view.FoodListView;
import com.nguyenducdungbk.myapp.view.impl.FoodListFragment;

import javax.inject.Inject;

public final class FoodListPresenterImpl extends BasePresenterImpl<FoodListView> implements FoodListPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final FoodListInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public FoodListPresenterImpl(@NonNull FoodListInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            if (mView.getArgument() != null) {
                String type = mView.getArgument().getString(FoodListFragment.TYPE_SCREEN, "");
                switch (type) {
                    case FoodListFragment.TYPE_HISTORY:
                        new Handler().postDelayed(this::initFoodHistory, 300);
                        break;
                    case FoodListFragment.TYPE_PROMOTION:
                        new Handler().postDelayed(this::initFoodPromotion, 300);
                        break;
                    case FoodListFragment.TYPE_SUGGEST:
                        new Handler().postDelayed(this::initFoodSuggest, 300);
                        break;
                }
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


    private void initFoodSuggest() {
        compositeDisposable.add(mInteractor.getListFoodSuggest()
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
                        mView.updateListFood(response.getFoodResponses());
                    }
                }, Throwable::printStackTrace));
    }

    private void initFoodPromotion() {
        compositeDisposable.add(mInteractor.getListFoodPromotion()
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
                        mView.updateListFood(response.getFoodResponses());
                    }
                }, Throwable::printStackTrace));
    }

    private void initFoodHistory() {
        compositeDisposable.add(mInteractor.getListFoodHistory()
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
                        mView.updateListFood(response.getFoodResponses());
                    }
                }, Throwable::printStackTrace));
    }
}