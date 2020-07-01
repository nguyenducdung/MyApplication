package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nguyenducdungbk.myapp.interactor.FoodOrderInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.presenter.FoodOrderPresenter;
import com.nguyenducdungbk.myapp.utils.StringUtil;
import com.nguyenducdungbk.myapp.view.FoodOrderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class FoodOrderPresenterImpl extends BasePresenterImpl<FoodOrderView> implements FoodOrderPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final FoodOrderInteractor mInteractor;
    private int price = 0;

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
                for (FoodResponse foodResponse : MainPresenterImpl.foodOrder) {
                    price = price + Integer.valueOf(foodResponse.getPrice());
                }
                mView.initPrice(StringUtil.convertViewQuantity(price));
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
    public void order() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("table_id", String.valueOf(1));
            jsonObject.put("num_of_food", MainPresenterImpl.foodOrder.size() + "");
            jsonObject.put("price_total", price + "");
            JSONArray jsonArrayFood = new JSONArray();
            for (int i = 0; i < MainPresenterImpl.foodOrder.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("food_id", MainPresenterImpl.foodOrder.get(i).getId() + "");
                object.put("num_of_food", String.valueOf(1));
                object.put("price_total", MainPresenterImpl.foodOrder.get(i).getPrice());
                jsonArrayFood.put(object);
            }
            jsonObject.put("foods", jsonArrayFood);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), jsonObject.toString());
        compositeDisposable.add(mInteractor.createBill(body)
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
                        mView.createBillSuccess();
                    }
                }, Throwable::printStackTrace));
    }

    @Override
    public List<FoodResponse> getListFood() {
        return MainPresenterImpl.foodOrder;
    }
}