package com.base.commlibs.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.base.commlibs.constant.Action;

public class MServiceNewOrd extends Service {

    private Handler mHandler = new Handler();
    private Boolean isDestroy = false;

    @Override
    public void onCreate() {
        super.onCreate();


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
                        if (!isDestroy) {
                            //这里想几秒刷新一次就写几秒
                            sendEmptyMessageDelayed(0, 180000);
                            break;
                        }
                }
            }
        };

        mHandler.sendEmptyMessage(0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
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
