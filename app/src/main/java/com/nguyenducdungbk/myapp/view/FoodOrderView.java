package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

@UiThread
public interface FoodOrderView extends BaseView {

    void initData(List<FoodResponse> foodOrder);

    void initPrice(String price);

    void createBillSuccess();
}