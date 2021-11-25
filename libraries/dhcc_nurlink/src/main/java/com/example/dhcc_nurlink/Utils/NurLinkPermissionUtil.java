package com.example.dhcc_nurlink.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * com.example.dhcc_nurlink.Utils
 * <p>
 * author Q
 * Date: 2021/7/8
 * Time:9:36
 */
public class NurLinkPermissionUtil {

    private static final int REQUEST_CODE = 1;
    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.SYSTEM_ALERT_WINDOW
    };
    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        int permission1 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission3 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.SYSTEM_ALERT_WINDOW);

        if (permission != PackageManager.PERMISSION_GRANTED||
                permission1 != PackageManager.PERMISSION_GRANTED||
                permission2 != PackageManager.PERMISSION_GRANTED||
                permission3 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }
}
