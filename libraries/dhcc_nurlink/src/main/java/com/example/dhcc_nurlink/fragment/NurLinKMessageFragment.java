package com.example.dhcc_nurlink.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.adapter.MessageUserAdapter;
import com.example.dhcc_nurlink.adapter.MessageVoiceAdapter;
import com.example.dhcc_nurlink.bean.MessageListBean;
import com.example.dhcc_nurlink.bean.NoteBean;
import com.example.dhcc_nurlink.bean.PhoneBookListBean;
import com.example.dhcc_nurlink.dialog.VoicePlayDialog;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;
import com.raisound.speech.SpeechRecognizerManager;
import com.raisound.speech.http.callback.RequestCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * MessageFragment
 * 消息页面
 */
public class NurLinKMessageFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyTel;
    private TextView tvNur,tvVoice;
    private MessageUserAdapter messageUserAdapter = new MessageUserAdapter(new ArrayList<>());
    private MessageVoiceAdapter messageVoiceAdapter = new MessageVoiceAdapter(new ArrayList<>());
    private ArrayList userList = new ArrayList();
    private MessageListBean messageListBean = new MessageListBean();
    private List beanTemp = new ArrayList();
    private List beanVoicTemp = new ArrayList();


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nurlink_message
                , container, false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }else {
            messageVoiceAdapter.stop();
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
        setToolbarCenterTitle(getString(R.string.str_tab_message), 0xffffffff, 21);

        initAdapter();
        initView(view);
        initData();
        //注册事件总线
        EventBus.getDefault().register(this);
    }

    private void initView(View view) {
        recyTel = view.findViewById(R.id.recy_phone);
        recyTel.setHasFixedSize(true);
        recyTel.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyTel.setAdapter(messageUserAdapter);
        tvNur = view.findViewById(R.id.tv_msg_nur);
        tvVoice = view.findViewById(R.id.tv_msg_voice);
        tvNur.setSelected(true);
        tvNur.setOnClickListener(v -> showNurMsg());
        tvVoice.setOnClickListener(v -> showVoiceMsg());
    }

    private void showNurMsg(){
        messageVoiceAdapter.stop();
        tvNur.setSelected(true);
        tvVoice.setSelected(false);
        List beanTemp = new ArrayList();
        for (int i = 0; i < messageListBean.getMesList().size(); i++) {
            if (!messageListBean.getMesList().get(i).getActionCode().equals("YY")){
                beanTemp.add(messageListBean.getMesList().get(i));
            }
            if (i==messageListBean.getMesList().size()-1){

                recyTel.setAdapter(messageUserAdapter);
                messageUserAdapter.setNewData(beanTemp);
            }
        }
    }
    private void showVoiceMsg(){
        if (messageListBean==null){
            showToast("获取留言信息失败");
            return;
        }
        tvNur.setSelected(false);
        tvVoice.setSelected(true);
        messageVoiceAdapter.setPlayPosition(-1);
        recyTel.setAdapter(messageVoiceAdapter);
    }
    private void initAdapter() {
        messageUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick: 111111"+ messageUserAdapter.getData().get(position).getMsgAddress());
                if (messageUserAdapter.getData().get(position).getActionCode().equals("YY")){
                    playVoiceMsg(messageUserAdapter.getData().get(position).getMsgAddress());
                }else {
                    WebApiManager.readMessage(messageUserAdapter.getData().get(position).getRowId(), new CommonCallBack<PhoneBookListBean>() {
                        @Override
                        public void onFail(String code, String msg) {
                            showToast(msg);
                        }

                        @Override
                        public void onSuccess(PhoneBookListBean bean, String type) {
                            Bundle bundle = new Bundle();
                            bundle.putString("userName",messageUserAdapter.getData().get(position).getMsgFromUserName());
                            bundle.putString("userCode",messageUserAdapter.getData().get(position).getMsgFromUserCode());
                            bundle.putString("messageCode",messageUserAdapter.getData().get(position).getRowId());
                            bundle.putString("messageTime",messageUserAdapter.getData().get(position).getRecDateTime());
                            bundle.putString("messageCotent",messageUserAdapter.getData().get(position).getContext());
                            bundle.putString("messageStatus",messageUserAdapter.getData().get(position).getMesStatus());
                            bundle.putString("messageType",messageUserAdapter.getData().get(position).getActionDesc());
                            bundle.putString("ActionCode",messageUserAdapter.getData().get(position).getActionCode());
                            startFragment(NurLinkMessageDetailFragment.class,bundle);
                        }
                    });

                }

            }
        });

        messageVoiceAdapter.setVoiceFinishCallBack(new MessageVoiceAdapter.voiceFinishCallBack() {
            @Override
            public void finish() {
                initData();
            }
        });

        messageVoiceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.ll_message_rightmenu){
//                 showToast("删除"+noteBookAdapter.getData().get(position).getSoundId());
                    deleteMsgVoice(messageVoiceAdapter.getItem(position).getRowId());
                }

                if (view.getId() ==R.id.tv_playvoice){


                    if (messageVoiceAdapter.getData().get(position).getMsgAddress()!=null&& !messageVoiceAdapter.getData().get(position).getMsgAddress().isEmpty()){

                        messageVoiceAdapter.setPlayPosition(position);
                        messageVoiceAdapter.setPlayTime(0);
                        messageVoiceAdapter.setPATH(messageVoiceAdapter.getData().get(position).getMsgAddress()+"");
                        messageVoiceAdapter.setNewData(beanVoicTemp);

                    }else {

                    }
                    WebApiManager.readMessage(messageVoiceAdapter.getData().get(position).getRowId(), new CommonCallBack<PhoneBookListBean>() {
                        @Override
                        public void onFail(String code, String msg) {
                            showToast(msg);
                        }

                        @Override
                        public void onSuccess(PhoneBookListBean bean, String type) {
                            bgRefresh=1;
                            initData();
                        }
                    });


                }
            }
        });

    }
    private void deleteMsgVoice(String rowId){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        WebApiManager.deleteMsgData(rowId, new CommonCallBack<NoteBean>() {
            @Override
            public void onFail(String code, String msg) {
                showToast(msg);
            }

            @Override
            public void onSuccess(NoteBean bean, String type) {
                showToast("删除成功");
//                messageVoiceAdapter.setNewData(new ArrayList<>());
                WebApiManager.getMessageList(new CommonCallBack<MessageListBean>() {
                    @Override
                    public void onFail(String code, String msg) {
                        hideLoadingTip();
                        showToast(msg);
                    }

                    @Override
                    public void onSuccess(MessageListBean bean, String type) {
                        hideLoadingTip();
                        messageListBean = bean;
                        beanVoicTemp = new ArrayList();
                        for (int i = 0; i < bean.getMesList().size(); i++) {
                            if (bean.getMesList().get(i).getActionCode().equals("YY")){
                                beanVoicTemp.add(bean.getMesList().get(i));
                            }
                            if (i==bean.getMesList().size()-1){
                                messageVoiceAdapter.setNewData(beanVoicTemp);
                            }
                        }

                    }
                });

            }
        });
    }

    private int bgRefresh=0;
    private void initData() {

        showLoadingTip(BaseActivity.LoadingType.FULL);
        WebApiManager.getMessageList(new CommonCallBack<MessageListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(MessageListBean bean, String type) {
                hideLoadingTip();
                messageListBean = bean;
                beanTemp = new ArrayList();
                for (int i = 0; i < bean.getMesList().size(); i++) {
                    if (!bean.getMesList().get(i).getActionCode().equals("YY")){
                        beanTemp.add(bean.getMesList().get(i));
                    }
                    if (i==bean.getMesList().size()-1){
                        messageUserAdapter.setNewData(beanTemp);
                    }
                }
                beanVoicTemp = new ArrayList();
                for (int i = 0; i < messageListBean.getMesList().size(); i++) {
                    if (messageListBean.getMesList().get(i).getActionCode().equals("YY")){
                        beanVoicTemp.add(messageListBean.getMesList().get(i));
                    }
                    if (i==messageListBean.getMesList().size()-1){
                        if (bgRefresh==0){
                            messageVoiceAdapter.setNewData(beanVoicTemp);
                        }
                    }
                }

                bgRefresh=0;
            }
        });


    }

    private void playVoiceMsg(String msgFileId){

        SpeechRecognizerManager.getRecordMp3(msgFileId, new RequestCallback<String>() {
            @Override
            public void onSuccess(int i, String o) {
                Log.e(TAG, "onSuccessmp3: 111111111"+o);
                VoicePlayDialog voicePlayDialog = new VoicePlayDialog(getActivity());
                voicePlayDialog.setPATH(o+"");
                voicePlayDialog.show();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG, "onSuccess: 1111111112mp3"+s);
                showToast(s+"");
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


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

//    @Override
//    public void finishCurFragment() {
//
//    }

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

    @Override
    public void onStop() {
        super.onStop();
        messageVoiceAdapter.stop();
    }
}
