package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.NoteBean;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;
import com.raisound.speech.AsrResult;
import com.raisound.speech.SpeechError;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.enums.AudioSource;
import com.raisound.speech.http.callback.RequestCallback;
import com.raisound.speech.listener.RecognizerListener;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static com.blankj.utilcode.util.ViewUtils.runOnUiThread;


/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SendVoiceMsgDialog extends Dialog {
    private Context context;
    SPUtils sp = SPUtils.getInstance();

    TextView tvVoiceMsg;
    Button btnSpeech,btnSend;
    ImageView imgRate;
    private String toUserCode,title;
    private TextView tvTitle;

    private boolean bSaved=false;//判断是否点击过一次保存
    public void setTitle(String title) {
        this.title = title;
        if (tvTitle!=null){
            tvTitle.setText(title);
        }
    }

    public void setToUserCode(String toUserCode) {
        this.toUserCode = toUserCode;
    }

    public SendVoiceMsgDialog(Context context) {
        super(context, com.base.commlibs.R.style.MyDialog2);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_send_dialog);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
    }

    private void initView() {

        tvVoiceMsg = findViewById(R.id.tv_voice_msg);
        btnSend = findViewById(R.id.btn_send);
        btnSpeech = findViewById(R.id.btn_speec);
        imgRate = findViewById(R.id.img_voice_rate);
        initVoice();
        btnSpeech.setOnClickListener(v -> changeSpeech());
        btnSend.setOnClickListener(v -> finishRecord());
        btnSpeech.setSelected(true);
        startTimer();
        findViewById(R.id.view_cancle).setOnClickListener(v -> dismiss());
//        btnSpeech.performClick();

        tvTitle = findViewById(R.id.textView1);
        if (title!=null){
            tvTitle.setText(title);
        }
    }

    public void changeSpeech(){
//        if (btnSpeech.getText().toString().equals("暂停")){
//            btnSpeech.setText("继续");
//            stopSpeech();
//        }else {
//            btnSpeech.setText("暂停");
//            initVoice();
//        }
        if (btnSpeech.isSelected()){
            btnSpeech.setSelected(false);
            stopSpeech();
            stopTimer();
        }else {
            btnSpeech.setSelected(true);
            initVoice();
            startTimer();
        }
    }




    public String meetingId = SPUtils.getInstance().getString(SharedPreference.USERID)+System.currentTimeMillis();
    public String fileId = "";
    public Boolean voiceStart = false;
    private void initVoice(){
        stopByNoVoice = false;
        time = System.currentTimeMillis();
//        SpeechRecognizerManager.stopNow();
        SpeechRecognizerManager.with(context)
                .scene(getScene())
                //设置回调方法
                .puncFlag(true)
                .listener(listener)
                //构建SpeechRecognizer对象
//                .audioSource(AudioSource.COMMUNICATION)
                .build();
        SpeechRecognizerManager.switchScene(getScene());
        if (!voiceStart){
            SpeechRecognizerManager.startRecord(meetingId, new RequestCallback() {
                @Override
                public void onSuccess(int i, Object o) {
                    fileId=o.toString();
                    Log.e(TAG,meetingId+"startRecord fileId:"+fileId);
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.e(TAG,meetingId+"startRecord error:"+s);
                }
            });
            voiceStart=true;
        }




        SpeechRecognizerManager.start(meetingId);
    }
    public String getScene(){
        String speechScene = "donghua_1_1";
        if (SharedPreference.SCENE_LIST!=null){
            if (SharedPreference.SCENE_LIST.size()<1){
                ToastUtils.showLong("未获取场景");
            }else {
                speechScene = SharedPreference.SCENE_LIST.get(0).getSceneId();
                for (int i = 0; i < SharedPreference.SCENE_LIST.size(); i++) {
                    if ("转写场景".equals(SharedPreference.SCENE_LIST.get(i).getSceneName())){
                        speechScene = SharedPreference.SCENE_LIST.get(i).getSceneId();
                    }
                }
            }
        }else {
            ToastUtils.showLong("未获取场景");
        }
        return speechScene;
    }


    public void stopSpeech(){

        SpeechRecognizerManager.stop();
    }

    public void finishRecord(){
        btnSend.setClickable(false);
        btnSpeech.setClickable(false);
        if (bSaved){
            saveSound();
            Log.e(TAG, "finishRecord: "+bSaved );
        }else {
            Log.e(TAG, "finishRecord: "+bSaved );
            tvTitle.setText("正在发送留言");
            bSaved=true;
            btnSpeech.setSelected(false);
            stopTimer();
            SpeechRecognizerManager.stop();
            btnSend.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SpeechRecognizerManager.finishRecord(fileId, new RequestCallback() {
                        @Override
                        public void onSuccess(int i, Object o) {
                            Log.e(TAG+"mp3","finishRecord fileId:"+fileId+"____"+o.toString());
                            saveSound();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.e(TAG+"mp3","finishRecord error:"+s);
                            ToastUtils.showLong("录音失败");
                        }
                    });

                }
            }, 1000);

        }




    }
    private Boolean bVoiceSaved=false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    removeMessages(0);
                    saveSound();
                    break;
            }
        }
    };
    private void saveSound() {
        btnSend.postDelayed(new Runnable() {
            @Override
            public void run() {

                SpeechRecognizerManager.getRecordFile(fileId, "wav", new RequestCallback() {
                    @Override
                    public void onSuccess(int i, Object o) {
                        Log.e(TAG, "onSuccesswav: 111111111"+o);
                        HashMap<String ,String > map = new HashMap<>();
                        map.put("Context","来自"+sp.getString(SharedPreference.USERNAME)+"的语音留言");
//        map.put("soundId",fileId);
                        map.put("MsgFromUserCode",sp.getString(SharedPreference.USERCODE));
                        map.put("MsgToUserCode", toUserCode);
                        map.put("MsgAddress",o.toString());
                        WebApiManager.recYYMessage(map, new CommonCallBack<NoteBean>() {
                            @Override
                            public void onFail(String code, String msg) {
                                ToastUtils.showLong("发送失败");
                            }

                            @Override
                            public void onSuccess(NoteBean bean, String type) {
                                ToastUtils.showLong("发送成功");
                                bVoiceSaved = true;
                                dismiss();
                            }
                        });
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.e(TAG, fileId+"onSuccess: 1111111112mp3"+s);
                        ToastUtils.showLong(s);
                        btnSend.setClickable(true);
                        tvVoiceMsg.setText("保存失败，请重试");
                    }
                });




//                SpeechRecognizerManager.getRecordMp3(fileId, new RequestCallback<String>() {
//                    @Override
//                    public void onSuccess(int i, String o) {
//                        Log.e(TAG, "onSuccessmp3: 111111111"+o);
//                        HashMap<String ,String > map = new HashMap<>();
//                        map.put("Context","来自"+sp.getString(SharedPreference.USERNAME)+"的语音留言");
////        map.put("soundId",fileId);
//                        map.put("MsgFromUserCode",sp.getString(SharedPreference.USERCODE));
//                        map.put("MsgToUserCode", toUserCode);
//                        map.put("MsgAddress",o);
//                        WebApiManager.recYYMessage(map, new CommonCallBack<NoteBean>() {
//                            @Override
//                            public void onFail(String code, String msg) {
//                                ToastUtils.showLong("发送失败");
//                            }
//
//                            @Override
//                            public void onSuccess(NoteBean bean, String type) {
//                                ToastUtils.showLong("发送成功");
//                                bVoiceSaved = true;
//                                dismiss();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                        Log.e(TAG, fileId+"onSuccess: 1111111112mp3"+s);
//                        ToastUtils.showLong(s);
//                        btnSend.setClickable(true);
//                        tvVoiceMsg.setText("保存失败，请重试");
////                if (!bVoiceSaved){
////                    handler.sendEmptyMessageDelayed(0, 5000);
////                }
//
//                    }
//                });

            }
        }, 3000);



    }


    private  Timer mTimer; // 计时器，每1秒执行一次任务
    private  MyTimerTask mTimerTask; // 计时任务，判断是否未操作时间到达5s
    // 登录成功，开始计时
    private  void startTimer() {
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        // 每过1s检查一次
        mTimer.schedule(mTimerTask, 0, 1000);
        //        Log.e("nurseprotime", "start timer");
    }

    // 停止计时任务
    private void stopTimer() {
        mTimer.cancel();
        //        Log.e("nurseprotime", "cancel timer");
    }
    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            voiceTime++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvVoiceMsg.setText("录音时间："+voiceTime+"秒");
                }
            });

        }
    }

    int voiceTime = 0;
    String TAG = "sendVoiceMsgDialog1111";
    Long time = System.currentTimeMillis();
    Boolean stopByNoVoice=false;
    RecognizerListener listener = new RecognizerListener() {
        @Override
        public void onRecordData(byte[] bytes) {

        }

        @Override
        public void onVolumeChanged(int i) {
            //返回音量变化，自定义界面展示录音动画
//            Log.d(TAG, "onVolumeChanged:" + i);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    if (!stopByNoVoice){
//                        tvVoiceMsg.setText("实时音量:"+i);
                        setImgRate(i);
                        Log.e(TAG, "onVolumeChanged: "+i);
                        if (i>300){
                            time = System.currentTimeMillis();
                        }else {
                            if (System.currentTimeMillis()-time>5000){

                                Log.e(TAG, "onVolumeChanged: timeout" );
//                                tvVoiceMsg.setText("5秒静音了，暂停");
                                btnSpeech.performClick();
                                stopByNoVoice = true;
                            }
                        }
                    }

                }
            });


        }

        @Override
        public void onBeginOfSpeech() {
            Log.i(TAG, "开始录音");
        }

        @Override
        public void onEndOfSpeech() {
            Log.i(TAG+"json5", "录音结束");

        }

        @Override
        public void onResult(AsrResult asrResult, boolean isLast) {
            Log.e(TAG, "onResult: "+asrResult.jsonResult );
        }

        @Override
        public void onError(final SpeechError speechError) {
            Log.e(TAG, "onError: " );
            //SpeechError speechError.getErrorCode() 错误码请参考接入文档
            Message message = new Message();
            message.what = 99;
            message.obj = speechError.getErrorDescription();
            SpeechRecognizerManager.stop();
            return;
        }
    };

    public void setImgRate(int i){
        if (i<300){
            imgRate.setImageResource(R.mipmap.icon_voicerate_0);
        }else if (i<1000){
            imgRate.setImageResource(R.mipmap.icon_voicerate_1);
        }else if (i<2200){
            imgRate.setImageResource(R.mipmap.icon_voicerate_2);
        }else if (i<3300){
            imgRate.setImageResource(R.mipmap.icon_voicerate_3);
        }else if (i<4400){
            imgRate.setImageResource(R.mipmap.icon_voicerate_4);
        }else if (i<5500){
            imgRate.setImageResource(R.mipmap.icon_voicerate_5);
        }else if (i<6600){
            imgRate.setImageResource(R.mipmap.icon_voicerate_6);
        }else if (i<7700){
            imgRate.setImageResource(R.mipmap.icon_voicerate_7);
        }else if (i<8800){
            imgRate.setImageResource(R.mipmap.icon_voicerate_8);
        }else if (i<9900){
            imgRate.setImageResource(R.mipmap.icon_voicerate_9);
        }else{
            imgRate.setImageResource(R.mipmap.icon_voicerate_10);
        }
    }

}