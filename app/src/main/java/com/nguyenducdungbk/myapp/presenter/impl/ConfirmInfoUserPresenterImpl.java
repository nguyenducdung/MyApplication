package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.interactor.ConfirmInfoUserInteractor;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.presenter.ConfirmInfoUserPresenter;
import com.nguyenducdungbk.myapp.utils.StringUtil;
import com.nguyenducdungbk.myapp.view.ConfirmInfoUserView;

import javax.inject.Inject;

public final class ConfirmInfoUserPresenterImpl extends BasePresenterImpl<ConfirmInfoUserView> implements ConfirmInfoUserPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ConfirmInfoUserInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public ConfirmInfoUserPresenterImpl(@NonNull ConfirmInfoUserInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            if (mInteractor.getUser() != null) {
                mView.updateUser(mInteractor.getUser());
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

    @Override
    public void saveUser() {
        if (mView == null) {
            return;
        }

        if (!TextUtils.isEmpty(mView.getName())) {
            mView.showErrorDialog(R.string.vui_long_nhap_ho_ten);
            return;
        }
        if (!TextUtils.isEmpty(mView.getPhone())) {
            mView.showErrorDialog(R.string.vui_long_nhap_so_dien_thoai);
            return;
        }
        if (!StringUtil.isPhoneValid(mView.getPhone())) {
            mView.showErrorDialog(R.string.phone_valid);
            return;
        }
        if (!TextUtils.isEmpty(mView.getGender())) {
            mView.showErrorDialog(R.string.vui_long_nhap_gioitinh);
            return;
        }
        if (!TextUtils.isEmpty(mView.getDate())) {
            mView.showErrorDialog(R.string.vui_long_nhap_date);
            return;
        }
        mInteractor.deleteUser();
        UserResponse userResponse = new UserResponse();
        userResponse.setName(mView.getName());
        userResponse.setPhone(mView.getPhone());
        userResponse.setGender(mView.getGender());
        userResponse.setDateOfBirth(mView.getDate());
        mInteractor.saveUser(userResponse);
    }
}