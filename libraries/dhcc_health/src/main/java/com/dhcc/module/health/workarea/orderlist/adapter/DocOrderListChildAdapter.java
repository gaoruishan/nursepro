package com.dhcc.module.health.workarea.orderlist.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderlist.bean.DocOrderListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.docorderlist.adapter
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:16:20
 */
public class DocOrderListChildAdapter extends BaseQuickAdapter<DocOrderListBean.OrdListBean, BaseViewHolder> {

    private int selectItem;

    public DocOrderListChildAdapter(@Nullable List<DocOrderListBean.OrdListBean> data) {
        super(R.layout.health_item_docorders_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DocOrderListBean.OrdListBean item) {
        helper.setText(R.id.tv_item_order_childname, item.getOrddesc())
                .setText(R.id.tv_item_order_childunit, item.getOrddose());

    }
}