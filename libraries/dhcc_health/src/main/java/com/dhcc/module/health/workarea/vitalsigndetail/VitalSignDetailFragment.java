package com.dhcc.module.health.workarea.vitalsigndetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.ViewUtil;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.vitalsign.api.VitalSignsApiManager;
import com.dhcc.module.health.workarea.vitalsign.bean.VitalSignDetailBean;
import com.dhcc.module.health.workarea.vitalsigndetail.adapter.VitalSignDetailAdapter;
import com.dhcc.res.infusion.CustomScanView;
import com.dhcc.res.nurse.CustomEmptyLayout;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignDetailFragment extends BaseFragment implements View.OnClickListener, OnDateSetListener {

    private RecyclerView recyclerView;
    private LinearLayout llvid,lllist;
    private TextView tvEndate, tvStdate,tvcode,tvdatetime;
    private LinearLayout llEmpty;
//    private List<VitalSignDetailBean.TempDataListBean> listBeans;
    private List<Map> listMap =new ArrayList<>();
    private List<VitalSignDetailBean.TempConfigBean> listBeansTitle =new ArrayList<>();
    private VitalSignDetailAdapter vitalSignDetailAdapter;
    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeId, stDate, enDate, datestr;
    private Bundle mBundle;
    private CustomScanView customScan;

    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle("生命体征", 0xffffffff, 17);

        stDate = spUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
        enDate = spUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
//        Bundle bundle = getArguments();
//        mBundle = bundle;
//        episodeId = bundle.getString("episodeId");

        initView(view);
//        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    private void initView(View view) {
        customScan = f(R.id.custom_scan, CustomScanView.class);
        customScan.setTitle("请扫描腕带").setWarning("请使用扫描设备,扫描病人腕带");
        lllist = view.findViewById(R.id.ll_vital_list);
        llvid = view.findViewById(R.id.ll_vid);
        llEmpty = view.findViewById(R.id.ll_vital_empty);
        tvStdate = view.findViewById(R.id.tv_stdate);
        tvEndate = view.findViewById(R.id.tv_endate);
        tvStdate.setOnClickListener(this);
        tvEndate.setOnClickListener(this);
        tvStdate.setText(stDate);
        tvEndate.setText(enDate);
        tvcode = view.findViewById(R.id.tv_detail_codetitle);
        tvcode.setTextSize(14);
        tvdatetime = view.findViewById(R.id.tv_detail_datetime);
        tvdatetime.setTextSize(14);

        recyclerView = view.findViewById(R.id.recy_vitalsign_detail);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vitalSignDetailAdapter = new VitalSignDetailAdapter(new ArrayList<Map>(),getContext());
        recyclerView.setAdapter(vitalSignDetailAdapter);
//        vitalSignDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Map bean = listMap.get(position);
//                mBundle.putString("time",bean.get("time").toString());
//                mBundle.putString("date",bean.get("date").toString());
//                startFragment(VitalSignRecordFragment.class,mBundle);
//            }
//        });
    }

    private void initData() {
        showLoadingTip(BaseActivity.LoadingType.FULL);

        VitalSignsApiManager.getPatTempDetail(episodeId, stDate, enDate, new CommonCallBack<VitalSignDetailBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();

            }

            @Override
            public void onSuccess(VitalSignDetailBean bean, String type) {
                hideLoadingTip();
                customScan.setVisibility(View.GONE);
//                customScan.setVisibility(View.GONE);
                listBeansTitle = bean.getTempConfig();
//                List list = new ArrayList<Map>();
                listMap = (List) bean.getMap().get("tempDataList");
                if (listMap.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    lllist.setVisibility(View.GONE);
                } else {
                    llEmpty.setVisibility(View.GONE);
                    lllist.setVisibility(View.VISIBLE);
                }

                vitalSignDetailAdapter.setListTitle(listBeansTitle);
                vitalSignDetailAdapter.setNewData(listMap);
                vitalSignDetailAdapter.notifyDataSetChanged();

                initTitle(listBeansTitle);
            }
        });

//        VitalSignDetailApiManager.getVitalSignDetail(map, new VitalSignDetailApiManager.GetEventsResultMsgCallBack() {
//            @Override
//            public void onSuccess(VitalSignDetailBean vitalSignDetailBean) {
////                listBeans = vitalSignDetailBean.getTempDataList();
//                listBeansTitle = vitalSignDetailBean.getTempConfig();
////                List list = new ArrayList<Map>();
//                listMap = (List) vitalSignDetailBean.getMap().get("tempDataList");
//                if (listMap.size() == 0) {
//                    llEmpty.setVisibility(View.VISIBLE);
//                    lllist.setVisibility(View.GONE);
//                } else {
//                    llEmpty.setVisibility(View.GONE);
//                    lllist.setVisibility(View.VISIBLE);
//                }
//
//                vitalSignDetailAdapter.setListTitle(listBeansTitle);
//                vitalSignDetailAdapter.setNewData(listMap);
//                vitalSignDetailAdapter.notifyDataSetChanged();
//
//                initTitle(listBeansTitle);
//            }
//            @Override
//            public void onFail(String code, String msg) {
//                showToast("error" + code + ":" + msg);
//            }
//        });
    }

    private void initTitle(List<VitalSignDetailBean.TempConfigBean> listBeansConfig) {
        llvid.removeAllViews();
        int height = ConvertUtils.dp2px(60);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height, height);
        for (int i = 0; i < listBeansConfig.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setText(listBeansConfig.get(i).getDesc());
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
            textView.setTextSize(14);
            llvid.addView(textView);
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        //精确到day
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        if (datestr.equals("start")) {
            tvStdate.setText(time);
            stDate = time;
        } else {
            tvEndate.setText(time);
            enDate = time;
        }
        initData();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_stdate) {
            datestr = "start";
            pickTime(TimeUtils.string2Millis(tvStdate.getText().toString() + " 00:00:00"));
        } else if (id == R.id.tv_endate) {
            datestr = "end";
            pickTime(TimeUtils.string2Millis(tvEndate.getText().toString() + " 00:00:00"));
        }
    }

    private void pickTime(long currentTimeMillis) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(getActivity().getSupportFragmentManager(), "ALL");
    }
    @Override
    public void getScanMsg(Intent intent) {
        super.getScanMsg(intent);
        episodeId = doScanInfo(intent);
        initData();
    }
}