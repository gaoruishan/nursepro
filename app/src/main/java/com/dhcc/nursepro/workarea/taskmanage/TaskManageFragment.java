package com.dhcc.nursepro.workarea.taskmanage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.MainConfigUtil;
import com.dhcc.nursepro.workarea.taskmanage.adapter.TaskManageAdapter;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageRequest;
import com.dhcc.res.infusion.CustomDateTimeView;
import com.dhcc.res.infusion.CustomTabView;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-11/15:03
 * @email:grs0515@163.com
 */
public class TaskManageFragment extends BaseCommFragment {


    private TaskManageRequest.GetOrderTasks params;
    private TaskManageBean mBean;
    private CustomTabView customTabView;
    private TaskManageAdapter taskManageAdapter;
    private CustomDateTimeView customDate;
    private int selectPosition = 0;

    @Override
    protected void initConfig() {
        super.initConfig();
        setToolbarCenterTitle(MainConfigUtil.getToolBarTitle(TaskManageFragment.class));
        //注册事件总线
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent event) {
        if (event.getType() == MessageEvent.MessageType.UPDATE_TASK_MANAGE_LIST) {
            getOrderTasks();
        }
    }

    @Override
    protected void initDatas() {
        params = new TaskManageRequest.GetOrderTasks();
        getOrderTasks();
    }

    private void getOrderTasks() {
        getStartDateTime(customDate);
        getEndDateTime(customDate);
        TaskManageApi.getOrderTasks(params, new CommonCallBack<TaskManageBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(TaskManageBean bean, String type) {
                mBean = bean;
                switchTabDatas(selectPosition);
            }
        });
    }

    @Override
    protected void initViews() {
        RecyclerView rvList = f(R.id.rv_list, RecyclerView.class);
        RecyclerViewHelper.setGridRecyclerView(mContext,rvList,2,0,false);
        taskManageAdapter = new TaskManageAdapter(null);
        rvList.setAdapter(taskManageAdapter);
        taskManageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemEntity entity = taskManageAdapter.getData().get(position);
                int num = 0;
                if (TaskManageBean.TYPE_1 == entity.getItemType()) {
                    TaskManageBean.TaskPatListBean bean = (TaskManageBean.TaskPatListBean) entity;
                    if(!TextUtils.isEmpty(bean.getSheetOrdNum())){
                        num = Integer.valueOf(bean.getSheetOrdNum());
                    }
                }
                if (TaskManageBean.TYPE_2 == entity.getItemType()) {
                    TaskManageBean.TaskSheetListBean bean = (TaskManageBean.TaskSheetListBean) entity;
                    if(!TextUtils.isEmpty(bean.getSheetOrdNum())){
                        num = Integer.valueOf(bean.getSheetOrdNum());
                    }
                }
                if (num != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("json",new Gson().toJson(entity));
                    bundle.putInt("type",entity.getItemType());
                    startFragment(TaskManage2Fragment.class,bundle);
                }
            }
        });
        customTabView = f(R.id.custom_tab, CustomTabView.class);
        customTabView.setTabText(new String[]{"病人任务", "类别任务"});
        customTabView.setOnTabClickLisenter(new CustomTabView.OnTabClickLisenter() {
            @Override
            public void onTabClick(int pst, View v) {
                selectPosition = pst;
                switchTabDatas(pst);
            }
        });

        customDate = f(R.id.custom_date, CustomDateTimeView.class);
        customDate.setShowTime(true);
        customDate.setOnDateSetListener(new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getOrderTasks();
            }
        }, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                getOrderTasks();
            }
        });
    }

    private void getEndDateTime(CustomDateTimeView customDate) {
        String s = customDate.getEndDateTimeText();
        if (s.contains(" ")) {
            params.endDate = s.split(" ")[0];
            params.endTime = s.split(" ")[1];
        }
    }

    private void getStartDateTime(CustomDateTimeView customDate) {
        String s = customDate.getStartDateTimeText();
        if (s.contains(" ")) {
            params.startDate = s.split(" ")[0];
            params.startTime = s.split(" ")[1];
        }
    }

    private void switchTabDatas(int pst) {
        if (mBean==null)return;
        if (pst == 1) {
            List<TaskManageBean.TaskSheetListBean> taskSheetList = mBean.getTaskSheetList();
            taskManageAdapter.replaceData(taskSheetList);
        }else {
            List<TaskManageBean.TaskPatListBean> taskPatList = mBean.getTaskPatList();
            taskManageAdapter.replaceData(taskPatList);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_manage;
    }
}
