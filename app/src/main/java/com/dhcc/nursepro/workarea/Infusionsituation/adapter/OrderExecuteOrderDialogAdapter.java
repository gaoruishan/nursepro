package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.ScanResultBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

public class OrderExecuteOrderDialogAdapter extends BaseQuickAdapter<ScanResultBean.OrdersBean, BaseViewHolder> {

    public OrderExecuteOrderDialogAdapter(@Nullable List<ScanResultBean.OrdersBean> data) {
        super(R.layout.item_orderexec_popup_childorderinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanResultBean.OrdersBean item) {
        helper.setText(R.id.tv_popup_childorderinfo, item.getArcimDesc())
                .setText(R.id.tv_status,item.getExeStatus())
                .setText(R.id.tv_popup_childorderunit, item.getDoseQtyUnit());
        helper.setGone(R.id.tv_status, !StringUtils.isEmpty(item.getExeStatus()));
        if(!StringUtils.isEmpty(item.getExeStatus()) && !StringUtils.isEmpty(item.getExeStColor())){
            Drawable drawable = new DrawableCreator.Builder()
                    .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_5))
                    .setSolidColor(Color.parseColor(item.getExeStColor()))
                    .build();
            helper.getView(R.id.tv_status).setBackground(drawable);
        }
    }
}
