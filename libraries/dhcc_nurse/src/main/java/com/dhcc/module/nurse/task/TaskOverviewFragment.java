package com.dhcc.module.nurse.task;

import com.dhcc.module.nurse.BaseNurseFragment;
import com.dhcc.module.nurse.R;

/**
 * 任务总览
 * @author:gaoruishan
 * @date:202020-07-17/10:41
 * @email:grs0515@163.com
 */
public class TaskOverviewFragment extends BaseNurseFragment {
    @Override
    protected void initDatas() {
        super.initDatas();
        setToolbarCenterTitle("任务总览");
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_overview;
    }
}
