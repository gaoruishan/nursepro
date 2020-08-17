package com.dhcc.module.nurse.task.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.StatusListBean;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/14
 * Time:14:19
 */
public class StatusFilterAdapter  extends BaseQuickAdapter<StatusListBean, BaseViewHolder> {

    private int selectItem=-1;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public StatusFilterAdapter(int layoutResId, @Nullable List<StatusListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatusListBean item) {
        helper.setText(R.id.tv_time_desc,item.getText()+":")
                .setTextColor(R.id.tv_time_desc,selectItem==helper.getAdapterPosition()?mContext.getResources().getColor(R.color.blue):mContext.getResources().getColor(R.color.black))
                .setBackgroundRes(R.id.ll_status_filter,selectItem==helper.getAdapterPosition()?R.drawable.bg_timefilter_select:R.drawable.bg_timefilter_unselect)
                .setBackgroundRes(R.id.view_select,selectItem==helper.getAdapterPosition()?R.drawable.time_select:R.drawable.time_unselect);

    }
}