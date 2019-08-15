package com.nguyenducdungbk.myapp.view.impl;

import androidx.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentNewfeedBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerNewfeedViewComponent;
import com.nguyenducdungbk.myapp.injection.NewfeedViewModule;
import com.nguyenducdungbk.myapp.presenter.NewfeedPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.view.NewfeedView;

import javax.inject.Inject;

public final class NewfeedFragment extends BaseFragment<NewfeedPresenter, NewfeedView, FragmentNewfeedBinding> implements NewfeedView {
    @Inject
    PresenterFactory<NewfeedPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public NewfeedFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerNewfeedViewComponent.builder()
                .appComponent(parentComponent)
                .newfeedViewModule(new NewfeedViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_newfeed;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<NewfeedPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {

    }
}
