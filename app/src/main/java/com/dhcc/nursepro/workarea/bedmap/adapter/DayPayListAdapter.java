package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.DayPayListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2019/11/7
 * Time:9:17
 */
public class DayPayListAdapter  extends BaseQuickAdapter<DayPayListBean.PriceListBean.SubPriceListBean, BaseViewHolder> {

    public DayPayListAdapter(@Nullable List<DayPayListBean.PriceListBean.SubPriceListBean> data) {
            super(R.layout.item_daypay_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DayPayListBean.PriceListBean.SubPriceListBean item) {
        helper.setText(R.id.tv_payname,item.getArcDesc())
                .setText(R.id.tv_paysum,item.getSum())
                .setText(R.id.tv_everyprice,item.getUnitprice())
                .setText(R.id.tv_amount,item.getQty()+item.getUom());
    }

}
