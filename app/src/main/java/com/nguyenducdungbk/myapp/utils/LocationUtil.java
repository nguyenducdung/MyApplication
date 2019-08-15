package com.nguyenducdungbk.myapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by DungND on 8/15/2019.
 */
public class LocationUtil {
    @SuppressLint("MissingPermission")
    public static void getCurrentLocation(Activity activity, OnSuccessListener<Location> callback) {
        LocationServices.getFusedLocationProviderClient(activity)
                .getLastLocation()
                .addOnSuccessListener(callback);
//        LocationServices.FusedLocationApi.requestLocationUpdates(
//                mGoogleApiClient, mLocationRequest, this);
    }
}
