package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;

import java.util.List;

public class InfusionDetailWayAdapter extends BaseQuickAdapter<GetInfusionDetailByWardBean.WayNoOrdListBean, BaseViewHolder> {

    public InfusionDetailWayAdapter(@Nullable List<GetInfusionDetailByWardBean.WayNoOrdListBean> data) {
        super(R.layout.item_infusiondetail_way, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionDetailByWardBean.WayNoOrdListBean item) {

        helper.setText(R.id.tv_wayno_id, item.getWayNum());

        RecyclerView recyOrders = helper.getView(R.id.recy_orders_id);
        recyOrders.setHasFixedSize(true);
        recyOrders.setLayoutManager(new LinearLayoutManager(mContext));

        InfusionDetailOrderAdapter orderAdapter = new InfusionDetailOrderAdapter(item.getWayNoOrdSubList());
        recyOrders.setAdapter(orderAdapter);
    }
}
