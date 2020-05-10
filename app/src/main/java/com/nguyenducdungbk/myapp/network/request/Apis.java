package com.nguyenducdungbk.myapp.network.request;

import com.nguyenducdungbk.myapp.network.response.BaseResponse;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.User;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Apis {

    @FormUrlEncoded
    @POST("user/login")
    Single<BaseResponse> login(@FieldMap Map<String, String> loginFields);

    @GET("list_user.json")
    Single<User> getUserList();

    @GET("list_food.json")
    Single<FoodFirebase> getFoodList();

}
