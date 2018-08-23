package com.dhcc.nursepro.workarea.vitalsigndetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsigndetail.adapter.VitalSignDetailAdapter;
import com.dhcc.nursepro.workarea.vitalsigndetail.adapter.VitalSignDetailCodeAdapter;
import com.dhcc.nursepro.workarea.vitalsigndetail.adapter.VitalSignTitleAdapter;
import com.dhcc.nursepro.workarea.vitalsigndetail.api.VitalSignDetailApiManager;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignDetailFragment extends BaseFragment implements View.OnClickListener,OnDateSetListener {

    private RecyclerView recyclerView,recyclertitleView,recyclercodeView;
    private TextView tvEndate,tvStdate,tvcodetitle;
    private List<VitalSignDetailBean.TempDataListBean> listBeans;
    private List<VitalSignDetailBean.TempConfigBean> listBeansTitle;
    private List<String> listcode;
    private List<Map<String,String>> listTitle;
    private VitalSignDetailAdapter vitalSignDetailAdapter;
    private VitalSignDetailCodeAdapter vitalSignDetailCodeAdapter;
    private VitalSignTitleAdapter vitalSignTitleAdapter;
    private SPUtils spUtils = SPUtils.getInstance();
    private String episodeId,stDate,enDate,datestr;
    @Override
    public View onCreateViewByYM(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vital_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbarType(BaseActivity.ToolbarType.TOP);
        setToolbarBottomLineVisibility(true);
        setToolbarCenterTitle(getString(R.string.title_vitalsigndetail));

        stDate = spUtils.getString("SCHSTDATETIME").substring(0,10);
        enDate = spUtils.getString("SCHENDATETIME").substring(0,10);
//        stDate = "2018-03-23";
//        enDate ="2018-08-23";
        Bundle bundle = getArguments();
        episodeId = bundle.getString("episodeId");

        initView(view);
        initData();





    }

    private void initView(View view){
        tvStdate = view.findViewById(R.id.tv_stdate);
        tvEndate = view.findViewById(R.id.tv_endate);
        tvStdate.setOnClickListener(this);
        tvEndate.setOnClickListener(this);
        tvStdate.setText(stDate);
        tvEndate.setText(enDate);
        tvcodetitle = view.findViewById(R.id.tv_detail_codetitle);


        recyclerView = view.findViewById(R.id.recy_vitalsign_detail);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vitalSignDetailAdapter = new VitalSignDetailAdapter(new ArrayList<VitalSignDetailBean.TempDataListBean>());
        recyclerView.setAdapter(vitalSignDetailAdapter);
        vitalSignDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast(position+"----");
            }
        });

        recyclercodeView = view.findViewById(R.id.recy_vitalsign_detailcode);
        recyclercodeView.setHasFixedSize(true);
        recyclercodeView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vitalSignDetailCodeAdapter = new VitalSignDetailCodeAdapter(new ArrayList<String>());
        recyclercodeView.setAdapter(vitalSignDetailCodeAdapter);

        recyclertitleView = view.findViewById(R.id.recy_vitalsign_title);
        recyclertitleView.setHasFixedSize(true);
        recyclertitleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        vitalSignTitleAdapter = new VitalSignTitleAdapter(new ArrayList<Map<String, String>>());
        recyclertitleView.setAdapter(vitalSignTitleAdapter);



        listTitle = new ArrayList<Map<String, String>>();

    }

    private void initData(){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("episodeId",episodeId);
        map.put("stDate",stDate);
        map.put("endDate",enDate);
        VitalSignDetailApiManager.GetVitalSignDetail(map, new VitalSignDetailApiManager.GetEventsResultMsgCallBack() {
            @Override
            public void onSuccess(VitalSignDetailBean vitalSignDetailBean) {
                listBeans = vitalSignDetailBean.getTempDataList();
                listBeansTitle = vitalSignDetailBean.getTempConfig();
                if (listBeans.size() == 0){
                    tvcodetitle.setVisibility(View.GONE);
                    showToast("该时间段内没有体征记录");
                }else {
                    tvcodetitle.setVisibility(View.VISIBLE);
                }

                vitalSignDetailAdapter.setNewData(listBeans);
                vitalSignDetailAdapter.notifyDataSetChanged();
                listcode = new ArrayList<String>();
                for (int i = 0;i<listBeans.size();i++){
                    listcode.add(i+1+"");
                }
                vitalSignDetailCodeAdapter.setNewData(listcode);
                vitalSignDetailCodeAdapter.notifyDataSetChanged();
//                showToast(listBeans.size()+"--"+listcode.size());
                initTitle(listBeansTitle);


            }

            @Override
            public void onFail(String code, String msg) {

            }
        });
    }

    private void initTitle(List<VitalSignDetailBean.TempConfigBean> listBeansConfig){
        HashMap<String,String> map = new HashMap<String, String>();
        listTitle = new ArrayList<Map<String, String>>();
        for (int i = 0;i<listBeansConfig.size();i++){
            map.put(listBeansConfig.get(i).getCode(),listBeansConfig.get(i).getDesc());
        }
        listTitle.add(map);
        vitalSignTitleAdapter.setNewData(listTitle);
        vitalSignTitleAdapter.notifyDataSetChanged();

    }

    private void pickTime(){
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
                .setThemeColor(getResources().getColor(R.color.colorAccent))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.settype(1);
        //取时间前两个字符转为int（02，06...）
        String[] stringhours = {"02:00","06:00","10:00","14:00"};
//                mDialogAll.setintHour(stringhours);

        mDialogAll.show(getActivity().getSupportFragmentManager(), "ALL");

    }
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//精确到day
        String time = format.format(date);
        if (datestr.equals("start")){
            tvStdate.setText(time);
            stDate = time;
        }else {
            tvEndate.setText(time);
            enDate = time;
        }
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_stdate:
                datestr = "start";
                pickTime();
                break;
            case R.id.tv_endate:
                datestr = "end";
                pickTime();
                break;
        }
    }
}
