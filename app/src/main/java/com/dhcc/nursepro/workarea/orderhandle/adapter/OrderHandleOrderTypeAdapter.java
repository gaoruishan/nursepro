package com.dhcc.nursepro.workarea.orderhandle.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderhandle.bean.OrderHandleBean;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;

/**
 * OrderSearchOrderTypeAdapter
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderHandleOrderTypeAdapter extends BaseQuickAdapter<OrderHandleBean.SheetListBean, BaseViewHolder> {
    private int selectedPostion;

    public OrderHandleOrderTypeAdapter(@Nullable List<OrderHandleBean.SheetListBean> data) {
        super(R.layout.item_orderhandle_ordertype, data);
    }

    public int getSelectedPostion() {
        return selectedPostion;
    }

    public void setSelectedPostion(int selectedPostion) {
        this.selectedPostion = selectedPostion;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderHandleBean.SheetListBean item) {
        LinearLayout llOrderType = helper.getView(R.id.ll_orderhandle_ordertype);
        View viewOrderType = helper.getView(R.id.view_orderhandle_ordertype);

        if (selectedPostion == helper.getAdapterPosition()) {
            llOrderType.setSelected(true);
            viewOrderType.setVisibility(View.VISIBLE);

        } else {
            llOrderType.setSelected(false);
            viewOrderType.setVisibility(View.INVISIBLE);
        }

        helper.setText(R.id.tv_orderhandle_ordertype, item.getDesc());

    }
}
