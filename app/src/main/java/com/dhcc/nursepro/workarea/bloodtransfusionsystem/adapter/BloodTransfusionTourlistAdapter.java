package com.dhcc.nursepro.workarea.bloodtransfusionsystem.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodTransDetailBean;

import java.util.List;

/**
 * BloodTransfusionTourlistAdapter
 *
 * @author DevLix126
 * @date 2018/9/29
 */
public class BloodTransfusionTourlistAdapter extends BaseQuickAdapter<BloodTransDetailBean.PatrolRecordBean, BaseViewHolder> {
    public BloodTransfusionTourlistAdapter(@Nullable List<BloodTransDetailBean.PatrolRecordBean> data) {
        super(R.layout.item_blood_tourlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodTransDetailBean.PatrolRecordBean item) {
        helper.setText(R.id.tv_item_bloodtour_datetime, item.getDate() + " " + item.getTime())
                .setText(R.id.tv_item_bloodtour_nurse, item.getUser())
                .setText(R.id.tv_item_bloodtour_speed, item.getSpeed())
                .setText(R.id.tv_item_bloodtour_effect, item.getEffect())
                .setText(R.id.tv_item_bloodtour_situation, item.getSituation());

    }
}
