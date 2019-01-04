package com.dhcc.nursepro.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.login.bean.LoginBean;

import java.util.List;
import java.util.Objects;

public class TransBroadcastUtil {
    // TransBroadcastUtils实例
    private static TransBroadcastUtil mTransBroadcastUtil;
    // 程序的Context对象
    private static Context mContext;

    private static List<LoginBean.BroadcastListBean> broadcastList;
    private static String scanAction = "";
    private static String scanKey = "";
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

    //    public static void setScanAction(String scanActionStr) {
    //        scanAction = scanActionStr;
    //        IntentFilter filter = new IntentFilter();
    //        filter.addAction(scanAction);
    //        mContext.registerReceiver(mReceiver, filter);
    //    }

    public static void setScanActionList(List<LoginBean.BroadcastListBean> broadcastListBeans) {
        broadcastList = broadcastListBeans;
        IntentFilter filter = new IntentFilter();
        for (int i = 0; i < broadcastList.size(); i++) {
            filter.addAction(broadcastList.get(i).getAction());
        }
        mContext.registerReceiver(mReceiver, filter);
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
            if (StringUtils.isEmpty(scanAction)) {
                for (int i = 0; i < broadcastList.size(); i++) {
                    if (Objects.requireNonNull(intent.getAction()).equals(broadcastList.get(i).getAction())) {
                        scanAction = broadcastList.get(i).getAction();
                        scanKey = broadcastList.get(i).getDecode();
                        Bundle bundle = intent.getExtras();
                        if (bundle != null) {
                            String scanInfo = bundle.getString(scanKey);
                            bundle.putString("data", scanInfo);
                            Intent tbIntent = new Intent();
                            tbIntent.setAction(Action.DEVICE_SCAN_CODE);
                            tbIntent.putExtras(bundle);
                            mContext.sendBroadcast(tbIntent);
                        }

                        break;
                    }
                }

            } else {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    String scanInfo = bundle.getString(scanKey);
                    bundle.putString("data", scanInfo);
                    Intent tbIntent = new Intent();
                    tbIntent.setAction(Action.DEVICE_SCAN_CODE);
                    tbIntent.putExtras(bundle);
                    mContext.sendBroadcast(tbIntent);
                }
            }
        }
    }

}
