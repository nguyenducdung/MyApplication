package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.SearchFoodInteractor;
import com.nguyenducdungbk.myapp.network.request.Apis;
import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.utils.rx.RxSchedulers;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;

public final class SearchFoodInteractorImpl implements SearchFoodInteractor {
    private RestaurantData restaurantData;
    private RxPreferenceHelper rxPreferenceHelper;
    private Apis apis;
    private RxSchedulers rxSchedulers;
    @Inject
    public SearchFoodInteractorImpl(RestaurantData restaurantData, RxPreferenceHelper rxPreferenceHelper, Apis apis, RxSchedulers rxSchedulers) {
        this.restaurantData = restaurantData;
        this.rxPreferenceHelper = rxPreferenceHelper;
        this.apis = apis;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public List<FoodResponse> getListFood() {
        return null;
    }

    @Override
    public List<String> getRecentMapSearch() {
        return rxPreferenceHelper.getRecentMapSearch();
    }

    @Override
    public void writeMapRecentSearches(String recentSearch) {
        rxPreferenceHelper.writeMapRecentSearches(recentSearch);
    }

    @Override
    public Single<FoodFirebase> searchFood(RequestBody body) {
        return apis.searchFood(restaurantData.getUser().getToken(), body)
                .observeOn(rxSchedulers.androidThread())
                .subscribeOn(rxSchedulers.io());
    }
}