package com.base.commlibs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Intent跳转类
 * @作者:gaoruishan
 * @时间:2018/2/2/15:13
 * @邮箱:grs0515@163.com
 */

public class IntentHelper {
    //跳转变量
    private static final String EXTRA_DATA_STRING = "extra_data_string";
    private static final String EXTRA_DATA_STRING2 = "extra_data_string2";
    private static final String EXTRA_DATA_STRING3 = "extra_data_string3";
    private static final String EXTRA_DATA_STRING4 = "extra_data_string4";
    private static final String EXTRA_DATA_OBJECT = "extra_data_object";
    private static final String EXTRA_DATA_INT = "extra_data_int";
    private static final String EXTRA_DATA_BOOLEAN = "extra_data_boolean";
    private static final String EXTRA_DATA_OBJECT_LIST = "extra_data_object_list";
    private static final String TAG = IntentHelper.class.getSimpleName();
    //单例
    private static volatile IntentHelper intentHelper;
    //当前事件
    private Intent intent;
    private Context mActivity;

    private IntentHelper() {
    }

    public static IntentHelper get() {
        if (intentHelper == null) {
            synchronized (IntentHelper.class) {
                if (intentHelper == null) {
                    intentHelper = new IntentHelper();
                }
            }
        }
        return intentHelper;
    }

    /**
     * 基本跳转
     *
     * @param activity
     * @param cls
     */
    public static void startActivity(@NonNull Activity activity, @NonNull Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    /**
     * 通过类名跳转
     *
     * @param context
     * @param name
     */
    public static void startActivityForName(@NonNull Context context, @NonNull String name) {
        try {
            if (!TextUtils.isEmpty(name)) {
                Class clazz = Class.forName(name);
                if (clazz != null) {
                    Intent intent = new Intent(context, clazz);
                    context.startActivity(intent);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过清单中的Action跳转
     *
     * @param context
     * @param action
     */
    public static void startActivityForAction(@NonNull Context context, @NonNull String action) {
        if (!TextUtils.isEmpty(action)) {
            context.startActivity(new Intent(action));
        }
    }

    /**
     * 默认的scheme跳转逻辑
     */
    public static void schemeTo(@NonNull Context context, @NonNull String scheme) {
        if (!TextUtils.isEmpty(scheme)) {
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
            if (in.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(in);
            } else {
                Log.e(TAG,"(IntentHelper.java:109) 应用未安装");
            }
        } else {
            Log.e(TAG,"(IntentHelper.java:113) 应用未安装");
        }
    }

    /**
     * 判断网络
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 获取PackageInfo
     *
     * @param activity
     * @return
     */
    @Nullable
    public static PackageInfo getPackageInfo(Context activity) {
        PackageManager pm = activity.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 注意:startBuilder(Class<?> cls)方法 和 build()方法联合使用
     *
     * @param cls
     * @return
     */
    public IntentHelper start(Context activity, Class<?> cls) {
        if (activity != null) {
            this.mActivity = activity;
            intent = new Intent(activity, cls);
        }
        return this;
    }

    public IntentHelper buildExtra(int data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_INT, data);
        }
        return this;
    }

    public IntentHelper buildExtra(boolean data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_BOOLEAN, data);
        }
        return this;
    }

    public IntentHelper buildExtra(String data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_STRING, data);
        }
        return this;
    }

    public IntentHelper buildExtra2(String data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_STRING2, data);
        }
        return this;
    }

    public IntentHelper buildExtra3(String data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_STRING3, data);
        }
        return this;
    }

    public IntentHelper buildExtra4(String data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_STRING4, data);
        }
        return this;
    }

    public IntentHelper buildExtra(Parcelable data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_OBJECT, data);
        }
        return this;
    }

    public IntentHelper buildExtra(Serializable data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_OBJECT, data);
        }
        return this;
    }

    public IntentHelper buildExtra(ArrayList<? extends Parcelable> data) {
        if (intent != null) {
            intent.putExtra(EXTRA_DATA_OBJECT_LIST, data);
        }
        return this;
    }

    public IntentHelper build() {
        if (intent != null && mActivity != null) {
            mActivity.startActivity(intent);
        }
        return this;
    }

    public String getStringExtra(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getStringExtra(EXTRA_DATA_STRING);
        }
        return null;
    }

    public String getStringExtra2(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getStringExtra(EXTRA_DATA_STRING2);
        }
        return null;
    }

    public String getStringExtra3(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getStringExtra(EXTRA_DATA_STRING3);
        }
        return null;
    }

    public String getStringExtra4(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getStringExtra(EXTRA_DATA_STRING4);
        }
        return null;
    }

    public int getIntExtra(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getIntExtra(EXTRA_DATA_INT, -1);
        }
        return -1;
    }

    public boolean getBooleanExtra(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getBooleanExtra(EXTRA_DATA_BOOLEAN, false);
        }
        return false;
    }

    public <T extends Parcelable> T getParcelableExtra(Activity activity, Class<T> t) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getParcelableExtra(EXTRA_DATA_OBJECT);
        }
        return null;
    }

    public <T extends Serializable> T getSerializableExtra(Activity activity, Class<T> t) {
        if (activity != null && activity.getIntent() != null) {
            return (T) activity.getIntent().getSerializableExtra(EXTRA_DATA_OBJECT);
        }
        return null;
    }

    public <T extends Parcelable> ArrayList<T> getParcelableListExtra(Activity activity, Class<T> t) {
        if (activity != null && activity.getIntent() != null) {
            return activity.getIntent().getParcelableArrayListExtra(EXTRA_DATA_OBJECT_LIST);
        }
        return null;
    }

}
