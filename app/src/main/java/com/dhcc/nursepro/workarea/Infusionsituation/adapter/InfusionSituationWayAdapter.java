package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLTextView;

import java.util.List;

public class InfusionSituationWayAdapter extends BaseQuickAdapter<GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean, BaseViewHolder> {

    public InfusionSituationWayAdapter(@Nullable List<GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean> data) {
        super(R.layout.item_infusionsituation_way, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionByWardBean.PatInfusionListBean.WayNoOrdListBean item) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("开始日期时间：").append(item.getStartDateTime());
        if (StringUtils.isEmpty(item.getFinishDateTime())) {
            stringBuilder.append("\n").append("预计用时：").append(item.getPreOverTime());
        } else {
            stringBuilder.append("\n").append("实际用时：").append(item.getOverTime())
                    .append("\n").append("结束日期时间：").append(item.getFinishDateTime());
        }

        BLTextView tvOrderStatus = helper.getView(R.id.tv_orderstatus_is);
        tvOrderStatus.setText(item.getInfusionStatus());
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(ConvertUtils.dp2px(4))
                .setSolidColor( Color.parseColor(item.getInfusionStatusColor()))
                .build();
        tvOrderStatus.setBackground(drawable);


        helper.setText(R.id.tv_wayno_is, item.getWayNum())
                .setText(R.id.tv_orderinfoex_is, stringBuilder.toString());

        RecyclerView recyOrders = helper.getView(R.id.recy_orders_is);
        recyOrders.setHasFixedSize(true);
        recyOrders.setLayoutManager(new LinearLayoutManager(mContext));

        InfusionSituationOrderDetailAdapter orderDetailAdapter = new InfusionSituationOrderDetailAdapter(item.getGroupOrdList());
        recyOrders.setAdapter(orderDetailAdapter);
        recyOrders.setLayoutFrozen(true);
    }
}
