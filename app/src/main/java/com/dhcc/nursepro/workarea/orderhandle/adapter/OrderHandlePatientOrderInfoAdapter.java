package com.dhcc.nursepro.workarea.orderhandle.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderhandle.bean.OrderHandleBean;
import com.dhcc.nursepro.workarea.ordersearch.bean.OrderSearchBean;

import java.util.List;

/**
 * OrderSearchPatientOrderItemAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderHandlePatientOrderInfoAdapter extends BaseQuickAdapter<OrderHandleBean.OrdersBean.PatOrdsBean, BaseViewHolder> {
    private int size;

    public OrderHandlePatientOrderInfoAdapter(@Nullable List<OrderHandleBean.OrdersBean.PatOrdsBean> data, int size) {
        super(R.layout.item_ospatient_handleorderinfo, data);
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderHandleBean.OrdersBean.PatOrdsBean item) {
        OrderHandleBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean = item.getOrderInfo();
        LinearLayout llorderinfosingle = helper.getView(R.id.ll_osporderinfo_handlesingleorder);
        LinearLayout llorderinfomulti1 = helper.getView(R.id.ll_osporderinfo_handlemultiorder1);
        View lineorderinfomulti = helper.getView(R.id.line_osporderinfo_handlemultiorder);
        LinearLayout llorderinfomulti2 = helper.getView(R.id.ll_osporderinfo_handlemultiorder2);
        TextView tvOrderType = helper.getView(R.id.tv_osporderinfo_handleordertype);
        if (size == 1) {
            llorderinfosingle.setVisibility(View.VISIBLE);
            llorderinfomulti1.setVisibility(View.GONE);
            lineorderinfomulti.setVisibility(View.GONE);
            llorderinfomulti2.setVisibility(View.GONE);
            helper.setText(R.id.tv_osporderinfo_handleordertype, orderInfoBean.getOecprDesc())
                    .setText(R.id.tv_osporderinfo_handleordername, orderInfoBean.getArcimDesc())
                    .setText(R.id.tv_osporderinfo_handleorderdatetime, orderInfoBean.getCreateDateTime().substring(0, 16))
                    .setText(R.id.tv_osporderinfo_handleorderoperate, orderInfoBean.getPhcinDesc())
                    .setText(R.id.tv_osporderinfo_handleorderdose, orderInfoBean.getPhOrdQtyUnit())
                    .setText(R.id.tv_osporderinfo_handleorderfrequency, orderInfoBean.getPhcfrCode())
                    .setText(R.id.tv_osporderinfo_handleordercreator, orderInfoBean.getCtcpDesc());
        } else {
            llorderinfosingle.setVisibility(View.GONE);

            if (helper.getLayoutPosition() == 0) {
                tvOrderType.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_osporderinfo_handleordertype, orderInfoBean.getOecprDesc());
            } else {
                tvOrderType.setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_osporderinfo_handleordername, orderInfoBean.getArcimDesc());
            if (helper.getLayoutPosition() == size - 1) {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                lineorderinfomulti.setVisibility(View.VISIBLE);
                llorderinfomulti2.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_osporderinfo_handleorderdose1, orderInfoBean.getPhOrdQtyUnit())
                        .setText(R.id.tv_osporderinfo_handleorderdatetime2, orderInfoBean.getCreateDateTime().substring(0, 16))
                        .setText(R.id.tv_osporderinfo_handleorderoperate2, orderInfoBean.getPhcinDesc())
                        .setText(R.id.tv_osporderinfo_handleorderfrequency2, orderInfoBean.getPhcfrCode())
                        .setText(R.id.tv_osporderinfo_handleordercreator2, orderInfoBean.getCtcpDesc());
            } else {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_osporderinfo_handleorderdose1, orderInfoBean.getPhOrdQtyUnit());
            }
        }
    }
}
