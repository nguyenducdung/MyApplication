package com.nguyenducdungbk.myapp.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.StringRes;

import com.nguyenducdungbk.myapp.utils.Define;
import com.nguyenducdungbk.myapp.utils.rx.RxBus;

/**
 * Created by DungND on 8/14/2019.
 */
public class MyDialog {
    private static boolean shown = false;

    private AlertDialog dialog = null;
    private AlertDialog.Builder builder;
    private static Dialog errorNetworkDialog;

    public MyDialog(Context context) {
        builder = new AlertDialog.Builder(context)
                .setOnDismissListener(dialog1 -> {
                    initialize();
                });
        RxBus.getInstance().subscribe(value -> {
            if (!(value instanceof String))
                return;
            if (value.equals(Define.Bus.OPEN_NOTIFICATION) || value.equals(Define.Bus.SHOW_SEARCH_RESULT)) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    public void showErrorNetwork() {
        if (errorNetworkDialog != null && !errorNetworkDialog.isShowing()) {
            errorNetworkDialog.show();
        }
    }

    public MyDialog(Context context, String title, String message,
                        String positive, OnDialogListener onPositiveClick,
                        String negative, OnDialogListener onNegativeClick,
                        boolean isCancelable) {
        builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onPositiveClick != null) {
                        onPositiveClick.onClick();
                    }
                })
                .setNegativeButton(negative, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onNegativeClick != null) {
                        onNegativeClick.onClick();
                    }
                })
                .setOnDismissListener(dialog1 -> {
                    initialize();
                })
                .setCancelable(isCancelable);
        dialog = builder.create();
        dialog.show();
        RxBus.getInstance().subscribe(value -> {
            if (!(value instanceof String))
                return;
            if (value.equals(Define.Bus.OPEN_NOTIFICATION)) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
    }

    public MyDialog setTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    public MyDialog setTitle(@StringRes int title) {
        builder.setTitle(title);
        return this;
    }

    public MyDialog setMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    public MyDialog setMessage(@StringRes int message) {
        builder.setMessage(message);
        return this;
    }

    public MyDialog setPositiveButton(String label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public MyDialog setPositiveButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public MyDialog setNegativeButton(String label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public MyDialog setNegativeButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public MyDialog setCancelable(boolean isCancelable) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            initialize();
        });
        return this;
    }

    public MyDialog setCancelable(boolean isCancelable, OnDialogListener onDialogListener) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public void show() {
        if (!shown && builder != null) {
            dialog = builder.create();
            dialog.show();
            forceShown();
        }
    }

    private void forceShown() {
        shown = true;
    }

    private void initialize() {
        shown = false;
    }

    public interface OnDialogListener {
        void onClick();
    }
}
