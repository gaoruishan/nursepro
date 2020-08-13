package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.AllBean;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/3
 * Time:15:19
 */
public class TaskListAdapter extends BaseQuickAdapter<AllBean, BaseViewHolder> {

    public TaskListAdapter(int layoutResId, @Nullable List<AllBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBean item) {
            helper.setText(R.id.tv_ord_name,item.getName())
                    .setText(R.id.tv_ord_num,item.getValue());
    }
}
