package com.nguyenducdungbk.myapp.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("bill")
    @Expose
    private BillResponse bill;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BillResponse getBill() {
        return bill;
    }

    public void setBill(BillResponse bill) {
        this.bill = bill;
    }
}
