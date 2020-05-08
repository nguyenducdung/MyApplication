package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

public interface HomepageInteractor extends BaseInteractor {
    UserResponse getUser();
}