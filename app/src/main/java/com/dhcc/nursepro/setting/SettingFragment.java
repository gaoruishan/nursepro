package com.dhcc.nursepro.setting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.login.bean.NurseInfo;
import com.dhcc.nursepro.message.api.MessageApiManager;
import com.dhcc.nursepro.message.bean.MessageBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * SettingFragment
 * 设置
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private SPUtils spUtils = SPUtils.getInstance();
    private TextView tvNurseProVersion;
    private TextView tvUserName;
    private TextView tvLoc;
    private TextView tvRelogin;
    private RelativeLayout rlDate;
    private RelativeLayout rlBeds;
    private RelativeLayout rlWay;
    private RelativeLayout rlReq;
    private NurseInfo loginNurseInfo;
    private List<Map<String, String>> locsList;
    private List<NurseInfo> nurseInfoList;

    private SettingVersionDialog settingVersionDialog;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
        //        setToolbarType(BaseActivity.ToolbarType.TOP);
        //        setToolbarBottomLineVisibility(true);
        //        hideToolbarNavigationIcon();
        initView(view);

        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();

    }

    private void initView(View view) {
        tvNurseProVersion = view.findViewById(R.id.tv_setting_aboutus);
        tvNurseProVersion.setOnClickListener(this);
        tvUserName = view.findViewById(R.id.tv_setting_username);
        tvUserName.setText(spUtils.getString(SharedPreference.USERNAME));
        tvLoc = view.findViewById(R.id.tv_setting_loc);
        tvLoc.setOnClickListener(this);
        tvLoc.setText(spUtils.getString(SharedPreference.LOCDESC));

        tvRelogin = view.findViewById(R.id.tv_setting_relogin);
        tvRelogin.setOnClickListener(this);
        rlDate = view.findViewById(R.id.rl_setting_choosedate);
        rlDate.setOnClickListener(this);
        rlBeds = view.findViewById(R.id.rl_setting_choosebeds);
        rlBeds.setOnClickListener(this);
        rlWay = view.findViewById(R.id.rl_setting_chooseway);
        rlWay.setOnClickListener(this);
        rlReq = view.findViewById(R.id.rl_setting_failrequest);
        rlReq.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_aboutus:
                showVersionDialog();
                break;
            case R.id.tv_setting_loc:
                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    String userCode = spUtils.getString(SharedPreference.USERCODE);
                    String logonWardId = spUtils.getString(SharedPreference.WARDID);
                    for (int i = 0; i < nurseInfoList.size(); i++) {
                        NurseInfo nurseInfo = nurseInfoList.get(i);
                        if (userCode.equals(nurseInfo.getUserCode())) {
                            logonWardId = nurseInfo.getWardId();
                            loginNurseInfo = nurseInfo;
                            changeLoc();
                        }
                    }
                }
                break;
            case R.id.rl_setting_choosedate:
                startFragment(SettingDateTimeFragment.class);
                break;
            case R.id.rl_setting_choosebeds:
                startFragment(SettingBedsFragment.class);
                break;
            case R.id.rl_setting_chooseway:
                startFragment(SettingWayFragment.class);
                break;
            case R.id.rl_setting_failrequest:
                startFragment(LocalRequesetFragment.class);
                break;
            case R.id.tv_setting_relogin:
                SettingExitDialog settingExitDialog = new SettingExitDialog(getActivity());
                settingExitDialog.setSureOnclickListener(new SettingExitDialog.onSureOnclickListener() {
                    @Override
                    public void onCancleClick() {
                        settingExitDialog.dismiss();
                    }
                    @Override
                    public void onSureClick() {
                        settingExitDialog.dismiss();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                settingExitDialog.show();

                break;
            default:
                break;
        }
    }

    private void showVersionDialog() {
        if (settingVersionDialog != null && settingVersionDialog.isShowing()) {
            settingVersionDialog.dismiss();
        }

        settingVersionDialog = new SettingVersionDialog(getActivity());
        settingVersionDialog.setCanceledOnTouchOutside(true);
        settingVersionDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                settingVersionDialog.dismiss();
            }
        }, 3000);
    }

    private void changeLoc() {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        locsList = new ArrayList<>();
        String LocJson = spUtils.getString(SharedPreference.LOCSLISTJSON);
        locsList = gson.fromJson(LocJson, type);
        String[] locDesc = new String[locsList.size()];
        for (int i = 0; i < locsList.size(); i++) {
            locDesc[i] = locsList.get(i).get("LocDesc");
        }
        //                Toast.makeText(NurseSetActivity.this,LocJson,Toast.LENGTH_LONG).show();


        final OptionPicker picker = new OptionPicker(getActivity(), locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        Boolean ifLocRem = true;
        for (int i = 0; i <locDesc.length ; i++) {
            if (spUtils.getString(SharedPreference.LOCDESC).equals(locDesc[i])){
                picker.setSelectedIndex(i);
                ifLocRem = false;
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
                loginNurseInfo.setGroupDesc(locsList.get(index).get("GroupDesc"));
                loginNurseInfo.setGroupId(locsList.get(index).get("GroupId"));
                loginNurseInfo.setHospitalRowId(locsList.get(index).get("HospitalRowId"));
                loginNurseInfo.setLinkLoc(locsList.get(index).get("LinkLoc"));
                loginNurseInfo.setLocDesc(locsList.get(index).get("LocDesc"));
                loginNurseInfo.setLocId(locsList.get(index).get("LocId"));
                loginNurseInfo.setWardId(locsList.get(index).get("WardId"));
                //
                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    int j;
                    String userCode = spUtils.getString(SharedPreference.USERCODE);
                    for (j = 0; j < nurseInfoList.size(); j++) {
                        NurseInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            //                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
                            loginNurseInfo.setId(nurseInfo1.getId());
                            daoSession.getNurseInfoDao().update(loginNurseInfo);

                            spUtils.put(SharedPreference.HOSPITALROWID, loginNurseInfo.getHospitalRowId());
                            spUtils.put(SharedPreference.GROUPID, loginNurseInfo.getGroupId());
                            spUtils.put(SharedPreference.GROUPDESC, loginNurseInfo.getGroupDesc());
                            spUtils.put(SharedPreference.LINKLOC, loginNurseInfo.getLinkLoc());
                            spUtils.put(SharedPreference.LOCID, loginNurseInfo.getLocId());
                            spUtils.put(SharedPreference.LOCDESC, loginNurseInfo.getLocDesc());
                            spUtils.put(SharedPreference.WARDID, loginNurseInfo.getWardId());

                            tvLoc.setText(loginNurseInfo.getLocDesc());
                            notifyMessage();
                            break;
                        }
                    }

                    if (j >= nurseInfoList.size()) {
                        //                                    Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
                    }

                } else {
                    //                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
                    daoSession.getNurseInfoDao().insert(loginNurseInfo);
                }
            }
        });
        picker.show();
    }

    public void notifyMessage() {
        {
            MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
                @Override
                public void onSuccess(MessageBean msgs) {
                    int messageNum = (msgs.getNewOrdPatList()!=null?msgs.getNewOrdPatList().size():0)
                            + (msgs.getAbnormalPatList()!=null?msgs.getAbnormalPatList().size():0)
                            + (msgs.getConPatList()!=null?msgs.getConPatList().size():0);
                    setMessage(messageNum,msgs.getSoundFlag(),msgs.getVibrateFlag());
                }

                @Override
                public void onFail(String code, String msg) {
                    Toast.makeText(getActivity(), code + "-消息获取失败：" + msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
