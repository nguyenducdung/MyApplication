package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.MyApp;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemFoodHorizontalBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder> {
    private List<FoodResponse> foodResponses;
    private OnClickFood onClickFood;
    private Context context;

    public FoodCategoryAdapter() {
    }

    public FoodCategoryAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickFood(OnClickFood onClickFood) {
        this.onClickFood = onClickFood;
    }

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
    }

    @NonNull
    @Override
    public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_horizontal, viewGroup, false);
        return new FoodCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryViewHolder foodCategoryViewHolder, int i) {
        Glide.with(context)
                .load(foodResponses.get(i).getImage())
                .into(foodCategoryViewHolder.binding.ivCover);
        foodCategoryViewHolder.binding.tvContent.setText(foodResponses.get(i).getName());
        foodCategoryViewHolder.binding.tvTimeTitle.setText("Giá niêm yết: " + foodResponses.get(i).getPrice() + " đồng");
        foodCategoryViewHolder.binding.tvTime.setText("Thời gian chờ đợi: " + foodResponses.get(i).getTime() + " phút");
    }

    @Override
    public int getItemCount() {
        return foodResponses.size();
    }

    class FoodCategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemFoodHorizontalBinding binding;

        FoodCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(v -> onClickFood.onClickFood(foodResponses.get(getAdapterPosition())));
        }
    }

    public interface OnClickFood {
        void onClickFood(FoodResponse foodResponse);
    }
}
