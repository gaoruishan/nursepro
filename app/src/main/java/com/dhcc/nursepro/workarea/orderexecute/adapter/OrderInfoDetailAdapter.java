package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;
import java.util.Map;

/**
 * OrderInfoDetailAdapter
 *
 * @author DevLix126
 * @date 2018/8/29
 */
public class OrderInfoDetailAdapter extends BaseQuickAdapter<OrderExecuteBean.DetailColumsBean, BaseViewHolder> {
    private Map<String, String> infomap;

    public OrderInfoDetailAdapter(@Nullable List<OrderExecuteBean.DetailColumsBean> data, Map<String, String> infomap) {
        super(R.layout.item_orderinfodetail, data);
        this.infomap = infomap;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderExecuteBean.DetailColumsBean item) {
        View topView = helper.getView(R.id.view_orderinfodetail_top);
        View lineView = helper.getView(R.id.view_orderinfodetail_line);
        if (helper.getLayoutPosition() % 4 == 0) {
            topView.setVisibility(View.GONE);
            lineView.setVisibility(View.VISIBLE);
        }else{
            topView.setVisibility(View.GONE);
            lineView.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_orderinfodetail_key, item.getName())
                .setText(R.id.tv_orderinfodetail_value, infomap.get(item.getCode()));
    }
}
