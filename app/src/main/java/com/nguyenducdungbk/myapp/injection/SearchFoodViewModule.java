package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.SearchFoodInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.SearchFoodInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.SearchFoodPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.SearchFoodPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class SearchFoodViewModule {
    @Provides
    public SearchFoodInteractor provideInteractor() {
        return new SearchFoodInteractorImpl();
    }

    @Provides
    public PresenterFactory<SearchFoodPresenter> providePresenterFactory(@NonNull final SearchFoodInteractor interactor) {
        return new PresenterFactory<SearchFoodPresenter>() {
            @NonNull
            @Override
            public SearchFoodPresenter create() {
                return new SearchFoodPresenterImpl(interactor);
            }
        };
    }
}
