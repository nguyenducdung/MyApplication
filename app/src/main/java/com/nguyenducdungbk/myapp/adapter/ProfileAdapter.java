package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemVoucherBinding;
import com.nguyenducdungbk.myapp.network.response.VoucherResponse;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.VoucherViewHolder> {
    private List<VoucherResponse> voucherResponses;
    private Context context;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    public void setVoucherResponses(List<VoucherResponse> voucherResponses) {
        this.voucherResponses = voucherResponses;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_voucher, viewGroup, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder voucherViewHolder, int i) {
        voucherViewHolder.binding.tvTitle.setText("Ưu đãi giảm giá " + 10 + " %");
        Glide.with(context)
                .load(R.drawable.voucher)
                .transform(new RoundedCorners(18))
                .into(voucherViewHolder.binding.ivVoucher);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class VoucherViewHolder extends RecyclerView.ViewHolder {
        private ItemVoucherBinding binding;

        VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
