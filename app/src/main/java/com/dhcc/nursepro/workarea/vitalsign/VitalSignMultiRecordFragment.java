package com.dhcc.nursepro.workarea.vitalsign;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.view.DIYKeyboardView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignMultiRecordPatAdapter;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.GetTempByPatListBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.PatientInfo;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignPatBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignSaveBean;
import com.dhcc.nursepro.workarea.vitalsign.bean.VsResult;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VitalSignMultiRecordFragment
 * 多人生命体征录入
 *
 * @author DevLix126
 * created at 2021/5/17 15:03
 */
public class VitalSignMultiRecordFragment extends BaseFragment implements OnDateSetListener {
    private static final int REQUEST_CODE = 101;
    private int editPosition = 0;


    private TextView tvDatetime;
    private CheckBox cbSelectbed;
    private CheckBox cbNeedflag;
    private LinearLayout llVsitem;

    private RecyclerView recyPatvitalsign;
    private DIYKeyboardView mKeyboard;

    private String date = "";
    private String time = "";
    private int titleNum = 0;

    private boolean selectBedCheck = false;
    private boolean needFlagCheck = false;
    private List<GetTempByPatListBean.PatListBean> patList = new ArrayList<>();
    private List<GetTempByPatListBean.PatListBean> tempdisplayList = new ArrayList<>();
    private List<GetTempByPatListBean.PatListBean> displayList = new ArrayList<>();
    private VitalSignMultiRecordPatAdapter patAdapter;
    private List<GetTempByPatListBean.TempConfigBean> tempConfigList = new ArrayList<>();
    private HashMap<Integer, Map<String, String>> strValue;
    private HashMap<String, HashMap<String, String>> finValue;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign_multirecord), 0xffffffff, 17);
        //右上角保存按钮
        View viewright = View.inflate(getActivity(), R.layout.view_fratoolbar_right, null);
        TextView textView = viewright.findViewById(R.id.tv_fratoobar_right);
        textView.setTextSize(15);
        textView.setText("   保存   ");
        textView.setTextColor(getResources().getColor(R.color.white));
        viewright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        setToolbarRightCustomView(viewright);

        Bundle bundle = getArguments();
        if (bundle != null) {
            date = bundle.getString("date");
            time = bundle.getString("time");

        }

        initView(view);
        initAdapter();
        initData();

    }

    private void save() {
        finValue = patAdapter.getDataMap();
        String[] patInfoStr = finValue.keySet().toArray(new String[0]);
        if (patInfoStr == null || patInfoStr.length < 1) {
            showToast("数据未经修改，无需保存");
            return;
        }

        for (int i = 0; i < patInfoStr.length; i++) {
            List<HashMap<String, String>> saveData = new ArrayList<>();
            HashMap<String, String> vsDataMap = finValue.get(patInfoStr[i]);
            String[] code = vsDataMap.keySet().toArray(new String[0]);
            for (int j = 0; j < code.length; j++) {
                String value = vsDataMap.get(code[j]);
                for (int k = 0; k < tempConfigList.size(); k++) {
                    GetTempByPatListBean.TempConfigBean config = tempConfigList.get(k);
                    if (code[j].equals(tempConfigList.get(k).getCode())) {
                        //检查输入数据范围
                        if (config.getNormalValueRangFrom() != null) {
                            if (config.getErrorValueLowTo() != null) {
                                if (isNumber(value)) {
                                    double edDou = Double.parseDouble(value);
                                    if (edDou >= Double.parseDouble(config.getErrorValueHightFrom()) || edDou <= Double.parseDouble(config.getErrorValueLowTo())) {
                                        showToast("请检查输入数值");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }

                HashMap<String, String> resItem = new HashMap<>();
                resItem.put("code", code[j]);
                resItem.put("value", value);
                saveData.add(resItem);
            }
            Gson gson = new Gson();
            String result = gson.toJson(saveData);
            saveSingleTempData(patInfoStr[i], result);
        }
    }

    private void initView(View view) {

        tvDatetime = view.findViewById(R.id.tv_datetime);
        tvDatetime.setText(date + " " + time);
        tvDatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseTime(TimeUtils.string2Millis(tvDatetime.getText().toString() + ":00"));
            }
        });
        cbSelectbed = view.findViewById(R.id.cb_selectbed);
        cbSelectbed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectBedCheck = isChecked;
                checkDisplayPatList(patList);
            }
        });
        cbNeedflag = view.findViewById(R.id.cb_needflag);
        cbNeedflag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                needFlagCheck = isChecked;
                checkDisplayPatList(patList);
            }
        });

        llVsitem = view.findViewById(R.id.ll_vsitem);
        recyPatvitalsign = view.findViewById(R.id.recy_patvitalsign);
        //提高展示效率
        recyPatvitalsign.setHasFixedSize(true);
        //设置的布局管理
        recyPatvitalsign.setLayoutManager(new LinearLayoutManager(getActivity()));
        mKeyboard = view.findViewById(R.id.ky_keyboard);
    }

    private void initAdapter() {
        patAdapter = new VitalSignMultiRecordPatAdapter(new ArrayList<>());
        patAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if (view.getId() == R.id.tv_name) {
//                    List<VitalSignPatBean> patList = new ArrayList<>();
//                    for (int i = 0; i < displayList.size(); i++) {
//                        GetTempByPatListBean.PatListBean displayItem = displayList.get(i);
//                        VitalSignPatBean patBean = new VitalSignPatBean(
//                                displayItem.getBedCode(),
//                                displayItem.getPatName(),
//                                displayItem.getRegNo(),
//                                displayItem.getEpisodeId());
//                        patList.add(patBean);
//                    }
//
//                    //将列表数据存储在sp，避免Intent传输数据过大报错
//                    Gson gson = new Gson();
//                    String patListJsonStr = gson.toJson(patList);
//                    SPUtils.getInstance().put(SharedPreference.DISPLAYLIST, patListJsonStr);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("time", time);
//                    bundle.putString("date", date);
//                    bundle.putInt("index", position);
////                    bundle.putSerializable("timeList", (Serializable) timeFilterList);
//
//                    startFragment(VitalSignRecordFragment.class, bundle);
//                } else
                if (view.getId() == R.id.tv_callmonitor) {
                    editPosition = position;
                    gotoVSMeasureApp((GetTempByPatListBean.PatListBean) adapter.getItem(editPosition));
                } else if (view.getId() == R.id.tv_singlerecord) {
                    List<VitalSignPatBean> patList = new ArrayList<>();
                    for (int i = 0; i < displayList.size(); i++) {
                        GetTempByPatListBean.PatListBean displayItem = displayList.get(i);
                        VitalSignPatBean patBean = new VitalSignPatBean(
                                displayItem.getBedCode(),
                                displayItem.getPatName(),
                                displayItem.getRegNo(),
                                displayItem.getEpisodeId());
                        patList.add(patBean);
                    }

                    //将列表数据存储在sp，避免Intent传输数据过大报错
                    Gson gson = new Gson();
                    String patListJsonStr = gson.toJson(patList);
                    SPUtils.getInstance().put(SharedPreference.DISPLAYLIST, patListJsonStr);

                    Bundle bundle = new Bundle();
                    bundle.putString("time", time);
                    bundle.putString("date", date);
                    bundle.putInt("index", position);
//                    bundle.putSerializable("timeList", (Serializable) timeFilterList);

                    startFragment(VitalSignRecordFragment.class, bundle);
                }

            }
        });
        recyPatvitalsign.setAdapter(patAdapter);
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.getTempByPatList(date, time, new VitalSignApiManager.GetTempByPatListCallback() {
            @Override
            public void onSuccess(GetTempByPatListBean getTempByPatListBean) {
                hideLoadingTip();
                if (StringUtils.isEmpty(date) || StringUtils.isEmpty(time) || StringUtils.isEmpty(tvDatetime.getText().toString())) {
                    date = getTempByPatListBean.getCurDate();
                    time = getTempByPatListBean.getCurTime();
                    tvDatetime.setText(date + " " + time);
                }
                boolean ifChange = ifChangeTitle(getTempByPatListBean.getTempConfig());
                tempConfigList = getTempByPatListBean.getTempConfig();
                //if (tempConfigList != null && titleNum == 0) {
                if (ifChange) {
                    patAdapter.setTempConfigBeanList(tempConfigList);
                    patAdapter.setmKeyboard(mKeyboard);
                    initTitle();
                }

                patList = getTempByPatListBean.getPatList();
                if (patList != null) {
                    checkDisplayPatList(patList);
                }
                if (!"999999".equals(getTempByPatListBean.getMsgcode())) {
                    showToast(getTempByPatListBean.getMsg());
                }

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);

            }
        });
    }

    public boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private void saveSingleTempData(String patInfoStr, String result) {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        VitalSignApiManager.saveTempData(patInfoStr.split("\\^")[0], date + " " + time, result, new VitalSignApiManager.SaveTempDataCallback() {
            @Override
            public void onSuccess(VitalSignSaveBean bean) {
                hideLoadFailTip();
                showToast(patInfoStr.split("\\^")[1] + "数据保存成功");
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error:" + msg);
            }
        });
    }

    /**
     * 选择时间
     */
    private void chooseTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

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
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        //        mDialogAll.settype(1);
        //        //取时间前两个字符转为int（02，06...）
        //
        //        List<String> timeList = new ArrayList();
        //        for (int i = 0; i < timeFilterList.size(); i++) {
        //            String str = (String) ((Map) timeFilterList.get(i)).get("time");
        //            timeList.add(str);
        //        }
        //        mDialogAll.setmHourMinute(timeList);

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    private void checkDisplayPatList(List<GetTempByPatListBean.PatListBean> patList) {
        tempdisplayList.clear();
        displayList.clear();
        if (selectBedCheck) {
            for (int i = 0; i < patList.size(); i++) {
                if ("1".equals(patList.get(i).getSelectedBed())) {
                    tempdisplayList.add(patList.get(i));
                }
            }
        } else {
            tempdisplayList.addAll(patList);
        }

        if (needFlagCheck) {
            for (int i = 0; i < tempdisplayList.size(); i++) {
                List<GetTempByPatListBean.PatListBean.TempListBean> tempListBeans = tempdisplayList.get(i).getTempList();
                for (int i1 = 0; i1 < tempListBeans.size(); i1++) {
                    if ("1".equals(tempListBeans.get(i1).getNeedFlag())) {
                        displayList.add(tempdisplayList.get(i));
                        break;
                    }
                }
            }
        } else {
            displayList.addAll(tempdisplayList);
        }

        recyPatvitalsign.scrollToPosition(0);
        patAdapter.setNewData(displayList);
    }

    private void gotoVSMeasureApp(GetTempByPatListBean.PatListBean patientItem) {
        try {
            Intent intent = new Intent();
            ComponentName name = new ComponentName("com.xicoo.vitalsignsmonitor", "com.xicoo.vitalsignsmonitor.MonitorActivity");
            intent.setComponent(name);
            Bundle bundle = new Bundle();
            PatientInfo patientInfo = new PatientInfo();
            patientInfo.bedNo = patientItem.getBedCode();
            patientInfo.name = patientItem.getPatName();

            bundle.putString("PatientInfo", new Gson().toJson(patientInfo));
            bundle.putBoolean("thirdPartyFlag", true);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            showToast("请先安装体征测量APP");
            e.printStackTrace();
        }
    }

    private void initTitle() {
        llVsitem.removeAllViews(); // EH
        for (int i = 0; i < tempConfigList.size(); i++) {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#4A4A4A"));
            textView.setTextSize(15);
            textView.setText(tempConfigList.get(i).getDesc());

            llVsitem.addView(textView);
            titleNum++;
        }


    }

    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
