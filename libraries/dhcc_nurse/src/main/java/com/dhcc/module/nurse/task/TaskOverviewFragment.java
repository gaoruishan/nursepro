package com.dhcc.module.nurse.task;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.module.nurse.AdapterFactory;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.adapter.TaskOverviewAdapter;
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

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("任务总览");
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
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
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME).substring(0, 10);
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD));
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD) - CustomDateTimeView.ONE_DAY * 6);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//                getEducationList();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//                getEducationList();
            }
        });


        addToolBarRightImageView(0, R.drawable.dhcc_filter_big_write);

        RecyclerView rvTaskList = RecyclerViewHelper.get(mContext, R.id.rv_list_task);
        taskOverviewAdapter = AdapterFactory.getTaskOverviewAdapter();
        rvTaskList.setAdapter(taskOverviewAdapter);

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
    }

    private void refreshItemData(BaseQuickAdapter adapter, int position) {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_overview;
    }
}
