package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.StaffInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.StaffInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.StaffPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.StaffPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class StaffViewModule {
    @Provides
    public StaffInteractor provideInteractor() {
        return new StaffInteractorImpl();
    }

    @Provides
    public PresenterFactory<StaffPresenter> providePresenterFactory(@NonNull final StaffInteractor interactor) {
        return new PresenterFactory<StaffPresenter>() {
            @NonNull
            @Override
            public StaffPresenter create() {
                return new StaffPresenterImpl(interactor);
            }
        };
    }
}
