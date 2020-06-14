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
import com.nguyenducdungbk.myapp.BuildConfig;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemSearchFoodBinding;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.utils.StringUtil;

import java.util.List;

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.SearchViewHolder> {

    private List<FoodResponse> foodResponses;
    private Context context;
    private OnClickSearchFood onClickSearchFood;

    public SearchFoodAdapter(Context context) {
        this.context = context;
    }

    public void setFoodResponses(List<FoodResponse> foodResponses) {
        this.foodResponses = foodResponses;
        notifyDataSetChanged();
    }

    public void setOnClickSearchFood(OnClickSearchFood onClickSearchFood) {
        this.onClickSearchFood = onClickSearchFood;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search_food, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        Glide.with(context)
                .load(BuildConfig.API_BASE_URL + foodResponses.get(i).getImage())
                .centerCrop()
                .transform(new RoundedCorners(16))
                .into(searchViewHolder.binding.ivCover);
        searchViewHolder.binding.tvTitle.setText(foodResponses.get(i).getName());
        searchViewHolder.binding.tvTime.setText(foodResponses.get(i).getTime() + " phút");
        searchViewHolder.binding.tvPrice.setText("Giá: " + StringUtil.convertViewQuantity(Long.valueOf(foodResponses.get(i).getPrice())) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return foodResponses != null ? foodResponses.size() : 0;
    }

    public void clear() {
        foodResponses.clear();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        private ItemSearchFoodBinding binding;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(v -> onClickSearchFood.onClickSearchFood(foodResponses.get(getAdapterPosition())));
        }
    }

    public interface OnClickSearchFood {
        void onClickSearchFood(FoodResponse foodResponse);
    }
}
