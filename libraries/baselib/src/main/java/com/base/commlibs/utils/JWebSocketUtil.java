package com.base.commlibs.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.base.commlibs.service.JWebSocketClientService;
import com.blankj.utilcode.util.ServiceUtils;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @author:gaoruishan
 * @date:202020-06-17/10:30
 * @email:grs0515@163.com
 */
public class JWebSocketUtil {


    public static void startService(Context context) {
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

    public static void stopService(Context mContext) {
        if (!UserUtil.isWebSocketFlag()) {
            return;
        }
        //停止服务
        if (ServiceUtils.isServiceRunning(JWebSocketClientService.class)) {
            Intent intent = new Intent(mContext, JWebSocketClientService.class);
            mContext.stopService(intent);
            mContext.unbindService(serviceConnection);
        }
    }

    private  static ServiceConnection  serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
//            JWebSocketClientService.JWebSocketClientBinder binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
//            jWebSClientService = binder.getService();
//            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };

    /**
     * 绑定服务
     * @param mContext
     */
    private static void bindJWebSocketClientService(Context mContext) {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        mContext.bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }
    /**
     * 启动服务（websocket客户端服务）
     * @param mContext
     */
    private static void startJWebSClientService(Context mContext) {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
        mContext.startService(intent);
    }
}
