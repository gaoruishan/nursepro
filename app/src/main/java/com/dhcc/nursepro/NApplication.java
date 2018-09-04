package com.dhcc.nursepro;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.dhcc.nursepro.greendao.DaoMaster;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NApplication extends BaseApplication {

    public static int screenPixelWidth = 0;
    public static int screenPixelHeight = 0;
    public static float displayDensity = 0;
    private static NApplication ymApp = null;
    private static ThreadPoolExecutor threadPoolExecutor;//线程池，用作全局的异步执行

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

        initThreadPool();

        //配置数据库
        GreenDaoHelper.initDatabase(this);

        //工具类
        Utils.init(this);

        Fresco.initialize(getApp());
    }


    //初始化全局线程池
    private static void initThreadPool() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }

}