package com.nguyenducdungbk.myapp.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.nguyenducdungbk.myapp.R;

/**
 * Created by DungND on 8/14/2019.
 */
public class MyLoading {
    private boolean shown = false;

    private AlertDialog dialog = null;

    private static MyLoading instance = null;

    private boolean hasActivityPermission = false;

    private Context context;

    public static MyLoading getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new MyLoading(context);
            return instance;
        }
    }

    private MyLoading(Context context) {
        this.context = context;
        if (context != null && !shown) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater li = LayoutInflater.from(context);
            View dialogView = li.inflate(R.layout.layout_loading, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
//            dialog.setOnKeyListener((dialog, keyCode, event) -> {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    // we cannot close dialog when we press back button
//                }
//                return false;
//            });
        }
    }

    public void show() {
        if (!((Activity) context).isFinishing()) {
            if (!shown && dialog != null) {
                forceShown();
                dialog.show();
            }
        }
    }

    public void hidden() {
        if (shown && dialog != null && dialog.isShowing() && !hasActivityPermission) {
            initialize();
            dialog.dismiss();
        }
    }

    public MyLoading setHasActivityPermission(boolean hasActivityPermission) {
        this.hasActivityPermission = hasActivityPermission;
        return this;
    }

    private void forceShown() {
        shown = true;
    }

    private void initialize() {
        shown = false;
    }

    public void destroyLoadingDialog() {
        if (instance != null && instance.dialog != null) {
            instance.dialog.dismiss();
        }
        instance = null;
    }
}
