package com.nguyenducdungbk.myapp.view;

import android.support.annotation.UiThread;

import com.nguyenducdungbk.myapp.network.response.VoucherResponse;

import java.util.List;

@UiThread
public interface ProfileView extends BaseView {

    void updateUser(String name);

    void initListVoucher(List<VoucherResponse> voucherResponses);
}