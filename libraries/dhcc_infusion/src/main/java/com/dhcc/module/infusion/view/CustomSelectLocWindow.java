package com.dhcc.module.infusion.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.db.GreenDaoHelper;
import com.dhcc.module.infusion.db.InfusionInfo;
import com.dhcc.module.infusion.greendao.DaoSession;
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
 * 切换科室
 * @author:gaoruishan
 * @date:202019-06-24/09:49
 * @email:grs0515@163.com
 */
public class CustomSelectLocWindow extends LinearLayout implements View.OnClickListener {
    private TextView tvLoc;
    private TextView tvWin;
    private SPUtils spUtils = SPUtils.getInstance();
    private DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private List<InfusionInfo> nurseInfoList;
    private InfusionInfo loginNurseInfo;
    private List<Map<String,String>> locsList;
    private Map<String,List> mapWins;
    public CustomSelectLocWindow(Context context) {
        this(context,null);
    }
    public CustomSelectLocWindow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CustomSelectLocWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_select_loc_window, this,false);
        addView(view);
        tvLoc = view.findViewById(R.id.tv_setting_loc);
        TextView tvUser = view.findViewById(R.id.tv_user);
        tvUser.setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
        tvLoc.setOnClickListener(this);
        tvLoc.setText(SPUtils.getInstance().getString(SharedPreference.LOCDESC));
        tvWin = view.findViewById(R.id.tv_setting_window);
        tvWin.setOnClickListener(this);
        tvWin.setText(UserUtil.getWindowName());
        nurseInfoList = daoSession.getInfusionInfoDao().queryBuilder().list();
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String,String>>>(){}.getType();
        locsList = new ArrayList<>();
        String LocJson = spUtils.getString(SharedPreference.LOCSLISTJSON);
        locsList =gson.fromJson(LocJson,type);


        java.lang.reflect.Type typeWin = new TypeToken<HashMap<String,List>>(){}.getType();
        mapWins = new HashMap<>();
        String WinJson = spUtils.getString(SharedPreference.WINSLISTJSON);
        mapWins = gson.fromJson(WinJson,typeWin);

    }

    @Override
    public void onClick(View v) {
        // 点击切换科室
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
        // 点击切换窗口
        if (v.getId() == R.id.tv_setting_window) {
            List list = new ArrayList();
            list = mapWins.get(spUtils.getString(SharedPreference.LOCDESC));
            changeWindow(list);
        }
    }
    private void changeWindow(List list) {
        String[] locDesc = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            locDesc[i] = list.get(i).toString();
        }
        Activity activity = null;
        if (getContext() instanceof Activity) {
            activity = (Activity) getContext();
        }
        if (activity == null) {
            return;
        }
        final OptionPicker picker = new OptionPicker(activity, locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setTitleText("选择窗口");
        picker.setTitleTextSize(16);
        picker.setTitleTextColor(Color.parseColor("#FF62ABFF"));
        picker.setSubmitTextSize(13);
        picker.setSubmitTextColor(Color.parseColor("#FF62ABFF"));
        picker.setDividerColor(Color.parseColor("#FF62ABFF"));
        picker.setCancelTextSize(13);
        picker.setCancelTextColor(Color.parseColor("#FFC8C8C8"));
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
        Activity activity = null;
        if (getContext() instanceof Activity) {
            activity = (Activity) getContext();
        }
        if (activity == null) {
            return;
        }
        final OptionPicker picker = new OptionPicker(activity, locDesc);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setTitleText("选择科室");
        picker.setTitleTextSize(16);
        picker.setTitleTextColor(Color.parseColor("#FF62ABFF"));
        picker.setSubmitTextSize(13);
        picker.setSubmitTextColor(Color.parseColor("#FF62ABFF"));
        picker.setDividerColor(Color.parseColor("#FF62ABFF"));
        picker.setCancelTextSize(13);
        picker.setCancelTextColor(Color.parseColor("#FFC8C8C8"));
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
}
