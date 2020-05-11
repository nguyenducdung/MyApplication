package com.nguyenducdungbk.myapp.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;
import com.nguyenducdungbk.myapp.view.custom.RestaurantToolbar;

public class FoodDetailDialog {

    public FoodDetailDialog(@NonNull Context context, FoodResponse foodResponse) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        View sheetView = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_food_detail, null);
        RestaurantToolbar toolbar = sheetView.findViewById(R.id.toolbar);
        ImageView ivFood = sheetView.findViewById(R.id.iv_food);
        TextView tvDescription = sheetView.findViewById(R.id.tv_description);
        toolbar.setOnBackClickListener(view -> dialog.dismiss());
        toolbar.setTextTitleToobar(foodResponse.getName());
        Glide.with(context)
                .load(foodResponse.getImage())
                .into(ivFood);
        tvDescription.setText(foodResponse.getDescription());
        dialog.setContentView(sheetView);
        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog d = (BottomSheetDialog) dialogInterface;
            View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
            if (bottomSheetInternal != null) {
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        dialog.show();
    }
}
