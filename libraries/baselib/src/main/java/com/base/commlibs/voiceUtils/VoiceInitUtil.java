package com.base.commlibs.voiceUtils;

import android.content.Context;
import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.raisound.speech.SpeechConfig;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.http.callback.RequestCallback;
import com.raisound.speech.http.response.Scene;
import com.raisound.speech.listener.SceneListResponse;

import java.util.List;

/**
 * com.base.commlibs.voiceUtils
 * <p>
 * author Q
 * Date: 2021/6/15
 * Time:10:55
 */
public class VoiceInitUtil {
    public static void initSound(Context context){
//初始化转写SDK appid、apiKey、apiSecret需要通过授权申请，实际项目中需更换为您所申请的appid、apiKey、apiSecret
        SpeechRecognizerManager.initialize("","Hj8BpLQ6","nDpxrJYxMeiRgEEk3FXgey9dNyJHvGbV");
        //创建SpeechConfig对象，初始化参数在此设置
        SpeechConfig speechConfig=new SpeechConfig("","Hj8BpLQ6","nDpxrJYxMeiRgEEk3FXgey9dNyJHvGbV");
        //设置鉴权服务器地址，可选，不设置使用默认公有云服务器
        if (SPUtils.getInstance().getString(SharedPreference.VOICE_IP).isEmpty()){
            SPUtils.getInstance().put(SharedPreference.VOICE_IP,"111.230.139.145");
        }
        if (SPUtils.getInstance().getString(SharedPreference.VOICE_PORT).isEmpty()){
            SPUtils.getInstance().put(SharedPreference.VOICE_PORT,"3392");
        }
        String voicIp = SPUtils.getInstance().getString(SharedPreference.VOICE_IP);
        String voicPort = SPUtils.getInstance().getString(SharedPreference.VOICE_PORT);
        speechConfig.setApiServer("http://"+voicIp, Integer.parseInt(voicPort));
//        speechConfig.setApiServer("http://218.17.161.34",3392);
        //设置转写服务器地址，可选，不设置使用默认公有云服务器
//        speechConfig.setAsrServer("111.230.139.145",6000);
        speechConfig.setAsrServer(voicIp,6000);
        //设置平台id
        speechConfig.setPlatform("1");
        //是否一体化管理服务，设置为true后，只需要设置apiServer即可，转写服务器设置将失效
        speechConfig.setAutomation(true);
        //是否打开SDK日志输出
        //SpeechRecognizerManager.isDebug(true);
        //初始化SDK
        SpeechRecognizerManager.initialize(speechConfig, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(int i, Boolean aBoolean) {
                SpeechRecognizerManager.with(context)
                        .puncFlag(true)
                        //构建SpeechRecognizer对象
                        .build();
                SpeechRecognizerManager.getSceneList(new SceneListResponse() {
                    @Override
                    public void onResponse(List<Scene> list, int i, String s) {
                        if (list.size()>0){
                            SharedPreference.SCENE_LIST = list;
                            Log.e("scenejson", GsonUtils.toJson(list)+"");
                        }
                    }
                });


            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtils.showLong("soundinitfail"+i+"--"+s);
            }
        });

    }
}
