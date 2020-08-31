package com.base.commlibs.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.service.JWebSocketClient;
import com.base.commlibs.service.JWebSocketClientService;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ServiceUtils;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @author:gaoruishan
 * @date:202020-06-17/10:30
 * @email:grs0515@163.com
 */
public class JWebSocketUtil {


    private static boolean isBind;
    private static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            JWebSocketClientService.JWebSocketClientBinder binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            JWebSocketClientService jWebSClientService = binder.getService();
            JWebSocketClient client = jWebSClientService.client;
            //发送
            if (ServiceUtils.isServiceRunning(JWebSocketClientService.class)) {
                jWebSClientService.setJWebSocketClientCallBack(new JWebSocketClientService.JWebSocketClientCallBack() {
                    @Override
                    public void onOpen() {
                        String s = "userId=" + SPStaticUtils.getString(SharedPreference.USERID);
                        s += "^deviceId=" + DeviceUtils.getAndroidID();
                        jWebSClientService.sendMsg(s);
                    }
                });
            }
            //已绑定
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
            isBind = false;
        }
    };

    public static void startService(Context context) {
        isBind = false;
        if (!UserUtil.isWebSocketFlag()) {
            return;
        }
        //检查通知权限
        AppUtil.checkNotification(context);
        //启动服务
        startJWebSClientService(context);
        //绑定服务
        bindJWebSocketClientService(context);
    }

    /**
     * 启动服务（websocket客户端服务）
     * @param mContext
     */
    private static void startJWebSClientService(Context mContext) {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
        mContext.startService(intent);
    }

    /**
     * 绑定服务
     * @param mContext
     */
    private static void bindJWebSocketClientService(Context mContext) {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        mContext.bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    public static void stopService(Context mContext) {
        if (!UserUtil.isWebSocketFlag()) {
            return;
        }
        //停止服务
        if (ServiceUtils.isServiceRunning(JWebSocketClientService.class)) {
            if (isBind) {
                Intent intent = new Intent(mContext, JWebSocketClientService.class);
                mContext.stopService(intent);
                mContext.unbindService(serviceConnection);
            }
        }
    }

}
