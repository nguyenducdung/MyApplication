package com.nguyenducdungbk.myapp.presenter;

import com.nguyenducdungbk.myapp.view.ListSearchFoodView;

public interface ListSearchFoodPresenter extends BasePresenter<ListSearchFoodView> {
    void setKeyword(String keyword);

    void loadData();
}