package com.base.commlibs.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;

import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommWebService;
import com.blankj.utilcode.util.AppUtils;


public class MServiceNewOrd extends Service {

    private Handler mHandler = new Handler();
    private Boolean isDestroy = false;


    @Override
    public void onCreate() {
        super.onCreate();

        startForegroundSdkO(); // EH 2022-04-08 解决android 8.0 退出崩溃
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Intent i = new Intent();
                        i.setAction(Action.NEWMESSAGE_SERVICE);
                        sendBroadcast(i);
                        removeMessages(0);
                        //这里想2分钟s刷新消息列表
                        String appPackageName = AppUtils.getAppPackageName();

                        if (CommWebService.COM_DHCC_INFUSION.equals(appPackageName)) {
                            sendEmptyMessageDelayed(0, 50 * 1000);
                        }
                        if (CommWebService.COM_DHCC_NURSEPRO.equals(appPackageName)) {
                            sendEmptyMessageDelayed(0, 2 * 60 * 1000);
                        }

                        break;
                }
            }
        };

        mHandler.sendEmptyMessage(0);
        acquireWakeLock();

        startForegroundSdkO();
        return START_STICKY;
    }

    public PowerManager.WakeLock wakeLock;//锁屏唤醒

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (pm != null) {
                wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
                if (null != wakeLock) {
                    wakeLock.acquire();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private  void startForegroundSdkO() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            android.app.NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            android.app.NotificationChannel mChannel = new android.app.NotificationChannel("10000", "护士站", android.app.NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
                android.app.Notification notification = new android.app.Notification.Builder(getApplicationContext(), "10000").build();
                startForeground(1001, notification);
            }
        }
    }
}