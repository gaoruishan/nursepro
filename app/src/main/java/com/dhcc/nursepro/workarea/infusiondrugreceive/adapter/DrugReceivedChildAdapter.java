package com.dhcc.nursepro.workarea.infusiondrugreceive.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.IfOrdListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive.adapter
 * <p>
 * author Q
 * Date: 2020/3/11
 * Time:15:14
 */
public class DrugReceivedChildAdapter extends BaseQuickAdapter<IfOrdListBean.OrdListBean.OeoreGroupBean, BaseViewHolder> {

    public DrugReceivedChildAdapter(@Nullable List<IfOrdListBean.OrdListBean.OeoreGroupBean> data) {
        super(R.layout.item_iford_child, data);
    }
    @Override
    protected void convert(BaseViewHolder helper,IfOrdListBean.OrdListBean.OeoreGroupBean item) {
        helper.setText(R.id.tv_osporderinfo_ordername,item.getArcimDesc())
                .setText(R.id.tv_unit,item.getDoseQtyUnit())
                .setText(R.id.tv_allunit,item.getPhOrdQtyUnit());

    }
}