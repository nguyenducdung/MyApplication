package com.nguyenducdungbk.myapp.data;

import com.nguyenducdungbk.myapp.network.response.UserResponse;

import javax.inject.Inject;

import io.realm.RealmResults;

public class RestaurantData {

    private final RealmManager realmManager;

    @Inject
    public RestaurantData(RealmManager realmManager) {
        this.realmManager = realmManager;
    }

    public void saveUser(UserResponse user) {
        realmManager.getRealm().executeTransaction(realm -> realm.copyToRealmOrUpdate(user));
    }

    public UserResponse getUser() {
        realmManager.getRealm().refresh();
        return realmManager.getRealm().where(UserResponse.class).findFirst();
    }

    public void deleteUser() {
        realmManager.getRealm().executeTransaction(realm -> {
            RealmResults<UserResponse> resultUser = realm.where(UserResponse.class).findAll();
            resultUser.deleteAllFromRealm();
        });
    }
}
