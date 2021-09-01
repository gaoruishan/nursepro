package com.base.commlibs.voiceUtils.voiceprint;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.commlibs.R;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.api.BaseWebApiManager;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.VoiceprintDCManager;
import com.raisound.speech.voiceprint.callback.RequestCallback;
import com.raisound.speech.voiceprint.dc.http.response.GroupInfo;
import com.raisound.speech.voiceprint.dc.http.response.UserInfo;
import com.raisound.speech.voiceprint.listener.VoiceprintDCListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class VoiceprintManagerActivity extends AppCompatActivity implements View.OnClickListener {

    TextView etUserName,tvNurse;
    Button btnVoice;
    TextView tvRegSample;
    TextView tvCount;
    TextView tvVoiceReg;
    TextView tvVoiceManager;
    RecyclerView recReged;
    RegedPatAdapter regedPatAdapter;

    private String regStep = "REG";
    private Boolean bReg = true,ifDel = false,ifConfirm = false;
    private TextView tvReg,tvConfirm,tvFinish,tvTip;

    private final String TAG = "Voiceprint";
    private ProgressDialog progressDialog;
    private Timer recordCount;
    private int count = 0;
    private String valCode="";

    //进行的什么操作
    private String actionStep="";

    private LocUsersBean locUsersBean ;
    private VoiceRegConfirmDialog voiceRegConfirmDialog;


    //0:查询用户是否存在，1：查询组是否存在，2：查询组成员
    private int managerStep = 0;

    private String stepMethod = "";

    private int groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voiceprint_manager);
        groupId = SPUtils.getInstance().getInt(SharedPreference.VOICE_GROUPID);

        etUserName = findViewById(R.id.tv_user_name);
        tvNurse = findViewById(R.id.tv_nursename);
        tvNurse.setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
        etUserName.setText(SPUtils.getInstance().getString(SharedPreference.USERCODE));
        tvReg = findViewById(R.id.tv_reg);
        tvConfirm = findViewById(R.id.tv_confirm);
        tvFinish = findViewById(R.id.tv_finish);
        tvTip = findViewById(R.id.tv_tip);
        recReged = findViewById(R.id.rec_regedpat);
        //提高展示效率
        recReged.setHasFixedSize(true);
        //设置的布局管理
        recReged.setLayoutManager(new GridLayoutManager(this, 1));
        regedPatAdapter = new RegedPatAdapter(new ArrayList<>());
        recReged.setAdapter(regedPatAdapter);
        regedPatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.ll_message_rightmenu){
                    deleteUser(regedPatAdapter.getItem(position).getUserCode());
                }
            }
        });


        tvRegSample = findViewById(R.id.tv_reg_sample);
        tvCount = findViewById(R.id.tv_count);
        tvVoiceReg = findViewById(R.id.tv_voice_reg);
        tvVoiceReg.setOnClickListener(v -> showVoiceReg(true));
        tvVoiceManager = findViewById(R.id.tv_voice_manager);
        tvVoiceManager.setOnClickListener(v -> showVoiceReg(false));
        btnVoice = findViewById(R.id.btn_voice);
        btnVoice.setOnTouchListener(onTouchListener1);


        SpeechRecognizerManager.with(getApplicationContext()).build();
        //SpeechRecognizerManager初始化后作为参数传入声纹SDK中
