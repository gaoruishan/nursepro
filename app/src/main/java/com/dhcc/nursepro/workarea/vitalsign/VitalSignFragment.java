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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignPatientAdapter;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignTypeAdapter;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VitalSignFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvVitalSignAllarea;
    private TextView tvVitalSignAdminarea;
    private TextView tvVitalSignNowoutarea;
    private TextView tvVitalSignAlloutarea;
    private TextView tvVitalSignWaitarea;

    private RecyclerView recyVitalSignType;
    private RecyclerView recyVitalSignPatient;

    private VitalSignPatientAdapter patientAdapter;
    private VitalSignTypeAdapter typeAdapter;

    private ArrayList topFilterList;

    private List leftFilterList;

    private List patientList;

    private List<Map> displayList = new ArrayList<>();


    private String topFilterStr = "inBedAll";
    private String timeFilterStr = "06:00";

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign));


        initView(view);
        initAdapter();
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
                if (view.getId() == R.id.tv_vitalsign_vitalsign_record){
                    //体征录入
                }else if(view.getId() == R.id.tv_vitalsign_event_record){
                    //事件登记
                }else if(view.getId() == R.id.tv_vitalsign_tmp_preview){
                    //体温单预览
                }else{
                    //普通点击
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

        VitalSignApiManager.getVitalSignList("2018-08-17", new VitalSignApiManager.GetVitalSignListCallback() {
            @Override
            public void onSuccess(Map<String, Object> map) {

                topFilterList = (ArrayList) map.get("topFilter");
                leftFilterList = (ArrayList)map.get("leftFilter");
                patientList = (ArrayList)map.get("patInfoList");

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

                hideLoadFailTip();
                updatePatientData();

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
                break;
            case R.id.tv_vitalsign_adminarea:
                topFilterStr = "manageInBed";
                break;
            case R.id.tv_vitalsign_nowoutarea:
                topFilterStr = "todayOut";
                break;
            case R.id.tv_vitalsign_alloutarea:
                topFilterStr = "allOut";
                break;
            case R.id.tv_vitalsign_waitarea:
                topFilterStr = "wait";
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

//
//
//        for (int i = 0; i < tmpFilterPatientList.size(); i++){
//
//            VitalSignBean.PatInfoListBean patient = tmpFilterPatientList.get(i);
//
//            List<VitalSignBean.PatInfoListBean.NeedMeasureInfoBean> list = patient.getNeedMeasureInfo();
//
//            boolean needShow = false;
//            for( int j = 0; j <  list.size(); j++){
//                List<VitalSignBean.PatInfoListBean.NeedMeasureInfoBean.NeedItemBean> need = list.get(j).getNeedItem();
//
//                if (list.get(j).getNeedTimePoint().equals(timeFilterStr)){
//
//                }
//
//                for (int k = 0; k < selectedLeftFilter.size(); k++){
//                    String code = selectedLeftFilter.get(k).getCode();
//                    if (need.get(j).getCode() != null && need.get(j).getCode().equals(code) && need.get(j).getValue().equals("1")){
//                        needShow = true;
//                    }
//                }
//            }
//            if (needShow){
//                displayList.add(patient);
//            }
//        }

        patientAdapter.setNewData(displayList);

    }



}
