package com.nguyenducdungbk.myapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemFoodHorizontalBinding;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder> {

    public FoodCategoryAdapter() {
    }

    @NonNull
    @Override
    public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_horizontal, viewGroup, false);
        return new FoodCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryViewHolder foodCategoryViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class FoodCategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemFoodHorizontalBinding binding;

        FoodCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
