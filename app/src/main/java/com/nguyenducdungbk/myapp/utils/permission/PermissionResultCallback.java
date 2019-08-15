package com.nguyenducdungbk.myapp.utils.permission;

public interface PermissionResultCallback {
    void permissionGranted(int requestCode);
    void permissionDenied(int requestCode);
    void neverAskAgain(int requestCode);
}
