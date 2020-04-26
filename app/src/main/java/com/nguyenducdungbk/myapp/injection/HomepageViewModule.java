package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.HomepageInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.HomepageInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.HomepagePresenter;
import com.nguyenducdungbk.myapp.presenter.impl.HomepagePresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class HomepageViewModule {
    @Provides
    public HomepageInteractor provideInteractor() {
        return new HomepageInteractorImpl();
    }

    @Provides
    public PresenterFactory<HomepagePresenter> providePresenterFactory(@NonNull final HomepageInteractor interactor) {
        return new PresenterFactory<HomepagePresenter>() {
            @NonNull
            @Override
            public HomepagePresenter create() {
                return new HomepagePresenterImpl(interactor);
            }
        };
    }
}
