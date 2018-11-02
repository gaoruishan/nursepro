package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

        if (size == 1) {
            llorderinfosingle.setVisibility(View.VISIBLE);
            llorderinfomulti1.setVisibility(View.GONE);
            lineorderinfomulti.setVisibility(View.GONE);
            llorderinfomulti2.setVisibility(View.GONE);
            helper.setText(R.id.tv_oeporderinfo_ordertype, orderInfoBean.getDisposeStatCode())
                    .setText(R.id.tv_oeporderinfo_ordername, orderInfoBean.getArcimDesc())
                    .setText(R.id.tv_oeporderinfo_orderdatetime, orderInfoBean.getCreateDateTime().substring(0, 16))
                    .setText(R.id.tv_oeporderinfo_orderoperate, orderInfoBean.getPhcinDesc())
                    .setText(R.id.tv_oeporderinfo_orderdose, orderInfoBean.getPhOrdQtyUnit())
                    .setText(R.id.tv_oeporderinfo_orderfrequency, orderInfoBean.getPhcfrCode())
                    .setText(R.id.tv_oeporderinfo_ordercreator, orderInfoBean.getCtcpDesc());
        } else {
            llorderinfosingle.setVisibility(View.GONE);

            if (helper.getLayoutPosition() == 0) {
                tvOrderType.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_oeporderinfo_ordertype, orderInfoBean.getDisposeStatCode());
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

        //        需处理，已处理，停止需处理，停止已处理，新开长嘱，非新长嘱，需执行，已执行，皮试
        switch (tvOrderType.getText().toString()) {
            case "需处理":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_1));
                break;
            case "已处理":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_2));
                break;
            case "停止需处理":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_3));
                break;
            case "停止已处理":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_4));
                break;
            case "新开长嘱":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_5));
                break;
            case "非新长嘱":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_6));
                break;
            case "需执行":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_7));
                break;
            case "已执行":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_8));
                break;
            case "皮试":
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_ordertype_9));
                break;
            default:
                tvOrderType.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_oval_green));
                break;
        }
    }
}
