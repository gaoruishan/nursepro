package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BasePopWindow;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.adapter.TaskListAdapter;
import com.dhcc.module.nurse.task.adapter.TimeFilterAdapter;
import com.dhcc.module.nurse.task.bean.AllBean;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TaskBean;
import com.dhcc.module.nurse.task.bean.TimesListBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.dhcc.res.nurse.ImgResetView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务总览
 * @author:gaoruishan
 * @date:202020-07-17/10:41
 * @email:grs0515@163.com
 */
public class TaskOverviewFragment extends BaseNurseFragment {
    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private RecyclerView recTaskList;
    private TaskListAdapter taskListAdapter = AdapterFactory.getTaskListAdapter();
    private String bedStr="",regNo="";
    private int askCount = 0;
    private ImgResetView imgReset;
    private List<SheetListBean> mSheetListBeanList = new ArrayList<>();
    private String sheetCode="";
    private List<TimesListBean> timeListBeans = new ArrayList<>();

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("任务总览");
        addToolBarRight();
        initAdapter();
        scanInfo="";
        getTaskViewList();
    }

    private void addToolBarRight(){
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        viewright.findViewById(R.id.img_toolbar_right_filter).setOnClickListener(this);
        viewright.findViewById(R.id.img_toolbar_right).setOnClickListener(this);
        setToolbarRightCustomView(viewright);

        addToolBarRightPopWindow();
        setOnPopClicListner(new OnPopClicListner() {
            @Override
            public void sure(String sttDt, String endDt) {
                customDate.setStartDateTime(TimeUtils.string2Millis(sttDt, YYYY_MM_DD_HH_MM));
                customDate.setEndDateTime(TimeUtils.string2Millis(endDt, YYYY_MM_DD_HH_MM));
                getTaskViewList();
            }

            @Override
            public void statusSure(String code) {

            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        recTaskList = f(R.id.rv_list_task,RecyclerView.class);
        recTaskList.setAdapter(taskListAdapter);
        recTaskList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 00:00", YYYY_MM_DD_HH_MM));
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate.substring(0,10)+" 23:59", YYYY_MM_DD_HH_MM));
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getTaskViewList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getTaskViewList();
            }
        });
        imgReset = f(R.id.img_resetview,ImgResetView.class);
        imgReset.setTouchListener(new ImgResetView.touchEventListner() {
            @Override
            public void reset() {
                regNo = "";
                bedStr = "";
                getTaskViewList();
            }
        });
    }

    private void initAdapter(){

        taskListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("sheetCode",taskListAdapter.getData().get(position).getCode());
                bundle.putString("bedStr",bedStr);
                bundle.putString("regNo",regNo);
                bundle.putString("patInfo",imgReset.getTvPatInfo());
                bundle.putString("sttDateTime",customDate.getStartDateTimeText());
                bundle.putString("endDateTime",customDate.getEndDateTimeText());
                bundle.putSerializable("timeList", (Serializable) timeListBeans);
                bundle.putSerializable("listAllBean", (Serializable) taskListAdapter.getData());
                switch (sheetCode){
                    case "ordTask"://医嘱查询
                        try {
                            Class<? extends BaseFragment> OrderExecuteFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment");
                            startFragment(OrderExecuteFragmentClass,bundle);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "nurOrdTask"://护嘱查询
                        startFragment(NurOrdTaskFragment.class,bundle);
                        break;
                    case "nurNormal"://常规治疗
                        startFragment(NormalOrdTaskFragment.class,bundle);
                        break;
                    case "nurRate"://护理评估
                        break;
                    case "nurTemper"://体征查询

                        startFragment(TempTaskFragment.class,bundle);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            regNo="";
            if (askCount == 0) {
                askCount++;
                getTaskViewList();
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.img_toolbar_right) {
            try {
                Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.bedselect.BedSelectFragment");
                startFragment(BedFragmentClass,1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.img_toolbar_right_filter) {
            setMaskShow();
        }
    }

    private void getTaskViewList() {
        showLoadingTip(BaseActivity.LoadingType.FULL);
        TaskViewApiManager.getTaskList(regNo, customDate.getStartDateTimeText(), customDate.getEndDateTimeText(),bedStr, new CommonCallBack<TaskBean>() {
            @Override
            public void onFail(String code, String msg) {
                askCount = 0;
                hideLoadingTip();
                onFailThings();
            }
            @Override
            public void onSuccess(TaskBean bean, String type) {
                askCount = 0;
                hideLoadingTip();
                mSheetListBeanList = new ArrayList<>();
                if (regNo.equals("")){
                    imgReset.setTvPatText("全部患者");
                }
                if (!bedStr.equals("")){
                    imgReset.setTvPatText("选床患者");
                }
                for (int i = 0; i <bean.getSchSheetList().size() ; i++) {
                    mSheetListBeanList.add(new SheetListBean(bean.getSchSheetList().get(i).getSchCode(),bean.getSchSheetList().get(i).getSchDesc()));
                }
                customSheet.setDatas(mSheetListBeanList);
                customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        desc = mSheetListBeanList.get(position).getDesc();
                        sheetCode = mSheetListBeanList.get(position).getCode();
                        taskListFilter(bean);
                    }
                });
                if (sheetCode.equals("")){
                    sheetCode=bean.getSchSheetList().get(0).getSchCode();
                }
                timeListBeans = bean.getTimesList();
                setTimeListData(bean.getTimesList());
                taskListFilter(bean);
            }
        });
    }

    private void taskListFilter(TaskBean bean){
        Boolean ifTaskEmpty=true;
        for (int i = 0; i < bean.getSchList().size(); i++) {
            if (sheetCode.equals(bean.getSchList().get(i).getSchCode())){
                taskListAdapter.setNewData(bean.getSchList().get(i).getRecList().getBody().getAll());
                ifTaskEmpty=false;
            }
        }
        if (ifTaskEmpty){
            taskListAdapter.setNewData(new ArrayList<>());
            showToast("该项没有任务");
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
                bedStr = "";
                imgReset.setTvPatText("".equals(bean.getPatInfo().getBedCode()) ? "未分床  " + bean.getPatInfo().getName() + "  " + bean.getPatInfo().getSex() + "  " +
                        "" + bean.getPatInfo().getAge() : bean.getPatInfo().getBedCode().replace("床", "") + "床  " + bean.getPatInfo().getName() + "" +
                        "  " + bean.getPatInfo().getSex() + "  " + bean.getPatInfo().getAge());
                getTaskViewList();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_overview;
    }
}
