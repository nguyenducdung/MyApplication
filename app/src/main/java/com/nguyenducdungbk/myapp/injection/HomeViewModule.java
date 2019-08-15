package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.HomeInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.HomeInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.HomePresenter;
import com.nguyenducdungbk.myapp.presenter.impl.HomePresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomeViewModule {
    @Provides
    public HomeInteractor provideInteractor() {
        return new HomeInteractorImpl();
    }

    @Provides
    public PresenterFactory<HomePresenter> providePresenterFactory(@NonNull final HomeInteractor interactor) {
        return new PresenterFactory<HomePresenter>() {
            @NonNull
            @Override
            public HomePresenter create() {
                return new HomePresenterImpl(interactor);
            }
        };
    }
}
