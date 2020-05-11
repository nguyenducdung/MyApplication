package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public interface SearchFoodInteractor extends BaseInteractor {
    List<FoodResponse> getListFood();

    List<String> getRecentMapSearch();

    void writeMapRecentSearches(String recentSearch);
}