package com.nguyenducdungbk.myapp.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentLoginBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.DaggerLoginViewComponent;
import com.nguyenducdungbk.myapp.injection.LoginViewModule;
import com.nguyenducdungbk.myapp.presenter.LoginPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.StringUtil;
import com.nguyenducdungbk.myapp.view.LoginView;

import javax.inject.Inject;

public final class LoginFragment extends BaseFragment<LoginPresenter, LoginView, FragmentLoginBinding> implements LoginView {
    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        binding.clContainer.setOnClickListener(v -> hideKeyboard());
        binding.btnLogin.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            hideKeyboard();
            if (TextUtils.isEmpty(binding.etName.getText().toString().trim())) {
                showErrorDialog(R.string.vui_long_nhap_ho_ten);
                return;
            }
            if (TextUtils.isEmpty(binding.etPhone.getText().toString().trim())) {
                showErrorDialog(R.string.vui_long_nhap_so_dien_thoai);
                return;
            }
            if (!StringUtil.isPhoneValid(binding.etPhone.getText().toString().trim())) {
                showErrorDialog(R.string.phone_valid);
                return;
            }
            showLoading();
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(StringUtil.createEmail(binding.etName.getText().toString().trim()), binding.etPhone.getText().toString().trim())
                    .addOnCompleteListener(getActivity(), task -> {
                        Toast.makeText(getContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString(HomeFragment.NAME_USER, binding.etName.getText().toString().trim());
                        bundle.putString(HomeFragment.PHONE_USER, binding.etPhone.getText().toString().trim());
                        hiddenLoading();
                        getViewController().addFragment(HomeFragment.class, bundle);
                    });
        });
    }
}
