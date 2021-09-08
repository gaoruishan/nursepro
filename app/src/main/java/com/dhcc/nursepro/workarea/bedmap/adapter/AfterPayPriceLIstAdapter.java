package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.PdaArcListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:10:19
 */
public class AfterPayPriceLIstAdapter extends BaseQuickAdapter<PdaArcListBean.ArcItemListBean, BaseViewHolder> {

    public AfterPayPriceLIstAdapter(@Nullable List<PdaArcListBean.ArcItemListBean> data) {
        super(R.layout.item_afterpay_price, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PdaArcListBean.ArcItemListBean item) {
            helper.setText(R.id.tv_ord_name, item.getItemDesc())
                    .addOnClickListener(R.id.tv_ord_name)
                    .addOnClickListener(R.id.tv_ord_name);

    }
}