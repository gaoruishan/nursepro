package com.dhcc.nursepro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by levis on 2018/8/2.
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static BaseActivity sPermissionActivity;
    private static ArrayList<BaseActivity> sActivities;

    private static Context context;

    private static int activityCount;

    private static Application sMe;


    public static Context getContext() {
        return context;
    }


    public BaseApplication(){
        super();
        sMe = this;
        sActivities = new ArrayList<>();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        getApp().registerActivityLifecycleCallbacks(this);

    }

    public static Application getApp() {
        return sMe;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity instanceof BaseActivity) {
            sActivities.add((BaseActivity) activity);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity instanceof BaseActivity) {
            sActivities.remove(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {activityCount++;}

    @Override
    public void onActivityResumed(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {activityCount--;}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}



    /**
     * 请求权限
     * @param permission 申请的权限名称
     */
    public static void requestPermission(final String permission) {
        updatePermissionActivity();
        if (sPermissionActivity != null) {
            sPermissionActivity.requestPermission(permission);
        }
    }

    /**
     * 请求权限
     * @param permission 申请的权限名称
     * @param requestMessage 申请权限提示框中提示文本
     */
    public static void requestPermission(final String permission, final String requestMessage) {
        updatePermissionActivity();
        if (sPermissionActivity != null) {
            sPermissionActivity.requestPermission(permission, requestMessage);
        }
    }

    // 更新sPermissionActivity
    private static void updatePermissionActivity() {
        for (int i = sActivities.size() - 1; i >= 0; i--) {
            BaseActivity activity = sActivities.get(i);
            if (activity.isResume) {
                sPermissionActivity = activity;
                break;
            }
        }
    }

    // 权限更新后
    final void requestPermissionsResult(int requestCode,
                                        @NonNull String[] permissions,
                                        @NonNull int[] grantResults) {
        // 通知其下所有BaseActivity
        for (int i = sActivities.size() - 1; i >= 0; i--) {
            BaseActivity activity = sActivities.get(i);
            if (activity != null) {
                activity.requestPermissionsResult(permissions, grantResults);
            }
        }
    }

    public static int getActivityCount(){
        return activityCount;
    }
}