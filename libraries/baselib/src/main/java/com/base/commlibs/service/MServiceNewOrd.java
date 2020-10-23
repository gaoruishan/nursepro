package com.base.commlibs.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

}
