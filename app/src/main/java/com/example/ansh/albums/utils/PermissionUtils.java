package com.example.ansh.albums.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.example.ansh.albums.MainActivity;

public class PermissionUtils {

    public static final int MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;

    private MainActivity mMainActivity;
    private static PermissionUtils instance = null;

    // Private Constructor
    private PermissionUtils(MainActivity activity) {
        this.mMainActivity = activity;
    }

    // Method to get instance of the PermissionUtils class
    public static PermissionUtils getInstance(MainActivity activity) {
        if(instance == null) {
            instance = new PermissionUtils(activity);
            return instance;
        } else {
            return instance;
        }
    }

    public boolean checkPerm(Context context) {

        // ContextCompat.checkSelfPermission() check if it's has permission.
        // This if() will run if there's no permission
        if(ContextCompat.checkSelfPermission
                (mMainActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    mMainActivity, Manifest.permission.READ_EXTERNAL_STORAGE
            )) {

                // Show an explanation to the user *asynchronously* --don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Show Information about why you need the permission


                return false;
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(mMainActivity,
                        new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                        }, MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);

                // The callback methods gets the result of the request.
            }
            return false;
        } else {

            // Permission has already been granted.
            return true;
        }
    }


}
