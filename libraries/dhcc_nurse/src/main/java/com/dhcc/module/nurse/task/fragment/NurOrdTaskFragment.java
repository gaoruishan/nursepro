package com.dhcc.module.nurse.task.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.TaskViewApiManager;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdAdapter;
import com.dhcc.module.nurse.task.bean.AllBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurTaskSchBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:14:25
 */
public class NurOrdTaskFragment  extends BaseNurseFragment {

    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private TaskNurOrdAdapter taskNurOrdAdapter;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private ImgResetView imgReset;
    private int askCount = 0;
    private String regNo = "",bedStr = "",sheetCode="",patInfoFromTask,statusCode="1",inputTyp="0";
    private List<TimesListBean> timesListBeans= new ArrayList<>();
    private List<AllBean> listAllBean = new ArrayList<>();
    private NurTaskSchBean nurTaskSchBean ;

    @Override
    protected void initViews() {
        super.initViews();
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (nurTaskSchBean.getTypeList().size()>0){
                    for (int i = 0; i < nurTaskSchBean.getTypeList().size(); i++) {
                        if (customSheet.getSheetListAdapter().getData().get(position).getCode().equals(nurTaskSchBean.getTypeList().get(i).getLongNameEN())){
                            inputTyp = nurTaskSchBean.getTypeList().get(i).getId();
                        }
                    }
                    getNurOrdTaskList();
                }

            }
        });
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 00:00", YYYY_MM_DD_HH_MM));
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 23:59", YYYY_MM_DD_HH_MM));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNurOrdTaskList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNurOrdTaskList();
            }
        });

        taskNurOrdAdapter = AdapterFactory.getTaskNurOrdAdapter();
        RecyclerViewHelper.get(mContext, R.id.rv_list_ord).setAdapter(taskNurOrdAdapter);

        taskNurOrdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("recordId",taskNurOrdAdapter.getData().get(position).getRecordId());
                bundle.putString("interventionDR",taskNurOrdAdapter.getData().get(position).getInterventionDR());
                bundle.putString("episodeId",taskNurOrdAdapter.getData().get(position).getEpisodeID());
                bundle.putString("patInfo",taskNurOrdAdapter.getData().get(position).getBedCode()+taskNurOrdAdapter.getData().get(position).getName());
                startFragment(NurOrdRecordFragment.class,bundle);
            }
        });
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        addToolBarRight();
        setToolbarCenterTitle("护嘱任务");
        if (bundle != null) {
            if (bundle.getString("sheetCode") != null) {
                sheetCode = bundle.getString("sheetCode");
                regNo = bundle.getString("regNo");
                patInfoFromTask = bundle.getString("patInfo");
                bedStr = bundle.getString("bedStr");
                timesListBeans = (List<TimesListBean>) bundle.getSerializable("timeList");
                listAllBean = (List<AllBean>) bundle.getSerializable("listAllBean");
                mSheetListBeanList = new ArrayList<>();
                for (int i = 0; i < listAllBean.size(); i++) {
                    mSheetListBeanList.add(new SheetListBean(listAllBean.get(i).getCode(),listAllBean.get(i).getName(),listAllBean.get(i).getValue()));
                }
                customSheet.setDatas(mSheetListBeanList);
                if (timesListBeans.size()>0){
                    setTimeListData(timesListBeans);
                }
                customDate.setStartDateTime(TimeUtils.string2Millis(bundle.getString("sttDateTime").substring(0, 10)+" "+bundle.getString("sttDateTime").substring(11, 16), YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(bundle.getString("endDateTime").substring(0, 10)+" "+bundle.getString("endDateTime").substring(11, 16), YYYY_MM_DD_HH_MM));
            }
        }
        customSheet.setSheetDefCode(sheetCode);
        imgReset = f(R.id.img_resetview, ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                regNo = "";
                bedStr = "";
                getNurOrdTaskList();
            }
        });
        if (patInfoFromTask!=null){
            imgReset.setTvPatText(patInfoFromTask);
        }
        getNurTaskSch();
    }

    @Override
    public void onResume() {
        super.onResume();
        getNurOrdTaskList();
    }

    private void addToolBarRight(){
        addToolBarRightImageView(R.drawable.dhcc_filter_big_write, R.drawable.dhcc_icon_bed_select);

        setbStatus(true);
        addToolBarRightPopWindow();
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {
                customDate.setStartDateTime(TimeUtils.string2Millis(sttDt, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDt, YYYY_MM_DD_HH_MM));
                getNurOrdTaskList();
            }

            @Override
            public void statusSure(String code) {
                statusCode = code;
                getNurOrdTaskList();
            }
        });
    }

    private void getNurTaskSch(){
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getNurTaskSch(new CommonCallBack<NurTaskSchBean>() {
            @Override
            public void onFail(String code, String msg) {
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurTaskSchBean bean, String type) {
                nurTaskSchBean = bean;
                hideLoadingTip();
                statusCode = nurTaskSchBean.getStatusList().get(0).getValue();
                setStatusListData(bean.getStatusList());
                if (nurTaskSchBean.getTypeList().size()>0) {
                    for (int i = 0; i < nurTaskSchBean.getTypeList().size(); i++) {
                        if (customSheet.getSheetListAdapter().getSelectedCode().equals(nurTaskSchBean.getTypeList().get(i).getLongNameEN())) {
                            inputTyp = nurTaskSchBean.getTypeList().get(i).getId();
                        }
                    }
                }
//                getNurOrdTaskList();
            }
        });
    }
    private void getNurOrdTaskList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getNurOrdTaskList(customDate.getStartDateTimeText(),customDate.getEndDateTimeText(),regNo, statusCode, inputTyp, bedStr, new CommonCallBack<NurOrdTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                askCount = 0;
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurOrdTaskBean bean, String type) {
                askCount = 0;
                hideLoadingTip();
                if ("".equals(regNo)){
                    imgReset.setTvPatText("全部患者");
                }
                if (!"".equals(bedStr)){
                    imgReset.setTvPatText("选床患者");
                }
                taskNurOrdAdapter.setNewData(bean.getTaskDataList());
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right1) {
            setMaskShow();
        }
        if (v.getId() == R.id.img_toolbar_right2) {
            startFragment(FRAGMENT_BED_SELECT,null,1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            regNo = "";
            if (askCount == 0) {
                askCount++;
                getNurOrdTaskList();
            }
        }
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
                getNurOrdTaskList();
            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_task_nurord;
    }
}
