package com.nguyenducdungbk.myapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodOrderViewHolder> {
    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_order, viewGroup, false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderViewHolder foodOrderViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class FoodOrderViewHolder extends RecyclerView.ViewHolder {

        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
