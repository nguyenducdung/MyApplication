package com.nguyenducdungbk.myapp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nguyenducdungbk.myapp.R;
import com.nguyenducdungbk.myapp.adapter.RateFoodAdapter;
import com.nguyenducdungbk.myapp.network.response.FoodResponse;

import java.util.List;

public class RateDialog {

    public RateDialog(Context context, List<FoodResponse> foodResponses) {
        Dialog dialog = new Dialog(context);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.layout_rate_food);
        RecyclerView rvFood = dialog.findViewById(R.id.rv_food);
        RateFoodAdapter adapter = new RateFoodAdapter(foodResponses, context);
        rvFood.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvFood.setAdapter(adapter);
        dialog.show();
    }
}
