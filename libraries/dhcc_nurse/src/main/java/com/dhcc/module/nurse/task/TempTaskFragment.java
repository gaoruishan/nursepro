package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.adapter.TaskTempAdapter;
import com.dhcc.module.nurse.task.bean.AllBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TempTaskBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/11
 * Time:10:21
 */
public class TempTaskFragment extends BaseNurseFragment {

    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private RecyclerView recNormalOrd;
    private LinearLayout llOrderType ;
    private View viewOrderType;
    private TextView tvOrderType ,tvOrderNum;
    private TaskTempAdapter taskTempAdapter;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private TempTaskBean tempTaskBean = new TempTaskBean();
    private ImgResetView imgReset;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private int askCount = 0;
    private String regNo = "",bedStr = "",sheetCode,patInfoFromTask;
    private Boolean ifFromTask=false;
    private List<TimesListBean> timesListBeans= new ArrayList<>();
    private List<AllBean> listAllBean = new ArrayList<>();

    @Override
    protected void initDatas() {
        super.initDatas();
        addToolBarRight();
        setToolbarCenterTitle("体征任务");
        if (bundle != null) {
            if (bundle.getString("sheetCode") != null) {
                sheetCode = bundle.getString("sheetCode");
                regNo = bundle.getString("regNo");
                patInfoFromTask = bundle.getString("patInfo");
                bedStr = bundle.getString("bedStr");
                startDate = bundle.getString("sttDateTime").substring(0, 10);
                startTime = bundle.getString("sttDateTime").substring(11, 16);
                endDate = bundle.getString("endDateTime").substring(0, 10);
                endTime = bundle.getString("endDateTime").substring(11, 16);
                timesListBeans = (List<TimesListBean>) bundle.getSerializable("timeList");
                listAllBean = (List<AllBean>) bundle.getSerializable("listAllBean");
                if (timesListBeans.size()>0){
                    setTimeListData(timesListBeans);
                }

                customDate.setStartDateTime(TimeUtils.string2Millis(startDate+" "+startTime, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDate+" "+endTime, YYYY_MM_DD_HH_MM));
                ifFromTask = true;
            }
        }
        if (patInfoFromTask!=null){
            imgReset.setTvPatText(patInfoFromTask);
        }
        getTempTaskList();
    }
    private void addToolBarRight(){
        addToolBarRightImageView(0,R.drawable.dhcc_icon_bed_select);
        addToolBarRightPopWindow();
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {
                customDate.setStartDateTime(TimeUtils.string2Millis(sttDt, YYYY_MM_DD));
                showToast(sttDt);
            }

            @Override
            public void statusSure(String code) {

            }
        });
    }
    @Override
    protected void initViews() {
        super.initViews();
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("执行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execEdu();
            }
        }));
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setIfSelectOne(false);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (customSheet.getSheetListAdapter().getData().get(position).getSelected().equals("0")){
                    customSheet.getSheetListAdapter().getData().get(position).setSelected("1");
                }else {
                    customSheet.getSheetListAdapter().getData().get(position).setSelected("0");
                }
                customSheet.getSheetListAdapter().notifyItemChanged(position);

                getLeftFilterTaskList();
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
//        customDate.showOnlyOne();
        customDate.setShowTime(true);
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 00:00", YYYY_MM_DD_HH_MM));
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 23:59", YYYY_MM_DD_HH_MM));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getTempTaskList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getTempTaskList();
            }
        });

        recNormalOrd = RecyclerViewHelper.get(mContext, R.id.rv_list_ord);
        taskTempAdapter = AdapterFactory.getTaskTempAdapter();
        recNormalOrd.setAdapter(taskTempAdapter);
        taskTempAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    //体征录入
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("fromTask", "Y");
                    bundle.putString("timekey", customDate.getStartDateTimeText().substring(0,10)+" "+taskTempAdapter.getData().get(position).getTimeKey());
                    bundle.putString("episodeId", taskTempAdapter.getData().get(position).getEpisodeId());
                    Class<? extends BaseFragment> VitalSignRecordFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.vitalsign.VitalSignRecordFragment");
                    startFragment(VitalSignRecordFragmentClass,bundle,1);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        imgReset = f(R.id.img_resetview, ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                regNo = "";
                bedStr = "";
                getTempTaskList();
            }
        });
        llOrderType = f(R.id.ll_temp_all, LinearLayout.class);
        llOrderType.setOnClickListener(this);
        viewOrderType = f(R.id.view_temp_all, View.class);
        tvOrderType = f(R.id.tv_temp_all, TextView.class);
        tvOrderNum = f(R.id.tv_temp_allnum, TextView.class);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right2) {
            try {
                Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.bedselect.BedSelectFragment");
                startFragment(BedFragmentClass,1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.ll_temp_all) {
          showAllTemp(true);
            for (int i = 0; i < customSheet.getSheetListAdapter().getData().size(); i++) {
                customSheet.getSheetListAdapter().getData().get(i).setSelected("0");
            }
            customSheet.getSheetListAdapter().notifyDataSetChanged();
            getLeftFilterTaskList();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if ("saveTemp".equals(data.getStringExtra("bedselectinfoStr"))){
                getTempTaskList();
            }else {
                bedStr = data.getStringExtra("bedselectinfoStr");
                regNo="";
                if (askCount == 0) {
                    askCount++;
                    getTempTaskList();
                }
            }
        }
    }

    private void getTempTaskList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getTempTaskList(regNo, "",  customDate.getStartDateTimeText(), customDate.getEndDateTimeText(), bedStr, new CommonCallBack<TempTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                askCount = 0;
                hideLoadingTip();
            }
            @Override
            public void onSuccess(TempTaskBean bean, String type) {
                askCount = 0;
                hideLoadingTip();
                if (regNo.equals("")){
                    imgReset.setTvPatText("全部患者");
                }
                if (!bedStr.equals("")){
                    imgReset.setTvPatText("选床患者");
                }
                tempTaskBean = bean;
                tvOrderNum.setText("总数："+bean.getTempDateList().size());
                mSheetListBeanList = new ArrayList<>();

                for (int i = 0; i < bean.getTempCodeList().size(); i++) {
                    mSheetListBeanList.add(new SheetListBean(bean.getTempCodeList().get(i).getCode(),
                                                             bean.getTempCodeList().get(i).getDesc(),
                            bean.getTempCodeList().get(i).getNumDesc()+":" + bean.getTempCodeList().get(i).getNum()));
                }
                customSheet.setDatas(mSheetListBeanList);
                showAllTemp(true);
                taskTempAdapter.setNewData(bean.getTempDateList());
                for (int i = 0; i < customSheet.getSheetListAdapter().getData().size(); i++) {
                    if (sheetCode.equals(customSheet.getSheetListAdapter().getData().get(i).getCode())){
                        customSheet.getSheetListAdapter().getData().get(i).setSelected("1");
                        customSheet.getSheetListAdapter().notifyDataSetChanged();
                        showAllTemp(false);
                        getLeftFilterTaskList();
                        sheetCode="";
                    }
                }

                ifFromTask = false;
            }
        });
    }

    private void showAllTemp(Boolean ifShowAll){
        llOrderType.setSelected(ifShowAll);
        tvOrderType.setTextColor(ifShowAll?mContext.getResources().getColor(com.grs.dhcc_res.R.color.dhcc_ordersearch_left_text_selected_color)
                :mContext.getResources().getColor(com.grs.dhcc_res.R.color.dhcc_ordersearch_left_text_normal_color));
        tvOrderType.setTypeface(ifShowAll?Typeface.DEFAULT_BOLD:Typeface.DEFAULT);
        viewOrderType.setVisibility(ifShowAll?View.VISIBLE:View.INVISIBLE);
    }

    private void getLeftFilterTaskList(){
        for (int i = 0; i < tempTaskBean.getTempDateList().size(); i++) {
            tempTaskBean.getTempDateList().get(i).setSelect(false);
        }
        Boolean ifShowAll = true;
        for (int i = 0; i < customSheet.getSheetListAdapter().getData().size(); i++) {
            if (customSheet.getSheetListAdapter().getData().get(i).getSelected().equals("1")){
                ifShowAll=false;
            }
        }
        if (ifShowAll){
            showAllTemp(true);
            taskTempAdapter.setNewData(tempTaskBean.getTempDateList());
        }else {
            showAllTemp(false);
            List<TempTaskBean.TempDateListBean> listBeans = new ArrayList<>();
            for (int i = 0; i < tempTaskBean.getTempDateList().size(); i++) {
                for (int j = 0; j < tempTaskBean.getTempDateList().get(i).getObsDataList().size(); j++) {
                    for (int k = 0; k < customSheet.getSheetListAdapter().getData().size(); k++) {
                        if (customSheet.getSheetListAdapter().getData().get(k).getSelected().equals("1")&&customSheet.getSheetListAdapter().getData().get(k).getCode().contains(tempTaskBean.getTempDateList().get(i).getObsDataList().get(j).getObsKey())){
                            tempTaskBean.getTempDateList().get(i).setSelect(true);
                        }
                    }
                }
            }
            for (int i = 0; i < tempTaskBean.getTempDateList().size(); i++) {
                if (tempTaskBean.getTempDateList().get(i).getSelect()){
                    listBeans.add(tempTaskBean.getTempDateList().get(i));
                }
            }
            taskTempAdapter.setNewData(listBeans);
        }

    };

    private void execEdu() {
        showToast("执行医嘱");
    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
        TaskViewApiManager.getScaninfo(scanInfo, new CommonCallBack<ScanResultBean>() {
            @Override
            public void onFail(String code, String msg) {
            }
            @Override
            public void onSuccess(ScanResultBean bean, String type) {
                regNo = bean.getPatInfo().getRegNo();
                bedStr="";
                imgReset.setTvPatText("".equals(bean.getPatInfo().getBedCode()) ? "未分床  " + bean.getPatInfo().getName() + "  " + bean.getPatInfo().getSex() + "  " +
                        "" + bean.getPatInfo().getAge() : bean.getPatInfo().getBedCode().replace("床", "") + "床  " + bean.getPatInfo().getName() + "" +
                        "  " + bean.getPatInfo().getSex() + "  " + bean.getPatInfo().getAge());
                getTempTaskList();
            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_temptask;
    }
}
