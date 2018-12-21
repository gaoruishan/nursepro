package com.dhcc.nursepro.workarea.ordersearch.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;

/**
 * OrderSearchOrderTypeAdapter
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderSearchOrderTypeAdapter extends BaseQuickAdapter<OrderSearchBean.SheetListBean, BaseViewHolder> {
    private int selectedPostion;

    public OrderSearchOrderTypeAdapter(@Nullable List<OrderSearchBean.SheetListBean> data) {
        super(R.layout.item_ordersearch_ordertype, data);
    }

    public int getSelectedPostion() {
        return selectedPostion;
    }

    public void setSelectedPostion(int selectedPostion) {
        this.selectedPostion = selectedPostion;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSearchBean.SheetListBean item) {
        LinearLayout llOrderType = helper.getView(R.id.ll_ordersearch_ordertype);
        View viewOrderType = helper.getView(R.id.view_ordersearch_ordertype);

        TextView tvOrderType = helper.getView(R.id.tv_ordersearch_ordertype);
        if (selectedPostion == helper.getAdapterPosition()) {
            llOrderType.setSelected(true);
            tvOrderType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_selected_text));
            tvOrderType.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewOrderType.setVisibility(View.VISIBLE);

        } else {
            llOrderType.setSelected(false);
            tvOrderType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_normal_text));
            tvOrderType.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            viewOrderType.setVisibility(View.INVISIBLE);
        }

        helper.setText(R.id.tv_ordersearch_ordertype, item.getDesc());

    }
}
