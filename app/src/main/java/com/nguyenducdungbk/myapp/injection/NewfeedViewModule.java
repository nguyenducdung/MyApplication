package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.interactor.NewfeedInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.NewfeedInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.NewfeedPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.NewfeedPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class NewfeedViewModule {
    @Provides
    public NewfeedInteractor provideInteractor() {
        return new NewfeedInteractorImpl();
    }

    @Provides
    public PresenterFactory<NewfeedPresenter> providePresenterFactory(@NonNull final NewfeedInteractor interactor) {
        return new PresenterFactory<NewfeedPresenter>() {
            @NonNull
            @Override
            public NewfeedPresenter create() {
                return new NewfeedPresenterImpl(interactor);
            }
        };
    }
}
