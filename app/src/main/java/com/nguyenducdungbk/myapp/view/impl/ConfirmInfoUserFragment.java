package com.nguyenducdungbk.myapp.view.impl;

import android.support.annotation.NonNull;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.FragmentConfirmInfoUserBinding;
import com.nguyenducdungbk.myapp.injection.AppComponent;
import com.nguyenducdungbk.myapp.injection.ConfirmInfoUserViewModule;
import com.nguyenducdungbk.myapp.injection.DaggerConfirmInfoUserViewComponent;
import com.nguyenducdungbk.myapp.network.response.UserResponse;
import com.nguyenducdungbk.myapp.presenter.ConfirmInfoUserPresenter;
import com.nguyenducdungbk.myapp.presenter.loader.PresenterFactory;
import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.view.ConfirmInfoUserView;
import com.nguyenducdungbk.myapp.view.custom.DateInputMask;
import com.nguyenducdungbk.myapp.view.dialog.MyDialog;

import javax.inject.Inject;

public final class ConfirmInfoUserFragment extends BaseFragment<ConfirmInfoUserPresenter, ConfirmInfoUserView, FragmentConfirmInfoUserBinding> implements ConfirmInfoUserView {
    @Inject
    PresenterFactory<ConfirmInfoUserPresenter> mPresenterFactory;
    private String gender = "";

    // Your presenter is available using the mPresenter variable

    public ConfirmInfoUserFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerConfirmInfoUserViewComponent.builder()
                .appComponent(parentComponent)
                .confirmInfoUserViewModule(new ConfirmInfoUserViewModule())
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_confirm_info_user;
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @NonNull
    @Override
    protected PresenterFactory<ConfirmInfoUserPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initView() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).hideIconOrder();
        }
        binding.btnAccountEditDisconnect.setOnClickListener(v -> backPressed());
        new DateInputMask(binding.etBirthday);
        binding.etBirthday.setSelection(0);
        binding.btnMale.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            gender = Define.GENDER_NAM;
            binding.btnMale.setSelected(true);
            binding.btnFemale.setSelected(false);
        });
        binding.btnFemale.setOnClickListener(v -> {
            if (avoidDuplicateClick()) {
                return;
            }
            gender = Define.GENDER_NU;
            binding.btnMale.setSelected(false);
            binding.btnFemale.setSelected(true);
        });

        binding.btnConnectEditComplete.setOnClickListener(view -> {
            if (!avoidDuplicateClick() && mPresenter != null) {
                mPresenter.saveUser();
            }
        });
        binding.toolbar.setOnBackClickListener(v -> backPressed());
    }

    @Override
    public void updateUser(UserResponse user) {
        binding.tvNameSns.setText(user.getName());
        binding.edtName.setText(user.getName());
        binding.edtPhone.setText(user.getPhone());
        if (user.getGender() == 1) {
            binding.btnMale.setSelected(true);
            binding.btnFemale.setSelected(false);
        } else {
            binding.btnMale.setSelected(false);
            binding.btnFemale.setSelected(true);
        }
        binding.etBirthday.setText(user.getBirthday());
    }

    @Override
    public String getName() {
        return binding.edtName.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return binding.edtPhone.getText().toString().trim();
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getDate() {
        return binding.etBirthday.getText().toString().trim();
    }

    @Override
    public void showUpdateSuccess() {
        new MyDialog(getContext())
                .setMessage(getString(R.string.cap_nhat_thanh_cong))
                .setPositiveButton(R.string.ok, this::backPressed)
                .show();
    }
}
