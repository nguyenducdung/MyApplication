package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.HomepageInteractor;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.utils.rx.RxBus;
import com.nguyenducdungbk.myapp.view.HomepageView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class HomepagePresenterImpl extends BasePresenterImpl<HomepageView> implements HomepagePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final HomepageInteractor mInteractor;
    Consumer<Object> editUser = null;

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
            editUser = value -> {
                if (!(value instanceof String) || mView == null)
                    return;
                if (value.equals(Define.Bus.EDIT_USER) && mInteractor.getUser() != null) {
                    mView.updateUserName(mInteractor.getUser().getName());
                }
            };

            if (mInteractor.getUser() != null) {
                mView.updateUserName(mInteractor.getUser().getName());
            }
            initFoodHistory();
            initFoodPromotion();
            initFoodSuggest();
            compositeDisposable.add(RxBus.getInstance().subscribe(editUser));
        }
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
                        if (response.getFoodResponses().size() > 5) {
                            mView.initFoodSuggest(response.getFoodResponses().subList(0, 4));
                        } else {
                            mView.initFoodSuggest(response.getFoodResponses());
                        }
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
                        if (response.getFoodResponses().size() > 5) {
                            mView.initFoodPromotion(response.getFoodResponses().subList(0, 4));
                        } else {
                            mView.initFoodPromotion(response.getFoodResponses());
                        }
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
                        if (response.getFoodResponses().size() > 5) {
                            mView.initFoodHistory(response.getFoodResponses().subList(0, 4));
                        } else {
                            mView.initFoodHistory(response.getFoodResponses());
                        }
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
}