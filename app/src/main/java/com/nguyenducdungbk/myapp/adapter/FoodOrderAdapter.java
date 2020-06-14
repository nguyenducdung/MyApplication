package com.nguyenducdungbk.myapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemFoodOrderBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.utils.StringUtil;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodOrderViewHolder> {
    private List<FoodResponse> foodResponses;

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
    }

    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_order, viewGroup, false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderViewHolder foodOrderViewHolder, int i) {
        foodOrderViewHolder.binding.tvNameFood.setText(foodResponses.get(i).getName());
        foodOrderViewHolder.binding.tvPrice.setText(StringUtil.convertViewQuantity(Long.valueOf(foodResponses.get(i).getPrice())));
    }

    @Override
    public int getItemCount() {
        return foodResponses != null ? foodResponses.size() : 0;
    }

    class FoodOrderViewHolder extends RecyclerView.ViewHolder {
        private ItemFoodOrderBinding binding;

        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
