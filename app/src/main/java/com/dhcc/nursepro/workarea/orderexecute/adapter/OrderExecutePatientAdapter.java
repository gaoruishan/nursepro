package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;

/**
 * OrderExecutePatientAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderExecutePatientAdapter extends BaseQuickAdapter<OrderExecuteBean.OrdersBean, BaseViewHolder> {
    private List<OrderExecuteBean.DetailColumsBean> detailColums;

    public OrderExecutePatientAdapter(@Nullable List<OrderExecuteBean.OrdersBean> data) {
        super(R.layout.item_ordersearch_patient, data);
    }

    public List<OrderExecuteBean.DetailColumsBean> getDetailColums() {
        return detailColums;
    }

    public void setDetailColums(List<OrderExecuteBean.DetailColumsBean> detailColums) {
        this.detailColums = detailColums;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderExecuteBean.OrdersBean item) {
        helper.setText(R.id.tv_ospat_patinfo, "".equals(item.getBedCode()) ? "未分" : item.getBedCode() + "床  " + item.getName());

        RecyclerView recyPatientOrder = helper.getView(R.id.recy_ospat_patorder);
        //提高展示效率
        recyPatientOrder.setHasFixedSize(true);
        //设置的布局管理
        recyPatientOrder.setLayoutManager(new LinearLayoutManager(mContext));

        List<List<OrderExecuteBean.OrdersBean.PatOrdsBean>> patOrds = item.getPatOrds();
        OrderExecutePatientOrderAdapter patientOrderAdapter = new OrderExecutePatientOrderAdapter(patOrds, patOrds.size(), detailColums);

        recyPatientOrder.setAdapter(patientOrderAdapter);

    }
}
