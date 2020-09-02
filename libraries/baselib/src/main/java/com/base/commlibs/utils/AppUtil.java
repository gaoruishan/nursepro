package com.base.commlibs.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.R;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * app配置工具类
 * @author:gaoruishan
 * @date:202019-06-22/14:33
 * @email:grs0515@163.com
 */
public class AppUtil {
    private static Map<Integer, Integer> soundIDMap;
    private static SoundPool sSoundPool;
    private static String TAG = AppUtil.class.getSimpleName();

    /**
     * 获取当前Fragment名称
     * @return
     */
    public static String getCurActivityFragmentName() {
        //获取当前Activity的Fragment
        Fragment fragment = getCurActivityFragment();
        if (fragment != null) {
            return fragment.getClass().getName();
        }
        return "";
    }

    /**
     * 获取当前Fragment
     * @return
     */
    public static Fragment getCurActivityFragment() {
        //获取当前Activity的Fragment
        Activity activity = ActivityUtils.getTopActivity();
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            List<Fragment> fragments = appCompatActivity.getSupportFragmentManager().getFragments();
            if (fragments.size() > 0) {
               return fragments.get(0);
            }
        }
        return null;
    }

    /**
     * 是否支持多种扫描方式
     * @return
     */
    public static boolean isMultiScan() {
        return SPUtils.getInstance().getBoolean(SharedPreference.MUlTISCAN, false);
    }

    /**
     * 获取科室窗口
     * @return
     */
    public static String getWindowName() {
        String windName = SPUtils.getInstance().getString(SharedPreference.WINDOWNAME);
        if (TextUtils.isEmpty(windName)) {//如果为空从WINSLISTJSON中获取
            String LOCDESC = SPUtils.getInstance().getString(SharedPreference.LOCDESC);
            java.lang.reflect.Type typeWin = new TypeToken<HashMap<String, List>>() {
            }.getType();
            String WinJson = SPUtils.getInstance().getString(SharedPreference.WINSLISTJSON);
            if (!TextUtils.isEmpty(WinJson)) {
                Map<String, List> mapWins = new Gson().fromJson(WinJson, typeWin);
                //默认第一个窗口
                return mapWins.get(LOCDESC).get(0).toString();
            }
        }
        return windName;
    }
    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    public static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }
    /**
     * 警告-初始化
     * @param context
     * @param resIds
     */
    public static void initPlay(Context context, int... resIds) {
        //提示音集合
        SoundPool soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        soundIDMap = new HashMap<>();
        for (int resId : resIds) {
            int soundID;
            if (resId != 0) {
                soundID = soundPool.load(context, resId, 1);
            } else {
                resId = R.raw.notice_warn_1;
                soundID = soundPool.load(context, resId, 1);
            }
            soundIDMap.put(resId, soundID);
        }
        sSoundPool = soundPool;
    }

    /**
     * 警告-播放
     * @param context
     * @param resId
     */
    public static void playSound(Context context, int resId) {
        if (soundIDMap==null) return;

        AudioManager mgr = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;
        if (resId == 0) {
            resId = R.raw.notice_warn_1;
        }
        int sSoundID = soundIDMap.get(resId);
        if (sSoundPool != null && sSoundID != 0) {
            //参数：1、soundID值   2、当前音量     3、最大音量  4、优先级   5、重播次数   6、播放速度
            sSoundPool.play(sSoundID, volume, volume, 1, 0, 1f);
        } else {
            Log.e(TAG, "请在onCreated 调用initPlay() 方法");
        }

    }
    public static int search(String str, String strRes) {//查找字符串里与指定字符串相同的个数
        int n = 0;
        while (str.indexOf(strRes) != -1) {
            int i = str.indexOf(strRes);
            n++;
            str = str.substring(i + 1);
        }
        return n;
    }
    public static boolean isIP(String addr) {

        //判断"."的个数，大于3个返回错误
        int pointNum = search(addr, ".");
        if (pointNum > 3) {
            return false;
        }

        String ipStr = addr;
        if (addr.contains(":")) {

            //判断":"个数，大于1个返回错误
            int containNum = search(addr, ":");
            if (containNum > 1) {
                return false;
            }

            //判断":"后面的内容，空的话返回错误，有数字外其他字符也返回错误
            String lastStr = addr.substring(addr.indexOf(":") + 1);
            if (lastStr.contains(".") || lastStr.length() < 1) {
                return false;
            }
            ipStr = addr.substring(0, addr.indexOf(":"));
        }

        if (ipStr.length() < 7 || ipStr.length() > 15 || "".equals(ipStr)) {
            return false;
        }
        // 判断IP格式是否规范
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ipStr);
        boolean ipAddress = mat.find();
        //============对之前的ip判断的bug在进行判断
        if (ipAddress) {
            String[] ips = ipStr.split("\\.");
            if (ips.length == 4) {
                try {
                    for (String ip : ips) {
                        if (Integer.parseInt(ip) < 0 || Integer.parseInt(ip) > 255) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 检测是否开启通知
     *
     * @param context
     */
    public static void checkNotification(final Context context) {
        if (!isNotificationEnabled(context)) {
            new AlertDialog.Builder(context).setTitle("温馨提示")
                    .setMessage("你还未开启系统通知，将影响消息的接收，要去开启吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNotification(context);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }
    /**
     * 如果没有开启通知，跳转至设置界面
     *
     * @param context
     */
    private static void setNotification(Context context) {
        Intent localIntent = new Intent();
        //直接跳转到应用通知设置的代码：
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.putExtra("app_package", context.getPackageName());
            localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
            localIntent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(localIntent);
    }

    /**
     * 获取通知权限,监测是否开启了系统通知
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static boolean isNotificationEnabled(Context context) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return true;
        }
        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showNotification(Context context,Intent intent) {
        showNotification(context,intent,"新消息","点击即可查看");
    }

    /**
     * 消息通知栏
     * @param context
     * @param intent
     */
    public static void showNotification(Context context,Intent intent,String title,String content) {
        Boolean bLight = SPUtils.getInstance().getBoolean(SharedPreference.LIGHT, true);
        Boolean bSound = SPUtils.getInstance().getBoolean(SharedPreference.SOUND, true);
        Boolean bVibrator = SPUtils.getInstance().getBoolean(SharedPreference.VIBRATOR, true);
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL = context.getPackageName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notifyChannel = new NotificationChannel(NOTIFICATION_CHANNEL, "infusion",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notifyChannel.setLightColor(Color.GREEN);
            notifyChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notifyChannel);
        }

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, NOTIFICATION_CHANNEL);
        } else {
            builder = new Notification.Builder(context);
        }
        //Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        intent.putExtra("ClickPendingIntent", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        /**设置通知左边的大图标**/
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_logo))
                /**设置通知右边的小图标**/
                .setSmallIcon(R.drawable.ic_launcher_logo)
                /**通知首次出现在通知栏，带上升动画效果的**/
                .setTicker("通知")
                /**设置通知的标题**/
                .setContentTitle(title)
                /**设置通知的内容**/
                .setContentText(content)
                /**通知产生的时间，会在通知信息里显示**/
                .setWhen(System.currentTimeMillis())
                /**设置该通知优先级**/
                .setPriority(Notification.PRIORITY_DEFAULT)
                /**设置这个标志当用户单击面板就可以让通知将自动取消**/
                .setAutoCancel(true)
                /**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**/
                .setOngoing(false)
                /**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**/
//                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent)
                .build();
        //        if (LoginUser.SoundF == true)
//        builder.setDefaults(Notification.DEFAULT_VIBRATE |Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS);
        Notification notification = builder.getNotification();
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        //        if (LoginUser.VibrateF == true)
        if (bVibrator) {
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (bSound) {
            notification.defaults |= Notification.DEFAULT_SOUND;
        }
        if (bLight) {
            notification.defaults |= Notification.DEFAULT_LIGHTS;
        }

//        //        if (LoginUser.LigthF == true)
//        notification.flags |= Notification.FLAG_INSISTENT;
        /**发起通知**/
        notificationManager.notify(1, notification);
    }

}
