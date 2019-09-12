package com.base.commlibs.utils;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.R;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

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
}
