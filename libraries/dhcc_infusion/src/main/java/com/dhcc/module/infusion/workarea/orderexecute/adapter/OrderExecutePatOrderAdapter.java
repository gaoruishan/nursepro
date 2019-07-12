package com.dhcc.module.infusion.workarea.orderexecute.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.orderexecute.bean.OrderExecuteBean;

import java.io.Serializable;
import java.util.List;

/**
 * OrderSearchPatientOrderAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderExecutePatOrderAdapter extends BaseQuickAdapter<List<OrderExecuteBean.OrdersBean.PatOrdsBean>, BaseViewHolder> {
    private int size;
    private List<OrderExecuteBean.DetailColumsBean> detailColums;


    public OrderExecutePatOrderAdapter(@Nullable List<List<OrderExecuteBean.OrdersBean.PatOrdsBean>> data) {
        super(R.layout.dhcc_item_oepatient_order, data);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<OrderExecuteBean.DetailColumsBean> getDetailColums() {
        return detailColums;
    }

    public void setDetailColums(List<OrderExecuteBean.DetailColumsBean> detailColums) {
        this.detailColums = detailColums;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<OrderExecuteBean.OrdersBean.PatOrdsBean> item) {
        View topview = helper.getView(R.id.view_oepat_top);
        if (helper.getLayoutPosition() == 0) {
            topview.setVisibility(View.VISIBLE);
        } else {
            topview.setVisibility(View.GONE);
        }
        LinearLayout lloepatOrderSelect = helper.getView(R.id.ll_oepat_orderselect);
        if (item.size() > 0) {
            OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = item.get(0);
            if (patOrdsBean.getSelect() == null || "0".equals(patOrdsBean.getSelect()) || "".equals(patOrdsBean.getSelect())) {
                lloepatOrderSelect.setSelected(false);
            } else {
                lloepatOrderSelect.setSelected(true);
            }
        }

        helper.addOnClickListener(R.id.ll_oepat_orderselect);

        RecyclerView recyOrderInfo = helper.getView(R.id.recy_oepat_orderinfo);
        //提高展示效率
        recyOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyOrderInfo.setLayoutManager(new LinearLayoutManager(mContext));

        OrderExecutePatOrderInfoAdapter orderInfoAdapter = new OrderExecutePatOrderInfoAdapter(item, item.size());
        orderInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderExecuteBean.OrdersBean.PatOrdsBean patOrdsBean = (OrderExecuteBean.OrdersBean.PatOrdsBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("detailcolums", (Serializable) detailColums);
                bundle.putSerializable("patorderinfo", patOrdsBean.getOrderInfo());
//                ((BaseActivity) mContext).startFragment(OrderInfoDetailFragment.class, bundle);
            }
        });
        recyOrderInfo.setAdapter(orderInfoAdapter);

        View view = helper.getView(R.id.line_oepat_orderinfo);
        View bottomView = helper.getView(R.id.view_oepat_bottom);
        if (helper.getLayoutPosition() < size - 1) {
            view.setVisibility(View.VISIBLE);
            bottomView.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.GONE);
            bottomView.setVisibility(View.VISIBLE);
        }
    }
}
