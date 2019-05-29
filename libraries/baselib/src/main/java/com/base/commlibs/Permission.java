package com.base.commlibs;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Created by levis on 2018/6/5.
 */

public class Permission {
    private Permission() {
    }

    /**
     * 请求权限
     */
    public static void requestPermission(final String permission) {
        BaseApplication.requestPermission(permission);
    }

    /**
     * 判断是否拥有读写日历的权限
     */
    public static boolean hasCalendarPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.WRITE_CALENDAR);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求读写日历的权限
     */
    public static void requestCalendarPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.WRITE_CALENDAR, requestMessage);
    }

    /**
     * 判断是否拥有使用摄像头的权限
     */
    public static boolean hasCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Context context = BaseApplication.getApp();
            int res = ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.CAMERA);
            return res == PackageManager.PERMISSION_GRANTED;
        } else {
            try {
                Camera camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                List<Camera.Size> previewSizelist = camera.getParameters().getSupportedPreviewSizes();
                camera.release();
                return previewSizelist != null && previewSizelist.size() > 0;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 请求使用摄像头的权限
     */
    public static boolean requestCameraPermission(String requestMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BaseApplication.requestPermission(
                    android.Manifest.permission.CAMERA, requestMessage);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否拥有读写通讯录的权限
     */
    public static boolean hasContactsPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.WRITE_CONTACTS);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求读写通讯录的权限
     */
    public static void requestContactsPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.WRITE_CONTACTS, requestMessage);
    }

    /**
     * 判断是否拥有使用地理位置的权限
     */
    public static boolean hasLocationPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求使用地理位置的权限
     */
    public static void requestLocationPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION, requestMessage);
    }

    /**
     * 判断是否拥有使用录音的权限
     */
    public static boolean hasRecorderPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.RECORD_AUDIO);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求使用录音的权限
     */
    public static void requestRecorderPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.RECORD_AUDIO, requestMessage);
    }

    /**
     * 判断是否拥有拨打电话的权限
     */
    public static boolean hasCallPhonePermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.CALL_PHONE);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求使用读取电话状态信息的权限
     */
    public static void requestReadPhoneStatePermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.READ_PHONE_STATE, requestMessage);
    }

    /**
     * 判断是否拥有读取电话状态信息的权限
     */
    public static boolean hasReadPhoneStatePermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.READ_PHONE_STATE);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求使用拨打电话的权限
     */
    public static void requestCallPhonePermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.CALL_PHONE, requestMessage);
    }

    /**
     * 判断是否拥有使用生命体征传感器的权限
     */
    public static boolean hasBodySensorsPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.BODY_SENSORS);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求使用生命体征传感器的权限
     */
    public static void requestBodySensorsPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.BODY_SENSORS, requestMessage);
    }

    /**
     * 判断是否拥有读写短信的权限
     */
    public static boolean hasSmsPermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.READ_SMS);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求读写短信的权限
     */
    public static void requestSmsPermission(String requestMessage) {
        BaseApplication.requestPermission(
                android.Manifest.permission.READ_SMS, requestMessage);
    }

    /**
     * 判断是否拥有读写SDCard的权限
     */
    public static boolean hasStoragePermission() {
        Context context = BaseApplication.getApp();
        int res = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求读写SDCard的权限
     */
    public static boolean requestStoragePermission(String requestMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BaseApplication.requestPermission(
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE, requestMessage);
            return true;
        } else {
            return false;
        }
    }
}