package com.nguyenducdungbk.myapp.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nguyenducdungbk.myapp.MyApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionUtils {
    public static final int REQUEST_PERMISSION = 1;

    Context context;
    Activity currentActivity;

    PermissionResultCallback permissionResultCallback;

    ArrayList<String> permissionList = new ArrayList<>();
    ArrayList<String> listPermissionsNeeded = new ArrayList<>();
    int reqCode;

    public PermissionUtils(Context context) {
        this.context = context;
        this.currentActivity = (Activity) context;
    }

    public void setPermissionResultCallback(PermissionResultCallback permissionResultCallback) {
        this.permissionResultCallback = permissionResultCallback;
    }

    public void check_permission(ArrayList<String> permissions, int requestCode) {
        this.permissionList = permissions;
        this.reqCode = requestCode;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkAndRequestPermissions(permissions, requestCode)) {
                permissionResultCallback.permissionGranted(requestCode);
                //all permission granted
            }
        } else {
            permissionResultCallback.permissionGranted(requestCode);
            //all permission granted
        }

    }

    private boolean checkAndRequestPermissions(ArrayList<String> permissions, int requestCode) {

        if (!permissions.isEmpty()) {
            listPermissionsNeeded = new ArrayList<>();

            for (int i = 0; i < permissions.size(); i++) {
                int hasPermission = ContextCompat.checkSelfPermission(currentActivity, permissions.get(i));

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions.get(i));
                }

            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(currentActivity,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        requestCode);
                return false;
            }
        }

        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    Map<String, Integer> perms = new HashMap<>();

                    for (int i = 0; i < permissions.length; i++) {
                        perms.put(permissions[i], grantResults[i]);
                    }

                    final ArrayList<String> pendingPermissions = new ArrayList<>();

                    for (int i = 0; i < listPermissionsNeeded.size(); i++) {
                        if (perms.get(listPermissionsNeeded.get(i)) != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(currentActivity,
                                    listPermissionsNeeded.get(i)))
                                pendingPermissions.add(listPermissionsNeeded.get(i));
                            else {
                                permissionResultCallback.neverAskAgain(reqCode);
                                return;
                            }
                        }
                    }

                    if (!pendingPermissions.isEmpty()) {
                        //denied
                        permissionResultCallback.permissionDenied(reqCode);

                    } else {
                        //all permission granted
                        permissionResultCallback.permissionGranted(reqCode);
                    }
                }
                break;
        }
    }

    public static boolean isAcceptPermission(List<String> listPermission) {

        for (String permission : listPermission) {
            if (ActivityCompat.checkSelfPermission(MyApp.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAcceptPermission(String permission) {
        return ActivityCompat.checkSelfPermission(MyApp.getContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }
}
