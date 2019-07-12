package com.dhcc.module.infusion.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.db.GreenDaoHelper;
import com.dhcc.module.infusion.db.InfusionInfo;
import com.dhcc.module.infusion.greendao.DaoSession;
import com.dhcc.module.infusion.login.LoginActivity;
import com.base.commlibs.utils.UserUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * SettingFragment
 * 设置
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener{

    private SPUtils spUtils = SPUtils.getInstance();
    private TextView tvUserName;
    private TextView tvLoc;
    private TextView tvWin;
    private TextView tvRelogin;

    private RelativeLayout rlPat;
    private RelativeLayout rlBeds;
    private RelativeLayout rlWay;

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private InfusionInfo loginNurseInfo;
    private List<Map<String,String>> locsList;
    private List<InfusionInfo> nurseInfoList;

    private Map<String,List> mapWins;
    private View mView;


    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_infusion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setStatusBarBackgroundViewVisibility(false, 0xffffffff);
        setToolbarType(BaseActivity.ToolbarType.HIDE);
//        setToolbarType(BaseActivity.ToolbarType.TOP);
//        setToolbarBottomLineVisibility(true);
//        hideToolbarNavigationIcon();
        mView = view;
        initView(view);

        nurseInfoList = daoSession.getInfusionInfoDao().queryBuilder().list();

    }

    @Override
    public void onResume() {
        super.onResume();
        initView(mView);
    }

    private void initView(View view) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String,String>>>(){}.getType();
        locsList = new ArrayList<>();
        String LocJson = spUtils.getString(SharedPreference.LOCSLISTJSON);
        locsList =gson.fromJson(LocJson,type);


        java.lang.reflect.Type typeWin = new TypeToken<HashMap<String,List>>(){}.getType();
        mapWins = new HashMap<>();
        String WinJson = spUtils.getString(SharedPreference.WINSLISTJSON);
        mapWins = gson.fromJson(WinJson,typeWin);


        tvUserName = view.findViewById(R.id.tv_setting_username);
        tvUserName.setText(spUtils.getString(SharedPreference.USERNAME));
        tvLoc = view.findViewById(R.id.tv_setting_loc);
        tvLoc.setOnClickListener(this);
        tvLoc.setText(spUtils.getString(SharedPreference.LOCDESC));
        tvWin = view.findViewById(R.id.tv_setting_window);
        tvWin.setOnClickListener(this);
        tvWin.setText(UserUtil.getWindowName());

        tvRelogin =view.findViewById(R.id.tv_setting_relogin);
        tvRelogin.setOnClickListener(this);
        rlPat = view.findViewById(R.id.rl_setting_pat_list);
        rlPat.setOnClickListener(this);
        rlBeds = view.findViewById(R.id.rl_setting_choosebeds);
        rlBeds.setOnClickListener(this);
        rlWay = view.findViewById(R.id.rl_setting_chooseway);
        rlWay.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_setting_loc) {
            if (nurseInfoList != null && nurseInfoList.size() > 0) {
                String userCode = spUtils.getString(SharedPreference.USERCODE);
                String logonWardId = spUtils.getString(SharedPreference.WARDID);
                for (int i = 0; i < nurseInfoList.size(); i++) {
                    InfusionInfo nurseInfo = nurseInfoList.get(i);
                    if (userCode.equals(nurseInfo.getUserCode())) {
                        logonWardId = nurseInfo.getWardId();
                        loginNurseInfo = nurseInfo;
                        changeLoc();
                    }
                }
            }
        }
        if (v.getId() == R.id.tv_setting_window) {
            List list = new ArrayList();
            list = mapWins.get(spUtils.getString(SharedPreference.LOCDESC));
            changeWindow(list);
        }
        //工作统计
        if (v.getId() == R.id.rl_setting_choosebeds) {
            startFragment(WorkStatisticsFragment.class);
        }
        if (v.getId() == R.id.tv_setting_relogin) {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            finish();
        }
        //提醒方式
        if (v.getId() == R.id.rl_setting_chooseway) {
            startFragment(SettingWayFragment.class);
        }

    }

    private void changeWindow(List list) {
            String[] locDesc = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                locDesc[i] = list.get(i).toString();
            }
            final OptionPicker picker = new OptionPicker(getActivity(), locDesc);
            picker.setCanceledOnTouchOutside(false);
            picker.setDividerRatio(WheelView.DividerConfig.FILL);
            picker.setSelectedIndex(0);
            picker.setCycleDisable(true);
            picker.setTextSize(20);
            picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                    spUtils.put(SharedPreference.WINDOWNAME,item);
                    tvWin.setText(UserUtil.getWindowName());
                }
            });
            picker.show();
        }
    private void changeLoc(){
        String[] locDesc = new String[locsList.size()];
        for (int i = 0;i< locsList.size();i++){
            locDesc[i] = locsList.get(i).get("LocDesc");
        }

        final OptionPicker picker = new OptionPicker(getActivity(), locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
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
                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    int j;
                    String userCode = spUtils.getString(SharedPreference.USERCODE);
                    for (j = 0; j < nurseInfoList.size(); j++) {
                        InfusionInfo nurseInfo1 = nurseInfoList.get(j);
                        if (userCode.equals(nurseInfo1.getUserCode())) {
                            loginNurseInfo.setId(nurseInfo1.getId());
                            daoSession.getInfusionInfoDao().update(loginNurseInfo);

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
                        daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                    }

                } else {
                    daoSession.getInfusionInfoDao().insert(loginNurseInfo);
                }
            }
        });
        picker.show();
    }
    //TODO 需要修改
    public void notifyMessage(){ {
//            MessageApiManager.getMessage(new MessageApiManager.GetMessageCallback() {
//                @Override
//                public void onSuccess(MessageBean msgs) {
//                    int messageNum =  msgs.getNewOrdPatList().size()+msgs.getAbnormalPatList().size()+msgs.getConPatList().size();
//                    setMessage(messageNum);
//                }
//
//                @Override
//                public void onFail(String code, String msg) {
//                    Toast.makeText(getActivity(), code + "-消息获取失败：" + msg, Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
