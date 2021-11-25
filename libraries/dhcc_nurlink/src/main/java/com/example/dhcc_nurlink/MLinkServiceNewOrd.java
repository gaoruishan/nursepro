package com.example.dhcc_nurlink;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.service.AliveService;
import com.base.commlibs.utils.SystemTTS;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.dhcc_nurlink.Notify.NotifyCallBack;
import com.example.dhcc_nurlink.Notify.Notifycation;
import com.example.dhcc_nurlink.Utils.DateUtils;
import com.example.dhcc_nurlink.Utils.RingPlayUtil;
import com.example.dhcc_nurlink.bean.CallingHistoryBean;
import com.example.dhcc_nurlink.bean.MeetingUserBean;
import com.example.dhcc_nurlink.bean.MessageListBean;
import com.example.dhcc_nurlink.bean.MsgNotifyBean;
import com.example.dhcc_nurlink.bean.PermissionBean;
import com.example.dhcc_nurlink.dialog.SendVoiceMsgDialog;
import com.example.dhcc_nurlink.dialog.VoiceIpDialog;
import com.example.dhcc_nurlink.greendao.GreenDaoHelper;
import com.example.dhcc_nurlink.greendao.NativePhoneBean;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;
import com.google.gson.Gson;
import com.raisound.voip.CallingState;
import com.raisound.voip.ConferenceMember;
import com.raisound.voip.VoipListener;
import com.raisound.voip.VoipUtils;
import com.raisound.voip.callback.VoipRequestCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.blankj.utilcode.util.ViewUtils.runOnUiThread;


public class MLinkServiceNewOrd extends AliveService implements VoipListener {

    public int DELAY_MILLIS = 5 * 1000;
    private Handler mHandler = new Handler();
    private Boolean isDestroy = false;
    public int MSG_DELAY_MILLIS = 180 * 1000;
    private String notifyMsg="";
    Notifycation notifycation;
    private Thread newThread; //声明一个子线程
    // 新医嘱提示
    private NotificationManager notificationManager;
    private int notifyId = 0;

    private Boolean notifyCloseByExsit=false;

    private SPUtils spUtils = SPUtils.getInstance();

//    private Boolean bLoginVoip=false;//判断voip是否登陆成功，未登陆成功不可拨号


    private MainReceiver mainReceiver = new MainReceiver();
    private IntentFilter mainfilter = new IntentFilter();

    RingPlayUtil ringPlayUtil;
    private Gson gson = new Gson();
    @Override
    public void onCreate() {
        super.onCreate();
        initVoip();
        ringPlayUtil = new RingPlayUtil(this);
        ringPlayUtil.setRingStopListner(new RingPlayUtil.RingStopListner() {
            @Override
            public void ringStopCallback() {
                dismissVoipDialog(0);
            }
        });
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (mainReceiver != null) {
            mainfilter.addAction(Action.CALL_RING_SERVICE);
            mainfilter.addAction("com.dw.action.key_ptt_down");
            mainfilter.addAction("com.dw.action.voice_wake_up");
            mainfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(mainReceiver, mainfilter);
        }
//        mHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what) {
//                    case 0:
//                        removeMessages(0);
//
//                        notifiedConnect();
//                        if (!isDestroy) {
//                            break;
//                        }
//                }
//            }
//        };
//
//        mHandler.sendEmptyMessage(0);

//        mKeyguardManager = (KeyguardManager)mContext.getSystemService(Context.KEYGUARD_SERVICE);
//        voiceContral();

    }

    private void initVoip(){
        if (SharedPreference.NurLinkApkName.contains("公网")){
            VoipUtils.TransportType("udp");
        }
        VoipUtils.init(this, SharedPreference.VOIP_ADDRESS, "donghua", this, new VoipRequestCallback() {
            @Override
            public void onSuccess(int i, Object o) {
                VoipUtils.login("1805");
                notifiedMsg();
                Log.e(TAG, "onSuccess:voip-----成功" );
            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtils.showLong("voip初始化失败，请重试");
                Log.e(TAG, "onSuccess:voip初始化----失败，请重试 " );
            }
        });


    }

