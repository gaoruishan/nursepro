package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;

import java.util.List;

public class OrderExecuteOrderDialogAdapter extends BaseQuickAdapter<ScanResultBean.OrdersBean, BaseViewHolder> {

    public OrderExecuteOrderDialogAdapter(@Nullable List<ScanResultBean.OrdersBean> data) {
        super(R.layout.item_orderexec_popup_childorderinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanResultBean.OrdersBean item) {
        helper.setText(R.id.tv_popup_childorderinfo, item.getArcimDesc())
                .setText(R.id.tv_popup_childorderunit, item.getDoseQtyUnit());
    }
}
