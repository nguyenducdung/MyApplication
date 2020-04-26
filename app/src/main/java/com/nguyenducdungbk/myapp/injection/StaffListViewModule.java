package com.nguyenducdungbk.myapp.injection;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.StaffListInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.StaffListInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.StaffListPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.StaffListPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class StaffListViewModule {
    @Provides
    public StaffListInteractor provideInteractor() {
        return new StaffListInteractorImpl();
    }

    @Provides
    public PresenterFactory<StaffListPresenter> providePresenterFactory(@NonNull final StaffListInteractor interactor) {
        return new PresenterFactory<StaffListPresenter>() {
            @NonNull
            @Override
            public StaffListPresenter create() {
                return new StaffListPresenterImpl(interactor);
            }
        };
    }
}
