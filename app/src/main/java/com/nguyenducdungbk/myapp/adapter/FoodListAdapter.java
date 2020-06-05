package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemFoodVerticalBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private List<FoodResponse> foodResponses;
    private FoodCategoryAdapter.OnClickFood onClickFood;
    private Context context;

    public FoodListAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickFood(FoodCategoryAdapter.OnClickFood onClickFood) {
        this.onClickFood = onClickFood;
    }

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_vertical, viewGroup, false);
        return new FoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListViewHolder foodListViewHolder, int i) {

        Glide.with(context)
                .load(foodResponses.get(i).getImage())
                .into(foodListViewHolder.binding.ivCover);
        foodListViewHolder.binding.tvContent.setText(foodResponses.get(i).getName());
        foodListViewHolder.binding.tvTimeTitle.setText("Giá niêm yết: " + foodResponses.get(i).getPrice() + " đồng");
        foodListViewHolder.binding.tvTime.setText("Thời gian chờ đợi: " + foodResponses.get(i).getTime() + " phút");
    }

    @Override
    public int getItemCount() {
        return foodResponses != null ? foodResponses.size() : 0;
    }

    class FoodListViewHolder extends RecyclerView.ViewHolder {
        private ItemFoodVerticalBinding binding;

        public FoodListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(v -> onClickFood.onClickFood(foodResponses.get(getAdapterPosition())));
        }
    }
}
