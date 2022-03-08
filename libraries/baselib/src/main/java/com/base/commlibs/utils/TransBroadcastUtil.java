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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransBroadcastUtil {
    // TransBroadcastUtils实例
    private static TransBroadcastUtil mTransBroadcastUtil;
    // 程序的Context对象
    private static Context mContext;

    private static List<BroadcastListBean> broadcastList = new ArrayList<>();
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
        if (broadcastListBeans == null) {
            broadcastListBeans = new ArrayList<>();
        }
        broadcastList.addAll(broadcastListBeans);
        IntentFilter filter = new IntentFilter();
        for (int i = 0; i < broadcastList.size(); i++) {
            String action = broadcastList.get(i).getAction();
            if(!TextUtils.isEmpty(action)){
                filter.addAction(action);
            }
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
        //默认添加
        broadcastList.add(new BroadcastListBean("com.scanner.broadcast","data","成为"));
        broadcastList.add(new BroadcastListBean("dhc","value","东华"));
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
            if (scanInfo == null || "".equals(trim(scanInfo))) { // EH 2022-03-07 11:55 优博讯型号DT50传的byte
                try {
                    byte[] barcode = intent.getByteArrayExtra(scanKey);
                    String barcodeStr = new String(barcode);
                    if (barcodeStr != null && !"".equals(barcodeStr)) scanInfo = barcodeStr;
                    //Log.e("TAG","barcodeStr："+barcodeStr);
                } catch (Exception e) {}
            }
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

    /**
     * EH 2022-03-08 03:04 去回车
     * @param barcode
     * @return
     */
    public static String trim(String barcode) {
        if (barcode == null) barcode = "";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\s*|\t|\r|\n");
        java.util.regex.Matcher m = p.matcher(barcode);
        barcode = m.replaceAll("");
        return barcode;
    }
}
