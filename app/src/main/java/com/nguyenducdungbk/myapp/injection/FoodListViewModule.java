package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.FoodListInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.FoodListInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.FoodListPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.FoodListPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class FoodListViewModule {
    @Provides
    public FoodListInteractor provideInteractor() {
        return new FoodListInteractorImpl();
    }

    @Provides
    public PresenterFactory<FoodListPresenter> providePresenterFactory(@NonNull final FoodListInteractor interactor) {
        return new PresenterFactory<FoodListPresenter>() {
            @NonNull
            @Override
            public FoodListPresenter create() {
                return new FoodListPresenterImpl(interactor);
            }
        };
    }
}
