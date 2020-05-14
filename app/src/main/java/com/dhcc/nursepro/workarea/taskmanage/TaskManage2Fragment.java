package com.dhcc.nursepro.workarea.taskmanage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.MessageEvent;
import com.base.commlibs.comm.BaseCommFragment;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.MainConfigUtil;
import com.dhcc.nursepro.workarea.taskmanage.adapter.TaskManage2Adapter;
import com.dhcc.nursepro.workarea.taskmanage.bean.SheetPatListBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-12/15:20
 * @email:grs0515@163.com
 */
public class TaskManage2Fragment extends BaseCommFragment {

    private TaskManage2Adapter taskManageAdapter;

    @Override
    protected void initDatas() {
        setToolbarCenterTitle(MainConfigUtil.getToolBarTitle(TaskManageFragment.class));
        //注册事件总线
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initViews() {
        String json = getArguments().getString("json");
        int type = getArguments().getInt("type");
        if (json == null) return;
        List<SheetPatListBean> data = null;
        if (TaskManageBean.TYPE_1 == type) {
            TaskManageBean.TaskPatListBean bean = new Gson().fromJson(json, TaskManageBean.TaskPatListBean.class);
            data = bean.getSheetList();
            f(R.id.tv_info, TextView.class).setText("" + bean.getBedCode() + "  " + bean.getPatName());
        }
        if (TaskManageBean.TYPE_2 == type) {
            TaskManageBean.TaskSheetListBean bean = new Gson().fromJson(json, TaskManageBean.TaskSheetListBean.class);
            data = bean.getSheetPatList();
            f(R.id.tv_info, TextView.class).setText("" + bean.getSheetDesc());
        }
        RecyclerView rvList = f(R.id.rv_list, RecyclerView.class);
        RecyclerViewHelper.setGridRecyclerView(mContext, rvList, 2, 0, false);
        //type类型要重置
        type = type == TaskManageBean.TYPE_1 ? TaskManageBean.TYPE_2 : TaskManageBean.TYPE_1;
        taskManageAdapter = new TaskManage2Adapter(data, type);
        rvList.setAdapter(taskManageAdapter);
        taskManageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SheetPatListBean entity = taskManageAdapter.getData().get(position);
                if (!TextUtils.isEmpty(entity.getSheetPatOrdNum()) && !"0".equals(entity.getSheetPatOrdNum())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("json", new Gson().toJson(entity));
                    startFragment(TaskManageOrdListFragment.class, bundle);
                }
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_task_manage_2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent event) {
        if (event.getType() == MessageEvent.MessageType.UPDATE_TASK_MANAGE_LIST) {
            int num = event.getTag();
            String sheetCode = event.getMessage();
            for (SheetPatListBean bean : taskManageAdapter.getData()) {
                if (bean.getSheetCode().equals(sheetCode)) {
                    bean.setSheetPatOrdNum(num+"");
                    break;
                }
            }
            taskManageAdapter.notifyDataSetChanged();
        }
    }
}
