package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;

import java.util.List;

public class InfusionDetailOrderDetailAdapter extends BaseQuickAdapter<GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean.GroupOrdListBean, BaseViewHolder> {

    public InfusionDetailOrderDetailAdapter(@Nullable List<GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean.GroupOrdListBean> data) {
        super(R.layout.item_infusionsituation_orderdetail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean.GroupOrdListBean item) {
        GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean.GroupOrdListBean.OrderInfoBean orderInfo= item.getOrderInfo();
        helper.setText(R.id.tv_orderinfo_name_is, orderInfo.getArcimDesc())
                .setText(R.id.tv_orderinfo_unit_is, orderInfo.getDoseQtyUnit());

    }
}
