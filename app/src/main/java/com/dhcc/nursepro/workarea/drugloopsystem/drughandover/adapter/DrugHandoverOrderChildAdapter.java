package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.DrugHandOverScanOrderList;

import java.util.List;

public class DrugHandoverOrderChildAdapter extends BaseQuickAdapter<DrugHandOverScanOrderList.OrdListBean.OeoreGroupBean, BaseViewHolder> {

    public DrugHandoverOrderChildAdapter(@Nullable List<DrugHandOverScanOrderList.OrdListBean.OeoreGroupBean> data) {
        super(R.layout.item_drughandover_orderchild, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugHandOverScanOrderList.OrdListBean.OeoreGroupBean item) {
        helper.setText(R.id.tv_drugorder_cname, item.getArcimDesc())
                .setText(R.id.tv_drugorder_cdose, item.getDoseQtyUnit());
    }
}
