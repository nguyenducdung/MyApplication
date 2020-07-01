package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.BuildConfig;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemRateFoodBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public class RateFoodAdapter extends RecyclerView.Adapter<RateFoodAdapter.RateViewHolder> {
    private List<FoodResponse> foodResponses;
    private Context context;

    public RateFoodAdapter(List<FoodResponse> foodResponses, Context context) {
        this.foodResponses = foodResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rate_food, viewGroup, false);
        return new RateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int i) {
        holder.binding.tvName.setText(foodResponses.get(i).getName());
        Glide.with(context)
                .load(BuildConfig.API_BASE_URL + foodResponses.get(i).getImage())
                .into(holder.binding.ivFood);

    }

    @Override
    public int getItemCount() {
        return foodResponses != null ? foodResponses.size() : 0;
    }

    class RateViewHolder extends RecyclerView.ViewHolder {
        private ItemRateFoodBinding binding;

        public RateViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
