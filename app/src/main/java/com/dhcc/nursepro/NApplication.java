package com.dhcc.nursepro;

import android.os.Handler;

import com.base.commlibs.BaseApplication;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.base.commlibs.voiceUtils.VoiceInitUtil;
import com.blankj.utilcode.util.Utils;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.example.dhcc_nurlink.voiceplayutil.download.DownloadConfiguration;
import com.example.dhcc_nurlink.voiceplayutil.download.DownloadManager;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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

        VoiceInitUtil.initSound(getApplicationContext());

        screenPixelWidth = getApp().getResources().getDisplayMetrics().widthPixels;
        screenPixelHeight = getApp().getResources().getDisplayMetrics().heightPixels;
        displayDensity = getApp().getResources().getDisplayMetrics().density;
        uiHandler = new Handler(getMainLooper());

        initThreadPool();

        //配置数据库
        GreenDaoHelper.initDatabase(this);
        com.example.dhcc_nurlink.greendao.GreenDaoHelper.initDatabase(this);

        //工具类
        Utils.init(this);

        Fresco.initialize(getApp());

        TransBroadcastUtil.init(this);

        DownloadConfiguration downloadConfiguration = new DownloadConfiguration.Builder(getApplicationContext())
                .setCacheDir(getExternalCacheDir())        //设置下载缓存目录，必须设置
                .setTaskExecutor(executorService)    //同上传类似
                .setThreadPriority(5)  //同上传类似
                .setThreadPoolCoreSize(5)  //同上传类似
                .build();
        DownloadManager.getInstance(this).init(downloadConfiguration);
    }


    public static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d")
                    .daemon(true).build());


    //初始化全局线程池
    private static void initThreadPool() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }

    @Override
    public void onTerminate() {
        TransBroadcastUtil.unreg(this);
        super.onTerminate();

    }
}
