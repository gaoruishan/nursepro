package com.dhcc.nursepro.workarea.dosingreview.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;

import java.util.List;

/**
 * DosingReviewPatientOrderAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class DosingReviewPatientOrderAdapter extends BaseQuickAdapter<List<DosingReViewBean.OrdersBean.PatOrdsBean>, BaseViewHolder> {
    private int size;

    public DosingReviewPatientOrderAdapter(@Nullable List<List<DosingReViewBean.OrdersBean.PatOrdsBean>> data, int size) {
        super(R.layout.item_drpatient_order, data);
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<DosingReViewBean.OrdersBean.PatOrdsBean> item) {
        RecyclerView recyOrderInfo = helper.getView(R.id.recy_drpat_orderinfo);
        //提高展示效率
        recyOrderInfo.setHasFixedSize(true);
        //设置的布局管理
        recyOrderInfo.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        DosingReviewPatientOrderInfoAdapter orderInfoAdapter = new DosingReviewPatientOrderInfoAdapter(item, item.size());

        recyOrderInfo.setAdapter(orderInfoAdapter);

        View view = helper.getView(R.id.line_drpat_orderinfo);
        if (helper.getLayoutPosition() < size - 1) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
        LinearLayout llOrderCancel = helper.getView(R.id.ll_drpat_ordercancel);
        if (item.size() > 0 && item.get(0).isCancelGone()) {
            llOrderCancel.setVisibility(View.GONE);
        } else {
            llOrderCancel.setVisibility(View.VISIBLE);
        }
        helper.addOnClickListener(R.id.ll_drpat_ordercancel);
    }
}
