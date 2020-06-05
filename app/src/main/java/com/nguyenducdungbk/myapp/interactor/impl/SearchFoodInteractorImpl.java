package com.nguyenducdungbk.myapp.interactor.impl;

import javax.inject.Inject;

import com.nguyenducdungbk.myapp.data.RestaurantData;
import com.nguyenducdungbk.myapp.interactor.SearchFoodInteractor;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;

import java.util.List;

public final class SearchFoodInteractorImpl implements SearchFoodInteractor {
    private RestaurantData restaurantData;
    private RxPreferenceHelper rxPreferenceHelper;
    @Inject
    public SearchFoodInteractorImpl(RestaurantData restaurantData, RxPreferenceHelper rxPreferenceHelper) {
        this.restaurantData = restaurantData;
        this.rxPreferenceHelper = rxPreferenceHelper;
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
}