package com.example.dhcc_nurlink.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.service.MServiceNewOrd;
import com.base.commlibs.utils.SystemTTS;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dhcc_nurlink.MLinkServiceNewOrd;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.Utils.DateUtils;
import com.example.dhcc_nurlink.adapter.TelListUserAdapter;
import com.example.dhcc_nurlink.bean.AllUserBean;
import com.example.dhcc_nurlink.bean.LocksListBean;
import com.example.dhcc_nurlink.bean.MessageListBean;
import com.example.dhcc_nurlink.bean.PhoneBookListBean;
import com.example.dhcc_nurlink.greendao.DaoSession;
import com.example.dhcc_nurlink.greendao.GreenDaoHelper;
import com.example.dhcc_nurlink.greendao.NativePhoneBean;
import com.example.dhcc_nurlink.webserviceapi.WebApiManager;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * com.example.dhcc_nurlink
 * <p>
 * author Q
 * Date: 2021/7/8
 * Time:16:05
 */
public class NurLinkFragment extends BaseFragment {

    private EditText etVoip;
    private TextView tvCall,tvLoc;
    private RecyclerView recyTel;
    private View viewright,viewRedPoint ;
    private TelListUserAdapter telePhoneUserAdapter = new TelListUserAdapter(new ArrayList<>());
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tellist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //状态栏 背景 默认蓝色
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(false);
        showToolbarNavigationIcon(R.drawable.icon_back_white);
        //toolbar 中间标题 默认黑色
        setToolbarCenterTitle("医呼通("+SPUtils.getInstance().getString(SharedPreference.USERNAME)+")", 0xffffffff, 17);


        viewright = View.inflate(getActivity(),R.layout.rightimg_icon_ca, null);
        viewRedPoint = viewright.findViewById(R.id.view_red_point);
        viewRedPoint.setVisibility(View.GONE);
        setToolbarRightCustomView(viewright);
        viewright.setOnClickListener(v -> startMessageFrag());


        initView(view);
//        initData();

