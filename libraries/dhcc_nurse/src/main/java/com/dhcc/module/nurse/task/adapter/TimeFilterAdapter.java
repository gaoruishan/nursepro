package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.TaskBean;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/4
 * Time:10:41
 */
public class TimeFilterAdapter extends BaseQuickAdapter<TaskBean.TimesListBean, BaseViewHolder> {

    private int selectItem=-1;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public TimeFilterAdapter(int layoutResId, @Nullable List<TaskBean.TimesListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskBean.TimesListBean item) {
        helper.setText(R.id.tv_time_desc,item.getTimesDesc()+":")
                .setText(R.id.tv_time_start,item.getTimeStt())
                .setText(R.id.tv_time_end,item.getTimeEnd())
                .setTextColor(R.id.tv_time_desc,selectItem==helper.getAdapterPosition()?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.black))
                .setTextColor(R.id.tv_time_device,selectItem==helper.getAdapterPosition()?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.black))
                .setTextColor(R.id.tv_time_start,selectItem==helper.getAdapterPosition()?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.black))
                .setTextColor(R.id.tv_time_end,selectItem==helper.getAdapterPosition()?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.black))
                .setBackgroundRes(R.id.ll_time_filter,selectItem==helper.getAdapterPosition()?R.drawable.bg_timefilter_select:R.drawable.bg_timefilter_unselect)
                .setBackgroundRes(R.id.view_select,selectItem==helper.getAdapterPosition()?R.drawable.time_select:R.drawable.time_unselect);

    }
}