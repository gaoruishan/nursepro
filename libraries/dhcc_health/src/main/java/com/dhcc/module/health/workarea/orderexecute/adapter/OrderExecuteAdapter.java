package com.dhcc.module.health.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdExecuteBean;

import java.util.List;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-10-30/16:00
 * @email:grs0515@163.com
 */
public class OrderExecuteAdapter extends BaseQuickAdapter<OrdExecuteBean, BaseViewHolder> {

    public OrderExecuteAdapter(@Nullable List<OrdExecuteBean> data) {
        super(R.layout.health_item_order_execute, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdExecuteBean item) {

    }
}
