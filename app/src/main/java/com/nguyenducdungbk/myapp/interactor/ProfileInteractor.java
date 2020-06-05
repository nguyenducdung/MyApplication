package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.network.response.Vouchers;

import io.reactivex.Single;

public interface ProfileInteractor extends BaseInteractor {
    UserResponse getUser();

    Single<Vouchers> getListVoucher();
}