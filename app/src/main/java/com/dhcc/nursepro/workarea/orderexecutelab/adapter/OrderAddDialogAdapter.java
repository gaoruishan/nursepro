package com.dhcc.nursepro.workarea.orderexecutelab.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.ScanResultBean;

import java.util.ArrayList;
import java.util.List;

public class OrderAddDialogAdapter extends BaseQuickAdapter<ScanResultBean.OrdersBean, BaseViewHolder> {

    private List<Integer> childNumList = new ArrayList<>();

    public OrderAddDialogAdapter(@Nullable List<ScanResultBean.OrdersBean> data) {
        super(R.layout.item_orderadd_dialog, data);
    }

    public void setChildNumList(List<Integer> childNumList) {
        this.childNumList = childNumList;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScanResultBean.OrdersBean item) {

        helper.setText(R.id.tv_popup_childorderinfo, item.getArcimDesc())
                .setText(R.id.tv_popup_childorderunit, item.getDoseQtyUnit());

        if (childNumList.contains(helper.getAdapterPosition() + 1)) {
            helper.setGone(R.id.tv_sttdatetime, true)
                    .setGone(R.id.tv_doc, true)
                    .setGone(R.id.tv_labdesc, true)
                    .setGone(R.id.tv_tubecolordesc, true)
                    .setGone(R.id.tv_labnote, true)
                    .setText(R.id.tv_sttdatetime, item.getSttDateTime())
                    .setText(R.id.tv_doc, item.getCtcpDesc())
                    .setText(R.id.tv_labdesc, item.getSpecDesc())
                    .setText(R.id.tv_tubecolordesc, item.getTubeColorDesc())
                    .setTextColor(R.id.tv_tubecolordesc, Color.parseColor(item.getTubeColor()))
                    .setText(R.id.tv_labnote, item.getLabNote());

        } else {
            helper.setGone(R.id.tv_sttdatetime, false)
                    .setGone(R.id.tv_doc, false)
                    .setGone(R.id.tv_labdesc, false)
                    .setGone(R.id.tv_tubecolordesc, false)
                    .setGone(R.id.tv_labnote, false);
        }
    }
}
