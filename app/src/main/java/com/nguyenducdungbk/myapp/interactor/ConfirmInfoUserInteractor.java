package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.CustomerResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface ConfirmInfoUserInteractor extends BaseInteractor {
    UserResponse getUser();

    void deleteUser();

    void saveUser(UserResponse userResponse);

    Single<CustomerResponse> updateUser(RequestBody requestBody);
}