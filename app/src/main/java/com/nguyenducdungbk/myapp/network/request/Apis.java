package com.nguyenducdungbk.myapp.network.request;

import com.nguyenducdungbk.myapp.network.response.BaseResponse;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.User;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface Apis {

    @FormUrlEncoded
    @POST("user/login")
    Single<BaseResponse> login(@FieldMap Map<String, String> loginFields);

    @GET("list_user.json")
    Single<User> getUserList();

    @GET("list_food.json")
    Single<FoodFirebase> getFoodList();

    @Headers({"Content-Type: application/json"})
    @POST("api/login")
    Single<User> loginUser(@Body RequestBody body);

}