//        VoiceprintDCManager.with(SpeechRecognizerManager.getInstance());
//        //使用前传入回调，构建对象
//        VoiceprintDCManager.build(VoiceprintDCListener);
        VoiceprintDCManager.with(getApplicationContext())
                //平台类型，可选配置
                .platform("platform")
                //结果回调对象
                .listener(VoiceprintDCListener)
                .build();
        getLocUsers();

    }

    private void getLocUsers(){
        BaseWebApiManager.getNursesByLoc(new BaseWebApiManager.getNursesByLocCallBack() {
            @Override
            public void onSuccess(LocUsersBean bean) {
                locUsersBean = bean;
                //获取语音组里列表
                userByName();
            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }

    private void showVoiceReg(Boolean bRegSHow){
        regedPatAdapter.setNewData(new ArrayList<>());

        findViewById(R.id.ll_top_reg).setVisibility(bRegSHow?View.VISIBLE:View.GONE);
        findViewById(R.id.ll_top_manager).setVisibility(bRegSHow?View.GONE:View.VISIBLE);
        tvVoiceReg.setTextColor(getResources().getColor(bRegSHow?R.color.blue:R.color.black));
        tvVoiceManager.setTextColor(getResources().getColor(bRegSHow?R.color.black:R.color.blue));
        findViewById(R.id.line_top1).setBackgroundColor(getResources().getColor(bRegSHow?R.color.blue:R.color.text_color_gray_8));
        findViewById(R.id.line_top2).setBackgroundColor(getResources().getColor(bRegSHow?R.color.text_color_gray_8:R.color.blue));
        if (bRegSHow){
            managerStep = 0;
            bReg = true;
            regStep = "REG";
            tvTip.setText("点击按钮注册");
            tvConfirm.setSelected(false);
            tvFinish.setSelected(false);
            userByName();
        }else {
            managerStep = 1;
            userListOfGroup();
        }
    }
    private VoiceprintDCListener VoiceprintDCListener = new VoiceprintDCListener() {
        @Override
        public void onGroupResult(List<GroupInfo> list) {//获取组列表，添加组//如果组不存在，自动添加
            Log.i(TAG+stepMethod, "onGroupResult"+GsonUtils.toJson(list));
        }

        @Override
        public void onUserResult(List<UserInfo> list) {//查询单用户，查询组用户
            Log.i(TAG+stepMethod, "onUserResult"+GsonUtils.toJson(list));


            if (managerStep == 0){

            }

            if (bReg){
                if (list.size()==1 && list.get(0).getAccount()==null){
                    regStep="REG";
                    tvReg.setSelected(true);
                    tvTip.setText("点击按钮注册!");
                    btnVoice.setVisibility(View.VISIBLE);
                }else {
                    regStep="FINISH";
                    tvReg.setSelected(true);
                    tvConfirm.setSelected(true);
                    tvFinish.setSelected(true);
                    tvTip.setText("注册完成!");
                    btnVoice.setVisibility(View.GONE);
                }
                bReg = false;
            }
            List<UserInfo> tempList = new ArrayList();
            if (list.size()>1){
//                regedPatAdapter.setNewData(list);
                tempList = list;
            }else if (list.size()==1){
                if (list.get(0).getAccount()==null){
//                    regedPatAdapter.setNewData(new ArrayList<>());
                }else {
//                    regedPatAdapter.setNewData(list);
                    tempList = list;
                }

            }else {
//                regedPatAdapter.setNewData(new ArrayList<>());
            }

            if (locUsersBean==null){

            }else {
                for (int i = 0; i < locUsersBean.getNurseList().size(); i++) {
                    locUsersBean.getNurseList().get(i).setVoiceReg(false);
                    for (int j = 0; j < tempList.size(); j++) {
                        if ((tempList.get(j).getAccount().toUpperCase()).equals((locUsersBean.getNurseList().get(i).getUserCode().toUpperCase()))){
                            locUsersBean.getNurseList().get(i).setVoiceReg(true);
                        }
                    }
                }
                if (SPUtils.getInstance().getString(SharedPreference.GROUPDESC).contains("护士长")){
                    regedPatAdapter.setNewData(locUsersBean.getNurseList());
                }else {
                    for (int i = 0; i < locUsersBean.getNurseList().size(); i++) {
                        if (locUsersBean.getNurseList().get(i).getUserId().equals(SPUtils.getInstance().getString(SharedPreference.USERID))){
                            List<LocUsersBean.NurseListBean> list1 = new ArrayList<LocUsersBean.NurseListBean>();
                            list1.add(locUsersBean.getNurseList().get(i));
                            regedPatAdapter.setNewData(list1);
                        }
                    }

                }
            }


//            Observable.just(showText).observeOn(AndroidSchedulers.mainThread()).subscribe(showText1 -> ToastUtils.showShort("查询成功"));
        }

        @Override
        public void onVerification(float score,int validWords) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            ifConfirm = false;
            if (score>50){
//                if (regStep.equals("CONFIRM")){
//                    regStep = "FINISH";
//                    tvConfirm.setSelected(true);
//                    tvFinish.setSelected(true);
//                    tvTip.setText("注册完成!");
//                }
                if (voiceRegConfirmDialog==null){
                    voiceRegConfirmDialog = new VoiceRegConfirmDialog(VoiceprintManagerActivity.this);
                }else {
                    voiceRegConfirmDialog.dismiss();
                }

                voiceRegConfirmDialog.setScore(score);
                voiceRegConfirmDialog.setSureOnclickListener(new VoiceRegConfirmDialog.onSureOnclickListener() {
                    @Override
                    public void onSureClick() {
                        if (regStep.equals("CONFIRM")){
                            regStep = "FINISH";
                            tvConfirm.setSelected(true);
                            tvFinish.setSelected(true);
                            tvTip.setText("注册完成!");
                        }
                        voiceRegConfirmDialog.dismiss();
                    }
                });
                voiceRegConfirmDialog.setCancelOnclickListener(new VoiceRegConfirmDialog.onCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        voiceRegConfirmDialog.dismiss();
                    }
                });
                voiceRegConfirmDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
//                    ToastUtils.showShort(score+"");
                        if (!regStep.equals("FINISH")){
                            regStep = "REG";
                            tvTip.setText("点击按钮注册");
                            tvConfirm.setSelected(false);
                            deleteUser(SPUtils.getInstance().getString(SharedPreference.USERCODE));
                        }
                    }
                });

                voiceRegConfirmDialog.show();
                voiceRegConfirmDialog.setCanceledOnTouchOutside(false);

            }else {
                ToastUtils.showShort("认证分数过低，请重新注册");
            }




            Log.i(TAG, "---认证分数--- score:" + score+",validWords:"+validWords);
