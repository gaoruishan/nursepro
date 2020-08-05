package com.dhcc.module.nurse.task;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomSheetListView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * com.dhcc.module.nurse.task
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:10
 */
public class NormalOrdTaskFragment extends BaseNurseFragment {
    private CustomSheetListView customSheet;
    private CustomDateTimeView customDate;
    private RecyclerView recTaskList;
    private int askCount = 0;

    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("任务总览");
        addToolBarRight();
        initAdapter();
        scanInfo="";
    }

    private void addToolBarRight(){
        View viewright = View.inflate(getActivity(), R.layout.view_toolbar_img_right, null);
        viewright.findViewById(R.id.img_toolbar_right_filter).setOnClickListener(this);
        viewright.findViewById(R.id.img_toolbar_right).setOnClickListener(this);
        setToolbarRightCustomView(viewright);
    }

    @Override
    protected void initViews() {
        super.initViews();
        customSheet = f(R.id.custom_sheet_list, CustomSheetListView.class);
        recTaskList = f(R.id.rv_list_task, RecyclerView.class);

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        String curDate = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        customDate.setShowTime(true);
        customDate.setEndDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD_HH_MM));
        customDate.setStartDateTime(TimeUtils.string2Millis(curDate, YYYY_MM_DD_HH_MM) - CustomDateTimeView.ONE_DAY * 6);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

            }
        });
    }

    private void initAdapter(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
//            bedStr = data.getStringExtra("bedselectinfoStr");
            if (askCount == 0) {
                askCount++;

            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    protected void getScanOrdList() {
        super.getScanOrdList();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_overview;
    }
}
