package com.dhcc.nursepro.workarea.ordersearch.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.BaseActivity;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.UniversalActivity;
import com.dhcc.nursepro.workarea.bedmap.BedMapPatFragment;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.ordersearch.OrderInfoDetailFragment;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.io.Serializable;
import java.util.List;

/**
 * OrderSearchPatientOrderAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderSearchPatientOrderAdapter extends BaseQuickAdapter<List<OrderSearchBean.OrdersBean.PatOrdsBean>, BaseViewHolder> {
    private int size;
    private List<OrderSearchBean.DetailColumsBean> detailColums;

    public OrderSearchPatientOrderAdapter(@Nullable List<List<OrderSearchBean.OrdersBean.PatOrdsBean>> data, int size, List<OrderSearchBean.DetailColumsBean> detailColums) {
        super(R.layout.item_ospatient_order, data);
        this.size = size;
        this.detailColums = detailColums;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<OrderSearchBean.OrdersBean.PatOrdsBean> item) {
        RecyclerView recyOrderInfo = helper.getView(R.id.recy_ospat_orderinfo);
        //提高展示效率
        recyOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyOrderInfo.setLayoutManager(new LinearLayoutManager(mContext));

        OrderSearchPatientOrderInfoAdapter orderInfoAdapter = new OrderSearchPatientOrderInfoAdapter(item, item.size());
        orderInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderSearchBean.OrdersBean.PatOrdsBean patOrdsBean = (OrderSearchBean.OrdersBean.PatOrdsBean) adapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("detailcolums", (Serializable) detailColums);
                bundle.putSerializable("patorderinfo", patOrdsBean.getOrderInfo());
                ((BaseActivity)mContext).startFragment(OrderInfoDetailFragment.class, bundle);

            }
        });
        recyOrderInfo.setAdapter(orderInfoAdapter);

        View view = helper.getView(R.id.line_ospat_orderinfo);
        if (helper.getLayoutPosition() < size - 1) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