//            Observable.just(score).observeOn(AndroidSchedulers.mainThread()).subscribe(score1 -> ToastUtils.showShort("认证分数:" + score1 +",validWords:"+validWords));
        }

        @Override
        public void onSearch(List<UserInfo> list, int validWords) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }

            String result="";
            for(UserInfo userInfo:list){
                Log.i(TAG, "---声纹搜索--- group_id:" + userInfo.getGroup_id() + ",user_id:" + userInfo.getId() + ",user_name:" + userInfo.getAccount() + ",score:" + userInfo.getScore());
                result+="---声纹搜索--- group_id:" + userInfo.getGroup_id() + ",user_id:" + userInfo.getId() + ",user_name:" + userInfo.getAccount() + ",score:" + userInfo.getScore()+"\n";
            }
            Observable.just(result).observeOn(AndroidSchedulers.mainThread()).subscribe(result1 -> ToastUtils.showShort(result1+",validWords:"+validWords));
        }

        @Override
        public void onStatusResult(String statusMsg) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }
            if (ifDel){
                ifDel = false;
            }else {
                if (regStep.equals("REG")){
                    regStep = "CONFIRM";
                    tvConfirm.setSelected(true);
                    tvTip.setText("点击按钮认证!");
                }else if (regStep.equals("CONFIRM")){
                    regStep = "FINISH";
                    tvConfirm.setSelected(true);
                    tvFinish.setSelected(true);
                    tvTip.setText("注册完成!");
                }
            }
            Log.i(TAG, "---状态信息--- status:" + statusMsg);
