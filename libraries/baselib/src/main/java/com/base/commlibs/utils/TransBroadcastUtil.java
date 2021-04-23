package com.base.commlibs.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.constant.Action;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;
import java.util.Objects;

public class TransBroadcastUtil {
    // TransBroadcastUtils实例
    private static TransBroadcastUtil mTransBroadcastUtil;
    // 程序的Context对象
    private static Context mContext;

    private static List<BroadcastListBean> broadcastList;
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

    /**
     * LoginActivity中设置广播码
     * @param broadcastListBeans
     */
    public static void setScanActionList(List<BroadcastListBean> broadcastListBeans) {
        broadcastList = broadcastListBeans;
        IntentFilter filter = new IntentFilter();
        for (int i = 0; i < broadcastList.size(); i++) {
            filter.addAction(broadcastList.get(i).getAction());
        }
        mContext.registerReceiver(mReceiver, filter);
        Log.e("TAG","(TransBroadcastUtil.java:55) setScanActionList");
    }

    /**
     * Application注册
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
    }

    /**
     * Application取消注册
     * @param context
     */
    public static void unreg(Context context) {
        mContext.unregisterReceiver(mReceiver);
    }


    private static class Receiver extends BroadcastReceiver {
        @SuppressLint("NewApi")
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("TAG","(BaseReceiver.java:909) 广播码"+intent.toString());
            if (StringUtils.isEmpty(scanAction)) {
                for (int i = 0; broadcastList!=null&&i < broadcastList.size(); i++) {
                    if (Objects.requireNonNull(intent.getAction()).equals(broadcastList.get(i).getAction())) {
                        scanAction = broadcastList.get(i).getAction();
                        scanKey = broadcastList.get(i).getDecode();
                        sendBroadcastIntent(intent);

                        break;
                    }
                }

            } else {
                sendBroadcastIntent(intent);
            }
        }
    }

    /**
     * 发送广播数据
     * @param intent
     */
    protected static void sendBroadcastIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String scanInfo = bundle.getString(scanKey);
            //判空
            if(!TextUtils.isEmpty(scanInfo)){
                bundle.putString("data", scanInfo);
                Intent tbIntent = new Intent();
                tbIntent.setAction(Action.DEVICE_SCAN_CODE);
                tbIntent.putExtras(bundle);
                mContext.sendBroadcast(tbIntent);
            }
        }
    }

}
