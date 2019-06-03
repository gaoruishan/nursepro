package com.dhcc.nursepro.workarea.dosingreview.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.Action;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;

import java.util.List;

/**
 * DosingReviewPatientAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class DosingReviewPatientAdapter extends BaseQuickAdapter<DosingReViewBean.OrdersBean, BaseViewHolder> {

    public DosingReviewPatientAdapter(@Nullable List<DosingReViewBean.OrdersBean> data) {
        super(R.layout.item_dosingreview_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DosingReViewBean.OrdersBean item) {
        helper.setText(R.id.tv_drpat_patinfo, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode() + "  " + item.getName());

        RecyclerView recyPatientOrder = helper.getView(R.id.recy_drpat_patorder);

        //提高展示效率
        recyPatientOrder.setHasFixedSize(true);
        //设置的布局管理
        recyPatientOrder.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        List<List<DosingReViewBean.OrdersBean.PatOrdsBean>> patOrds = item.getPatOrds();
        DosingReviewPatientOrderAdapter patientOrderAdapter = new DosingReviewPatientOrderAdapter(patOrds, patOrds.size());
        patientOrderAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DosingReViewBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean = ((List<DosingReViewBean.OrdersBean.PatOrdsBean>) adapter.getItem(position)).get(0).getOrderInfo();
                if (view.getId() == R.id.ll_drpat_ordercancel) {
                    String orderId = orderInfoBean.getID();
                    Intent intent = new Intent().setAction(Action.DOSING_REVIEW);
                    intent.putExtra("orderId", orderId);
                    mContext.sendBroadcast(intent);
                }
            }
        });
        recyPatientOrder.setAdapter(patientOrderAdapter);

    }
}
