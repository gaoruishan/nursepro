package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.adapter.MeetingListAdapter;
import com.example.dhcc_nurlink.bean.MeetingUserBean;
import com.example.dhcc_nurlink.greendao.DaoSession;
import com.example.dhcc_nurlink.greendao.GreenDaoHelper;
import com.raisound.speech.AsrResult;
import com.raisound.speech.SpeechError;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.listener.RecognizerListener;
import com.raisound.voip.ConferenceMember;
import com.raisound.voip.VoipUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.ViewUtils.runOnUiThread;

/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class VoiceIpDialog extends Dialog implements DialogInterface.OnDismissListener {
    private Context context;
    private TextView tvTitle,tvMsg,tvSure,tvCancle,tvNoVoice,tvOutVoice,tvSendVoiceMsg,tvDestroyMeeting;
    private RecyclerView recMeeting;
    private String title="",msg="";
    private int cancleVisible = View.VISIBLE;
    private MeetingListAdapter meetingListAdapter;
    private LinearLayout llRejectCall,llAcceptCall,llNoVoice,llOutVoice,llSendVoiceMsg;
    private int rejectCallShow=View.VISIBLE,acceptCallShow=View.VISIBLE,noVoiceShow=View.VISIBLE,outVoiceShow=View.VISIBLE;
    private Boolean noVoiceSelect=false,outVoiceSelect=true;

    private int sendVoiceMsg=View.GONE;
    private int tvDestroyMeetingShow = View.GONE;

    private Boolean bVoiceToText = false;//判断是否掉语音接听功能
    AudioManager mAudioManager;

    public void setVoiceSel(Boolean b){
        tvOutVoice.setSelected(b);
    }
    public void setbVoiceToText(Boolean bVoiceToText) {
        this.bVoiceToText = bVoiceToText;
    }

    public void setTvDestroyMeetingShow(int tvDestroyMeetingShow) {
        this.tvDestroyMeetingShow = tvDestroyMeetingShow;
    }

    public void setSendVoiceMsg(int sendVoiceMsg) {
        this.sendVoiceMsg = sendVoiceMsg;
        if (llSendVoiceMsg!=null){
            llSendVoiceMsg.setVisibility(sendVoiceMsg);
        }
    }

    public void setNoVoiceSelect(Boolean noVoiceSelect) {
        this.noVoiceSelect = noVoiceSelect;
        if (tvNoVoice!=null){
            tvNoVoice.setSelected(noVoiceSelect);
        }
    }

    public void setOutVoiceSelect(Boolean outVoiceSelect) {
        this.outVoiceSelect = outVoiceSelect;
        if (tvOutVoice!=null){
            tvOutVoice.setSelected(outVoiceSelect);
        }
    }

    public void setRejectCallShow(int rejectCallShow) {
        this.rejectCallShow = rejectCallShow;
        if (llRejectCall!=null){
            llRejectCall.setVisibility(rejectCallShow);
        }
    }

    public void setAcceptCallShow(int acceptCallShow) {
        this.acceptCallShow = acceptCallShow;
        if (llAcceptCall!=null){
            llAcceptCall.setVisibility(acceptCallShow);
        }
    }

    public void setNoVoiceShow(int noVoiceShow) {
        this.noVoiceShow = noVoiceShow;
        if (llNoVoice!=null){
            llNoVoice.setVisibility(noVoiceShow);
        }
    }

    public void setOutVoiceShow(int outVoiceShow) {
        this.outVoiceShow = outVoiceShow;
        if (llOutVoice!=null){
            llOutVoice.setVisibility(outVoiceShow);
        }
    }

    private List<MeetingUserBean> meetingList= new ArrayList<>();

    public void refreshMeetingList(String name,String status){
        if (meetingListAdapter!=null){
            for (int i = 0; i < meetingListAdapter.getData().size(); i++) {
                if (meetingListAdapter.getData().get(i).getUserId().equals(name)){
                    meetingListAdapter.getData().get(i).setJoinStatus(status);
                    meetingListAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    public void setMeetingList(List<MeetingUserBean> meetingList) {

//        DaoSession daoSession = GreenDaoHelper.getDaoSession();
//        for (int i = 0; i < meetingList.size(); i++) {
//            for (int j = 0; j < daoSession.getNativePhoneBeanDao().queryBuilder().list().size(); j++) {
//                if (daoSession.getNativePhoneBeanDao().queryBuilder().list().get(j).getVOIPId().equals(meetingList.get(i).nickname)){
//                    meetingList.get(i).nickname=daoSession.getNativePhoneBeanDao().queryBuilder().list().get(j).getUserName();
//                }
//            }
//        }
        Log.e("onReceiveInvite", "setMeetingList: "+meetingList );
        this.meetingList = meetingList;
        if (meetingListAdapter!=null){
            meetingListAdapter.setNewData(meetingList);
        }
    }

    public void setCancleVisible(int cancleVisible){
        this.cancleVisible = cancleVisible;
        if (tvCancle!=null){
            tvCancle.setVisibility(cancleVisible);
        }
    }
    public void setTitle(String title) {
        this.title = title;
        if (tvTitle!=null){
            tvTitle.setText(title);
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        if (tvMsg!=null){
            tvMsg.setText(msg);
        }
        if (msg.contains("正在等待对方接听")){
            setSendVoiceMsg(View.VISIBLE);
        }else {
            setSendVoiceMsg(View.GONE);
        }
    }

    public String getMsg() {
        return msg;
    }

    public VoiceIpDialog(Context context) {
        super(context, R.style.MyDialog2);
        this.context = context;

    }

    public void setTvNoVoice(String str){
        if (tvNoVoice!=null){
            tvNoVoice.setText(str);
        }
    }
    public void setTvOutVoice(String str){
        if (tvOutVoice!=null){
            tvOutVoice.setText(str);
        }
    }

    public String getTvNoVoice() {
        return tvNoVoice.getText().toString();
    }
    public String gettvOutVoice() {
        return tvOutVoice.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voip_dialog_layout);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initAdapter();
        initData();
        mAudioManager =(AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    private void sure(){
        endVoip();
        callBack.sure();
    }
    private void cancle(){
        endVoip();
        callBack.cancle();
    }
    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvMsg = findViewById(R.id.tv_msg);
        tvSure = findViewById(R.id.tv_sure);
        tvCancle = findViewById(R.id.tv_cancle);
        tvSure.setOnClickListener(v -> sure());
        tvCancle.setOnClickListener(v -> cancle());
        tvNoVoice = findViewById(R.id.tv_no_voice);
        tvOutVoice = findViewById(R.id.tv_out_voice);
        tvNoVoice.setOnClickListener(v -> setTvNoVoiceSelect());
        tvOutVoice.setOnClickListener(v -> setTvOutVoiceSelect());
        tvOutVoice.setSelected(true);

        recMeeting = findViewById(R.id.recy_meeting_list);
        recMeeting.setHasFixedSize(true);
        recMeeting.setLayoutManager(new LinearLayoutManager(context));
        recMeeting.setAdapter(meetingListAdapter);

        llRejectCall = findViewById(R.id.ll_rejectcall);
        llAcceptCall = findViewById(R.id.ll_acceptcall);
        llNoVoice = findViewById(R.id.ll_novoice);
        llOutVoice = findViewById(R.id.ll_outvoice);

        llSendVoiceMsg = findViewById(R.id.ll_send_voice_msg);
        tvSendVoiceMsg = findViewById(R.id.tv_send_voice_msg);
        tvSendVoiceMsg.setOnClickListener(v -> callBack.sendVoiceMsg());

        tvDestroyMeeting = findViewById(R.id.tv_destroy_meeting);
        tvDestroyMeeting.setOnClickListener(v ->destroyMeeting());
    }


    private void initData(){
        if (title!=null&&tvTitle!=null){
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (msg!=null&&tvMsg!=null){
            tvMsg.setText(msg);
        }
        if (tvCancle!=null){
            tvCancle.setVisibility(cancleVisible);
        }
        if (meetingListAdapter!=null&&meetingList!=null){
            meetingListAdapter.setNewData(meetingList);
        }
        recMeeting.setAdapter(meetingListAdapter);

        if (llRejectCall!=null){
            llRejectCall.setVisibility(rejectCallShow);
        }
        if (llAcceptCall!=null){
            llAcceptCall.setVisibility(acceptCallShow);
        }

        if (llNoVoice!=null){
            llNoVoice.setVisibility(noVoiceShow);
        }
        if (llOutVoice!=null){
            llOutVoice.setVisibility(outVoiceShow);
        }

        if (tvNoVoice!=null){
            tvNoVoice.setSelected(noVoiceSelect);
        }
        if (tvOutVoice!=null){
            tvOutVoice.setSelected(outVoiceSelect);
        }

        if (llSendVoiceMsg!=null){
            llSendVoiceMsg.setVisibility(sendVoiceMsg);
        }
        if (tvDestroyMeeting!=null){
            tvDestroyMeeting.setVisibility(tvDestroyMeetingShow);
        }
        VoipUtils.unMute();
        VoipUtils.openSpeaker();

//        if (bVoiceToText){
//            SpeechRecognizerManager.with(context)
//                    .scene(getScene())
//
//                    //设置回调方法
//                    .puncFlag(true)
//                    .listener(listener1)
//                    //构建SpeechRecognizer对象
//                    .build();
//            startVoiceIp();
//        }else {
//            SpeechRecognizerManager.with(context)
//                    .scene(getScene())
//
//                    //设置回调方法
//                    .puncFlag(true)
//                    .listener(listener1)
//                    //构建SpeechRecognizer对象
//                    .build();
//            endVoip();
//        }




    }
    public String getScene(){
        String speechScene = "donghua_5_37";
        if (SharedPreference.SCENE_LIST!=null){
            if (SharedPreference.SCENE_LIST.size()<1){
                ToastUtils.showLong("未获取场景");
            }else {
                speechScene = SharedPreference.SCENE_LIST.get(0).getSceneId();
                for (int i = 0; i < SharedPreference.SCENE_LIST.size(); i++) {
                    if ("通用场景".equals(SharedPreference.SCENE_LIST.get(i).getSceneName())){
                        speechScene = SharedPreference.SCENE_LIST.get(i).getSceneId();
                    }
                }
            }
        }else {
            ToastUtils.showLong("未获取场景");
        }
        return speechScene;

    }

    public void startVoiceIp(){
        SpeechRecognizerManager.start();
    }
    private void endVoip(){
//        SpeechRecognizerManager.stopNow();
    }

    private void initAdapter(){
        meetingListAdapter = new MeetingListAdapter(new ArrayList<>());
    }

    private void destroyMeeting(){
        VoipUtils.destroyConference();
        dismiss();
    }

    private String TAG = "voicetotextdialog";
    private RecognizerListener listener1 = new RecognizerListener() {
        @Override
        public void onRecordData(byte[] bytes) {

        }

        @Override
        public void onVolumeChanged(int i) {
            //返回音量变化，自定义界面展示录音动画
            Log.d(TAG, "onVolumeChanged:" + i);
//            showWave(i);
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
            Log.e(TAG+"1", "onResult: "+asrResult.results );
            if (asrResult.isResult){
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("results", asrResult.results);
                    bundle.putString("json", new JSONObject(asrResult.jsonResult).toString());
                    bundle.putBoolean("isLast", isLast);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (asrResult.results.contains("接听")){
//                                ToastUtils.showLong("接听。。。。");
                               sure();
                            }else if (asrResult.results.contains("挂断")||asrResult.results.contains("拒绝")){
                                cancle();
                            }
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "onResult: ee"+e );
                }
                //bundle.putString("json", "{\"command\":{\"action\":\"生命体征\",\"code\":2001},\"form\":{\"code\":200}}");

            }
//            voicResult(asrResult,isLast);
        }

        @Override
        public void onError(final SpeechError speechError) {
            return;
        }
    };




    private void setTvNoVoiceSelect(){
        callBack.noVoice();
        if (tvNoVoice.isSelected()){
            tvNoVoice.setSelected(false);
            VoipUtils.unMute();
        }else {
            tvNoVoice.setSelected(true);
            VoipUtils.mute();
        }
    }
    private void setTvOutVoiceSelect(){
        callBack.outVoice();
        VoipUtils.openSpeaker();
        if (tvOutVoice.isSelected()){
            tvOutVoice.setSelected(false);
//            VoipUtils.closeSpeaker();

            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 1, AudioManager.FLAG_PLAY_SOUND);
        }else {
            tvOutVoice.setSelected(true);
//            VoipUtils.openSpeaker();
            mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 6, AudioManager.FLAG_PLAY_SOUND);
        }
    }
    public CallBack callBack;
    public void setCallBack(CallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
    }

//    @Override
//    public void setOnDismissListener(@Nullable OnDismissListener listener) {
//        super.setOnDismissListener(listener);
//        Log.e(TAG, "onDismiss: 1111111111111" );
//        VoipUtils.unMute();
//        VoipUtils.openSpeaker();
//        endVoip();
//    }

    public interface CallBack{
        public void sure();
        public void cancle();
        public void noVoice();
        public void outVoice();
        public void sendVoiceMsg();
    }


}