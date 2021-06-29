package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.orderexecute.adapter
 * <p>
 * author Q
 * Date: 2021/6/25
 * Time:14:47
 */
public class OrdLoopAdapter extends BaseQuickAdapter<OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean.LoopInfoBean, BaseViewHolder> {
    public OrdLoopAdapter(@Nullable List<OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean.LoopInfoBean> data) {
        super(R.layout.item_ord_loop, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, OrderExecuteBean.OrdersBean.PatOrdsBean.OrderInfoBean.LoopInfoBean item) {
        helper.setText(R.id.tv_title, item.getLoopDate()+" "+item.getLoopTime()+" | "+item.getWorkUser())
                .setVisible(R.id.tv_from, false)
                .setVisible(R.id.tv_to, false)
                .setText(R.id.tv_type, item.getWorkType())
                .setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.bg_green))
                .setTextColor(R.id.tv_type, mContext.getResources().getColor(R.color.bg_green))
//                .setGone(R.id.tv_from, !TextUtils.isEmpty(item.getWorkType()))
        ;

    }
}

