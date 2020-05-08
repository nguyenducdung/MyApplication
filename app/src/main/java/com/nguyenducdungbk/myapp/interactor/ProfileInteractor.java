package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

public interface ProfileInteractor extends BaseInteractor {
    UserResponse getUser();
}