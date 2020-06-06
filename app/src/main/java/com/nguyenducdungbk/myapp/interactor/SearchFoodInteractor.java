package com.nguyenducdungbk.myapp.interactor;

import com.nguyenducdungbk.myapp.network.response.FoodFirebase;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface SearchFoodInteractor extends BaseInteractor {
    List<FoodResponse> getListFood();

    List<String> getRecentMapSearch();

    void writeMapRecentSearches(String recentSearch);

    Single<FoodFirebase> searchFood(RequestBody body);
}