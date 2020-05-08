package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

public interface ConfirmInfoUserInteractor extends BaseInteractor {
    UserResponse getUser();

    void deleteUser();

    void saveUser(UserResponse userResponse);
}