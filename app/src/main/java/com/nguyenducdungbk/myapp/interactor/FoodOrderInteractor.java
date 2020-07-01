package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.Bill;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface FoodOrderInteractor extends BaseInteractor {
    Single<Bill> createBill(RequestBody requestBody);
}