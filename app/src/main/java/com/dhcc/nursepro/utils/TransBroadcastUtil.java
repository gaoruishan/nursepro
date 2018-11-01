package com.dhcc.nursepro.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.dhcc.nursepro.constant.Action;

import java.util.List;
import java.util.Objects;

public class TransBroadcastUtil {
    // TransBroadcastUtils实例
    private static TransBroadcastUtil mTransBroadcastUtil;
    // 程序的Context对象
    private static Context mContext;

    private static String scanAction = "";
    private static IntentFilter filter;
    private static Receiver mReceiver = new Receiver();

    public TransBroadcastUtil() {
    }

    /**
     * 获取TransBroadcastUtil实例
     *
     * @return
     */
    public static TransBroadcastUtil getInstance() {
        if (mTransBroadcastUtil == null) {
            mTransBroadcastUtil = new TransBroadcastUtil();
        }
        return mTransBroadcastUtil;
    }

    public static void setScanAction(String scanActionStr) {
        scanAction = scanActionStr;
        filter = new IntentFilter();
        filter.addAction(scanAction);
        mContext.registerReceiver(mReceiver, filter);
    }

    public static void setScanAction(List<String> scanActionStr) {
//        scanAction = scanActionStr;
//        filter = new IntentFilter();
//        filter.addAction(scanAction);
//        mContext.registerReceiver(mReceiver, filter);
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static void unreg(Context context) {
        mContext.unregisterReceiver(mReceiver);
    }



    private static class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.requireNonNull(intent.getAction()).equals(scanAction)) {
                Intent tbIntent = new Intent();
                tbIntent.setAction(Action.DEVICE_SCAN_CODE);
                tbIntent.putExtras(Objects.requireNonNull(intent.getExtras()));
                mContext.sendBroadcast(tbIntent);
            }
        }
    }

}