    public void notifiedMsg(){
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        removeMessages(0);
                        getMsg();
                        Log.e(TAG, "handleMessage: " );
                        mHandler.sendEmptyMessageDelayed(0,MSG_DELAY_MILLIS);
                        if (!isDestroy) {
                            break;
                        }
                }
            }
        };

        mHandler.sendEmptyMessage(0);
    }
    String msgPath="";
    private void getMsg() {

        WebApiManager.getMessageList(new CommonCallBack<MessageListBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(MessageListBean bean, String type) {
                for (int i = 0; i < bean.getMesList().size(); i++) {
                    if (bean.getMesList().get(i).getMesStatus().equals("未读")&&bean.getMesList().get(i).getActionDesc().contains("留言")){
                        if (!msgPath.equals(bean.getMesList().get(i).getMsgAddress())){
                            notifyMsg="您有新的留言消息";
                            if (msgPath.equals("")){
                                msgPath="1";
                            }else {
                                msgPath=bean.getMesList().get(i).getMsgAddress();
                            }
                            SystemTTS.getInstance(getApplicationContext()).play(notifyMsg);
                        }

                        showNotification(getApplication(), "0", "1");
                        break;
                    }
                }

            }
        });


    }


    public void notifiedConnect(){
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                notifycation = new Notifycation();

                notifycation.setCallBack(new NotifyCallBack() {
                    @Override
                    public void onConnected() {
                        DELAY_MILLIS = 5 * 1000;
                        Log.e("oonConnected1111111", "onConnected: ");
                    }

                    @Override
                    public void onClose() {
                        Log.e("onClose1111111", "onClose: "+notifyCloseByExsit);
                        if (!notifyCloseByExsit){
                            mHandler.sendEmptyMessageDelayed(0,DELAY_MILLIS);
                        }
                    }

                    @Override
                    public void onMsg(String msg) {
                        try {
                            Log.e("msg1111111", "onMsg: "+msg );
                            notifyMsg = msg;
                            //showNotification(getApplication(), "0", "1");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onConnectFail() {
                        Log.e("fail1111111", "onMsg: fail" );
                        mHandler.sendEmptyMessageDelayed(0,DELAY_MILLIS);
                        if (DELAY_MILLIS<600000){
                            DELAY_MILLIS = DELAY_MILLIS + 5000;
                        }
                    }
                });
                notifyInit();
            }
        });

        newThread.start();
    }


    private void notifyInit(){
//        notifycation.notifyInit("10.1.21.97",9001,"com/base/commlibs/base/Notifycation","3532");
        notifycation.notifyInit(SharedPreference.NOTIFY_IP,SharedPreference.NOTIFY_PORT,"com/base/commlibs/base/Notifycation",spUtils.getString(SharedPreference.USERCODE));
    }

    private Boolean bReceivecallCallback=false;//判断是否收到来电回传
    @Override
    public void onConnected() {
        //ToastUtils.showShort("登陆成功");
//        bLoginVoip = true;
//        Log.e(TAG, "onConnected: "+bLoginVoip );
    }

    @Override
    public void onDisconnected(int i, String s) {
        ToastUtils.showShort("VOIP登陆失败");
        dismissVoipDialog(0);
//        bLoginVoip = false;


        Log.e(TAG, "onDisconnected: "+i+"--"+s );
    }

    @Override
    public void onCalling() {
        Log.e(TAG, "onCalling: " );

    }
    @Override
    public void receiveCall(String s,String nickName) {
//        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
//            Log.e(TAG, "onReceiveInvite: " );
////            Intent intent = new Intent();
////            intent.setClass(getApplicationContext(),MainActivity.class);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
////            startActivity(intent);
//            //得到键盘锁管理器对象
//            km= (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
//            kl = km.newKeyguardLock("unLock");
//            //解锁
//            kl.disableKeyguard();
//            isLock = true;
//        }
        if (nickName==null){
            nickName="";
//            nickName = s;
        }
//        String finalNickName1 = nickName;
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                ToastUtils.showLong(finalNickName1 +s);
//            }
//        });
        Log.e(TAG, "receiveCall: "+s+nickName );
        if (nickName.isEmpty()){
            for (int k = 0; k < listUsers.size(); k++) {
                if (listUsers.get(k).getVOIPId().equals(s)){
                    nickName =listUsers.get(k).getUserName();
                }
                if (k==listUsers.size()-1){
                    bReceivecallCallback = true;
                    String finalNickName = nickName;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialogTitle = finalNickName;
                            dialogCallStatus = "正在呼叫您...";
                            if (SPUtils.getInstance().getString(SharedPreference.NUR_LINK_NOBOTHERMODEL).equals("1")){
                                addReceiveCall(finalNickName +"来电","N-call",s,"","in");
                                VoipUtils.endCall();
                            }else {
                                showReceiveCallDialog(s);
                            }

                            ToastUtils.showLong("用户"+s+"来电");
                        }
                    });
                }
            }

        }else {
            bReceivecallCallback = true;
            String finalNickName = nickName;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialogTitle = finalNickName;
                    dialogCallStatus = "正在呼叫您...";
                    if (SPUtils.getInstance().getString(SharedPreference.NUR_LINK_NOBOTHERMODEL).equals("1")){
                        addReceiveCall(finalNickName +"来电","N-call",s,"","in");
                        VoipUtils.endCall();
                    }else {
                        showReceiveCallDialog(s);
                    }

                    ToastUtils.showLong("用户"+s+"来电");
                }
            });
        }

    }

    @Override
    public void onCalled() {
        Log.e(TAG, "onCalled: " );
//       -
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ToastUtils.showShort("已拨通，等待对方接听");
            }
        });

