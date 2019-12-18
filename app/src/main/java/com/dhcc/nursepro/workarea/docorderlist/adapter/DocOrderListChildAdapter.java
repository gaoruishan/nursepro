package com.dhcc.nursepro.workarea.docorderlist.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;

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
        super(R.layout.item_docorders_child, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DocOrderListBean.OrdListBean item) {
        helper.setText(R.id.tv_item_order_childname, item.getOrddesc())
                .setText(R.id.tv_item_order_childunit, item.getOrddose());
        TextView tvChil = helper.getView(R.id.tv_item_order_childunit);
        if (item.getOrddose().equals("") || item.getOrddose() ==null){
            tvChil.setVisibility(View.GONE);
        }else {
            tvChil.setVisibility(View.VISIBLE);
        }

    }
}