        if (SPUtils.getInstance().getString(SharedPreference.NUR_LINK_USERS_LIST).isEmpty() ||
                !DateUtils.getDateFromSystem().equals(SPUtils.getInstance().getString(SharedPreference.NUR_LINK_USERS_LIST))) {
            initDb();
        }else {
            initData(SPUtils.getInstance().getString(SharedPreference.LOCID));
        }
        initAdapter();
        getMsg();
    }
    private void getMsg() {

        WebApiManager.getMessageList(new CommonCallBack<MessageListBean>() {
            @Override
            public void onFail(String code, String msg) {
            }

            @Override
            public void onSuccess(MessageListBean bean, String type) {
                for (int i = 0; i < bean.getMesList().size(); i++) {
                    if (bean.getMesList().get(i).getMesStatus().equals("未读")){
                        viewRedPoint.setVisibility(View.VISIBLE);

                        break;
                    }
                }

            }
        });


    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unBind();
    }

    public void unBind(){
        if (serviceConnection != null && ServiceUtils.isServiceRunning(MServiceNewOrd.class)) {
            getActivity().unbindService(serviceConnection);
        }

    }

    private void initView(View view) {
        etVoip=view.findViewById(R.id.et_voip);
        tvCall= view.findViewById(R.id.tv_call);
        tvCall.setOnClickListener(v -> voipBinder.callOutVoipDialog("calling",etVoip.getText().toString(),null));

        tvLoc= view.findViewById(R.id.tv_loc);
        tvLoc.setOnClickListener(v -> changeLoc());
        tvLoc.setText(SPUtils.getInstance().getString(SharedPreference.LOCDESC));

        recyTel = view.findViewById(R.id.recy_telephone);
        recyTel.setHasFixedSize(true);
        recyTel.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyTel.setAdapter(telePhoneUserAdapter);
    }

    private void initData(String  locId) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        WebApiManager.getPhoneBook(locId,SPUtils.getInstance().getString(SharedPreference.USERID),new CommonCallBack<PhoneBookListBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
            }

            @Override
            public void onSuccess(PhoneBookListBean bean, String type) {
                hideLoadingTip();
                telePhoneUserAdapter.setNewData(bean.getUserList());
                bindService();
            }
        });

    }

    private void initAdapter() {
        telePhoneUserAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_tel_call){

                    voipBinder.callOutVoipDialog("calling",
                            telePhoneUserAdapter.getData().get(position).getVOIPId(),null);

//                    Boolean oneTel = true;
//                    String users = "";
//                    meetingList = new ArrayList<>();
//                    for (int i = 0; i < telePhoneUserAdapter.getData().size(); i++) {
//                        if (telePhoneUserAdapter.getData().get(i).getSelect().equals("1")){
//                            meetingList.add(telePhoneUserAdapter.getData().get(i).getVOIPId());
//                            oneTel = false;
//                            if (!users.isEmpty()){
//                                users = users+"、"+telePhoneUserAdapter.getData().get(i).getUserName();
//                            }else {
//                                users = telePhoneUserAdapter.getData().get(i).getUserName();
//                            }
//                        }
//                    }
//                    String callStr = "";
//
//                    VoipUtil.bohao = true;
//                    if (oneTel){
//                        callStr = "拨号给"+telePhoneUserAdapter.getData().get(position).getUserName();
//                    }else {
//                        callStr = "拨号给"+users;
//                    }
////                    showToast(callStr);
//                    if (!users.contains("、")){
////                        if (SPUtils.getInstance().getString(SharedPreference.VOIP_ID).equals("0144")){
////                            VoipUtils.makeCall("5995");
////                        }else {
////                            VoipUtils.makeCall("0144");
////                        }
//                        VoipUtil.confId="";
////                        VoipUtils.makeCall(telePhoneUserAdapter.getData().get(position).getVOIPId());
//                        VoipUtil.bohaoName = telePhoneUserAdapter.getData().get(position).getVOIPId();
//                        VoipUtil.callName=telePhoneUserAdapter.getData().get(position).getVOIPId();
//                        voipBinder.callOutVoipDialog("calling",
//                                telePhoneUserAdapter.getData().get(position).getVOIPId(),null);
//                    }else {
////                        if (SPUtils.getInstance().getString(SharedPreference.VOIP_ID).equals("0144")){
////                            VoipUtils.createAndJoinConference(new String[]{"5995"});
////                        }else {
////                            VoipUtils.createAndJoinConference(new String[]{"0144"});
////                        }
//                        String[] str = new String[meetingList.size()];
//                        for (int i = 0; i < meetingList.size(); i++) {
//                            str[i] =meetingList.get(i);
//                        }
////                        VoipUtils.createAndJoinConference(str);
//
//                        voipBinder.callOutVoipDialog("meeting",
//                                null,str);
//                    }


                }
                if (view.getId() == R.id.img_telstatu){
                    if (telePhoneUserAdapter.getData().get(position).getSelect().equals("0")){
                        telePhoneUserAdapter.getData().get(position).setSelect("1");
                    }else {
                        telePhoneUserAdapter.getData().get(position).setSelect("0");
                    }
                    telePhoneUserAdapter.notifyItemChanged(position);
                }

            }
        });

    }


    DaoSession daoSession = GreenDaoHelper.getDaoSession();;
    private void initDb() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        WebApiManager.getAllPhoneBook(new CommonCallBack<AllUserBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast(msg);
                initData(SPUtils.getInstance().getString(SharedPreference.LOCID));
            }

            @Override
            public void onSuccess(AllUserBean bean, String type) {
                hideLoadingTip();
                daoSession.getNativePhoneBeanDao().deleteAll();
                for (int i = 0; i < bean.getUserList().size(); i++) {
                    NativePhoneBean nativePhoneBean = new NativePhoneBean();
                    nativePhoneBean.setUserName(bean.getUserList().get(i).getUserName());
                    nativePhoneBean.setUserCode(bean.getUserList().get(i).getUserCode());
                    nativePhoneBean.setPinName(bean.getUserList().get(i).getPinName());
                    nativePhoneBean.setCTLOCDesc(bean.getUserList().get(i).getCTLOCDesc());
                    nativePhoneBean.setVOIPId(bean.getUserList().get(i).getVOIPId());
                    nativePhoneBean.setDeviceId(bean.getUserList().get(i).getDeviceId());
                    nativePhoneBean.setFirstPin(bean.getUserList().get(i).getFirstPin());
                    if (bean.getUserList().get(i).getGroupList().size() > 0) {
                        String strGroup = "";
                        for (int j = 0; j < bean.getUserList().get(i).getGroupList().size(); j++) {
                            strGroup = strGroup + "、" + bean.getUserList().get(i).getGroupList().get(j).getGroupName();
                        }
                    } else {
                        nativePhoneBean.setStrGroupList("");
                    }
                    daoSession.insert(nativePhoneBean);
                    if (i == bean.getUserList().size() - 1) {
                        Log.e(TAG, "onSuccess: " + daoSession.getNativePhoneBeanDao().queryBuilder().list());
                    }
                }
                if (bean.getUserList().size()>0){
                    SPUtils.getInstance().put(SharedPreference.NUR_LINK_USERS_LIST, DateUtils.getDateFromSystem());
                }

                initData(SPUtils.getInstance().getString(SharedPreference.LOCID));

//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
    }


    private LocksListBean locksListBean;
    private void changeLoc() {
        if (locksListBean==null){
            WebApiManager.getLocList(new CommonCallBack<LocksListBean>() {
                @Override
                public void onFail(String code, String msg) {
                    showToast(msg);
                }

                @Override
                public void onSuccess(LocksListBean bean, String type) {
                    locksListBean = bean;
                    showPicker(bean);
                }
            });
        }else {
            showPicker(locksListBean);
        }

    }

    private void showPicker(LocksListBean bean){
        List<String>  listStr = new ArrayList<String>();
        for (int i = 0; i < bean.getLocList().size(); i++) {
            listStr.add(bean.getLocList().get(i).getLocDesc());
        }
        final OptionPicker picker = new OptionPicker(getActivity(), listStr);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        Boolean ifLocRem = true;
        for (int i = 0; i <listStr.size() ; i++) {
            if (SPUtils.getInstance().getString(SharedPreference.LOCDESC).equals(listStr.get(i))){
                picker.setSelectedIndex(i);
                ifLocRem = false;
                break;
            }
        }
        if (ifLocRem){
            picker.setSelectedIndex(0);
        }
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                tvLoc.setText(item);
                initData(bean.getLocList().get(index).getLocId());
            }
        });
        picker.show();
    }
    private void startMessageFrag(){
        viewRedPoint.setVisibility(View.GONE);
        startFragment(NurLinKMessageFragment.class);
//        startActivity(new Intent(this,VoipActivity.class));
    }

}
