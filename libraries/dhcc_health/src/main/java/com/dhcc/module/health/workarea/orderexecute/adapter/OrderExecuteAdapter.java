package com.dhcc.module.health.workarea.orderexecute.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdExecuteBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-10-30/16:00
 * @email:grs0515@163.com
 */
public class OrderExecuteAdapter extends BaseQuickAdapter<OrdExecuteBean.OrdListBean, BaseViewHolder> {

    public OrderExecuteAdapter(@Nullable List<OrdExecuteBean.OrdListBean> data) {
        super(R.layout.health_item_order_execute, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdExecuteBean.OrdListBean item) {
        helper.setText(R.id.tv_dose, item.getOrderDoseQty())
                .setText(R.id.tv_frequency, item.getOrderFreq())
                .setText(R.id.tv_creator, item.getUserAdd())
                .setText(R.id.tv_operate, item.getOrderStatus())
                .setText(R.id.tv_time, item.getOrderSttDate());
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setText(item.getOrderStatus());

        String labColor = "#62ABFF";
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                .build();
        blTvStatus.setBackground(drawable);
        helper.setGone(R.id.tv_time, !TextUtils.isEmpty(item.getOrderSttDate()));
        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getOrderPrior()));

        boolean isList = item.getArcimDescList() != null && item.getArcimDescList().size() > 0;
        helper.setGone(R.id.rv_item, isList);
        helper.setGone(R.id.tv_name, !isList);
        helper.setText(R.id.tv_name, item.getArcimDesc());
        RecyclerView rvItem = helper.getView(R.id.rv_item);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvItem);
        OrderExecuteChildAdapter childAdapter = new OrderExecuteChildAdapter(item.getArcimDescList());
        rvItem.setAdapter(childAdapter);
    }

    private class OrderExecuteChildAdapter extends BaseQuickAdapter<OrdExecuteBean.OrdListBean.ArcimDescListBean, BaseViewHolder> {

        OrderExecuteChildAdapter(@Nullable List<OrdExecuteBean.OrdListBean.ArcimDescListBean> data) {
            super(R.layout.health_item_ord_child_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrdExecuteBean.OrdListBean.ArcimDescListBean item) {
            helper.setText(R.id.tv_name, item.getArcimDesc());
        }
    }

}
