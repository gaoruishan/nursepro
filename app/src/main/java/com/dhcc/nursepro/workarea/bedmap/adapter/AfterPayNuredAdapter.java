package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.NurOrdListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:10:19
 */
public class AfterPayNuredAdapter extends BaseQuickAdapter<NurOrdListBean.OrdListBean, BaseViewHolder> {

    public AfterPayNuredAdapter(@Nullable List<NurOrdListBean.OrdListBean> data) {
        super(R.layout.item_afterpay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurOrdListBean.OrdListBean item) {
        helper.setGone(R.id.ll_message_rightmenu,false);
        helper.setText(R.id.tv_ord_name, item.getArcimDesc())
                .setText(R.id.tv_ord_no,(helper.getAdapterPosition()+1)+"")
                .setText(R.id.tv_ord_num,item.getPhOrdQtyUnit())
                .setText(R.id.tv_ord_price,item.getTotalAmount())
        ;


    }
}