package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;

import com.base.commlibs.utils.SimpleCallBack;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.TaskBean;
import com.dhcc.res.infusion.CustomCheckView;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/3
 * Time:15:19
 */
public class TaskListAdapter extends BaseQuickAdapter<TaskBean.OrdNumListBean.BodyBeanXXX.AllBeanXXX, BaseViewHolder> {

    public TaskListAdapter(int layoutResId, @Nullable List<TaskBean.OrdNumListBean.BodyBeanXXX.AllBeanXXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskBean.OrdNumListBean.BodyBeanXXX.AllBeanXXX item) {
            helper.setText(R.id.tv_ord_name,item.getName())
                    .setText(R.id.tv_ord_num,item.getValue());
    }
}
