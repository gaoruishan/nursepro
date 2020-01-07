package com.dhcc.nursepro.workarea.vitalsign;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.api.AllotBedApiManager;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignPatientAdapter;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignTypeAdapter;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignDetailFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private TextView tvVitalSignAllarea;
    private TextView tvVitalSignAdminarea;
    private TextView tvVitalSignNowoutarea;
    private TextView tvVitalSignAlloutarea;
    private TextView tvVitalSignWaitarea;
    private TextView tvVitalSignChooseTime;
    private TextView tvResetting;

    private RecyclerView recyVitalSignType;
    private RecyclerView recyVitalSignPatient;

    private VitalSignPatientAdapter patientAdapter;
    private VitalSignTypeAdapter typeAdapter;

    private ArrayList topFilterList = new ArrayList();

    private List leftFilterList = new ArrayList();

    private List patientList = new ArrayList();

    private List timeFilterList = new ArrayList();

    private List<Map> displayList = new ArrayList<>();


    private String topFilterStr = "inBedAll";
    private String timeFilterStr = "";
    private String dateFilterStr = "";
    private Boolean bResetting = false;

    private SPUtils spUtils = SPUtils.getInstance();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign), 0xffffffff, 17);


        initView(view);
        initAdapter();

        //不用本地时间，用后台返回的时间
        //        initData();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);
    }

    private void initView(View view) {

        tvResetting = view.findViewById(R.id.tv_resetting);
        tvResetting.setOnClickListener(this);
        tvVitalSignAllarea = view.findViewById(R.id.tv_vitalsign_allarea);
        tvVitalSignAllarea.setOnClickListener(this);
        tvVitalSignAdminarea = view.findViewById(R.id.tv_vitalsign_adminarea);
        tvVitalSignAdminarea.setOnClickListener(this);
        tvVitalSignNowoutarea = view.findViewById(R.id.tv_vitalsign_nowoutarea);
        tvVitalSignNowoutarea.setOnClickListener(this);
        tvVitalSignAlloutarea = view.findViewById(R.id.tv_vitalsign_alloutarea);
        tvVitalSignAlloutarea.setOnClickListener(this);
        tvVitalSignWaitarea = view.findViewById(R.id.tv_vitalsign_waitarea);
        tvVitalSignWaitarea.setOnClickListener(this);

        tvVitalSignChooseTime = view.findViewById(R.id.tv_vitalsign_time);
        tvVitalSignChooseTime.setOnClickListener(this);

        setTopFilterSelect(tvVitalSignAllarea);

        recyVitalSignType = view.findViewById(R.id.recy_vitalsign_type);
        recyVitalSignPatient = view.findViewById(R.id.recy_vitalsign_patient);

        //提高展示效率
        recyVitalSignType.setHasFixedSize(true);
        //设置的布局管理
        recyVitalSignType.setLayoutManager(new LinearLayoutManager(getActivity()));
        //提高展示效率
        recyVitalSignPatient.setHasFixedSize(true);
        //设置的布局管理
        recyVitalSignPatient.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initAdapter() {

        patientAdapter = new VitalSignPatientAdapter(new ArrayList<Map>());
        patientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Map patientInfo = displayList.get(position);
                //将列表数据存储在sp，避免Intent传输数据过大报错
                Gson gson = new Gson();
                String displayListJsonStr = gson.toJson(displayList);
                spUtils.put(SharedPreference.DISPLAYLIST, displayListJsonStr);
                if (view.getId() == R.id.tv_vitalsign_vitalsign_record) {
                    //体征录入
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", (Serializable) patientInfo);
                    bundle.putString("time", timeFilterStr);
                    bundle.putString("date", dateFilterStr);
                    bundle.putInt("index", position);
                    bundle.putSerializable("timeList", (Serializable) timeFilterList);

                    startFragment(VitalSignRecordFragment.class, bundle);

                } else if (view.getId() == R.id.tv_vitalsign_event_record) {
                    //事件登记
                    Bundle bundle = new Bundle();
                    bundle.putString("regNo", (String) patientInfo.get("regNo"));
                    startFragment(PatEventsFragment.class, bundle);

                } else if (view.getId() == R.id.tv_vitalsign_tmp_preview) {
                    //体温单预览

                    String episodeId = (String) patientInfo.get("episodeId");
                    //                    viewPatientTempImages("94");
                    viewPatientTempImages(episodeId);

                } else {
                    //普通点击
                    String episodeId = (String) patientInfo.get("episodeId");
                    Bundle bundle = new Bundle();
                    bundle.putString("episodeId", episodeId);
                    bundle.putInt("index", position);
                    startFragment(VitalSignDetailFragment.class, bundle);

                }
            }
        });

        typeAdapter = new VitalSignTypeAdapter(new ArrayList<Map>());

        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                typeAdapter.filterClicked(position);
                typeAdapter.notifyDataSetChanged();
                updatePatientData();
            }
        });

        recyVitalSignType.setAdapter(typeAdapter);
        recyVitalSignPatient.setAdapter(patientAdapter);


    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        VitalSignApiManager.getVitalSignList(dateFilterStr, timeFilterStr, new VitalSignApiManager.GetVitalSignListCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {

                topFilterList = (ArrayList) map.get("topFilter");
                leftFilterList = (ArrayList) map.get("leftFilter");
                patientList = (ArrayList) map.get("patInfoList");
                timeFilterList = (ArrayList) map.get("timeSelect");
                timeFilterStr = (String) map.get("timePoint");
                dateFilterStr = (String) map.get("datePoint");
                tvVitalSignChooseTime.setText(dateFilterStr + "  " + timeFilterStr);



                for (int i = 0; i <leftFilterList.size() ; i++) {
                    String key = (String) ((Map)(leftFilterList.get(i))).get("code");
                    int typeNum = 0 ;
                    ((Map)(leftFilterList.get(i))).put("temNum",typeNum+"");
                    for (int j = 0; j <patientList.size() ; j++) {
                        List<Map> needMeasureList1 = (List<Map>) ((Map)( patientList.get(j))).get("needMeasureInfo");
//                        for (int k = 0; k <needMeasureList1.size() ; k++) {
                        if (needMeasureList1.size()>0){
                            if (needMeasureList1.get(0).get(key).equals("1")){
                                ((Map)(leftFilterList.get(i))).put("temNum",""+(++typeNum));
//                            }
                            }

                        }
                    }
                }

                typeAdapter.setNewData(leftFilterList);

                for (int i = 0; i < topFilterList.size(); i++) {
                    Map filter = (Map) topFilterList.get(i);
                    if ("".equals(filter.get("code"))) {
                        String code = (String) filter.get("code");
                        switch (code) {
                            case "inBedAll":
                                tvVitalSignAllarea.setVisibility(View.GONE);
                                break;
                            case "manageInBed":
                                tvVitalSignAdminarea.setVisibility(View.GONE);
                                break;
                            case "todayOut":
                                tvVitalSignNowoutarea.setVisibility(View.GONE);
                                break;
                            case "allOut":
                                tvVitalSignAlloutarea.setVisibility(View.GONE);
                                break;
                            case "wait":
                                tvVitalSignWaitarea.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                    }
                }

                hideLoadingTip();
                updatePatientData();

            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void setTopFilterSelect(View view) {
        tvVitalSignAllarea.setSelected(view == tvVitalSignAllarea);
        tvVitalSignAdminarea.setSelected(view == tvVitalSignAdminarea);
        tvVitalSignNowoutarea.setSelected(view == tvVitalSignNowoutarea);
        tvVitalSignAlloutarea.setSelected(view == tvVitalSignAlloutarea);
        tvVitalSignWaitarea.setSelected(view == tvVitalSignWaitarea);

        if (view == tvVitalSignAllarea) {
            tvVitalSignAllarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvVitalSignAdminarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignNowoutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAlloutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvVitalSignAdminarea) {
            tvVitalSignAllarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAdminarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvVitalSignNowoutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAlloutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvVitalSignNowoutarea) {
            tvVitalSignAllarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAdminarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignNowoutarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvVitalSignAlloutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvVitalSignAlloutarea) {
            tvVitalSignAllarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAdminarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignNowoutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAlloutarea.setTypeface(Typeface.DEFAULT_BOLD);
            tvVitalSignWaitarea.setTypeface(Typeface.DEFAULT);
        } else if (view == tvVitalSignWaitarea) {
            tvVitalSignAllarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAdminarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignNowoutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignAlloutarea.setTypeface(Typeface.DEFAULT);
            tvVitalSignWaitarea.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    private void viewPatientTempImages(String episodeId) {

        //
        showLoadingTip(BaseActivity.LoadingType.FULL);

        VitalSignApiManager.gePatientTempImages(episodeId, new VitalSignApiManager.GetPatientTempImagesCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {

                hideLoadFailTip();


                int sum = ((ArrayList) map.get("urlList")).size();
                if (sum == 0) {
                    showToast("该病人无体温单");
                    return;
                }

                ImagePipeline imagePipeline = Fresco.getImagePipeline();

                ArrayList<String> urls = new ArrayList<>();
                for (int i = 0; i < sum; i++) {
                    Map item = (Map) ((ArrayList) map.get("urlList")).get(i);
                    urls.add((String) item.get("url"));
                    Uri uri = Uri.parse((String) item.get("url"));
                    imagePipeline.evictFromMemoryCache(uri);
                    imagePipeline.evictFromDiskCache(uri);
                    imagePipeline.evictFromCache(uri);
                }


                new ImageViewer.Builder(getContext(), urls)
                        .setStartPosition(0)
                        .show();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void updatePatientData() {

        displayList.clear();

        //顶部区域过滤
        for (int i = 0; i < patientList.size(); i++) {

            Map patientInfo = (Map) patientList.get(i);

            boolean topmatch = false;

            switch (topFilterStr) {
                case "inBedAll":
                    if ("1".equals(patientInfo.get("inBedAll"))) {
                        topmatch = true;
                    }
                    break;
                case "manageInBed":
                    if ("1".equals(patientInfo.get("manageInBed"))) {
                        topmatch = true;
                    }
                    break;
                case "todayOut":
                    if ("1".equals(patientInfo.get("todayOut"))) {
                        topmatch = true;
                    }
                    break;
                case "allOut":
                    if ("1".equals(patientInfo.get("allOut"))) {
                        topmatch = true;
                    }
                    break;
                case "wait":
                    if ("1".equals(patientInfo.get("wait"))) {
                        topmatch = true;
                    }
                    break;
                default:
                    break;
            }
            if (topmatch) {
                displayList.add(patientInfo);
            }
        }

        //左侧筛选项无选中状态，则全部显示
        if (typeAdapter.getFilterList().size() == 0) {
            patientAdapter.setNewData(displayList);
            tvResetting.setSelected(false);
            tvResetting.setText("筛选");
            tvResetting.setTextColor(getResources().getColor(R.color.vital_sign_type_normal_text));
            bResetting = false;
            return;
        } else {
            tvResetting.setSelected(true);
            tvResetting.setText("重置");
            tvResetting.setTextColor(getResources().getColor(R.color.vital_sign_type_selected_text));
            bResetting = true;
        }

        List<Map> tmpFilterPatientList = new ArrayList<>();
        tmpFilterPatientList.addAll(displayList);

        displayList.clear();

        //时间点过滤
        for (int i = 0; i < tmpFilterPatientList.size(); ++i) {

            Map patientInfo = tmpFilterPatientList.get(i);
            //每个病人需测项目为一个array，需根据时间点过滤病人列表

            boolean needMeasure = false;

            for (int j = 0; j < ((List) patientInfo.get("needMeasureInfo")).size(); j++) {
                Map measureInfo = (Map) ((List) patientInfo.get("needMeasureInfo")).get(j);
                if (measureInfo.get("needTimePoint").equals(timeFilterStr)) {
                    needMeasure = true;
                }
            }

            if (needMeasure) {
                displayList.add(patientInfo);
            }
        }


        //左侧待测项目过滤
        tmpFilterPatientList.clear();
        tmpFilterPatientList.addAll(displayList);
        displayList.clear();

        //通过 typeAdapter 中的选中index 列表获取选中的左侧需测项目列表
        List<Map> selectedLeftFilter = new ArrayList<>();

        for (int i = 0; i < typeAdapter.getFilterList().size(); i++) {
            int index = typeAdapter.getFilterList().get(i).intValue();
            selectedLeftFilter.add((Map) leftFilterList.get(index));
        }

        for (int i = 0; i < tmpFilterPatientList.size(); i++) {
            Map patientInfo = tmpFilterPatientList.get(i);

            List<Map> needMeasureList = (List) patientInfo.get("needMeasureInfo");

            boolean needShow = false;

            for (int j = 0; j < needMeasureList.size(); j++) {

                for (int k = 0; k < selectedLeftFilter.size(); k++) {
                    String key = (String) selectedLeftFilter.get(k).get("code");
                    if (needMeasureList.get(j).get(key).equals("1")) {
                        needShow = true;
                    }
                }

            }
            if (needShow) {
                displayList.add(patientInfo);
            }


        }

        patientAdapter.setNewData(displayList);

    }

    //扫码获取信息
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        if (intent.getAction().equals(Action.DEVICE_SCAN_CODE)) {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            initScanMsg(bundle.getString("data"));

        }
    }

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign, container, false);
    }

    //扫码直接进入
    private void initScanMsg(String regNo) {

        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo", regNo);
        mapmsg.put("wardId", wardId);
        //获取用户信息，跟allotbed共用一个api
        AllotBedApiManager.getUserMsg(mapmsg, "getPatWristInfo", new AllotBedApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(GetScanPatsBean getScanPatsBean) {


                for (int i = 0; i < displayList.size(); i++) {
                    if ((getScanPatsBean.getPatInfo().getRegNo()).equals(displayList.get(i).get("regNo"))) {

                        Map patientInfo = displayList.get(i);
                        //将列表数据存储在sp，避免Intent传输数据过大报错
                        Gson gson = new Gson();
                        String displayListJsonStr = gson.toJson(displayList);
                        spUtils.put(SharedPreference.DISPLAYLIST, displayListJsonStr);

                        //体征录入
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", (Serializable) patientInfo);
                        bundle.putString("time", timeFilterStr);
                        bundle.putString("date", dateFilterStr);
                        bundle.putInt("index", i);
                        bundle.putSerializable("timeList", (Serializable) timeFilterList);

                        startFragment(VitalSignRecordFragment.class, bundle);
                    }
                }
            }

            @Override
            public void onFail(String code, String msg) {
                showToast("error" + code + ":" + msg);
            }
        });

    }

    private void initData() {
        dateFilterStr = TimeUtils.getNowString().substring(0, 11);
        tvVitalSignChooseTime.setText(dateFilterStr + "  " + timeFilterStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_resetting:
                if (bResetting) {
                    List<Integer> filterList = new ArrayList<>();
                    typeAdapter.setFilterList(filterList);
                    typeAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_vitalsign_allarea:
                topFilterStr = "inBedAll";
                setTopFilterSelect(tvVitalSignAllarea);
                break;
            case R.id.tv_vitalsign_adminarea:
                topFilterStr = "manageInBed";
                setTopFilterSelect(tvVitalSignAdminarea);
                break;
            case R.id.tv_vitalsign_nowoutarea:
                topFilterStr = "todayOut";
                setTopFilterSelect(tvVitalSignNowoutarea);
                break;
            case R.id.tv_vitalsign_alloutarea:
                topFilterStr = "allOut";
                setTopFilterSelect(tvVitalSignAlloutarea);
                break;
            case R.id.tv_vitalsign_waitarea:
                topFilterStr = "wait";
                setTopFilterSelect(tvVitalSignWaitarea);
                break;
            case R.id.tv_vitalsign_time:
                chooseTime(TimeUtils.string2Millis(dateFilterStr +  " " + timeFilterStr + ":00"));
                break;
            default:
                break;
        }
        updatePatientData();
    }

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

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String date = TimeUtils.millis2String(millseconds).substring(0, 10);
        String time = TimeUtils.millis2String(millseconds).substring(11, 16);

        if (!date.equals(dateFilterStr) || !time.equals(timeFilterStr)) {
            //日期发生改变，需重新请求数据
            Boolean b1 = !date.equals(dateFilterStr);
            Boolean b2 = !time.equals(timeFilterStr);
            dateFilterStr = date;
            timeFilterStr = time;
            asyncInitData();
        }

        //        if (!time.equals(timeFilterStr)) {
        //            timeFilterStr = time;
        //            updatePatientData();
        //        }

        //        tvVitalSignChooseTime.setText(TimeUtils.millis2String(millseconds).substring(0, 16));
    }


}
