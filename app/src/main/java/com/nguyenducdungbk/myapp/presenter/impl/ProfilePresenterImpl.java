package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.ProfileInteractor;
import com.nguyenducdungbk.myapp.presenter.ProfilePresenter;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.utils.rx.RxBus;
import com.nguyenducdungbk.myapp.view.ProfileView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public final class ProfilePresenterImpl extends BasePresenterImpl<ProfileView> implements ProfilePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ProfileInteractor mInteractor;
    Consumer<Object> editUser = null;

    // The view is available using the mView variable

    @Inject
    public ProfilePresenterImpl(@NonNull ProfileInteractor interactor) {
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
                    mView.updateUser(mInteractor.getUser().getName());
                }
            };

            if (mInteractor.getUser() != null) {
                mView.updateUser(mInteractor.getUser().getName());
            }

            initListVoucher();
            compositeDisposable.add(RxBus.getInstance().subscribe(editUser));
        }
    }

    private void initListVoucher() {
        compositeDisposable.add(mInteractor.getListVoucher()
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
                        mView.initListVoucher(response.getVoucherResponses());
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