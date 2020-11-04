package com.base.commlibs.service;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;

import com.base.commlibs.constant.Action;
import com.base.commlibs.utils.AppUtil;

/**
 * @author:gaoruishan
 * @date:202020-10-23/09:55
 * @email:grs0515@163.com
 */
public class AliveService extends Service {

    public static final String ACTION_WEB_SOCKET = "com.websocket.service.content";
    public static final String MESSAGE = "message";
    public static final String TAG = "AliveService";
    private final static int GRAY_SERVICE_ID = 1001;
    //    -------------------------------------websocket心跳检测------------------------------------------------
    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔10秒进行一次对长连接的心跳检测
    private static final String CHANNEL_ID_STRING = "10000";
    private PowerManager.WakeLock wakeLock;//锁屏唤醒
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
//    private Handler mHandler = new Handler();
//    private Runnable heartBeatRunnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.e(TAG, "心跳包检测websocket连接状态");
//            onHeartBeatRunning();
//            //每隔一定的时间，对长连接进行一次心跳检测
//            mHandler.postDelayed(this, HEART_BEAT_RATE);
//        }
//    };


    public AliveService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测

        //设置service为前台服务，提高优先级
        if (Build.VERSION.SDK_INT <= 18) {
            //Android4.3以下 ，隐藏Notification上的图标
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT > 18 && Build.VERSION.SDK_INT <= 25) {
            //Android4.3 - Android7.0，隐藏Notification上的图标
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else {
            //Android7.0以上app启动后通知栏会出现一条"正在运行"的通知
//            startForeground(GRAY_SERVICE_ID, new Notification());
            startForegroundSdkO();
        }

        acquireWakeLock();
        return START_STICKY;
    }

    private void startForegroundSdkO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID_STRING, "护士站", NotificationManager.IMPORTANCE_HIGH);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
                Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
                startForeground(GRAY_SERVICE_ID, notification);
            }
        }
    }

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (pm != null) {
                wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
                if (wakeLock != null) {
                    wakeLock.acquire();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 检查锁屏状态，如果锁屏先点亮屏幕
     */
    public void checkLockPowerManager() {
        //管理锁屏的一个服务
        KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        //锁屏
        if (km!=null&&km.inKeyguardRestrictedInputMode()) {
            //获取电源管理器对象
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            if (pm!=null&&!pm.isScreenOn()) {
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                if (wl != null) {
                    wl.acquire();  //点亮屏幕
                    wl.release();  //任务结束后释放
                }
            }
        }
    }


    /**
     * 发送通知
     * @param content
     */
    private void sendNotification(String content) {
        Intent intent = new Intent(Action.MainActivity);
        AppUtil.showNotification(this, intent, "消息", content);
//        intent.setClass(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new NotificationCompat.Builder(this)
//                .setAutoCancel(true)
//                // 设置该通知优先级
//                .setPriority(Notification.PRIORITY_MAX)
//                .setSmallIcon(R.drawable.ic_launcher_logo)
//                .setContentTitle("消息")
//                .setContentText(content)
//                .setVisibility(VISIBILITY_PUBLIC)
//                .setWhen(System.currentTimeMillis())
//                // 向通知添加声音、闪灯和振动效果
//                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
//                .setContentIntent(pendingIntent)
//                .build();
//        notifyManager.notify(1, notification);//id要保证唯一
    }

    //灰色保活
    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    //用于Activity和service通讯
    public class JWebSocketClientBinder extends Binder {
        public AliveService getService() {
            return AliveService.this;
        }
    }
}
