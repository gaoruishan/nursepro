package com.dhcc.health;

import android.os.Handler;

import com.base.commlibs.BaseApplication;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;

public class NApplication extends BaseApplication {

    public static int screenPixelWidth = 0;
    public static int screenPixelHeight = 0;
    public static float displayDensity = 0;
    private static NApplication ymApp = null;

    private static Handler uiHandler; //主线程Handler


    public NApplication() {
        super();
    }

    public static synchronized NApplication getDelegateApp() {
        return ymApp;
    }

    private static synchronized void setDelegateApp(NApplication app) {
        ymApp = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setDelegateApp(this);


        screenPixelWidth = getApp().getResources().getDisplayMetrics().widthPixels;
        screenPixelHeight = getApp().getResources().getDisplayMetrics().heightPixels;
        displayDensity = getApp().getResources().getDisplayMetrics().density;
        uiHandler = new Handler(getMainLooper());


        //配置数据库
     //   GreenDaoHelper.initDatabase(this);

        //工具类
        Utils.init(this);

        Fresco.initialize(getApp());

        TransBroadcastUtil.init(this);
    }

    @Override
    public void onTerminate() {
        TransBroadcastUtil.unreg(this);
        super.onTerminate();

    }
}
