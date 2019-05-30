package com.dhcc.module.infusion.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.setting.bean.WorkStatisticsBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-04-23/10:49
 * @email:grs0515@163.com
 */
public class WorkStatisticsAdapter extends BaseQuickAdapter<WorkStatisticsBean.UserListBean, BaseViewHolder> {

    public WorkStatisticsAdapter(int layoutResId, @Nullable List<WorkStatisticsBean.UserListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkStatisticsBean.UserListBean item) {
        helper.setText(R.id.tv_name, item.getUserName()).setText(R.id.tv_num, item.getUserNum() + "次");
        helper.setVisible(R.id.v_line, helper.getAdapterPosition() + 1 != mData.size());
    }
}