//        if (Action.PDA_DEVICE_TEMPERATURES.equals(intent.getAction())) {
//            String temp = intent.getStringExtra(PDATemperatures.TEMPERATURES);
//            ToastUtils.showShort("体温: " + temp);
//            View view = Objects.requireNonNull(getActivity()).getWindow().getDecorView().findFocus();
//
//            if (view instanceof EditText && "temperature".equals(view.getTag())) {
//                ((EditText) view).setText(temp);
//            }
//        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign_multi_record, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String res = data.getStringExtra("result");
            VsResult vsResult = new Gson().fromJson(res, VsResult.class);

            GetTempByPatListBean.PatListBean patientItem = patAdapter.getItem(editPosition);

            if (vsResult != null && patientItem != null) {
                for (int i = 0; i < patientItem.getTempList().size(); i++) {
                    GetTempByPatListBean.PatListBean.TempListBean tempListBean = patientItem.getTempList().get(i);
                    if ("temperature".equals(tempListBean.getCode())) {
                        tempListBean.setValue(String.valueOf(vsResult.getBodyTemp()));
                    }

                    if ("pulse".equals(tempListBean.getCode())) {
                        tempListBean.setValue(String.valueOf(vsResult.getPrBp()));
                    }
                }
                patAdapter.notifyItemChanged(editPosition);
            }
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String dateStr = TimeUtils.millis2String(millseconds).substring(0, 11);
        String timeStr = TimeUtils.millis2String(millseconds).substring(11, 16);

        if (!dateStr.equals(date) || !timeStr.equals(time)) {
            //日期发生改变，需重新请求数据
            date = dateStr;
            time = timeStr;
            initData();
        }

        tvDatetime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
    }

    /// EH 2022-05-15 14:30 不同时间项目不同
    private boolean ifChangeTitle(List<com.dhcc.nursepro.workarea.vitalsign.bean.GetTempByPatListBean.TempConfigBean> list) {
        boolean ifChange = false;
        if (list != null) {
            if (tempConfigList == null) ifChange = true;
            else if (list.size() != tempConfigList.size()) ifChange = true;
            else {
                for (int i = 0; i < tempConfigList.size(); i++) {
                    if (!tempConfigList.get(i).getCode().equals(list.get(i).getCode())) {
                        ifChange = true;
                        break;
                    }
                }
            }
        }
        return ifChange;
    }

}