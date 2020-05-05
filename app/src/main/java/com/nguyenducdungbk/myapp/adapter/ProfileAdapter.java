package com.nguyenducdungbk.myapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemVoucherBinding;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.VoucherViewHolder> {
    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_voucher, viewGroup, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder voucherViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class VoucherViewHolder extends RecyclerView.ViewHolder {
        private ItemVoucherBinding binding;

        VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}