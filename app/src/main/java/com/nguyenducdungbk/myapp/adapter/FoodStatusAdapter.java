package com.nguyenducdungbk.myapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;

public class FoodStatusAdapter extends RecyclerView.Adapter<FoodStatusAdapter.FoodStatusViewHolder> {
    @NonNull
    @Override
    public FoodStatusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_order_old, viewGroup, false);
        return new FoodStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodStatusViewHolder foodStatusViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class FoodStatusViewHolder extends RecyclerView.ViewHolder {

        public FoodStatusViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
