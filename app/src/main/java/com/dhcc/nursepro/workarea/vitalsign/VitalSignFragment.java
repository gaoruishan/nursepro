package com.dhcc.nursepro.workarea.vitalsign;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.NurseAPI;
import com.base.commlibs.constant.Action;
import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.allotbed.api.AllotBedApiManager;
import com.dhcc.nursepro.workarea.allotbed.bean.GetScanPatsBean;
import com.dhcc.nursepro.workarea.patevents.PatEventsFragment;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignPatientAdapter;
import com.dhcc.nursepro.workarea.vitalsign.adapter.VitalSignTypeAdapter;
import com.dhcc.nursepro.workarea.vitalsign.api.VitalSignApiManager;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignChartsDetailFragment;
import com.dhcc.nursepro.workarea.vitalsigndetail.VitalSignDetailFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.vitalsign
 * <p>
 * author Q
 * Date: 2020/8/6
 * Time:14:02
 */
public class VitalSignFragment extends BaseFragment implements View.OnClickListener{

    private TextView tvVitalSignChooseTime;
    private TextView tvResetting;
    private RecyclerView recyVitalSignType;
    private RecyclerView recyVitalSignPatient;
    private LinearLayout llTopFilter;

    private VitalSignPatientAdapter patientAdapter;
    private VitalSignTypeAdapter typeAdapter = new VitalSignTypeAdapter(new ArrayList<>());

    private List<VitalSignBean.LeftFilterBean> listLeftFilter =new ArrayList();
    private List<VitalSignBean.PatInfoListBean> listPatInfo = new ArrayList<>();
    private List<TextView> textViewList = new ArrayList<>();
    private List timeFilterList = new ArrayList();

    private SPUtils spUtils = SPUtils.getInstance();
    private String timeFilterStr = "";
    private String dateFilterStr = "";
    private String topFilterCode="";
    private Boolean ifLoading = true;

