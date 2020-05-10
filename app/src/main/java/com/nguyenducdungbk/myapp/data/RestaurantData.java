package com.nguyenducdungbk.myapp.data;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.network.response.UserResponse;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
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

    public void saveFood(List<FoodResponse> foodResponse) {
        if (foodResponse != null) {
            realmManager.getRealm().executeTransaction(realm -> {
                RealmList<FoodResponse> foodResponses = new RealmList<>();
                foodResponses.addAll(foodResponse);
                realm.insertOrUpdate(foodResponses);
            });
        }
    }

    public List<FoodResponse> getFoodList() {
        return realmManager.getRealm()
                .where(FoodResponse.class)
                .findAll();
    }
}
