package com.dhcc.nursepro.workarea.ordersearch.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;

/**
 * OrderSearchPatientAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderSearchPatientAdapter extends BaseQuickAdapter<OrderSearchBean.OrdersBean, BaseViewHolder> {
    public OrderSearchPatientAdapter(@Nullable List<OrderSearchBean.OrdersBean> data) {
        super(R.layout.item_ordersearch_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSearchBean.OrdersBean item) {
        helper.setText(R.id.tv_ospat_patinfo, item.getBedCode() + "床  " + item.getName());

        RecyclerView recyPatientOrder = helper.getView(R.id.recy_ospat_patorder);
        //提高展示效率
        recyPatientOrder.setHasFixedSize(true);
        //设置的布局管理
        recyPatientOrder.setLayoutManager(new LinearLayoutManager(mContext));

        List<List<OrderSearchBean.OrdersBean.PatOrdsBean>> patOrds = item.getPatOrds();
        OrderSearchPatientOrderAdapter patientOrderAdapter = new OrderSearchPatientOrderAdapter(patOrds, patOrds.size());

        recyPatientOrder.setAdapter(patientOrderAdapter);

    }
}