    private VitalSignBean vitalSignBeanAll = new VitalSignBean();;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_sign, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsign), 0xffffffff, 17);
        initView(view);
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                asyncInitData();
            }
        }, 300);
    }

    private void initView(View view) {

        llTopFilter = view.findViewById(R.id.ll_top_filter);
        tvResetting = view.findViewById(R.id.tv_resetting);
        tvResetting.setOnClickListener(this);
        tvVitalSignChooseTime = view.findViewById(R.id.tv_vitalsign_time);
        tvVitalSignChooseTime.setOnClickListener(this);
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

        patientAdapter = new VitalSignPatientAdapter(new ArrayList<>());
        patientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Map patientInfo = patientAdapter.getData().get(position).getPatMap();
                //将列表数据存储在sp，避免Intent传输数据过大报错
                Gson gson = new Gson();
                String displayListJsonStr = gson.toJson(patientAdapter.getData());
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

//                    String episodeId = (String) patientInfo.get("episodeId");
//                    //                    viewPatientTempImages("94");
//                    viewPatientTempImages(episodeId);

                    String episodeId = (String) patientInfo.get("episodeId");
                    Bundle bundle = new Bundle();
                    bundle.putString("episodeId", episodeId);
                    bundle.putString("patInfo", patientAdapter.getItem(position).getBedCode()+" "+patientAdapter.getItem(position).getName());
                    bundle.putInt("index", position);
                    startFragment(VitalSignChartsDetailFragment.class, bundle);

                } else {
                    //普通点击
                    String episodeId = (String) patientInfo.get("episodeId");
                    Bundle bundle = new Bundle();
                    bundle.putString("episodeId", episodeId);
                    bundle.putString("patInfo", patientAdapter.getItem(position).getBedCode()+" "+patientAdapter.getItem(position).getName());
                    bundle.putInt("index", position);
                    startFragment(VitalSignDetailFragment.class, bundle);

                }
            }
        });

        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                typeAdapter.filterClicked(position);
                typeAdapter.notifyDataSetChanged();
                leftFilter();
            }
        });

        recyVitalSignType.setAdapter(typeAdapter);
        recyVitalSignPatient.setAdapter(patientAdapter);
    }

    private void asyncInitData() {
        if (ifLoading){
            showLoadingTip(BaseActivity.LoadingType.FULL);
            ifLoading = false;
        }
        VitalSignApiManager.getVitalSignList(dateFilterStr, timeFilterStr, new VitalSignApiManager.GetVitalSignListCallback() {
            @Override
            public void onSuccess(VitalSignBean vitalSignBean) {
                hideLoadingTip();
                vitalSignBeanAll = vitalSignBean;
                timeFilterStr = vitalSignBean.getTimePoint();
                dateFilterStr = vitalSignBean.getDatePoint();
                timeFilterList = (ArrayList) vitalSignBean.getMapAll().get("timeSelect");
                tvVitalSignChooseTime.setText(dateFilterStr + "  " + timeFilterStr);

                llTopFilter.removeAllViews();
                textViewList = new ArrayList<>();

                for (int i = 0; i < vitalSignBeanAll.getTopFilter().size(); i++) {
                    TextView tvButton = new TextView(getContext());
                    tvButton.setText(vitalSignBeanAll.getTopFilter().get(i).getDesc());
                    tvButton.setGravity(Gravity.CENTER);
                    LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ConvertUtils.dp2px(100), ViewGroup.LayoutParams.MATCH_PARENT);
                    titleParams.setMargins(0, 25, 0, 25);//4个参数按顺序分别是左上右下
                    titleParams.weight = 1;
                    tvButton.setLayoutParams(titleParams);
                    tvButton.setTextColor(Color.parseColor("#000000"));
                    if (topFilterCode.equals("")){
                        tvButton.setTextColor(Color.parseColor("#4A90E2"));
                        topFilterCode = vitalSignBeanAll.getTopFilter().get(0).getCode();
                    }else if (topFilterCode.equals(vitalSignBeanAll.getTopFilter().get(i).getCode())){
                        tvButton.setTextColor(Color.parseColor("#4A90E2"));
                    }
                    tvButton.setTextSize(15);
                    tvButton.setTag(R.string.key_vis_toptext,vitalSignBeanAll.getTopFilter().get(i).getCode());
                    tvButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < textViewList.size(); j++) {
                                textViewList.get(j).setTextColor(Color.parseColor("#000000"));
                            }
                            tvButton.setTextColor(Color.parseColor("#4A90E2"));
                            topFilterCode = tvButton.getTag(R.string.key_vis_toptext).toString();
                            topFilter();
                        }
                    });
                    textViewList.add(tvButton);
                    llTopFilter.addView(tvButton);
                }
                topFilter();
            }

            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }

    private void topFilter(){
        tvResetting.setSelected(false);
        tvResetting.setText("筛选");
        tvResetting.setTextColor(getResources().getColor(R.color.vital_sign_type_normal_text));
        typeAdapter.setFilterList(new ArrayList<>());
        listLeftFilter = new ArrayList<>();
        for (int i = 0; i < vitalSignBeanAll.getTopFilter().size(); i++) {
            if (topFilterCode.equals(vitalSignBeanAll.getTopFilter().get(i).getCode())){
                for (int j = 0; j < vitalSignBeanAll.getTopFilter().get(i).getLeftFilter().size(); j++) {
                    if (vitalSignBeanAll.getTopFilter().get(i).getLeftFilter().get(j).getTemNum()>0){
                        listLeftFilter.add(vitalSignBeanAll.getTopFilter().get(i).getLeftFilter().get(j));
                    }
                }
            }
        }
        typeAdapter.setNewData(listLeftFilter);
        listPatInfo = new ArrayList<>();
        for (int i = 0; i < vitalSignBeanAll.getTopFilter().size(); i++) {
            if (topFilterCode.equals(vitalSignBeanAll.getTopFilter().get(i).getCode())){
                listPatInfo = vitalSignBeanAll.getTopFilter().get(i).getPatInfoList();
                patientAdapter.setNewData(listPatInfo);
            }
        }
    }

    private void leftFilter(){
        List<VitalSignBean.PatInfoListBean> listPatleftFilter = new ArrayList<>();
        List<Integer> listTypeSel = typeAdapter.getFilterList();
        for (int i = 0; i < listPatInfo.size(); i++) {
            for (int j = 0; j < listTypeSel.size(); j++) {
                String typeCode = typeAdapter.getData().get(listTypeSel.get(j)).getCode();
                Map map = listPatInfo.get(i).getNeedMeasureInfoMap();
                if (map!=null&&map.size()>0&&map.get(typeCode)!=null&&map.get(typeCode).equals("1")){
                    Boolean ifExist = false;
                    for (int k = 0; k < listPatleftFilter.size(); k++) {
                        if (listPatleftFilter.get(k).getEpisodeId().equals(listPatInfo.get(i).getEpisodeId())){
                            ifExist = true;
                        }
                    }
                    if (!ifExist){
                        listPatleftFilter.add(listPatInfo.get(i));
                    }
                }
            }
        }
        patientAdapter.setNewData(listPatleftFilter);
        if (listTypeSel.size()<1){
            topFilter();
        }else {
            tvResetting.setSelected(true);
            tvResetting.setText("重置");
            tvResetting.setTextColor(getResources().getColor(R.color.vital_sign_type_selected_text));
        }
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
    //扫码直接进入
    private void initScanMsg(String regNo) {
        String wardId = spUtils.getString(SharedPreference.WARDID);
        HashMap<String, String> mapmsg = new HashMap<String, String>();
        mapmsg.put("regNo", regNo);
        mapmsg.put("wardId", wardId);
        //获取用户信息，跟allotbed共用一个api
        AllotBedApiManager.getUserMsg(mapmsg, NurseAPI.getPatWristInfo, new AllotBedApiManager.GetUserMsgCallBack() {
            @Override
            public void onSuccess(GetScanPatsBean getScanPatsBean) {
                for (int i = 0; i < vitalSignBeanAll.getPatInfoList().size(); i++) {
                    if ((getScanPatsBean.getPatInfo().getRegNo()).equals(vitalSignBeanAll.getPatInfoList().get(i).getRegNo())) {

                        Map patientInfo = vitalSignBeanAll.getPatInfoList().get(i).getPatMap();
                        //将列表数据存储在sp，避免Intent传输数据过大报错
                        Gson gson = new Gson();
                        String displayListJsonStr = gson.toJson(vitalSignBeanAll.getPatInfoList());
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_resetting:
                topFilter();
                break;
            case R.id.tv_vitalsign_time:
                Long currentTimeMillis = TimeUtils.string2Millis(dateFilterStr +  " " + timeFilterStr + ":00");
                DateUtils.chooseDateTime(currentTimeMillis,getContext(), getFragmentManager(), new OnDateSetListener() {
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
                            ifLoading = true;
                            asyncInitData();
                        }

                    }
                });

                break;
            default:
                break;
        }
    }
    //体温单图片
    private void viewPatientTempImages(String episodeId) {
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
                new ImageViewer.Builder(getContext(), urls).setStartPosition(0).show();
            }
            @Override
            public void onFail(String code, String msg) {
                hideLoadFailTip();
                showToast("error" + code + ":" + msg);
            }
        });
    }
}
