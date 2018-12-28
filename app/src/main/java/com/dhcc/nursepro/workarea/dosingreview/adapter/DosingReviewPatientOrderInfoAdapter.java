package com.dhcc.nursepro.workarea.dosingreview.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.dosingreview.bean.DosingReViewBean;

import java.util.List;

/**
 * DosingReviewPatientOrderItemAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class DosingReviewPatientOrderInfoAdapter extends BaseQuickAdapter<DosingReViewBean.OrdersBean.PatOrdsBean, BaseViewHolder> {
    private int size;

    public DosingReviewPatientOrderInfoAdapter(@Nullable List<DosingReViewBean.OrdersBean.PatOrdsBean> data, int size) {
        super(R.layout.item_drpatient_orderinfo, data);
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, DosingReViewBean.OrdersBean.PatOrdsBean item) {
        DosingReViewBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean = item.getOrderInfo();
        LinearLayout llorderinfosingle = helper.getView(R.id.ll_drporderinfo_singleorder);
        LinearLayout llorderinfomulti1 = helper.getView(R.id.ll_drporderinfo_multiorder1);
        View lineorderinfomulti = helper.getView(R.id.line_drporderinfo_multiorder);
        LinearLayout llorderinfomulti2 = helper.getView(R.id.ll_drporderinfo_multiorder2);
        LinearLayout llOrderStatus = helper.getView(R.id.ll_drporderinfo_orderstatus);
        TextView tvOrderPrepare = helper.getView(R.id.tv_drporderinfo_orderprepare);
        TextView tvOrderVerify = helper.getView(R.id.tv_drporderinfo_orderverify);

        if ("Y".equals(item.getOrderInfo().getPreparedFlag())) {
            tvOrderPrepare.setText("已配液");
            tvOrderPrepare.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_dosingreview_4));
        } else {
            tvOrderPrepare.setText("未配液");
            tvOrderPrepare.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_dosingreview_2));
        }

        if ("Y".equals(item.getOrderInfo().getVerifyFlag())) {
            tvOrderVerify.setText("已复核");
            tvOrderVerify.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_dosingreview_3));
        } else {
            tvOrderVerify.setText("未复核");
            tvOrderVerify.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_dosingreview_1));
        }

        if (size == 1) {
            llorderinfosingle.setVisibility(View.VISIBLE);
            llorderinfomulti1.setVisibility(View.GONE);
            lineorderinfomulti.setVisibility(View.GONE);
            llorderinfomulti2.setVisibility(View.GONE);
            helper.setText(R.id.tv_drporderinfo_ordername, orderInfoBean.getArcimDesc())
                    .setText(R.id.tv_drporderinfo_orderdatetime, orderInfoBean.getSttDateTime().substring(0, 16))
                    .setText(R.id.tv_drporderinfo_orderoperate, orderInfoBean.getPhcinDesc())
                    .setText(R.id.tv_drporderinfo_orderdose, orderInfoBean.getDoseQtyUnit())
                    .setText(R.id.tv_drporderinfo_orderfrequency, orderInfoBean.getPhcfrCode())
                    .setText(R.id.tv_drporderinfo_ordercreator, orderInfoBean.getCtcpDesc());
        } else {
            llorderinfosingle.setVisibility(View.GONE);

            if (helper.getLayoutPosition() == 0) {
                llOrderStatus.setVisibility(View.VISIBLE);
            } else {
                llOrderStatus.setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_drporderinfo_ordername, orderInfoBean.getArcimDesc());
            if (helper.getLayoutPosition() == size - 1) {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                lineorderinfomulti.setVisibility(View.VISIBLE);
                llorderinfomulti2.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_drporderinfo_orderdose1, orderInfoBean.getDoseQtyUnit())
                        .setText(R.id.tv_drporderinfo_orderdatetime2, orderInfoBean.getSttDateTime().substring(0, 16))
                        .setText(R.id.tv_drporderinfo_orderoperate2, orderInfoBean.getPhcinDesc())
                        .setText(R.id.tv_drporderinfo_orderfrequency2, orderInfoBean.getPhcfrCode())
                        .setText(R.id.tv_drporderinfo_ordercreator2, orderInfoBean.getCtcpDesc());
            } else {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_drporderinfo_orderdose1, orderInfoBean.getDoseQtyUnit());
            }
        }
    }
}
