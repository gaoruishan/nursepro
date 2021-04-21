package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;

import java.util.List;

public class InfusionSituationOrderDetailAdapter extends BaseQuickAdapter<GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean.GroupOrdListBean, BaseViewHolder> {

    public InfusionSituationOrderDetailAdapter(@Nullable List<GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean.GroupOrdListBean> data) {
        super(R.layout.item_infusionsituation_orderdetail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean.GroupOrdListBean item) {
        GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean.GroupOrdListBean.OrderInfoBean orderInfo= item.getOrderInfo();
        helper.setText(R.id.tv_orderinfo_name_is, orderInfo.getArcimDesc())
                .setText(R.id.tv_orderinfo_unit_is, orderInfo.getDoseQtyUnit());

    }
}
