package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;
import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLTextView;

import java.util.List;

public class InfusionDetailOrderAdapter extends BaseQuickAdapter<GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean, BaseViewHolder> {

    public InfusionDetailOrderAdapter(@Nullable List<GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean> data) {
        super(R.layout.item_infusiondetail_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionDetailByWardBean.WayNoOrdListBean.WayNoOrdSubListBean item) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("开始日期时间：").append(item.getStartDateTime());
        if (StringUtils.isEmpty(item.getFinishDateTime())) {
            stringBuilder.append("\n").append("预计用时：").append(item.getPreOverTime());
        } else {
            stringBuilder.append("\n").append("实际用时：").append(item.getOverTime())
                    .append("\n").append("结束日期时间：").append(item.getFinishDateTime());
        }

        BLTextView tvOrderStatus = helper.getView(R.id.tv_orderstatus_id);
        tvOrderStatus.setText(item.getInfusionStatus());
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(ConvertUtils.dp2px(4))
                .setSolidColor( Color.parseColor(item.getInfusionStatusColor()))
                .build();
        tvOrderStatus.setBackground(drawable);

        helper.setText(R.id.tv_orderinfoex_id, stringBuilder.toString());

        RecyclerView recyOrderDetail = helper.getView(R.id.recy_orderdetail_id);
        recyOrderDetail.setHasFixedSize(true);
        recyOrderDetail.setLayoutManager(new LinearLayoutManager(mContext));

        InfusionDetailOrderDetailAdapter orderDetailAdapter = new InfusionDetailOrderDetailAdapter(item.getGroupOrdList());
        recyOrderDetail.setAdapter(orderDetailAdapter);

    }
}
