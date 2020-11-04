package com.base.commlibs.comm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.service.MServiceNewOrd;
import com.blankj.utilcode.util.ServiceUtils;

/**
 * @author:gaoruishan
 * @date:202020-11-03/17:39
 * @email:grs0515@163.com
 */
public class BaseCommActivity extends BaseActivity {

    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startMServiceNewOrd();

    }

    private void startMServiceNewOrd() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "服务与活动成功绑定");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.e(TAG, "服务与活动成功断开");
            }
        };

        Intent i = new Intent(this, MServiceNewOrd.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android8.0以上通过startForegroundService启动service
            startForegroundService(i);
        } else {
            startService(i);
        }
        Intent bindIntent = new Intent(this, MServiceNewOrd.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        stopMServiceNewOrd();
        super.onDestroy();
    }

    private void stopMServiceNewOrd() {
        Intent i = new Intent(this, MServiceNewOrd.class);
        stopService(i);

        if (serviceConnection != null && ServiceUtils.isServiceRunning(MServiceNewOrd.class)) {
            unbindService(serviceConnection);
        }
    }
}
