package com.nguyenducdungbk.myapp.injection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nguyenducdungbk.myapp.interactor.MainInteractor;
import com.nguyenducdungbk.myapp.interactor.impl.MainInteractorImpl;
import com.nguyenducdungbk.myapp.presenter.MainPresenter;
import com.nguyenducdungbk.myapp.presenter.impl.MainPresenterImpl;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.impl.ViewController;

import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {

    private AppCompatActivity activity;
    private int layoutId;

    public MainViewModule(AppCompatActivity activity, int layoutId) {
        this.activity = activity;
        this.layoutId = layoutId;
    }
    @Provides
    public MainInteractor provideInteractor() {
        return new MainInteractorImpl();
    }

    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return new PresenterFactory<MainPresenter>() {
            @NonNull
            @Override
            public MainPresenter create() {
                return new MainPresenterImpl(interactor);
            }
        };
    }


    @Provides
    public ViewController provideViewController() {
        return new ViewController(activity.getSupportFragmentManager(), layoutId);
    }
}
