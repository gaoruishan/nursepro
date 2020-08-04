package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.BasePopWindow;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.adapter.TaskOverviewAdapter;
import com.dhcc.module.nurse.task.adapter.TaskListAdapter;
import com.dhcc.module.nurse.task.adapter.TimeFilterAdapter;
import com.dhcc.module.nurse.task.bean.ScanResultBean;
import com.dhcc.module.nurse.task.bean.TaskBean;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

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
    private TaskOverviewAdapter taskOverviewAdapter;
    private RecyclerView recTaskList;
    private TaskListAdapter taskListAdapter;
    private TimeFilterAdapter timeFilterAdapter = AdapterFactory.getTimeFilterAdapter();
    private View contentView;
    private String bedStr="",regNo="";
    private int askCount = 0;
    private TextView tvPatinfo;
    private ImageView imgReset;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("任务总览");
        scanInfo="";
        getTaskViewList();
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        recTaskList = f(R.id.rv_list_task,RecyclerView.class);
        taskListAdapter = AdapterFactory.getTaskListAdapter();
        recTaskList.setAdapter(taskListAdapter);
        recTaskList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        taskListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Class<? extends BaseFragment> OrderExecuteFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.orderexecute.OrderSearchAndExecuteFragment");
                    Bundle bundle = new Bundle();
                    bundle.putString("sheetCode",taskListAdapter.getData().get(position).getCode());
                    bundle.putString("bedStr",bedStr);
                    bundle.putString("regNo",regNo);
                    bundle.putString("patInfo",tvPatinfo.getText().toString());
                    startFragment(OrderExecuteFragmentClass,bundle);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        List<SheetListBean> mSheetListBeanList = new ArrayList<>();
        mSheetListBeanList.add(new SheetListBean("YZCX","医嘱查询"));
        mSheetListBeanList.add(new SheetListBean("HZCX","护嘱查询"));
        mSheetListBeanList.add(new SheetListBean("CGZL","常规治疗"));
        mSheetListBeanList.add(new SheetListBean("HLPG","护理评估"));
        mSheetListBeanList.add(new SheetListBean("TZCX","体征查询"));
        customSheet.setDatas(mSheetListBeanList);
        customSheet.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                desc = mSheetListBeanList.get(position).getDesc();
                showToast(desc);
//                getEducationList();
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
                getTaskViewList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getTaskViewList();
            }
        });

            addToolBarRight();
//        addToolBarRightImageView(0, R.drawable.dhcc_filter_big_write);

//        RecyclerView rvTaskList = RecyclerViewHelper.get(mContext, R.id.rv_list_task);
        taskOverviewAdapter = AdapterFactory.getTaskOverviewAdapter();
//        rvTaskList.setAdapter(taskOverviewAdapter);

        taskOverviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                refreshItemData(adapter, position);
            }
        });
        taskOverviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                refreshItemData(adapter, position);
            }
        });

        tvPatinfo = f(R.id.tv_patinfo,TextView.class);
        imgReset = f(R.id.img_reset,ImageView.class);
        imgReset.setVisibility(View.VISIBLE);
        imgReset.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        imgReset.setSelected(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        imgReset.setSelected(false);
                        regNo = "";
                        bedStr = "";
                        getTaskViewList();
                        break;
                }
                return true;
            }
        });
    }

    private void addToolBarRight(){

        contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_task_filter_layout, null);
        ImageView imgClose = contentView.findViewById(R.id.iv_finish_filter);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileterPop.setMask(mContext, View.INVISIBLE);
                FileterPop.closePopWindow();
            }
        });
        RecyclerView recTime = contentView.findViewById(R.id.rec_task_timefilter);
        recTime.setAdapter(timeFilterAdapter);
        recTime.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        timeFilterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                timeFilterAdapter.setSelectItem(position);
                timeFilterAdapter.notifyDataSetChanged();
            }
        });
        contentView.findViewById(R.id.tv_filter_clear_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFilterAdapter.setSelectItem(-1);
                timeFilterAdapter.notifyDataSetChanged();
            }
        });
        contentView.findViewById(R.id.tv_filter_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileterPop.setMask(mContext, View.INVISIBLE);
                FileterPop.closePopWindow();
                if (timeFilterAdapter.getSelectItem()!=-1){
                    customDate.setStartDateTime(TimeUtils.string2Millis(timeFilterAdapter.getData().get(timeFilterAdapter.getSelectItem()).getTimeStt(), YYYY_MM_DD_HH_MM));
                    customDate.setEndDateTime(TimeUtils.string2Millis(timeFilterAdapter.getData().get(timeFilterAdapter.getSelectItem()).getTimeEnd(), YYYY_MM_DD_HH_MM));
                }
                getTaskViewList();
            }
        });

        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        ImageView imageView = viewright.findViewById(R.id.img_toolbar_right);
        ImageView imgToolbarRightFilter = viewright.findViewById(R.id.img_toolbar_right_filter);
        imgToolbarRightFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileterPop.setMask(mContext, View.VISIBLE);
                FileterPop.initPopupWindow(mContext, BasePopWindow.EnumLocation.RIGHT, contentView);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class<? extends BaseFragment> BedFragmentClass = (Class<? extends BaseFragment>) Class.forName("com.dhcc.nursepro.workarea.bedselect.BedSelectFragment");
                    startFragment(BedFragmentClass,1);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        setToolbarRightCustomView(viewright);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            bedStr = data.getStringExtra("bedselectinfoStr");
            if (askCount == 0) {
                askCount++;
                getTaskViewList();
            }

        }
    }

    private void refreshItemData(BaseQuickAdapter adapter, int position) {

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
                if (regNo.equals("")){
                    tvPatinfo.setText("全部患者");
                }
                taskListAdapter.setNewData(bean.getOrdNumList().getBody().getAll());
                taskListAdapter.notifyDataSetChanged();
                timeFilterAdapter.setNewData(bean.getTimesList());
            }
        });
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
                tvPatinfo.setText("".equals(bean.getPatInfo().getBedCode()) ? "未分床  " + bean.getPatInfo().getName() + "  " + bean.getPatInfo().getSex() + "  " +
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
