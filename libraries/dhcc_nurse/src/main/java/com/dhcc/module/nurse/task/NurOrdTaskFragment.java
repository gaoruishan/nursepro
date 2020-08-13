package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.dhcc.module.nurse.task.adapter.TaskNormalOrdAdapter;
import com.dhcc.module.nurse.task.adapter.TaskNurOrdAdapter;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdRecordTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TempTaskBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomOrdExeBottomView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.ClickBean;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.Serializable;
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
    private RecyclerView recNormalOrd;
    private TaskNurOrdAdapter taskNurOrdAdapter;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private NormalOrdTaskBean normalOrdTaskBean = new NormalOrdTaskBean();
    private CustomOrdExeBottomView customSelectBottom;
    private ImgResetView imgReset;
    private int taskSheetSelet = 0;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private int askCount = 0;
    private String regNo = "",bedStr = "",sheetCode,patInfoFromTask;
    private Boolean ifFromTask=false;
    private List<TimesListBean> timesListBeans= new ArrayList<>();

    @Override
    protected void initDatas() {
        super.initDatas();
        addToolBarRight();
        setHindBottm(50);
        setToolbarCenterTitle("常规治疗");
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
                if (timesListBeans.size()>0){
                    setTimeListData(timesListBeans);
                }
                customDate.setStartDateTime(TimeUtils.string2Millis(startDate+" "+startTime, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDate+" "+endTime, YYYY_MM_DD_HH_MM));
                ifFromTask = true;
            }
        }
        imgReset = f(R.id.img_resetview, ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                regNo = "";
                bedStr = "";
                getNormalOrdList();
            }
        });
        if (patInfoFromTask!=null){
            imgReset.setTvPatText(patInfoFromTask);
        }
        getNormalOrdList();
    }
    private void addToolBarRight(){
        addToolBarRightImageView(R.drawable.dhcc_filter_big_write, R.drawable.dhcc_icon_bed_select);

        addToolBarRightPopWindow();
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {
                customDate.setStartDateTime(TimeUtils.string2Millis(sttDt, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDt, YYYY_MM_DD_HH_MM));
                showToast(sttDt);
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
            try {
                Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.bedselect.BedSelectFragment");
                startFragment(BedFragmentClass,1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            if (askCount == 0) {
                askCount++;
                getNormalOrdList();
            }
        }
    }

    private void getNormalOrdList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getNurOrdTaskList("", "", "", "", new CommonCallBack<NurOrdTaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                askCount = 0;
                hideLoadingTip();
            }

            @Override
            public void onSuccess(NurOrdTaskBean bean, String type) {
                askCount = 0;
                hideLoadingTip();
                if (regNo.equals("")){
                    imgReset.setTvPatText("全部患者");
                }
                if (!bedStr.equals("")){
                    imgReset.setTvPatText("选床患者");
                }
                taskNurOrdAdapter.setNewData(bean.getTaskDataList());
//                normalOrdTaskBean = bean;
                mSheetListBeanList = new ArrayList<>();
//                if (regNo.equals("")){
//                    imgReset.setTvPatText("全部患者");
//                }
//                for (int i = 0; i <bean.getBodyUnExec().size() ; i++) {
//                    mSheetListBeanList.add(new SheetListBean(bean.getBodyUnExec().get(i).getCode(),bean.getBodyUnExec().get(i).getName(),bean.getBodyUnExec().get(i).getOrders().size()+""));
//                }
//                customSheet.setDatas(mSheetListBeanList);
                getLeftFilterTaskList();
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSelectBottom = f(R.id.custom_ordnormaltask_select_bottom, CustomOrdExeBottomView.class);
        customSelectBottom.setExeTypeVisible(View.GONE);
        List<ClickBean> beans = new ArrayList<>();
        beans.add(new ClickBean("执行", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execEdu();
            }
        }));
        customSelectBottom.addBottomView(beans);
        customSelectBottom.setSelectText("已选 " + 0 + " 个");

        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getLeftFilterTaskList();
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD_HH_MM));
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD_HH_MM) - CustomDateTimeView.ONE_DAY * 6);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNormalOrdList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getNormalOrdList();
            }
        });


        recNormalOrd = RecyclerViewHelper.get(mContext, R.id.rv_list_ord);
        taskNurOrdAdapter = AdapterFactory.getTaskNurOrdAdapter();
        recNormalOrd.setAdapter(taskNurOrdAdapter);

        taskNurOrdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("recordId",taskNurOrdAdapter.getData().get(position).getRecordId());
                bundle.putString("interventionDR",taskNurOrdAdapter.getData().get(position).getInterventionDR());
//                startFragment(NurOrdRecordFragment.class,bundle);

                TaskViewApiManager.getExecuteTaskList( taskNurOrdAdapter.getData().get(position).getRecordId(),  taskNurOrdAdapter.getData().get(position).getInterventionDR(), new CommonCallBack<NurOrdRecordTaskBean>() {
                    @Override
                    public void onFail(String code, String msg) {
                        askCount = 0;
                        hideLoadingTip();
                    }

                    @Override
                    public void onSuccess(NurOrdRecordTaskBean bean, String type) {
                        askCount = 0;
                        hideLoadingTip();
                        String ss = taskNurOrdAdapter.getData().get(position).getRecordId()+taskNurOrdAdapter.getData().get(position).getInterventionDR();
                        showToast(bean.getMsg()+bean.getTaskSetList().size());
                    }
                });



//                if (taskNurOrdAdapter.getData().get(position).get(0).getSelect()){
//                    taskNurOrdAdapter.getData().get(position).get(0).setSelect(false);
//                }else {
//                    taskNurOrdAdapter.getData().get(position).get(0).setSelect(true);
//                }
//                taskNurOrdAdapter.notifyDataSetChanged();
//                int taskSelect = 0;
//                for (int i = 0; i < taskNurOrdAdapter.getData().size(); i++) {
//                    if (taskNurOrdAdapter.getData().get(i).get(0).getSelect()){
//                        taskSelect++;
//                    }
//                }
//                customSelectBottom.setSelectText("已选 " + taskSelect + " 个");

            }
        });

    }

    private void getLeftFilterTaskList(){
//        for (int i = 0; i < normalOrdTaskBean.getBodyUnExec().size(); i++) {
//            if (sheetCode!=null&&normalOrdTaskBean.getBodyUnExec().get(i).getCode().equals(sheetCode)){
//                customSheet.setSheetDefCode(sheetCode);
//                sheetCode = "";
//            }
//        }
//        for (int i = 0; i < taskNurOrdAdapter.getData().size(); i++) {
//            taskNurOrdAdapter.getData().get(i).get(0).setSelect(false);
//        }
//        for (int i = 0; i < normalOrdTaskBean.getBodyUnExec().size(); i++) {
//            if (normalOrdTaskBean.getBodyUnExec().get(i).getCode().equals(customSheet.getSheetListAdapter().getSelectedCode())){
//                taskNurOrdAdapter.setNewData(normalOrdTaskBean.getBodyUnExec().get(i).getOrders());
//            }
//        }
//        customSelectBottom.setSelectText("已选 " + 0 + " 个");
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
                getNormalOrdList();
            }
        });
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_task_nurord;
    }
}
