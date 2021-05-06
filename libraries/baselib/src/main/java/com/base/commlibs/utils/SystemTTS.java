package com.base.commlibs.utils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPStaticUtils;

import java.util.Locale;

/**
 * 语音播放的一个单例对象
 */
public class SystemTTS {

    //单例对象
    private static SystemTTS singleton;
    //context对象
    private Context mContext;
    //核心播放对象
    private TextToSpeech textToSpeech;
    //是否支持
    private boolean isSupport = true;

    private SystemTTS(Context context) {
        this.mContext = context.getApplicationContext();
        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                //textToSpeech的配置
                init(i);
            }
        });
    }


    public static SystemTTS getInstance(Context context) {
        if (singleton == null) {
            synchronized (SystemTTS.class) {
                if (singleton == null) {
                    singleton = new SystemTTS(context);
                }
            }
        }
        return singleton;
    }

    //textToSpeech的配置
    private void init(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.ENGLISH);
            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            textToSpeech.setPitch(2.0f);
            textToSpeech.setSpeechRate(3.0f);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //系统不支持中文播报
                isSupport = false;
            }
        }
    }


    public boolean play(String text) {
        if (!isSupport || TextUtils.isEmpty(text)) {
//            Toast.makeText(mContext, "暂不支持", Toast.LENGTH_SHORT).show();
            return false;
        }
        //语音开关
        String voiceFlag = SPStaticUtils.getString(SharedPreference.voiceFlag);
        if(TextUtils.isEmpty(voiceFlag)){
            return false;
        }
        if (textToSpeech != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null);
            }
        }
        return true;
    }


    private void stop() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    /**
     * 在onDestroy()方法调用
     */
    public void destroy() {
        stop();
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
        singleton = null;
    }


}