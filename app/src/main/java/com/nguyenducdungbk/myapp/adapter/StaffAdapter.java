package com.nguyenducdungbk.myapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemStaffBinding;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {
    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_staff, viewGroup, false);
        return new StaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder staffViewHolder, int i) {
        staffViewHolder.binding.tvNameStaff.setText("Nguyễn Đức Dũng " + i);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {
        private ItemStaffBinding binding;

        StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
