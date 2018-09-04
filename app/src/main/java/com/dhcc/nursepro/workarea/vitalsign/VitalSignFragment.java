package com.dhcc.nursepro.workarea.vitalsign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignPatientAdapter;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignTypeAdapter;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;

import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignDetailFragment;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VitalSignFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private TextView tvVitalSignAllarea;
    private TextView tvVitalSignAdminarea;
    private TextView tvVitalSignNowoutarea;
    private TextView tvVitalSignAlloutarea;
    private TextView tvVitalSignWaitarea;
    private TextView tvVitalSignChooseTime;

    private RecyclerView recyVitalSignType;
    private RecyclerView recyVitalSignPatient;

    private VitalSignPatientAdapter patientAdapter;
    private VitalSignTypeAdapter typeAdapter;

    private ArrayList topFilterList;

    private List leftFilterList;

    private List patientList;

    private List timeFilterList;

    private List<Map> displayList = new ArrayList<>();


    private String topFilterStr = "inBedAll";
    private String timeFilterStr = "06:00";
    private String dateFilterStr = "";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign),0xffffffff,17);


        initView(view);
        initAdapter();
        initData();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);


    }


    private void initView(View view) {

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

                Map patientInfo = (Map) patientList.get(position);

                if (view.getId() == R.id.tv_vitalsign_vitalsign_record){
                    //体征录入
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info",(Serializable)patientInfo);
                    bundle.putString("timepoint",dateFilterStr + " "+ timeFilterStr);
                    startFragment(VitalSignRecordFragment.class,bundle);

                }else if(view.getId() == R.id.tv_vitalsign_event_record){
                    //事件登记
                    Bundle bundle = new Bundle();
                    bundle.putString("regNo",(String) patientInfo.get("regNo"));
                    startFragment(PatEventsFragment.class,bundle);

                }else if(view.getId() == R.id.tv_vitalsign_tmp_preview){
                    //体温单预览

                    String episodeId = (String)patientInfo.get("episodeId");

                    viewPatientTempImages("94");

                }else{
                    //普通点击
                    Toast.makeText(getContext(), "普通点击", Toast.LENGTH_SHORT).show();
                    String episodeId = (String)patientInfo.get("episodeId");
                    Bundle bundle = new Bundle();
                    bundle.putString("episodeId",episodeId);
                    startFragment(VitalSignDetailFragment.class,bundle);

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

    private void initData(){
        dateFilterStr = TimeUtils.getNowString().substring(0,11);
        tvVitalSignChooseTime.setText(dateFilterStr + "  " + timeFilterStr);
    }

    private void asyncInitData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        VitalSignApiManager.getVitalSignList(dateFilterStr, new VitalSignApiManager.GetVitalSignListCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {

                topFilterList = (ArrayList) map.get("topFilter");
                leftFilterList = (ArrayList)map.get("leftFilter");
                patientList = (ArrayList)map.get("patInfoList");
                timeFilterList = (ArrayList)map.get("timeSelect");
                timeFilterStr = (String)map.get("timePoint");
                tvVitalSignChooseTime.setText(dateFilterStr + "  " + timeFilterStr);

                typeAdapter.setNewData(leftFilterList);

                for (int i = 0; i < topFilterList.size(); i++){
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
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void viewPatientTempImages(String episodeId){

        //
        showLoadingTip(BaseActivity.LoadingType.FULL);

        VitalSignApiManager.gePatientTempImages(episodeId, new VitalSignApiManager.GetPatientTempImagesCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {
                
                hideLoadFailTip();
                Toast.makeText(getActivity(), "体温单预览图片请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                Toast.makeText(getActivity(), code + ":" + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                chooseTime();
                break;
            default:
                break;
        }
        updatePatientData();
    }

    private void updatePatientData(){

        displayList.clear();

        //顶部区域过滤
        for (int i = 0; i < patientList.size(); i++) {

            Map patientInfo = (Map)patientList.get(i);

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
        if (typeAdapter.getFilterList().size() == 0){
            patientAdapter.setNewData(displayList);
            return;
        }

        List<Map> tmpFilterPatientList = new ArrayList<>();
        tmpFilterPatientList.addAll(displayList);

        displayList.clear();

        //时间点过滤
        for (int i = 0; i < tmpFilterPatientList.size() ; ++i){

            Map patientInfo = tmpFilterPatientList.get(i);
            //每个病人需测项目为一个array，需根据时间点过滤病人列表

            boolean needMeasure = false;

            for (int j = 0; j <((List)patientInfo.get("needMeasureInfo")).size(); j++) {
                Map measureInfo = (Map)((List)patientInfo.get("needMeasureInfo")).get(j);
                if (((String)measureInfo.get("needTimePoint")).equals(timeFilterStr)){
                    needMeasure = true;
                }
            }

            if (needMeasure){
                displayList.add(patientInfo);
            }
        }


        //左侧待测项目过滤
        tmpFilterPatientList.clear();
        tmpFilterPatientList.addAll(displayList);
        displayList.clear();

        //通过 typeAdapter 中的选中index 列表获取选中的左侧需测项目列表
        List<Map> selectedLeftFilter = new ArrayList<>();

        for (int i = 0 ; i < typeAdapter.getFilterList().size() ; i ++){
            int index = typeAdapter.getFilterList().get(i).intValue();
            selectedLeftFilter.add((Map) leftFilterList.get(index));
        }

        for (int i = 0; i < tmpFilterPatientList.size(); i++){
            Map patientInfo = tmpFilterPatientList.get(i);

            List<Map> needMeasureList = (List) patientInfo.get("needMeasureInfo");

            boolean needShow = false;

            for (int j = 0; j < needMeasureList.size(); j ++){

                for (int k = 0; k < selectedLeftFilter.size(); k++){
                    String key = (String) selectedLeftFilter.get(k).get("code");
                    if (((String)needMeasureList.get(j).get(key)).equals("1")){
                        needShow = true;
                    }
                }

            }
            if (needShow){
                displayList.add(patientInfo);
            }


        }

        patientAdapter.setNewData(displayList);

    }


    private void setTopFilterSelect(View view) {
        tvVitalSignAllarea.setSelected(view == tvVitalSignAllarea);
        tvVitalSignAdminarea.setSelected(view == tvVitalSignAdminarea);
        tvVitalSignNowoutarea.setSelected(view == tvVitalSignNowoutarea);
        tvVitalSignAlloutarea.setSelected(view == tvVitalSignAlloutarea);
        tvVitalSignWaitarea.setSelected(view == tvVitalSignWaitarea);
    }

    private void chooseTime(){
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
                .setHourText("：00")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.settype(1);
        //取时间前两个字符转为int（02，06...）

        List<String> hours = new ArrayList();
        for (int i = 0; i < timeFilterList.size(); i ++){
            String str = (String)((Map)timeFilterList.get(i)).get("time");
            hours.add(str);
        }

        mDialogAll.setintHour(hours);

        mDialogAll.show(getFragmentManager(), "ALL");

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        String date = TimeUtils.millis2String(millseconds).substring(0,11);
        String time = TimeUtils.millis2String(millseconds).substring(11,16);

        if (!date.equals(dateFilterStr)){
            //日期发生改变，需重新请求数据
            dateFilterStr = date;
            asyncInitData();
        }

        if (!time.equals(timeFilterStr)){
            timeFilterStr = time;
            updatePatientData();
        }

        tvVitalSignChooseTime.setText(TimeUtils.millis2String(millseconds).substring(0,16));
    }


}