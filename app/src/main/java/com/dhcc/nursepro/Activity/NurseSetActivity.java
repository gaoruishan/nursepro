package com.dhcc.nursepro.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.greendao.DaoSession;
import com.dhcc.nursepro.greendao.GreenDaoHelper;
import com.dhcc.nursepro.login.LoginActivity;
import com.dhcc.nursepro.login.bean.NurseInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class NurseSetActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {

    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    private Button btnSt, btnEnd, btnset, btnchangeLoc, btnrestart;
    private CheckBox ckLight, ckvibrate, ckSound, ckAll;
    private String timeTemp;
    private Notification baseNF;
    private NotificationManager nm;
    private ShowBedDialog showDialog;
    private Vibrator vibrator;
    private SPUtils spUtils = SPUtils.getInstance();
    private List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
    private NurseInfo loginNurseInfo;
    private List<Map<String, String>> locsList;
    private List<NurseInfo> nurseInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        requestWindowFeature(Window.FEATURE_NO_TITLE);

        nurseInfoList = daoSession.getNurseInfoDao().queryBuilder().list();

        setContentView(R.layout.activity_nurse_set);

        initview();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void initview() {

        btnSt = findViewById(R.id.btn_time_start);
        btnSt.setOnClickListener(this);
        btnEnd = findViewById(R.id.btn_time_end);
        btnEnd.setOnClickListener(this);
        ckLight = findViewById(R.id.check_light);
        ckLight.setOnClickListener(this);
        ckSound = findViewById(R.id.check_sound);
        ckSound.setOnClickListener(this);
        ckvibrate = findViewById(R.id.check_vibrate);
        ckvibrate.setOnClickListener(this);
        ckAll = findViewById(R.id.check_all_alarm);
        ckAll.setOnClickListener(this);
        btnset = findViewById(R.id.btn_bed_set);
        btnset.setOnClickListener(this);
        btnchangeLoc = findViewById(R.id.btn_changeloc);
        btnchangeLoc.setOnClickListener(this);
        btnrestart = findViewById(R.id.btn_restart);
        btnrestart.setOnClickListener(this);

        //        if (nurseInfoList != null && nurseInfoList.size() > 0) {
        //            String locstr = "";
        //            for (int i = 0; i < nurseInfoList.size(); i++) {
        //                NurseInfo nurseInfo = nurseInfoList.get(i);
        //                locstr = locstr+nurseInfo.getLocDesc();
        //                if (spUtils.getString(SharedPreference.USERCODE).equals(nurseInfo.getUserCode())) {
        //                    btnchangeLoc.setText(nurseInfo.getLocDesc());
        //                }
        //            }
        //            Toast.makeText(this,locstr+"---"+nurseInfoList.size(),Toast.LENGTH_LONG).show();
        //        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_time_start:
                timeTemp = "start";
                initAll(TimeUtils.string2Millis(btnSt.getText().toString()));
                break;
            case R.id.btn_time_end:
                timeTemp = "end";
                initAll(TimeUtils.string2Millis(btnEnd.getText().toString()));
                break;
            case R.id.check_light:
                //                Qnotify();
                startService(new Intent(this, MyService.class));
                break;
            case R.id.check_vibrate:
                nm.cancel(1);
                //                stopService(new Intent(this,MyService.class));
                break;
            case R.id.check_sound:
                //                Qnotify();
                break;
            case R.id.check_all_alarm:

                break;
            case R.id.btn_bed_set:
                final List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Map<String, String> map = new HashMap<String, String>();
                map.put("ck", "true");
                map.put("bedNo", "222");
                list.add(map);
                list.add(map);
                list.add(map);
                list.add(map);
                list.add(map);
                list.add(map);
                showDialog = new ShowBedDialog(this);
                showDialog.setTitle("结果");
                showDialog.setMessage("kk");
                showDialog.setList(list);
                showDialog.setYesOnclickListener("确定", new ShowBedDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        list1 = showDialog.getlist();
                        ss();
                        showDialog.dismiss();
                    }
                });

                //                        selfDialog.setNoOnclickListener("取消", new ShowBedDialog.onNoOnclickListener() {
                //                            @Override
                //                            public void onNoClick() {
                //                                Toast.makeText(getActivity(),"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                //                                selfDialog.dismiss();
                //                            }
                //                        });
                showDialog.show();
                break;
            case R.id.btn_changeloc:

                //                loginNurseInfo = new NurseInfo();
                if (nurseInfoList != null && nurseInfoList.size() > 0) {
                    String userCode = spUtils.getString(SharedPreference.USERCODE);
                    String logonWardId = spUtils.getString(SharedPreference.WARDID);
                    for (int i = 0; i < nurseInfoList.size(); i++) {
                        NurseInfo nurseInfo = nurseInfoList.get(i);
                        if (userCode.equals(nurseInfo.getUserCode())) {
                            logonWardId = nurseInfo.getWardId();
                            loginNurseInfo = nurseInfo;
                            ss();
                        }
                    }
                    Toast.makeText(this, userCode + "---" + nurseInfoList.size(), Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.btn_restart:
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            default:
                break;

        }
    }

    private void initAll(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(getSupportFragmentManager(), "ALL");
    }

    private void ss() {
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


        final OptionPicker picker = new OptionPicker(NurseSetActivity.this, locDesc);
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

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//精确到分钟
        String time = format.format(date);
        if (timeTemp.equals("start")) {
            btnSt.setText(time);
        } else {
            btnEnd.setText(time);
        }
    }


    private void Qnotify() {
        //        baseNF = new Notification();
        //
        //        // 设置通知在状态栏显示的图标
        //        baseNF.icon = R.mipmap.ic_launcher;
        //        baseNF.contentView = null;
        //        // 通知时在状态栏显示的内容
        //        baseNF.tickerText = "新医嘱!";

        // 通知的默认参数 DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS.
        // 如果要全部采用默认值, 用 DEFAULT_ALL.
        // 此处采用默认声音
        // baseNF.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
        //        if (LoginUser.SoundF == true)
        //            baseNF.defaults |= Notification.DEFAULT_SOUND;
        ////        if (LoginUser.VibrateF == true)
        //            baseNF.defaults |= Notification.DEFAULT_VIBRATE;
        ////        if (LoginUser.LigthF == true)
        //            baseNF.defaults |= Notification.DEFAULT_LIGHTS;

        // 让声音、振动无限循环，直到用户响应
        //        baseNF.flags |= Notification.FLAG_INSISTENT;
        //
        //        // 通知被点击后，自动消失
        //        baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
        //
        //        // 点击'Clear'时，不清楚该通知(QQ的通知无法清除，就是用的这个)
        //        baseNF.flags |= Notification.FLAG_NO_CLEAR;

        // 第二个参数 ：下拉状态栏时显示的消息标题 expanded message title
        // 第三个参数：下拉状态栏时显示的消息内容 expanded message text
        // 第四个参数：点击该通知时执行页面跳转
        //		baseNF.setLatestEventInfo(NurMain.this, "新医嘱", list.size() + "个患者", pd);

        // 发出状态栏通知
        // The first parameter is the unique ID for the Notification
        // and the second is the Notification object.
        nm.notify(1, baseNF);

    }


    private void initData(final String action, final NurseInfo nurseInfo) {
        //        final List<LoginBean.LocsBean> locsBeanList = loginBean.getLocs();
        //
        //        String[] locDesc = new String[locsBeanList.size()];
        //        for (int i = 0; i < locsBeanList.size(); i++) {
        //            locDesc[i] = locsBeanList.get(i).getLocDesc();
        //        }
        //
        //        final OptionPicker picker = new OptionPicker(NurseSetActivity.this, locDesc);
        //        picker.setCanceledOnTouchOutside(false);
        //        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        //        picker.setSelectedIndex(0);
        //        picker.setCycleDisable(true);
        //        picker.setTextSize(20);
        //        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
        //            @Override
        //            public void onOptionPicked(int index, String item) {
        //                LoginBean.LocsBean locsBean = locsBeanList.get(index);
        //
        //                if (nurseInfo == null) {
        //                    loginNurseInfo = new NurseInfo(null, loginBean.getSchEnDateTime(), loginBean.getSchStDateTime(), loginBean.getStatus(), loginBean.getUserId(), userCode, loginBean.getUserName(), locsBean.getGroupDesc(), locsBean.getGroupId(), locsBean.getHospitalRowId(), locsBean.getLinkLoc(), locsBean.getLocDesc(), locsBean.getLocId(), locsBean.getWardId());
        //
        //                } else {
        //                    loginNurseInfo.setGroupDesc(locsBean.getGroupDesc());
        //                    loginNurseInfo.setGroupId(locsBean.getGroupId());
        //                    loginNurseInfo.setHospitalRowId(locsBean.getHospitalRowId());
        //                    loginNurseInfo.setLinkLoc(locsBean.getLinkLoc());
        //                    loginNurseInfo.setLocDesc(locsBean.getLocDesc());
        //                    loginNurseInfo.setLocId(locsBean.getLocId());
        //                    loginNurseInfo.setWardId(locsBean.getWardId());
        //                }
        //
        //                if (nurseInfoList != null && nurseInfoList.size() > 0) {
        //                    int j;
        //                    for (j = 0; j < nurseInfoList.size(); j++) {
        //                        NurseInfo nurseInfo1 = nurseInfoList.get(j);
        //                        if (userCode.equals(nurseInfo1.getUserCode())) {
        //                            //                                        Toast.makeText(LoginActivity.this, "ward----已存在,更新数据", Toast.LENGTH_SHORT).show();
        //                            loginNurseInfo.setId(nurseInfo1.getId());
        //                            daoSession.getNurseInfoDao().update(loginNurseInfo);
        //                            initData("login", loginNurseInfo);
        //                            break;
        //                        }
        //                    }
        //
        //                    if (j >= nurseInfoList.size()) {
        //                        //                                    Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
        //                        daoSession.getNurseInfoDao().insert(loginNurseInfo);
        //                        initData("login", loginNurseInfo);
        //                    }
        //
        //                } else {
        //                    //                                Toast.makeText(LoginActivity.this, "ward----不存在，插入新数据", Toast.LENGTH_SHORT).show();
        //                    daoSession.getNurseInfoDao().insert(loginNurseInfo);
        //                    initData("login", loginNurseInfo);
        //                }
        //            }
        //        });
        //        picker.show();

    }


    //        List<Map> list = new ArrayList<>();
    //        Map map = new HashMap();
    //        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
    //        labOutDetailAdapter.setNewData(list);

    //        operationAdapter = new OperationAdapter(new ArrayList<Map>());
    //        recOperation.setAdapter(operationAdapter);
    //        List<Map> list = new ArrayList<>();
    //        Map map = new HashMap();
    //        list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);list.add(map);
    //        operationAdapter.setNewData(list);


    //双选护士医生
    //        List<String> list = new ArrayList<String>();
    //        for (int i = 0; i < locsBeanList.size(); i++) {
    //            locDesc[i] = locsBeanList.get(i).getLocDesc();
    //            list.add(locsBeanList.get(i).getLocDesc())
    //        }
    //
    //        final DoublePicker picker = new DoublePicker(LoginActivity.this, list,list);
    //        picker.setCanceledOnTouchOutside(false);
    //        picker.setDividerRatio(WheelView.DividerConfig.FILL);
    //        picker.setSelectedIndex(0,0);
    //        picker.setCycleDisable(true);
    //        picker.setTextSize(20);
    //        picker.setOnPickListener
}
