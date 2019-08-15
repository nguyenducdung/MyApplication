package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.MapInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.MapInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.MapPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.MapPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class MapViewModule {
    @Provides
    public MapInteractor provideInteractor() {
        return new MapInteractorImpl();
    }

    @Provides
    public PresenterFactory<MapPresenter> providePresenterFactory(@NonNull final MapInteractor interactor) {
        return new PresenterFactory<MapPresenter>() {
            @NonNull
            @Override
            public MapPresenter create() {
                return new MapPresenterImpl(interactor);
            }
        };
    }
}
