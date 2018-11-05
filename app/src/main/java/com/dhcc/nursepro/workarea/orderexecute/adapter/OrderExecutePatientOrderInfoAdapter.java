package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;

import java.util.List;

/**
 * OrderExecutePatientOrderItemAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderExecutePatientOrderInfoAdapter extends BaseQuickAdapter<OrderExecuteBean.OrdersBean.PatOrdsBean, BaseViewHolder> {
    private int size;

    public OrderExecutePatientOrderInfoAdapter(@Nullable List<OrderExecuteBean.OrdersBean.PatOrdsBean> data, int size) {
        super(R.layout.item_oepatient_orderinfo, data);
        this.size = size;
    }

    public OrderExecutePatientOrderInfoAdapter(@Nullable List<OrderExecuteBean.OrdersBean.PatOrdsBean> data) {
        super(R.layout.item_oepatient_orderinfo, data);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderExecuteBean.OrdersBean.PatOrdsBean item) {
        OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean = item.getOrderInfo();
        LinearLayout llorderinfosingle = helper.getView(R.id.ll_oeporderinfo_singleorder);
        LinearLayout llorderinfomulti1 = helper.getView(R.id.ll_oeporderinfo_multiorder1);
        View lineorderinfomulti = helper.getView(R.id.line_oeporderinfo_multiorder);
        LinearLayout llorderinfomulti2 = helper.getView(R.id.ll_oeporderinfo_multiorder2);
        TextView tvOrderType = helper.getView(R.id.tv_oeporderinfo_ordertype);
        GradientDrawable myGrad = (GradientDrawable)tvOrderType.getBackground();

        if (size == 1) {
            llorderinfosingle.setVisibility(View.VISIBLE);
            llorderinfomulti1.setVisibility(View.GONE);
            lineorderinfomulti.setVisibility(View.GONE);
            llorderinfomulti2.setVisibility(View.GONE);
            String[] typeStr = orderInfoBean.getDisposeStatCode().split("\\^");
            tvOrderType.setText(typeStr[0]);
            myGrad.setColor(Color.parseColor(typeStr[1]));
            helper.setText(R.id.tv_oeporderinfo_ordername, orderInfoBean.getArcimDesc())
                    .setText(R.id.tv_oeporderinfo_orderdatetime, orderInfoBean.getCreateDateTime().substring(0, 16))
                    .setText(R.id.tv_oeporderinfo_orderoperate, orderInfoBean.getPhcinDesc())
                    .setText(R.id.tv_oeporderinfo_orderdose, orderInfoBean.getPhOrdQtyUnit())
                    .setText(R.id.tv_oeporderinfo_orderfrequency, orderInfoBean.getPhcfrCode())
                    .setText(R.id.tv_oeporderinfo_ordercreator, orderInfoBean.getCtcpDesc());
        } else {
            llorderinfosingle.setVisibility(View.GONE);

            if (helper.getLayoutPosition() == 0) {
                tvOrderType.setVisibility(View.VISIBLE);
                String[] typeStr = orderInfoBean.getDisposeStatCode().split("\\^");
                tvOrderType.setText(typeStr[0]);
                myGrad.setColor(Color.parseColor(typeStr[1]));
            } else {
                tvOrderType.setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_oeporderinfo_ordername, orderInfoBean.getArcimDesc());
            if (helper.getLayoutPosition() == size - 1) {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                lineorderinfomulti.setVisibility(View.VISIBLE);
                llorderinfomulti2.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_oeporderinfo_orderdose1, orderInfoBean.getPhOrdQtyUnit())
                        .setText(R.id.tv_oeporderinfo_orderdatetime2, orderInfoBean.getCreateDateTime().substring(0, 16))
                        .setText(R.id.tv_oeporderinfo_orderoperate2, orderInfoBean.getPhcinDesc())
                        .setText(R.id.tv_oeporderinfo_orderfrequency2, orderInfoBean.getPhcfrCode())
                        .setText(R.id.tv_oeporderinfo_ordercreator2, orderInfoBean.getCtcpDesc());
            } else {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_oeporderinfo_orderdose1, orderInfoBean.getPhOrdQtyUnit());
            }
        }
    }
}
