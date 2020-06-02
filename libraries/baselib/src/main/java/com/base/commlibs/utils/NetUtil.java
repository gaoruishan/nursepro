package com.base.commlibs.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.BaseApplication;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPStaticUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 保存网络状态(每秒)
 * @author:gaoruishan
 * @date:202020-05-29/14:26
 * @email:grs0515@163.com
 */
public class NetUtil {
    public static final String TAG = "NetUtil";
    private static final String TOP_CORNER = "┌";
    private static final String MIDDLE_CORNER = "├";
    private static final String LEFT_BORDER = "│ ";
    private static final String BOTTOM_CORNER = "└";
    private static final String SIDE_DIVIDER =
            "────────────────────────────────────────────────────────";
    private static final String MIDDLE_DIVIDER =
            "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String TOP_BORDER = TOP_CORNER + SIDE_DIVIDER + SIDE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + MIDDLE_DIVIDER + MIDDLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_CORNER + SIDE_DIVIDER + SIDE_DIVIDER;
    private static final Format FORMAT = new SimpleDateFormat("yyyy-MM-dd HH-mm");
    private static final Format FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    private static final int APP_VERSION_CODE = AppUtils.getAppVersionCode();
    //每0.5秒 间隔
    private static final long TIME_PERIOD = 1000;
    private static long mStartActionTime;
    private static Timer mTimer;
    private static String commLog;
    private static int numberOfLevels = 5;


    public static void requestPermission(Activity activity) {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_WIFI_STATE
                , Manifest.permission.CHANGE_NETWORK_STATE
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.ACCESS_NETWORK_STATE
                , Manifest.permission.MODIFY_PHONE_STATE};

        for (int j = 0; j < permissions.length; j++) {
            int i = ContextCompat.checkSelfPermission(activity, permissions[j]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(activity, permissions, 321);
            }
        }
    }

    public static void startTimer() {
        //判断
        if(TextUtils.isEmpty(SPStaticUtils.getString(SharedPreference.NET_LOG))){
        	return;
        }

        mTimer = new Timer();
        MyTimerTask mTimerTask = new MyTimerTask();
        // 初始化上次操作时间为登录成功的时间
        mStartActionTime = System.currentTimeMillis();
        commLog = getCommLog();
        saveLog(commLog);
        // 每过1s检查一次
        mTimer.schedule(mTimerTask, TIME_PERIOD, TIME_PERIOD);

    }

    private static String getCommLog() {

        final String time = FORMAT_1.format(new Date(System.currentTimeMillis()));
        final StringBuilder sb = new StringBuilder();
        final String head = SIDE_DIVIDER + SIDE_DIVIDER + "\n"
                + LEFT_BORDER + "日期时间: " + time
                + LEFT_BORDER + "网络状态: " + NetworkUtils.isConnected()
                + LEFT_BORDER + "网络类型: " + NetworkUtils.getNetworkType()
                + LEFT_BORDER + "是否移动网络: " + NetworkUtils.isMobileData()
                + LEFT_BORDER + "是否移动WIFI: " + NetworkUtils.isWifiConnected()
                + LEFT_BORDER + "WIFI强度: " + getLevel()
                + "\n";
        sb.append(head);

        return sb.toString();
    }

    private static int getLevel() {
        int level = 0;
        WifiManager wifiManager = (WifiManager) BaseApplication.getApp().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int rssi = wifiInfo.getRssi();
        if (rssi != 0) {
            level = WifiManager.calculateSignalLevel(rssi, numberOfLevels);
        }
        return level;
    }

    private static void saveLog(String obj) {
        if (LocalTestManager.isLogFlag()) {
            String date = FORMAT.format(new Date(System.currentTimeMillis()));
            String finalName = "net/v" + APP_VERSION_CODE + "_" + date;
            String dhc = CommFile.ROOT_PATH + finalName;
            if (FileUtils.isFileExists(dhc)) {
                Log.e(TAG, "(NetUtil.java:63) read");
                CommFile.read(finalName, new SimpleCallBack<String>() {
                    @Override
                    public void call(String result, int type) {
                        CommFile.delete(dhc);
                        CommFile.write(finalName, result + obj);
                    }
                });
            } else {
                Log.e(TAG, "(NetUtil.java:63) write");
                CommFile.write(finalName, obj);
            }
        }
    }

    public static void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public static void getMobileDbm() {
        int dbm = -1;
        TelephonyManager tm = (TelephonyManager) BaseApplication.getApp().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = tm.getAllCellInfo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (null != cellInfoList) {
                for (CellInfo cellInfo : cellInfoList) {
                    if (cellInfo instanceof CellInfoGsm) {
                        CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthGsm.getDbm();
                        Log.e("66666", "cellSignalStrengthGsm" + cellSignalStrengthGsm.toString());
                    } else if (cellInfo instanceof CellInfoCdma) {
                        CellSignalStrengthCdma cellSignalStrengthCdma = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthCdma.getDbm();
                        Log.e("66666", "cellSignalStrengthCdma" + cellSignalStrengthCdma.toString());
                    } else if (cellInfo instanceof CellInfoWcdma) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            CellSignalStrengthWcdma cellSignalStrengthWcdma = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthWcdma.getDbm();
                            Log.e("66666", "cellSignalStrengthWcdma" + cellSignalStrengthWcdma.toString());
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                        dbm = cellSignalStrengthLte.getDbm();
                        Log.e("66666", "cellSignalStrengthLte.getAsuLevel()\t" + cellSignalStrengthLte.getAsuLevel());
                        Log.e("66666", "cellSignalStrengthLte.getCqi()\t" + cellSignalStrengthLte.getCqi());
                        Log.e("66666", "cellSignalStrengthLte.getDbm()\t " + cellSignalStrengthLte.getDbm());
                        Log.e("66666", "cellSignalStrengthLte.getLevel()\t " + cellSignalStrengthLte.getLevel());
                        Log.e("66666", "cellSignalStrengthLte.getRsrp()\t " + cellSignalStrengthLte.getRsrp());
                        Log.e("66666", "cellSignalStrengthLte.getRsrq()\t " + cellSignalStrengthLte.getRsrq());
                        Log.e("66666", "cellSignalStrengthLte.getRssnr()\t " + cellSignalStrengthLte.getRssnr());
                        Log.e("66666", "cellSignalStrengthLte.getTimingAdvance()\t " + cellSignalStrengthLte.getTimingAdvance());
                    }
                }
            }
        }
    }

    private static class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            LogUtils.d();
            Log.e(TAG, "(MyTimerTask.java:57) " + (System.currentTimeMillis() - mStartActionTime));
            commLog += getCommLog();
            //10秒保存一次
            if (System.currentTimeMillis() - mStartActionTime > 10 * 1000) {
                saveLog(commLog);
                mStartActionTime = System.currentTimeMillis();
                commLog = "";
            }
        }

    }
}
