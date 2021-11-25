package com.example.dhcc_nurlink.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.MessageEvent;
import com.base.commlibs.constant.Action;
import com.base.commlibs.http.CommonCallBack;
import com.example.dhcc_nurlink.MLinkServiceNewOrd;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.PhoneBookListBean;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * MessageFragment
 * 消息页面
 */
public class NurLinkMessageDetailFragment extends BaseFragment implements View.OnClickListener {


    private TextView tvName,tvCallTime,tvCall,tvCheck,tvReject,tvChecked,tvMsg;
    private String userName = "",userCode="",messageCode = "",messageTime = "",
            messageCotent = "",messageStatus="",messageType="",ActionCode = "";


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message_detail
                , container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hindMap();
//        hindBtn();
//        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
//        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();


        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        //        hideToolbarNavigationIcon();
        setToolbarCenterTitle(getString(R.string.str_tab_messagedetail), 0xffffffff, 21);

        bindService();
        Bundle bundle = getArguments();
        if (bundle!=null && bundle.getString("userName")!=null){
            userName = bundle.getString("userName");
            userCode = bundle.getString("userCode");
            messageCode = bundle.getString("messageCode");
            messageTime = bundle.getString("messageTime");
            messageCotent = bundle.getString("messageCotent");
            messageStatus = bundle.getString("messageStatus");
            messageType = bundle.getString("messageType");
            ActionCode =bundle.getString("ActionCode");
        }
        initAdapter();
        initView(view);
        initData();
        //注册事件总线
        EventBus.getDefault().register(this);
//        hindBtn();
    }

    private void initView(View view) {
        tvName = view.findViewById(R.id.tv_username);
        tvName.setText(userName);
        tvCall = view.findViewById(R.id.tv_call_time);
        tvCall.setText(userCode);
        tvCall = view.findViewById(R.id.tv_tel_call);
        tvCall.setOnClickListener(v -> callUser());

        tvCheck = view.findViewById(R.id.tv_checke);
        tvChecked = view.findViewById(R.id.tv_checked);
        tvReject = view.findViewById(R.id.tv_reject);
        if (messageType.contains("危急值")){

        }else if (messageStatus.contains("已审核")||messageStatus.contains("已拒绝")){
            tvChecked.setVisibility(View.VISIBLE);
            tvChecked.setText(messageStatus);
        }else {
            if (messageType.contains("会诊")){
                tvCheck.setText("接收");
            }
            if (messageType.contains("处方")){
                tvCheck.setText("接受");
                tvReject.setText("申诉");
            }
            tvCheck.setVisibility(View.VISIBLE);
            tvReject.setVisibility(View.VISIBLE);
        }
        tvMsg = view.findViewById(R.id.tv_msg_cotent);
        if (messageCotent.contains("<br>")){
            String msg = messageCotent.replaceAll("<br>","\n\n");
            msg = msg.replaceAll("<br/>","\n\n");
            msg = msg.replaceAll("</br>","\n\n");

            SpannableString span = new SpannableString(msg);
            span.setSpan(new ForegroundColorSpan(Color.BLACK), 0,  msg.indexOf("\n"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new StyleSpan(Typeface.BOLD), 0, msg.indexOf("\n"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvMsg.setText(span);
        }else {
            tvMsg.setText(messageCotent);
        }

            /**
             * 其中最后的参数flag：
             * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE --- 不包含两端start和end所在的端点 (a,b)
             * Spanned.SPAN_EXCLUSIVE_INCLUSIVE --- 不包含端start，但包含end所在的端点 (a,b]
             * Spanned.SPAN_INCLUSIVE_EXCLUSIVE --- 包含两端start，但不包含end所在的端点 [a,b)
             * Spanned.SPAN_INCLUSIVE_INCLUSIVE --- 包含两端start和end所在的端点 [a,b]
             */


//        tvMsg.setText(msg);

//        final String sText = "测试自定义标签：<br><h1><mxgsa>测试自定义标签</mxgsa></h1>";
//        tvMsg.setText(Html.fromHtml(messageCotent));
//        tvMsg.setClickable(true);
//        tvMsg.setMovementMethod(LinkMovementMethod.getInstance());
        tvReject.setOnClickListener(v -> refuseMessage());
        tvCheck.setOnClickListener(v -> auditMessage());

        if (ActionCode.equals("1006")){
            view.findViewById(R.id.ll_action).setVisibility(View.GONE);
        }
    }

    private void callUser(){
        voipBinder.callOutVoipDialog("calling",
                userCode,null);
    }

    private void initAdapter() {
    }
    private void initData() {
    }

    private void auditMessage(){
        WebApiManager.auditMessage(messageCode, new CommonCallBack<PhoneBookListBean>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(PhoneBookListBean bean, String type) {
                showToast(bean.getMsg());
                finish();
            }
        });
    }
    private void refuseMessage(){
        WebApiManager.refuseMessage(messageCode, new CommonCallBack<PhoneBookListBean>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(PhoneBookListBean bean, String type) {
                showToast(bean.getMsg());
                finish();
            }
        });

    }
    @Override
    public void onClick(View v) {
    }


    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (Action.NEWMESSAGE_SERVICE.equals(intent.getAction())) {
            initData();
        }

    }
//    @Override
//    public void finishCurFragment() {
//
//    }
    private ServiceConnection serviceConnection;
    public MLinkServiceNewOrd.VoipBinder voipBinder;

    public void bindService(){
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "服务与活动成功绑定");
                voipBinder = (MLinkServiceNewOrd.VoipBinder) iBinder;
                bindSuccess();
//            voipBinder.setContext(getActivity());
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.e(TAG, "服务与活动成功断开");
            }
        };
        Intent bindIntent = new Intent(getActivity(), MLinkServiceNewOrd.class);
        getActivity().bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void bindSuccess(){

    }

    /**
     * 接收事件- 更新数据
     *
     * @param event
     */
    @Subscribe
    public void updateList(MessageEvent event) {
        Log.e(getClass().getSimpleName(), "updateText:" + event.getType());
        if (event.getType() == MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST) {
            initData();
        }
    }
}
