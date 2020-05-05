package com.nguyenducdungbk.myapp.view.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.FoodCategoryAdapter;
import com.nguyenducdungbk.myapp.network.response.MonAnResponse;

public class FoodCategoryBottom extends RelativeLayout {

    FoodCategoryAdapter adapter;
    RecyclerView rvFoodCategory;
    TextView tvTitleCategory;
    LinearLayout llViewAll;
    OnItemClickFood onItemClickFood;


    public FoodCategoryBottom(Context context) {
        super(context);
        init(context);
    }

    public FoodCategoryBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FoodCategoryBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.layout_food_category, this);
        rvFoodCategory = findViewById(R.id.rv_food_category);
        tvTitleCategory = findViewById(R.id.tv_title_category);
        llViewAll = findViewById(R.id.ll_view_all);
        rvFoodCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapter = new FoodCategoryAdapter();
        rvFoodCategory.setAdapter(adapter);
        rvFoodCategory.setNestedScrollingEnabled(false);
    }

    public void setTitle(String title) {
        tvTitleCategory.setText(title);
    }

    public FoodCategoryBottom setOnItemClickFood(View.OnClickListener listener) {
        if (listener != null) {
            llViewAll.setOnClickListener(listener);
        }
        return this;
    }

    public interface OnItemClickFood {
        void onClick(MonAnResponse monAnResponse);

        void onClickAll();
    }
}