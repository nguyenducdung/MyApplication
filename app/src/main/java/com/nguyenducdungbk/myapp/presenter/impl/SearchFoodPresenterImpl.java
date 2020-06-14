package com.nguyenducdungbk.myapp.presenter.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nguyenducdungbk.myapp.interactor.SearchFoodInteractor;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.presenter.SearchFoodPresenter;
import com.nguyenducdungbk.myapp.utils.sharedpreference.RxPreferenceHelper;
import com.nguyenducdungbk.myapp.view.SearchFoodView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class SearchFoodPresenterImpl extends BasePresenterImpl<SearchFoodView> implements SearchFoodPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final SearchFoodInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public SearchFoodPresenterImpl(@NonNull SearchFoodInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
        if (viewCreated && mView != null) {
            getListTab();
        }
    }

    private void getListTab() {
        compositeDisposable.add(mInteractor.getTypeFood()
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(response -> {
                    if (response != null && mView != null) {
                        mView.initTab(response.getFoodCategoryResponses());
                    }
                }, Throwable::printStackTrace));
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }

    @Override
    public void searchData(String searchQuery) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keyword", searchQuery);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), jsonObject.toString());
        compositeDisposable.add(mInteractor.searchFood(body)
                .doOnSubscribe(disposable -> {
                    if (mView != null) {
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView != null) {
                        mView.hiddenLoading();
                    }
                })
                .subscribe(response -> {
                    if (response != null && mView != null) {
//                        mView.updateListFood(response.getFoodResponses());
                    }
                }, Throwable::printStackTrace));
    }

    @Override
    public void saveResentSearch(String keySearch) {
        if (TextUtils.isEmpty(keySearch)) {
            return;
        }
        mInteractor.writeMapRecentSearches(keySearch);
    }
}