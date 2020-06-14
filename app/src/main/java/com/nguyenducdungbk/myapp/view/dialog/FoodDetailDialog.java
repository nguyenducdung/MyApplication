package com.nguyenducdungbk.myapp.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.BuildConfig;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.view.custom.RestaurantToolbar;

public class FoodDetailDialog {
    public FoodDetailDialog(@NonNull Context context, FoodResponse foodResponse, OnClickFood onClickFood) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View sheetView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_food_detail, null);
        RestaurantToolbar toolbar = sheetView.findViewById(R.id.toolbar);
        ImageView ivFood = sheetView.findViewById(R.id.iv_food);
        TextView tvDescription = sheetView.findViewById(R.id.tv_description);
        Button btnOrder = sheetView.findViewById(R.id.btn_order);
        toolbar.setOnBackClickListener(view -> dialog.dismiss());
        toolbar.setTextTitleToobar(foodResponse.getName());
        Glide.with(context)
                .load(BuildConfig.API_BASE_URL + foodResponse.getImage())
                .into(ivFood);
        tvDescription.setText(foodResponse.getInfo());
        dialog.setContentView(sheetView);
        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog d = (BottomSheetDialog) dialogInterface;
            View bottomSheetInternal = d.findViewById(R.id.design_bottom_sheet);
            if (bottomSheetInternal != null) {
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        btnOrder.setOnClickListener(v -> {
            onClickFood.onClickFood(foodResponse);
            dialog.dismiss();
        });
        dialog.show();
    }

    public interface OnClickFood {
        void onClickFood(FoodResponse foodResponse);
    }
}
