package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vouchers {
    @SerializedName("vouchers")
    private List<VoucherResponse> voucherResponses;

    public List<VoucherResponse> getVoucherResponses() {
        return voucherResponses;
    }

    public void setVoucherResponses(List<VoucherResponse> voucherResponses) {
        this.voucherResponses = voucherResponses;
    }
}
