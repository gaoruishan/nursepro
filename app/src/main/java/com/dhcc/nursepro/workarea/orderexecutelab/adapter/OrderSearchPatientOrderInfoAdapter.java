package com.dhcc.nursepro.workarea.orderexecutelab.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.RotateTextView;
import com.dhcc.nursepro.workarea.orderexecutelab.bean.OrderSearchBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

/**
 * OrderSearchPatientOrderItemAdapter
 *
 * @author DevLix126
 * @date 2018/8/25
 */
public class OrderSearchPatientOrderInfoAdapter extends BaseQuickAdapter<OrderSearchBean.OrdersBean.PatOrdsBean, BaseViewHolder> {
    private int size;

    public OrderSearchPatientOrderInfoAdapter(@Nullable List<OrderSearchBean.OrdersBean.PatOrdsBean> data, int size) {
        super(R.layout.item_ospatient_orderinfo, data);
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSearchBean.OrdersBean.PatOrdsBean item) {
        OrderSearchBean.OrdersBean.PatOrdsBean.OrderInfoBean orderInfoBean = item.getOrderInfo();
        FlowLayout llorderinfosingle = helper.getView(R.id.ll_osporderinfo_singleorder);
        LinearLayout llExe = helper.getView(R.id.ll_executed);
        LinearLayout llorderinfomulti1 = helper.getView(R.id.ll_osporderinfo_multiorder1);
        View lineorderinfomulti = helper.getView(R.id.line_osporderinfo_multiorder);
        FlowLayout llorderinfomulti2 = helper.getView(R.id.ll_osporderinfo_multiorder2);
        TextView tvOrderType = helper.getView(R.id.tv_osporderinfo_ordertype);
        GradientDrawable myGrad = (GradientDrawable) tvOrderType.getBackground();

        LinearLayout llSkintestResult = helper.getView(R.id.ll_skintest_result);
        RotateTextView tvSkintestResult = helper.getView(R.id.tv_skintest_result);
        GradientDrawable skintestResultGrad = (GradientDrawable) llSkintestResult.getBackground();

        //静配标志显示
        TextView tvJp = helper.getView(R.id.tv_jp);

        if (size == 1) {
            if (orderInfoBean.getFilteFlagExtend() != null && orderInfoBean.getFilteFlagExtend().equals("JP")) {
                tvJp.setVisibility(View.VISIBLE);
            } else {
                tvJp.setVisibility(View.GONE);
            }
            llorderinfosingle.setVisibility(View.VISIBLE);
            if (orderInfoBean.getExecDateTime() == null || orderInfoBean.getExecDateTime().equals("")) {
                llExe.setVisibility(View.GONE);
            } else {
                llExe.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_executed_time, orderInfoBean.getExecDateTime());
                helper.setText(R.id.tv_executed_nur1, orderInfoBean.getExecCtcpDesc());
            }
            llorderinfomulti1.setVisibility(View.GONE);
            lineorderinfomulti.setVisibility(View.GONE);
            llorderinfomulti2.setVisibility(View.GONE);
            String[] typeStr = orderInfoBean.getDisposeStatCode().split("\\^");
            tvOrderType.setText(typeStr[0]);
            myGrad.setColor(Color.parseColor(typeStr[1]));

            if (orderInfoBean.getSkinResult() != null) {
                llSkintestResult.setVisibility(View.VISIBLE);
                tvSkintestResult.setText(orderInfoBean.getSkinResult());
                tvSkintestResult.setTextColor(Color.parseColor(orderInfoBean.getSkinColor()));
                skintestResultGrad.setStroke(ConvertUtils.dp2px(5), Color.parseColor(orderInfoBean.getSkinColor()));
            } else {
                llSkintestResult.setVisibility(View.GONE);
            }

            helper.setText(R.id.tv_osporderinfo_ordername, orderInfoBean.getArcimDesc())
                    .setText(R.id.tv_osporderinfo_orderdatetime, orderInfoBean.getSttDateTime())
                    .setText(R.id.tv_osporderinfo_orderoperate, orderInfoBean.getPhcinDesc())
                    .setText(R.id.tv_osporderinfo_orderdose, orderInfoBean.getDoseQtyUnit())
                    .setText(R.id.tv_osporderinfo_orderfrequency, orderInfoBean.getPhcfrCode())
                    .setText(R.id.tv_osporderinfo_ordercreator, orderInfoBean.getCtcpDesc())
                    .setText(R.id.tv_notes, orderInfoBean.getNotes());
        } else {

            llorderinfosingle.setVisibility(View.GONE);
            llExe.setVisibility(View.GONE);
            if (helper.getLayoutPosition() == 0) {
                if (orderInfoBean.getFilteFlagExtend() != null && orderInfoBean.getFilteFlagExtend().equals("JP")) {
                    tvJp.setVisibility(View.VISIBLE);
                } else {
                    tvJp.setVisibility(View.GONE);
                }
                tvOrderType.setVisibility(View.VISIBLE);
                String[] typeStr = orderInfoBean.getDisposeStatCode().split("\\^");
                tvOrderType.setText(typeStr[0]);
                myGrad.setColor(Color.parseColor(typeStr[1]));

                if (orderInfoBean.getSkinResult() != null) {
                    llSkintestResult.setVisibility(View.VISIBLE);
                    tvSkintestResult.setText(orderInfoBean.getSkinResult());
                    tvSkintestResult.setTextColor(Color.parseColor(orderInfoBean.getSkinColor()));
                    skintestResultGrad.setStroke(ConvertUtils.dp2px(5), Color.parseColor(orderInfoBean.getSkinColor()));
                } else {
                    llSkintestResult.setVisibility(View.GONE);
                }
            } else {
                tvJp.setVisibility(View.GONE);
                tvOrderType.setVisibility(View.GONE);
                llSkintestResult.setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_osporderinfo_ordername, orderInfoBean.getArcimDesc());
            if (helper.getLayoutPosition() == size - 1) {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                lineorderinfomulti.setVisibility(View.VISIBLE);
                llorderinfomulti2.setVisibility(View.VISIBLE);
                LinearLayout llExe2 = helper.getView(R.id.ll_executed2);
                if (orderInfoBean.getExecDateTime() == null || orderInfoBean.getExecDateTime().equals("")) {
                    llExe2.setVisibility(View.GONE);
                } else {
                    llExe2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_executed_time2, orderInfoBean.getExecDateTime())
                            .setText(R.id.tv_executed_nur2, orderInfoBean.getExecCtcpDesc());
                }
                helper.setText(R.id.tv_osporderinfo_orderdose1, orderInfoBean.getDoseQtyUnit())
                        .setText(R.id.tv_osporderinfo_orderdatetime2, orderInfoBean.getSttDateTime())
                        .setText(R.id.tv_osporderinfo_orderoperate2, orderInfoBean.getPhcinDesc())
                        .setText(R.id.tv_osporderinfo_orderfrequency2, orderInfoBean.getPhcfrCode())
                        .setText(R.id.tv_osporderinfo_ordercreator2, orderInfoBean.getCtcpDesc());

            } else {
                llorderinfomulti1.setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_osporderinfo_orderdose1, orderInfoBean.getDoseQtyUnit());
            }
        }
    }
}
