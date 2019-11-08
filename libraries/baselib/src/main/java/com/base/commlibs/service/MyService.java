package com.base.commlibs.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.base.commlibs.R;
import com.base.commlibs.constant.Action;

public class MyService extends Service {
    private Vibrator vibrator;
    private NotificationManager notificationManager;

    private IntentFilter intentFilter;
    private DataReceiver dataReceiver = null;
    private Context context;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Toast.makeText(this, "----", Toast.LENGTH_LONG).show();
        vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(30);

        //扫描广播
        intentFilter = new IntentFilter();
        intentFilter.addAction(Action.DEVICE_SCAN_CODE);
        dataReceiver = new DataReceiver();
        registerReceiver(dataReceiver, intentFilter);
        //        showNotification(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "----", Toast.LENGTH_LONG).show();
        return null;
    }

    private void showNotification(Context context) {
        Notification notification = new NotificationCompat.Builder(context)
                /**设置通知左边的大图标**/
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                /**设置通知右边的小图标**/
                .setSmallIcon(R.mipmap.ic_launcher)
                /**通知首次出现在通知栏，带上升动画效果的**/
                .setTicker("通知来了")
                /**设置通知的标题**/
                .setContentTitle("这是一个通知的标题")
                /**设置通知的内容**/
                .setContentText("这是一个通知的内容这是一个通知的内容")
                /**通知产生的时间，会在通知信息里显示**/
                .setWhen(System.currentTimeMillis())
                /**设置该通知优先级**/
                .setPriority(Notification.PRIORITY_DEFAULT)
                /**设置这个标志当用户单击面板就可以让通知将自动取消**/
                .setAutoCancel(true)
                /**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**/
                .setOngoing(false)
                /**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**/
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)

                .setContentIntent(PendingIntent.getActivity(context, 1, new Intent(Action.MainActivity), PendingIntent.FLAG_CANCEL_CURRENT))
                .build();
        //        if (LoginUser.SoundF == true)
        notification.defaults |= Notification.DEFAULT_SOUND;
        //        if (LoginUser.VibrateF == true)
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        //        if (LoginUser.LigthF == true)
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.flags |= Notification.FLAG_INSISTENT;
        notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        /**发起通知**/
        notificationManager.notify(1, notification);
    }

    //扫描腕带获取regNo、wardId
    public class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Action.DEVICE_SCAN_CODE)) {
                Bundle bundle = new Bundle();
                bundle = intent.getExtras();
                //                getUserMsg(bundle.getString("data"));
                showNotification(context);
            }
        }
    }
}