//            Observable.just(statusMsg).observeOn(AndroidSchedulers.mainThread()).subscribe(statusMsg1 -> ToastUtils.showShort(statusMsg1));
            userListOfGroup();

        }

        @Override
        public void onError(String errorMsg) {
            if(progressDialog!=null) {
                progressDialog.dismiss();
            }

            if (ifConfirm){
                regStep = "REG";
                tvTip.setText("点击按钮注册");
                tvConfirm.setSelected(false);
                deleteUser(SPUtils.getInstance().getString(SharedPreference.USERCODE));
                ifConfirm = false;

                Log.i(TAG, "---错误信息--- error:" + errorMsg);
                Observable.just(errorMsg).observeOn(AndroidSchedulers.mainThread()).subscribe(errorMsg1 -> ToastUtils.showShort(errorMsg1+"，请重新注册") );
            }else {
                Log.i(TAG, "---错误信息--- error:" + errorMsg);
                Observable.just(errorMsg).observeOn(AndroidSchedulers.mainThread()).subscribe(errorMsg1 -> ToastUtils.showShort(errorMsg1) );
            }

            if (errorMsg.contains("Verify User Failed")){
                regStep = "CONFIRM";
                tvReg.setSelected(true);
                tvConfirm.setSelected(true);
                btnVoice.setVisibility(View.VISIBLE);
                tvTip.setText("点击按钮认证!");
            }

        }
    };


    public void deleteUser(String userName) {
        stepMethod = "deleteUser";
        if (TextUtils.isEmpty(etUserName.getText())) {
            return;
        }
        ifDel = true;
        VoiceprintDCManager.getInstance().delUser(groupId, userName);
    }

    public void userListOfGroup() {
        stepMethod = "userListOfGroup";

        VoiceprintDCManager.getInstance().getGroupUsers(groupId);
    }

    public void userByName() {
        stepMethod = "userByName";
        managerStep = 0;
        if (TextUtils.isEmpty(etUserName.getText())) {
            return;
        }
        VoiceprintDCManager.getInstance().searchUser(etUserName.getText().toString());
    }
    public void regUserByValidate(){
        VoiceprintDCManager.getInstance().regUserByValidate(groupId, etUserName.getText().toString(), new RequestCallback() {
            @Override
            public void onSuccess(int status) {

                if(progressDialog!=null) {
                    progressDialog.dismiss();
                }
                //成功回调，status=0
                Log.i(TAG, "---注册成功--- 待验证");
                regStep = "CONFIRM";
                tvConfirm.setSelected(true);
                tvTip.setText("点击按钮认证!");
            }

            @Override
            public void onFailure(int status, String message) {

                if(progressDialog!=null) {
                    progressDialog.dismiss();
                }
                //失败回调，status=1,message=错误信息
                Log.i(TAG, "---注册失败---"+message+"--"+status);
                deleteUser(etUserName.getText().toString());
                tvTip.setText("注册失败!重新注册");
            }
        });

    }
    public void voiceprintVerifyReg() {
        stepMethod = "voiceprintVerifyReg";
        VoiceprintDCManager.getInstance().validateRegister(SPUtils.getInstance().getInt(SharedPreference.VOICE_GROUPID), etUserName.getText().toString(), valCode, new RequestCallback() {
            @Override
            public void onSuccess(int i) {
                if(progressDialog!=null) {
                    progressDialog.dismiss();
                }
                Log.i(TAG, "---注册认证--- onSuccess");
                regStep = "FINISH";
                tvConfirm.setSelected(true);
                tvFinish.setSelected(true);
                tvTip.setText("注册完成!");
                btnVoice.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int i, String errorMsg) {
                if(progressDialog!=null) {
                    progressDialog.dismiss();
                }
                Log.i(TAG, "---注册认证--- onFailure：" + errorMsg);
                regStep = "REG";
                tvConfirm.setSelected(false);
                tvTip.setText("认证失败，点击按钮重新注册");
            }
        });
    }

    View.OnTouchListener onTouchListener1 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btnVoice.setSelected(true);
                        String showText="";
                        valCode="";
                        switch (regStep){
                            case "REG":
                                if (TextUtils.isEmpty(etUserName.getText())) {
                                    break;
                                }
                                tvCount.setVisibility(View.VISIBLE);
                                valCode=getRegDCCode();
                                recordCount = new Timer();
                                recordCount.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        Observable.just(count++).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                                            @Override
                                            public void accept(Integer count) throws Exception {
                                                tvCount.setText(count + "");
                                            }
                                        });
                                        if (tvCount.getVisibility() == View.GONE) {
                                            recordCount.cancel();
                                            count = 0;
                                        }
                                    }
                                }, 0, 1000);

                                regUserByValidate();
                                break;
                            case "CONFIRM":
                                if (TextUtils.isEmpty(etUserName.getText())) {
                                    break;
                                }
                                valCode=getDCCode();
                                ifConfirm = true;
                                voiceprintVerifyReg();
                                break;
                            case "FINISH":
                                ToastUtils.showLong("已注册，请勿重新注册");
                                break;
                            default:
                                break;
                        }
                        showText=String.format("请朗读以下内容：\\n%s",valCode);
                        tvRegSample.setText(showText);
                        tvRegSample.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        btnVoice.setSelected(false);
                        tvCount.setVisibility(View.GONE);
                        tvRegSample.setVisibility(View.GONE);
                        if (progressDialog == null) {
                            progressDialog = new ProgressDialog(VoiceprintManagerActivity.this);
                        }
                        switch (regStep){
                            case "REG":
                                if (TextUtils.isEmpty(etUserName.getText())) {
                                    break;
                                }
                                progressDialog.setTitle("声纹注册");
                                progressDialog.setMessage("注册中...");
                                progressDialog.show();
                                break;
                            case "CONFIRM":
                                if (TextUtils.isEmpty(etUserName.getText())) {
                                    break;
                                }
                                progressDialog.setTitle("声纹认证 识别中...");
                                progressDialog.setMessage("请稍等");
                                progressDialog.show();
                                break;
                            case "FINISH":
                                break;
                            default:
                                break;
                        }
                        VoiceprintDCManager.getInstance().commit();
                        break;
                    default:
                        break;
                }
                return false;
            }
        }
    };


    private String[] arrayStr = new String[]{"0", "2", "3", "4", "5", "6", "7", "8", "9"};

    private String getRegDCCode() {
        String regDCString = "";
        for (int i = 0; i < 4; i++) {
            regDCString += getDCCode() + "\n";
        }
        regDCString=regDCString.substring(0,regDCString.length()-1);
        return regDCString;
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpeechRecognizerManager.stopNow();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