//        ToastUtils.showShort("已拨通，等待对方接听");
    }

    @Override
    public void onAccepted() {
        Log.e(TAG, "onAccepted: "+ringPlayUtil );
//        ToastUtils.showShort("对方已接听");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ringPlayUtil.stopPlay();
                if (callType==GIVE_CALL){
                    timerTask.cancel();//对方接听，计时任务结束
                    voiceIpDialog.setMsg("已接听，正在通话中");
                    voiceIpDialog.setSendVoiceMsg(View.GONE);
                    bGive_Call = true;
                    addReceiveCall("呼叫"+dialogTitle,"Y-call",give_call_voip,"","out");

                }else {
                    voiceIpDialog.setMsg("已接听，正在通话中");
                }
            }
        });
    }
    @Override
    public void onNetWorkUnstable() {
        ToastUtils.showShort("网络不可用");
        dismissVoipDialog(0);
        Log.e(TAG, "onNetWorkUnstable: " );
    }

    @Override
    public void onVoicePause() {
        Log.e(TAG, "onVoicePause: " );
    }

    @Override
    public void onVoiceResume() {
        Log.e(TAG, "onVoiceResume: " );
    }

    @Override
    public void onCallDisconnected(CallingState callingState) {

        bVoipShow = false;
        Log.e(TAG, "onCallDisconnected: " +callingState+bGive_Call+callStation+timerTask+timer);
//        ToastUtils.showLong(bGive_Call+"--"+callStation+"--"+callingState);
        timerTask.cancel();//任意一方挂断，结束计时任务
        if (callingState.toString().equals("Offline")){
            ToastUtils.showLong("对方未上线");
        }
        if (callingState.toString().equals("Busy")){
//            ringPlayUtil.setRingStopListner(new RingPlayUtil.RingStopListner() {
//                @Override
//                public void ringStopCallback() {
//                    dismissVoipDialog(0);
//                }
//            });
            ringPlayUtil = new RingPlayUtil(this);
            ringPlayUtil.setRingStopListner(new RingPlayUtil.RingStopListner() {
                @Override
                public void ringStopCallback() {
                    dismissVoipDialog(0);
                }
            });
            ringPlayUtil.ringByRaw(getApplication(), R.raw.call_ring_busy);
//        }else if (!bGive_Call&&callStation.contains("giveCall")){
        }else if (callingState.toString().equals("Declined")){
            Log.e(TAG, "onCallDisconnected: 11111111111 ");
            callStation = "cancle";
            ringPlayUtil = new RingPlayUtil(this);
            ringPlayUtil.setRingStopListner(new RingPlayUtil.RingStopListner() {
                @Override
                public void ringStopCallback() {
                    dismissVoipDialog(0);
                }
            });
            ringPlayUtil.ringByRaw(mContext, R.raw.call_reject);
        }else {
            dismissVoipDialog(0);
        }
        if (callType==GIVE_CALL){
            if (bGive_Call){
                bGive_Call=false;
            }else {
                if (!bReject){
                    bReject = true;
                    addReceiveCall("呼叫"+dialogTitle,"N-call",give_call_voip,"","out");
                }
//                Log.e(TAG, "onCallDisconnected: "+dialogTitle );
            }

        }else if (callType==RECEIVE_CALL){
            if (!bReject&&!bReceive){//未接听，未拒绝，对方挂断的情况下记录一条数据
                bReject = true;
                addReceiveCall(dialogTitle+"来电","N-call",receiveCallVoip,"","out");
            }
//                Log.e(TAG, "onCallDisconnected1: "+dialogTitle );
        }

    }



    @Override
    public void onConferenceCreate(String s) {
//        ToastUtils.showLong(s);
        Log.e(TAG, "onConferenceCreate: "+s );
        if (callType==GIVE_MEETING){
            addReceiveCall("发起会议","Y-meeting","",s,"out");
        }
    }

    @Override
    public void onMemberJoined(String s) {
        Log.e(TAG, "onMemberJoined1: "+s+"加入会议" );
        ToastUtils.showShort(s+"加入会议");

        if (callType==REJOIN_MEETING){

            Boolean bReVoip=false;//判断列表里是否有该用户
            for (int i = 0; i < listReJoinMeeting.size(); i++) {
                if (listReJoinMeeting.get(i).getUserId().equals(s)){
                    bReVoip=true;
                    break;
                }
            }
            if (!bReVoip){
                MeetingUserBean meetingUserBean=new MeetingUserBean();
                meetingUserBean.setNickname(s);
                meetingUserBean.setUserId(s);
                meetingUserBean.setJoinStatus("等待加入");

                for (int k = 0; k < listUsers.size(); k++) {
                    if (listUsers.get(k).getVOIPId().equals(s)){
                        meetingUserBean.setNickname(listUsers.get(k).getUserName());
                    }
                }
                listReJoinMeeting.add(meetingUserBean);
            }

        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (callType==REJOIN_MEETING){
                    voiceIpDialog.setMeetingList(listReJoinMeeting);
                }
                if (voiceIpDialog!=null){
                    voiceIpDialog.refreshMeetingList(s,"加入会议" );
                }
            }
        });

    }

    @Override
    public void onMemberExited(String s) {
        ToastUtils.showShort(s+"退出会议");
        Log.e(TAG, "onMemberJoined: "+s+"退出会议" );
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (voiceIpDialog!=null){
                    voiceIpDialog.refreshMeetingList(s,"退出会议");
                }
            }
        });
    }

    @Override
    public void onSpeakers(String s) {
        Log.e(TAG, "onSpeakers: "+s+"正在发言");
        ToastUtils.showLong(s+"正在发言");
    }


    @Override
    public void onReceiveInvite(String s, String s1, String s2, String s3, List<ConferenceMember> list) {
//        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
//            Log.e(TAG, "onReceiveInvite: " );
//
//        }
        Log.e(TAG, "onReceiveInvite: "+s+"--"+"--"+s1 );
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "onReceiveInvite: "+s+"--"+"--"+s1 );
//                ToastUtils.showShort("邀请您参加会议");

                String startName = s1;
                for (int i = 0; i < listUsers.size(); i++) {
                    if (listUsers.get(i).getVOIPId().equals(s1)){
                        startName = listUsers.get(i).getUserName();
                    }
                }
                List<MeetingUserBean> conferenceMemberList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    MeetingUserBean meetingUserBean = new MeetingUserBean();
                    if (list.get(i).nickname==null){
                        meetingUserBean.setNickname(list.get(i).userId);
                        for (int j = 0; j < listUsers.size(); j++) {
                            if (listUsers.equals(list.get(i).userId)){
                                meetingUserBean.setNickname(listUsers.get(j).getUserName());
                            }
                        }
                    }else {
                        meetingUserBean.setNickname(list.get(i).nickname);
                    }
                    meetingUserBean.setUserId(list.get(i).userId);
                    conferenceMemberList.add(meetingUserBean);
                }

                for (int i = 0; i < listUsers.size(); i++) {
                    for (int j = 0; j < conferenceMemberList.size(); j++) {
                        if (listUsers.get(i).getVOIPId().equals(conferenceMemberList.get(j).getUserId())){
                            conferenceMemberList.get(j).setNickname(listUsers.get(i).getUserName());
                            break;
                        }
                    }
                    if (i==listUsers.size()-1){
                        if (SPUtils.getInstance().getString(SharedPreference.NUR_LINK_NOBOTHERMODEL).equals("1")){
                            VoipUtils.exitConference();
                            String startMeetingName = s1;
                            for (int j = 0; j < listUsers.size(); j++) {
                                if (listUsers.get(j).getVOIPId().equals(startMeetingName)){
                                    startMeetingName = listUsers.get(j).getUserName();
                                    break;
                                }
                            }
                            addReceiveCall(startMeetingName+"发起会议","N-meeting","",s,"in");
                        }else {
                            showReceiveMeetingDialog(s, startName,conferenceMemberList);
                        }


                    }
                }

            }
        });



    }
    @Override
    public void onPassiveLeave(int i, String s) {
        dismissVoipDialog(0);
        ToastUtils.showLong("会议结束");
//        Log.e(TAG, "onPassiveLeave: 结束会议"+i+"--"+s );
    }

    @Override
    public void onJoinSuccess(String s) {
//        Log.e(TAG, "onJoinSuccess: "+s);
        ToastUtils.showLong("加入会议");
    }

    @Override
    public void onJoinFailed(int i, String s) {
        dismissVoipDialog(0);
        ToastUtils.showLong("会议结束");
//        Log.e(TAG, "onJoinFailed: "+i+s );
    }

    @Override
    public void onReceiveEmergencyCall(String s, String s1, String s2) {

    }

    @Override
    public void onEmergencyCallAccepted() {

    }


    private VoiceIpDialog voiceIpDialog;
    private SendVoiceMsgDialog sendVoiceMsgDialog;
    private String dialogTitle = "";
    private String dialogCallStatus="";
    public static List<NativePhoneBean> listUsers= GreenDaoHelper.getDaoSession().getNativePhoneBeanDao().queryBuilder().list();

    private Boolean bReceive;//判断是否已经接听，已接听再挂断按照接听存历史记录
    private Boolean bReject;//判断是否已经挂断，如果主动挂断，收到挂断回调就不用再记录，收到回调挂断主动挂断就不用记录
    private String receiveCallVoip="";//记录来电用户的VoIP
    private Boolean bVoipShow = false;//判断是否通话中
    private void showReceiveCallDialog(String callVoip){

        if (sendVoiceMsgDialog!=null){
            sendVoiceMsgDialog.dismiss();
        }




        if (receiveCallCallback!=null){
            receiveCallCallback.receiveCall();
            bReceivecallCallback=false;
        }

        callType = RECEIVE_CALL;
        ringPlayUtil.playSoundByMediaPlayer();
        dismissVoipDialog(1);
        voiceIpDialog.setTitle(dialogTitle);
        voiceIpDialog.setMsg(dialogCallStatus);
        voiceIpDialog.setAcceptCallShow(View.VISIBLE);
        voiceIpDialog.setRejectCallShow(View.VISIBLE);
        voiceIpDialog.setNoVoiceShow(View.GONE);
        voiceIpDialog.setOutVoiceShow(View.GONE);
        receiveCallVoip = callVoip;

        bReceive=false;
        bReject = false;


        //来电设置语音接听code
        voiceIpDialog.setbVoiceToText(true);

        voiceIpDialog.setCallBack(new VoiceIpDialog.CallBack() {
            @Override
            public void sure() {
                Log.e(TAG, "sure: "+ringPlayUtil );
                ringPlayUtil.stopPlay();
                voiceIpDialog.setAcceptCallShow(View.GONE);
                voiceIpDialog.setRejectCallShow(View.VISIBLE);
                voiceIpDialog.setNoVoiceShow(View.VISIBLE);
                voiceIpDialog.setOutVoiceShow(View.VISIBLE);
                VoipUtils.answerCall();
                bReceive=true;
                addReceiveCall(dialogTitle+"来电","Y-call",callVoip,"","in");
            }

            @Override
            public void cancle() {
                dismissVoipDialog(0);
                VoipUtils.endCall();
                if (!bReceive){
                    if (!bReject){
                        bReject = true;
                        addReceiveCall(dialogTitle+"来电","N-call",callVoip,"","in");
                    }

                }
            }

            @Override
            public void noVoice() {

            }

            @Override
            public void outVoice() {

            }

            @Override
            public void sendVoiceMsg() {

            }
        });

        voiceIpDialog.show();
        bVoipShow = true;
        voiceIpDialog.setCanceledOnTouchOutside(false);
        voiceIpDialog.setCancelable(false);
    }

    private String callStation = "";//判断当前的拨号状态
    private void showGiveCallDialog(String callOutName){

        if (spUtils.getString(SharedPreference.USERPERMISSION_TYPE).equals("PAT")){
            WebApiManager.getUserPermission(spUtils.getString(SharedPreference.USERCODE), new CommonCallBack<PermissionBean>() {
                @Override
                public void onFail(String code, String msg) {
                    ToastUtils.showLong(msg);
                }

                @Override
                public void onSuccess(PermissionBean bean1, String type) {
                    SPUtils.getInstance().put(SharedPreference.USERPERMISSION_TYPE, bean1.getUserType());
                    SPUtils.getInstance().put(SharedPreference.USERPERMISSION_PATCONTROL, bean1.getPatControl());
                    if (spUtils.getString(SharedPreference.USERPERMISSION_PATCONTROL).equals("1")){
                        ToastUtils.showLong("当前时间不能拨号");
                    }else {
                        makeCallByName(callOutName);
                    }
                }
            });

        }else{
            makeCallByName(callOutName);
        }

    }
    private void makeCallByName(String callOutName){
        bReject = false;

        Log.e(TAG, "showGiveCallDialog1: "+callOutName );
        callType = GIVE_CALL;
        dismissVoipDialog(1);
        voiceIpDialog.setMsg("正在等待对方接听...");
        String callTitle = callOutName;
        for (int j = 0; j < listUsers.size(); j++) {
            if (listUsers.get(j).getVOIPId().equals(callOutName)){
                callTitle=listUsers.get(j).getUserName();
                Log.e(TAG, "showGiveCallDialog2: "+ callTitle);
                voiceIpDialog.setTitle(callTitle);
                break;
            }
        }
        Log.e(TAG, "showGiveCallDialog: "+callTitle );
        voiceIpDialog.setTitle(callTitle);
        dialogTitle = callTitle;
        voiceIpDialog.setAcceptCallShow(View.GONE);
        Log.e(TAG, "showGiveCallDialog: "+ringPlayUtil );
//            ringPlayUtil.callOutSoundByMediaPlayer(getApplication());
        String finalCallTitle = callTitle;
        voiceIpDialog.setCallBack(new VoiceIpDialog.CallBack() {
            @Override
            public void sure() {

            }

            @Override
            public void cancle() {
                callStation="rjBySelf";
                dismissVoipDialog(0);
                VoipUtils.endCall();
                if (bGive_Call){
                    //挂断回调中再复制false
//                    bGive_Call=false;
                }else {
                    if (!bReject){
                        bReject = true;
                        addReceiveCall("呼叫"+dialogTitle,"N-call",give_call_voip,"","out");
                    }
                }
            }

            @Override
            public void noVoice() {

            }

            @Override
            public void outVoice() {

            }

            @Override
            public void sendVoiceMsg() {
                callStation="sendVoiceMsg";
                VoipUtils.endCall();
                dismissVoipDialog(2);
                if (sendVoiceMsgDialog!=null&&sendVoiceMsgDialog.isShowing()){
                    sendVoiceMsgDialog.dismiss();

                }

                sendVoiceMsgDialog = new SendVoiceMsgDialog(getApplicationContext());

                if (Build.VERSION.SDK_INT>=26) {//8.0新特性
                    sendVoiceMsgDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                }else{
                    sendVoiceMsgDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
                sendVoiceMsgDialog.setToUserCode(callOutName);
                sendVoiceMsgDialog.setTitle("给"+ finalCallTitle +"留言");
                sendVoiceMsgDialog.show();
            }
        });

        timer = new Timer();
        Field field;
        try {
            field = TimerTask.class.getDeclaredField("state");
            field.setAccessible(true);
            field.set(timerTask, 0);
            //计时留言改成主动点击留言
            timer.schedule(timerTask, 30000);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        voiceIpDialog.show();
        Log.e(TAG, "makeCallByName: "+callOutName+"--"+ spUtils.getString(SharedPreference.USERNAME) );
        VoipUtils.makeCall(callOutName,spUtils.getString(SharedPreference.USERNAME));
        give_call_voip = callOutName;
        callStation = "giveCall";
        bVoipShow = true;
        voiceIpDialog.setCanceledOnTouchOutside(false);
        voiceIpDialog.setCancelable(false);
    }


    private void showGiveMeetingDialog(String[] meetingNames){
        dismissVoipDialog(1);
        callType = GIVE_MEETING;
        List listNames = new ArrayList();
        for (int j = 0; j < meetingNames.length; j++) {
            MeetingUserBean meetingUserBean = new MeetingUserBean();
            meetingUserBean.setNickname(meetingNames[j]);
            meetingUserBean.setUserId(meetingNames[j]);
            meetingUserBean.setJoinStatus("等待加入");

            for (int k = 0; k < listUsers.size(); k++) {
                if (listUsers.get(k).getVOIPId().equals(meetingNames[j])){
                    meetingUserBean.setNickname(listUsers.get(k).getUserName());
                }
            }

            listNames.add(meetingUserBean);
            if (j==meetingNames.length-1){
//                                sureDialog.setMsg("邀请人员：");
                voiceIpDialog.setTitle("发起会议");
                voiceIpDialog.setMeetingList(listNames);

                voiceIpDialog.setAcceptCallShow(View.GONE);
                voiceIpDialog.setRejectCallShow(View.VISIBLE);
                voiceIpDialog.setNoVoiceShow(View.VISIBLE);
                voiceIpDialog.setOutVoiceShow(View.VISIBLE);
                voiceIpDialog.setTvDestroyMeetingShow(View.VISIBLE);
            }
        }
        voiceIpDialog.setCallBack(new VoiceIpDialog.CallBack() {

            @Override
            public void sure() {
                Log.e(TAG, "sure: "+ringPlayUtil );
//                    ringPlayUtil.stopPlay();
            }

            @Override
            public void cancle() {
                dismissVoipDialog(0);
                VoipUtils.exitConference();

            }

            @Override
            public void noVoice() {

            }

            @Override
            public void outVoice() {
//                dismissVoipDialog(0);
//                VoipUtils.destroyConference();
            }

            @Override
            public void sendVoiceMsg() {
            }
        });

        VoipUtils.createAndJoinConference(meetingNames);

//        for (int i = 0; i < meetingNames.length; i++) {
//            Log.e(TAG, "showGiveMeetingDialog:--"+meetingNames[i] +"--");
//        }

        voiceIpDialog.show();
        bVoipShow = true;
        voiceIpDialog.setCanceledOnTouchOutside(false);
        voiceIpDialog.setCancelable(false);
    }

    private void showReceiveMeetingDialog(String s, String s1, List<MeetingUserBean> list){

        if (spUtils.getString(SharedPreference.USERPERMISSION_TYPE).equals("PAT")){
            WebApiManager.getUserPermission(spUtils.getString(SharedPreference.USERCODE), new CommonCallBack<PermissionBean>() {
                @Override
                public void onFail(String code, String msg) {
                    ToastUtils.showLong(msg);
                }

                @Override
                public void onSuccess(PermissionBean bean1, String type) {
                    SPUtils.getInstance().put(SharedPreference.USERPERMISSION_TYPE, bean1.getUserType());
                    SPUtils.getInstance().put(SharedPreference.USERPERMISSION_PATCONTROL, bean1.getPatControl());
                    if (spUtils.getString(SharedPreference.USERPERMISSION_PATCONTROL).equals("1")){
                        ToastUtils.showLong("没有呼出权限");
                        return;
                    }
                }
            });

        }else {
            if (sendVoiceMsgDialog!=null){
                sendVoiceMsgDialog.dismiss();
            }

            dismissVoipDialog(1);
            callType = RECEIVE_MEETING;
            bReceive_Meeting=false;

//            ringPlayUtil.playSoundByMediaPlayer();

            voiceIpDialog.setTitle(s1+"发起会议");
            voiceIpDialog.setMsg("");
            if (list!=null){
                for (int j = 0; j < list.size(); j++) {
                    list.get(j).setJoinStatus("等待加入");
                }
            }
            voiceIpDialog.setMeetingList(list);
            voiceIpDialog.setAcceptCallShow(View.VISIBLE);
            voiceIpDialog.setRejectCallShow(View.VISIBLE);
            voiceIpDialog.setNoVoiceShow(View.GONE);
            voiceIpDialog.setOutVoiceShow(View.GONE);

            //来电设置语音接听code
            voiceIpDialog.setbVoiceToText(true);

            voiceIpDialog.setCallBack(new VoiceIpDialog.CallBack() {



                @Override
                public void sure() {
                    Log.e(TAG, "sure: "+ringPlayUtil );
                    ringPlayUtil.stopPlay();
                    VoipUtils.joinConference(s);
                    addReceiveCall(s1+"发起会议","Y-meeting","",s,"in");
                    bReceive_Meeting=true;
                    voiceIpDialog.setAcceptCallShow(View.GONE);
                    voiceIpDialog.setRejectCallShow(View.VISIBLE);
                    voiceIpDialog.setNoVoiceShow(View.VISIBLE);
                    voiceIpDialog.setOutVoiceShow(View.VISIBLE);
                    voiceIpDialog.refreshMeetingList(spUtils.getString(SharedPreference.USERCODE),"加入会议" );

                }

                @Override
                public void cancle() {
                    VoipUtils.exitConference();
                    Log.e(TAG, "cancle: "+bReceive_Meeting );
                    if (bReceive_Meeting){
                        bReceive_Meeting=false;
                    }else {
                        addReceiveCall(s1+"发起会议","N-meeting","",s,"in");
                    }

                    dismissVoipDialog(0);

                }

                @Override
                public void noVoice() {
                }

                @Override
                public void outVoice() {
                }
                @Override
                public void sendVoiceMsg() {

                }
            });
            voiceIpDialog.show();
            bVoipShow = true;
            voiceIpDialog.setCanceledOnTouchOutside(false);
            voiceIpDialog.setCancelable(false);
        }

    }

    private List<MeetingUserBean> listReJoinMeeting = new ArrayList<>();
    public void showRejoinMeetingDialog(String meetingTitle,String meetingNum){
        dismissVoipDialog(1);
        callType = REJOIN_MEETING;
        voiceIpDialog.setTitle(meetingTitle);
        voiceIpDialog.setMsg("");

        listReJoinMeeting = new ArrayList<>();
        voiceIpDialog.setMeetingList(listReJoinMeeting);

        voiceIpDialog.setAcceptCallShow(View.GONE);
        voiceIpDialog.setRejectCallShow(View.VISIBLE);
        voiceIpDialog.setNoVoiceShow(View.VISIBLE);
        voiceIpDialog.setOutVoiceShow(View.VISIBLE);
//        voiceIpDialog.setTvDestroyMeetingShow(View.VISIBLE);

        voiceIpDialog.setCallBack(new VoiceIpDialog.CallBack() {

            @Override
            public void sure() {
            }

            @Override
            public void cancle() {
                VoipUtils.exitConference();
                dismissVoipDialog(0);

            }

            @Override
            public void noVoice() {
            }

            @Override
            public void outVoice() {
            }
            @Override
            public void sendVoiceMsg() {

            }
        });
        VoipUtils.joinConference(meetingNum);
        voiceIpDialog.show();
        bVoipShow = true;
        voiceIpDialog.setCanceledOnTouchOutside(false);
        voiceIpDialog.setCancelable(false);
    }

    private void dismissVoipDialog1(int ifnew){
        if (voiceIpDialog!=null&&voiceIpDialog.isShowing()){
            voiceIpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
//                    VoipUtils.unMute();
                    VoipUtils.openSpeaker();
                }
            });

            voiceIpDialog.dismiss();

        }
    }

