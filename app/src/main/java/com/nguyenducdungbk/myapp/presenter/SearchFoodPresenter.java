package com.nguyenducdungbk.myapp.presenter;

import com.nguyenducdungbk.myapp.view.SearchFoodView;

public interface SearchFoodPresenter extends BasePresenter<SearchFoodView> {

    void searchData(String searchQuery);

    void saveResentSearch(String keySearch);
}