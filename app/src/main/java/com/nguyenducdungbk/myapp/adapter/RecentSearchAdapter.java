package com.nguyenducdungbk.myapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.databinding.ItemRecentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentViewHolder> {
    private Context context;
    private List<String> data;
    private OnItemClickRecentSearch onItemClickRecentSearch;
    private boolean isShowLastDivider = false;

    public RecentSearchAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<String> recentSearch) {
        data.clear();
        data.addAll(recentSearch);
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClickRecentSearch onItemClick) {
        this.onItemClickRecentSearch = onItemClick;
    }

    public boolean isHaveData() {
        return data != null && !data.isEmpty();
    }

    public void setShowLastDivider(boolean isShow) {
        isShowLastDivider = isShow;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recent_search, viewGroup, false);
        return new RecentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int i) {
        String recentSearch = data.get(i);
        holder.binding.tvName.setText(recentSearch);
        holder.binding.divider.setVisibility((i == data.size() - 1 && !isShowLastDivider) ? View.INVISIBLE : View.VISIBLE);
        holder.binding.llIndustrial.setOnClickListener(v -> {
            if (onItemClickRecentSearch != null) {
                onItemClickRecentSearch.onItemClick(recentSearch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class RecentViewHolder extends RecyclerView.ViewHolder {
        private ItemRecentSearchBinding binding;

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnItemClickRecentSearch {
        void onItemClick(String recentSearch);
    }
}
