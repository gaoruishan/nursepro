package com.dhcc.module.infusion.workarea.patrol.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.patrol.bean.InfusionTourListBean;

import java.util.List;

/**
 * 巡视情况
 * @author:gaoruishan
 * @date:202019-04-28/10:25
 * @email:grs0515@163.com
 */
public class InfusionTourAdapter extends BaseQuickAdapter<InfusionTourListBean, BaseViewHolder> {

    public InfusionTourAdapter(int layoutResId, @Nullable List<InfusionTourListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfusionTourListBean item) {
        boolean isReason = !TextUtils.isEmpty(item.getInfusionTourReason());
        helper.setGone(R.id.tv_reason, isReason);
        boolean isMeasure = !TextUtils.isEmpty(item.getInfusionTourMeasure());
        helper.setGone(R.id.tv_measure, isMeasure);
        String color = (isMeasure || isReason) ? "#FFFFF6F6" : "#FFFFFFFF";
        helper.setBackgroundColor(R.id.ll_tour, Color.parseColor(color));

        helper.setText(R.id.tv_date_time, item.getInfusionTourDate() + " " + item.getInfusionTourTime())
                .setText(R.id.tv_state, item.getInfusionTourState())
                .setText(R.id.tv_reason,item.getInfusionTourState()+"原因："+ item.getInfusionTourReason() )
                .setText(R.id.tv_measure,"处理措施："+ item.getInfusionTourMeasure());

    }
}
