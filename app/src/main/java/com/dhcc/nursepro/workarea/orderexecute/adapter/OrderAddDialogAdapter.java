package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

public class OrderAddDialogAdapter extends BaseQuickAdapter<ScanResultBean, BaseViewHolder> {

    public OrderAddDialogAdapter(@Nullable List<ScanResultBean> data) {
        super(R.layout.item_orderadd_dialog, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanResultBean item) {
        ScanResultBean.OrdersBean itemTemp = item.getOrders().get(0);
        helper.setText(R.id.tv_popup_childorderinfo, itemTemp.getArcimDesc())
                .setText(R.id.tv_status,itemTemp.getExeStatus())
                .setText(R.id.tv_popup_childorderunit, itemTemp.getDoseQtyUnit())
                .setText(R.id.tv_sttdatetime,itemTemp.getSttDateTime())
                .setText(R.id.tv_doc,itemTemp.getCtcpDesc());
        helper.setGone(R.id.tv_status, !TextUtils.isEmpty(itemTemp.getExeStatus()));
        if(!TextUtils.isEmpty(itemTemp.getExeStatus()) && !TextUtils.isEmpty(itemTemp.getExeStColor())){
            Drawable drawable = new DrawableCreator.Builder()
                    .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_5))
                    .setSolidColor(Color.parseColor(itemTemp.getExeStColor()))
                    .build();
            helper.getView(R.id.tv_status).setBackground(drawable);
        }
    }
}
