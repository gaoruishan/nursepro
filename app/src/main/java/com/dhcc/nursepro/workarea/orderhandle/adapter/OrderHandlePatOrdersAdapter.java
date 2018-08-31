package com.dhcc.nursepro.workarea.orderhandle.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderhandle.bean.OrderHandleBean;

import java.util.List;

/**
 * OrderSearchPatientAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderHandlePatOrdersAdapter extends BaseQuickAdapter<List<OrderHandleBean.OrdersBean.PatOrdsBean>, BaseViewHolder> {
    private List<OrderHandleBean.DetailColumsBean> detailColums;

    public OrderHandlePatOrdersAdapter(@Nullable List<List<OrderHandleBean.OrdersBean.PatOrdsBean>> data) {
        super(R.layout.item_ospatient_handleorder, data);
    }

    public List<OrderHandleBean.DetailColumsBean> getDetailColums() {
        return detailColums;
    }

    public void setDetailColums(List<OrderHandleBean.DetailColumsBean> detailColums) {
        this.detailColums = detailColums;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<OrderHandleBean.OrdersBean.PatOrdsBean> item) {
        RecyclerView recyOrderInfo = helper.getView(R.id.recy_ospat_handleorder);


        //提高展示效率
        recyOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyOrderInfo.setLayoutManager(new LinearLayoutManager(mContext));

        OrderHandlePatientOrderInfoAdapter orderInfoAdapter = new OrderHandlePatientOrderInfoAdapter(item, item.size());
        orderInfoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
        recyOrderInfo.setAdapter(orderInfoAdapter);

    }
}
