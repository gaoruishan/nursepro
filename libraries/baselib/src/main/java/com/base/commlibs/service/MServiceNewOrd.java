package com.base.commlibs.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

import com.base.commlibs.constant.Action;

public class MServiceNewOrd extends AliveService {

    public static final int DELAY_MILLIS = 2 * 60 * 1000;
//        public static final int DELAY_MILLIS = 2 * 1000;
    private Handler mHandler = new Handler();
    private Boolean isDestroy = false;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "心跳包检测mHandler连接状态");
//                ActivityUtils.isActivityAlive()
                switch (msg.what) {
                    case 0:
                        Intent i = new Intent();
                        i.setAction(Action.NEWMESSAGE_SERVICE);
                        sendBroadcast(i);
                        removeMessages(0);
                        if (!isDestroy) {
                            //这里想2分钟s刷新消息列表
                            sendEmptyMessageDelayed(0, DELAY_MILLIS);
                            break;
                        }
                }
            }
        };

        mHandler.sendEmptyMessage(0);
        acquireWakeLock();

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
        isDestroy = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}