package com.base.commlibs.voiceUtils.voiceprint;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.AsrDialog;
import com.base.commlibs.voiceUtils.api.BaseWebApiManager;
import com.base.commlibs.voiceUtils.voiceprint.ScoreBean;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.VoiceprintDCManager;
import com.raisound.speech.voiceprint.dc.http.response.GroupInfo;
import com.raisound.speech.voiceprint.dc.http.response.UserInfo;
import com.raisound.speech.voiceprint.listener.VoiceprintDCListener;

import java.util.List;
import java.util.Random;

/**
 * com.base.commlibs.voiceUtils
 * <p>
 * author Q
 * Date: 2021/6/15
 * Time:16:27
 */
public class VoicePrintUtil {

    private SPUtils spUtils=SPUtils.getInstance();
    private final String TAG="VoicePrintUtil";
    public static final String VOICE_TYPE_LOGIN="LOGIN";

    private ProgressDialog progressDialog;
    private View touchView;

    private Context mContext;
    private String voiceType="";
    public VoicePrintUtil(Context context,String type,View view){
        mContext = context;
        voiceType = type;
        touchView = view;
        initVoicePrint();
    }
    public void initVoicePrint(){
        SpeechRecognizerManager.with(mContext).build();
        //SpeechRecognizerManager初始化后作为参数传入声纹SDK中
//        VoiceprintDCManager.initialize(SpeechRecognizerManager.getInstance());
//        //使用前传入回调，构建对象
//        VoiceprintDCManager.build(VoiceprintDCListener);
        VoiceprintDCManager.with(mContext)
                //平台类型，可选配置
                .platform("platform")
                //结果回调对象
                .listener(VoiceprintDCListener)
                .build();
        getSoundSocre();
        queryGroup();
        touchView.setLongClickable(true);
        touchView.setOnTouchListener(onTouchListener);

    }
    private void getSoundSocre(){
        if (SPUtils.getInstance().getInt(SharedPreference.VOICE_SCORE)==-1){
            SPUtils.getInstance().put(SharedPreference.VOICE_SCORE,60);
        }
        BaseWebApiManager.GetSoundScore(new BaseWebApiManager.GetSoundScoreCallBack() {
            @Override
            public void onSuccess(ScoreBean scoreBean) {
                SPUtils.getInstance().put(SharedPreference.VOICE_SCORE,Integer.parseInt(scoreBean.getScore()));
                ToastUtils.showLong(scoreBean.getScore());
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }



    private String valCode="";
    private String[] arrayStr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String getDCCode() {
        String randomString = "";
        while (randomString.length() < 8) {
            int index = new Random().nextInt(arrayStr.length);
            if (!randomString.contains(arrayStr[index])) {
                randomString += arrayStr[index];
            }
        }
        return randomString;
    }

    public void voiceprintSearch() {
        String valCode=getDCCode();
//        VoiceprintDCManager.getInstance().validateSearch(5,valCode);
        VoiceprintDCManager.getInstance().validateSearch(spUtils.getInt(SharedPreference.VOICE_GROUPID),valCode);
    }


    VoicePrintReadDialog  voicePrintReadDialog;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(mContext);
                    }
                    voicePrintReadDialog = new VoicePrintReadDialog(mContext);

//                    AsrDialog asrDialog=new AsrDialog(mContext);
//                    asrDialog.show();
                    String showText="";
                    valCode="";
                    valCode=getDCCode();
                    voiceprintSearch();
//                    showText=String.format(getString(R.string.reg_tips),valCode);
//                    rlVoice.setVisibility(View.VISIBLE);
//                    tvCout.setText(valCode);
                    voicePrintReadDialog.setTipsMsg(valCode);
                    voicePrintReadDialog.show();

                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
//                    rlVoice.setVisibility(View.GONE);
//                    tvCout.setText("");
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(mContext);
                    }
                    if (voicePrintReadDialog!=null&&voicePrintReadDialog.isShowing()){
                        voicePrintReadDialog.dismiss();
                    }
                    progressDialog.setTitle("声纹搜索 识别中...");
                    progressDialog.setMessage("请稍等");
                    progressDialog.show();
                    VoiceprintDCManager.getInstance().commit();



//                    btnMove.setText("按下 说话");
//                    btnMove.setSelected(false);
////                  btnMove.setBackgroundColor(getResources().getColor(R.color.fragment_main_bg));
//                    mSp.stopRecord();
                    break;
            }
            return false;
        }
    };


    //进行的什么操作
    private String actionStep="";
    private String stepMethod = "";
    public void queryGroup() {
        stepMethod = "queryGroup";
        actionStep = "queryGroup";
        VoiceprintDCManager.getInstance().getGroupList();
    }

    public void addGroup() {
        stepMethod = "addGroup";
//        if (TextUtils.isEmpty(Sp)) {
//            return;
//        }
        actionStep = "queryGroup";
        //添加分组
//        VoiceprintDCManager.getInstance().addGroup(SPUtils.getInstance().getString(SharedPreference.LOCID));
        VoiceprintDCManager.getInstance().addGroup("dhc1001");
    }

    com.raisound.speech.voiceprint.listener.VoiceprintDCListener VoiceprintDCListener = new VoiceprintDCListener() {
        @Override
        public void onGroupResult(List<GroupInfo> list) {
            Log.i(TAG+stepMethod, "onGroupResult"+ GsonUtils.toJson(list));
            if (actionStep.equals("queryGroup")){
                Boolean isGroupExist = false;
                if (list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getName().equals("dhc1001")){
                            spUtils.put(SharedPreference.VOICE_GROUPID,list.get(i).getId());
                            isGroupExist = true;
                            break;
                        }
                    }
                }
                if (!isGroupExist){
                    addGroup();
                }else {
//                    userByName();
                }
            }
        }

        @Override
        public void onUserResult(List<UserInfo> list) {
            String showText = "";
            if (list.size() == 0) {
                showText = "---用户信息--- 没有用户";
            }
            Log.i(TAG, showText);
            for (int i = 0; i < list.size(); i++) {
                String tempString = "---用户信息--- group_id:" + list.get(i).getGroup_id() + ",user_id:" + list.get(i).getId() + ",user_name:" + list.get(i).getAccount() + ",is_delete:" + list.get(i).isDelete();
                showText += tempString + "\n";
                Log.i(TAG, tempString);
            }
            showText.substring(0, showText.length() - 2);
        }

        @Override
        public void onVerification(float score,int validWords) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            Log.i(TAG, "---认证分数--- score:" + score+",validWords:"+validWords);      }

        @Override
        public void onSearch(List<UserInfo> list, int validWords) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            String result="";
            Log.e(TAG, "onSearch: "+GsonUtils.toJson(list));
            for(UserInfo userInfo:list){
                Log.i(TAG, "---声纹搜索--- group_id:" + userInfo.getGroup_id() + ",user_id:" + userInfo.getId() + ",user_name:" + userInfo.getAccount() + ",score:" + userInfo.getScore());
                result+="---声纹搜索--- group_id:" + userInfo.getGroup_id() + ",user_id:" + userInfo.getId() + ",user_name:" + userInfo.getAccount() + ",score:" + userInfo.getScore()+"\n";
            }
            if (list.size()>0&&list.get(0).getScore()>spUtils.getInt(SharedPreference.VOICE_SCORE)){
                ToastUtils.showLong("匹配成功，"+list.get(0).getAccount());
                if (voicePrintResultCallBack!=null){
                    voicePrintResultCallBack.success(list.get(0).getAccount());
                }
            }else {
                ToastUtils.showLong("匹配失败，请重新登陆");
                if (voicePrintResultCallBack!=null){
                    voicePrintResultCallBack.fail();
                }
            }
//            Observable.just(result).observeOn(AndroidSchedulers.mainThread()).subscribe(result1 -> showToast(result1+",validWords:"+validWords));
        }

        @Override
        public void onStatusResult(String statusMsg) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            Log.i(TAG, "---状态信息--- status:" + statusMsg);
//            Observable.just(statusMsg).observeOn(AndroidSchedulers.mainThread()).subscribe(statusMsg1 -> showToast(statusMsg1));

        }

        @Override
        public void onError(String errorMsg) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            ToastUtils.showLong(errorMsg);
            voicePrintResultCallBack.fail();
            Log.i(TAG, "---错误信息--- error:" + errorMsg);
//            Observable.just(errorMsg).observeOn(AndroidSchedulers.mainThread()).subscribe(errorMsg1 -> showToast(errorMsg1));
        }
    };

    VoicePrintResultCallBack voicePrintResultCallBack;
    public void setVoicePrintResultCallBack(VoicePrintResultCallBack voicePrintResultCallBack){
        this.voicePrintResultCallBack = voicePrintResultCallBack;
    }
    public interface VoicePrintResultCallBack{
        void success(String userCode);
        void fail();
    }

}