//    KeyguardManager  km;
//    KeyguardManager.KeyguardLock kl;
//    private Boolean isLock=false;
    //关闭dialog;;ifnew:是否初始化dialog 1,初始，0，不,2，发送语音留言
    private void dismissVoipDialog(int ifnew){
        if (voiceIpDialog!=null&&voiceIpDialog.isShowing()){
            voiceIpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    VoipUtils.unMute();
                    VoipUtils.openSpeaker();
                    Log.e(TAG, "onDismiss:11222 "+ifnew+callStation );
                    if (ifnew!=2&&!callStation.equals("sendVoiceMsg")){
//                        SpeechRecognizerManager.stopNow();
                    }
                    Log.e(TAG, "onDismiss: 11111111111112" );
                }
            });

            if ("giveCall".equals(callStation)){
                callStation = callStation+"DisMiss";
            }else {
                callStation ="DisMiss";
            }

//            if (isLock){
//                isLock = false;
//                kl.reenableKeyguard();
//            }
            voiceIpDialog.dismiss();
            bVoipShow = false;
            Log.e(TAG, "dismissVoipDialog: "+ringPlayUtil );
            ringPlayUtil.stopPlay();
            if (timer!=null){
                timer.cancel();
                timer.purge();
                timer   = null;
            }
        }
        if (ifnew==1){
            setVoiceIpDialog();
        }
    }

    //初始化dialog
    private void setVoiceIpDialog(){
        voiceIpDialog = new VoiceIpDialog(getApplicationContext());

        if (Build.VERSION.SDK_INT>=26) {//8.0新特性
            voiceIpDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        }else{
            voiceIpDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
    }



    private int callType=-1;
    public final int GIVE_CALL = 1001;
    public final int RECEIVE_CALL = 1002;
    public final int GIVE_MEETING = 2001;
    public final int RECEIVE_MEETING = 2002;
    public final int REJOIN_MEETING = 2003;

    public Boolean bGive_Call=false;//判断去电是否接通
    public String give_call_voip="";//记录去电用户VOip

    public Boolean bReceive_Meeting=false;//判断来会议是否加入

    private void addReceiveCall(String name,String status,String voipId,String MeetingNum,String outOrIn){
        Log.e(TAG, "addReceiveCall: "+ MeetingNum);
        //callName 来电用户
        CallingHistoryBean.callBean callBean=new CallingHistoryBean.callBean();
        callBean.setCallName(name);
        callBean.setStatus(status);
        callBean.setPhoneNumber(voipId);
        callBean.setMeetingNumber(MeetingNum);
        callBean.setOutOrIn(outOrIn);
        callBean.setTime(DateUtils.getDateFromSystem()+"\n"+ DateUtils.getTimeFromSystem());
        addCallingBean(callBean);
    }


    private void addCallingBean(CallingHistoryBean.callBean bean){
        CallingHistoryBean callingHistoryBean ;
        callingHistoryBean= GsonUtils.fromJson(SPUtils.getInstance().getString(SharedPreference.NUR_LINK_CALLING_HISTORY),CallingHistoryBean.class);

        if (callingHistoryBean==null){
            callingHistoryBean  = new CallingHistoryBean();
        }
        if (callingHistoryBean.getCallList().size()>30){
            callingHistoryBean.getCallList().remove(0);
        }
        callingHistoryBean.getCallList().add(bean);

        SPUtils.getInstance().put(SharedPreference.NUR_LINK_CALLING_HISTORY,GsonUtils.toJson(callingHistoryBean));
    }



    private long curPttTime=System.currentTimeMillis();
    public class MainReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG+"111111111", "onReceive: dddd" +intent.getAction());
            if (Action.CALL_RING_SERVICE.equals(intent.getAction())) {
                String code = intent.getStringExtra("code");
                Log.e(TAG+"111111111", "onReceive: kkk" +code);
//                if (code.equals("0")){
//                    ringPlayUtil.playSoundByMediaPlayer();
//                }else if (code.equals("1")){
//                    ringPlayUtil.stopPlay();
//                }else if (code.equals("2")){
//                    ringPlayUtil.sendSoundByMediaPlayer(getApplication());
//                }else if (code.equals("3")){//拨号时的嘟嘟声
//                    ringPlayUtil.callOutSoundByMediaPlayer(getApplication());
//                }else if (code.equals("4")){//拨号时结束嘟嘟声
//                    ringPlayUtil.stopPlay();
//                }

            }else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())){
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo.State wifiState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();//获取wifi网络状态

                NetworkInfo.State mobileState = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();//获取移

                Log.e(TAG, "onReceive: "+wifiState+"----"+mobileState );
            }
        }
    }

    private Timer timer; // 计时器
    //计时任务，没人接听，固定时间后主动挂断
    private TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            Log.e(TAG, "run: ");
            dismissVoipDialog(0);
            VoipUtils.endCall();
            if (bGive_Call){
                //挂断回调中再复制false
//             bGive_Call=false;
            }else {
                if (!bReject){
                    bReject = true;
                    addReceiveCall("呼叫"+dialogTitle,"N-call",give_call_voip,"","out");
                }
            }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        if (mainReceiver != null) {
            unregisterReceiver(mainReceiver);
        }
    }


    private VoipBinder mBinder = new VoipBinder();
    private Context mContext=this;
    private String TAG = "VoipService11111111";
    private long callCurrtenTime=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    //用于Activity和service通讯
    public class VoipBinder extends Binder {
        public void setContext(Context context){ToastUtils.showLong(context.toString());
        }
        public void callOutVoipDialog(String callType,String callOutName1,String[] strings1){
//            if (!bLoginVoip){
//                if (callType.equals("calling")){
//                    showGiveCallDialog(callOutName1);
//                }else if (callType.equals("meeting")){
//                    showGiveMeetingDialog(strings1);
//                }else {//重新加入会议--calltype:会议标题；calloutname1:会议ID
//                    showRejoinMeetingDialog(callType,callOutName1);
//                }
//            }else {
//                ToastUtils.showLong("Voip未登陆，请重试");
//                initVoip();
//            }

            Log.e(TAG, "callOutVoipDialog: "+callCurrtenTime );
            if (System.currentTimeMillis()-callCurrtenTime<1000){
                Log.e(TAG, "callOutVoipDialog: 点击过于频繁");
            }else {
                callCurrtenTime=System.currentTimeMillis();
                if (callType.equals("calling")){
                    showGiveCallDialog(callOutName1);
                }else if (callType.equals("meeting")){
                    showGiveMeetingDialog(strings1);
                }else {//重新加入会议--calltype:会议标题；calloutname1:会议ID
                    showRejoinMeetingDialog(callType,callOutName1);
                }
            }
        }

        public MLinkServiceNewOrd getMLinkService(){
            return MLinkServiceNewOrd.this;
        }
    }

    public receiveCallCallback receiveCallCallback;
    public void setbReceivecallCallback(receiveCallCallback receivecallCallback){
        this.receiveCallCallback = receivecallCallback;
    }
    public interface receiveCallCallback{
        public void receiveCall();
    }

    private void showNotification(Context context, String soundFlag, String vibrateFlag) {
        Boolean bLight = spUtils.getBoolean(SharedPreference.LIGHT, true);
        Boolean bSound = spUtils.getBoolean(SharedPreference.LIGHT, true) && soundFlag.equals("1");
        Boolean bVibrator = spUtils.getBoolean(SharedPreference.LIGHT, true) && vibrateFlag.equals("1");

        NotificationCompat.Builder builder = null;
        NotificationChannel channel1, channel2;
        if (Build.VERSION.SDK_INT >= 26) {
            if (notificationManager != null) {
                notificationManager.deleteNotificationChannel("mobilenurse1");
                notificationManager.deleteNotificationChannel("mobilenurse2");
            }
            channel1 = new NotificationChannel("mobilenurse1", "响铃消息", NotificationManager.IMPORTANCE_HIGH);
            channel2 = new NotificationChannel("mobilenurse2", "静音消息", NotificationManager.IMPORTANCE_HIGH);


            if (notificationManager != null) {
                //Android8.0消息提示音是否响铃配置 还需处理
                if (bSound) {
                    channel1.getAudioAttributes();
                    notificationManager.createNotificationChannel(channel1);
                    builder = new NotificationCompat.Builder(context, "mobilenurse1");
                } else {
                    channel2.setSound(null, null);
                    notificationManager.createNotificationChannel(channel2);
                    builder = new NotificationCompat.Builder(context, "mobilenurse2");
                }
            }
        } else {
            builder = new NotificationCompat.Builder(context);
        }
        Intent intent =null;
        Class<? extends BaseFragment> aClass = null;
        try {
            aClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.Activity.MainActivity");
            intent = new Intent(context, aClass);
            intent.putExtra("message", 2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        /**设置通知左边的大图标**/
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_all_normal))
                /**设置通知右边的小图标**/
                .setSmallIcon(R.drawable.icon_all_normal)
                /**通知首次出现在通知栏，带上升动画效果的**/
                .setTicker("通知")
                /**设置通知的标题**/
                .setContentTitle("医护通新消息")
                /**设置通知的内容**/
                .setContentText(notifyMsg)
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
                .setContentIntent(PendingIntent.getActivity(context, 2, intent, PendingIntent.FLAG_CANCEL_CURRENT));

        Notification notification = builder.build();

        if (bVibrator) {
            notification.defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (bSound) {
            notification.defaults |= Notification.DEFAULT_SOUND;
        }
        if (bLight) {
            notification.defaults |= Notification.DEFAULT_LIGHTS;
        }
        //      发起通知
        notificationManager.notify(2, notification);    }




}
