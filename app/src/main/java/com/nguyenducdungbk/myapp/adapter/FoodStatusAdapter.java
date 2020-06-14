package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.BuildConfig;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemFoodOrderOldBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public class FoodStatusAdapter extends RecyclerView.Adapter<FoodStatusAdapter.FoodStatusViewHolder> {
    private List<FoodResponse> foodResponses;
    private Context context;

    public FoodStatusAdapter(Context context) {
        this.context = context;
    }

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
    }

    @NonNull
    @Override
    public FoodStatusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_order_old, viewGroup, false);
        return new FoodStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodStatusViewHolder holder, int i) {
        Glide.with(context)
                .load(BuildConfig.API_BASE_URL + foodResponses.get(i).getImage())
                .centerCrop()
                .into(holder.binding.ivAvatar);
        holder.binding.tvName.setText(foodResponses.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return foodResponses != null ? foodResponses.size() : 0;
    }

    class FoodStatusViewHolder extends RecyclerView.ViewHolder {
        private ItemFoodOrderOldBinding binding;

        public FoodStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
