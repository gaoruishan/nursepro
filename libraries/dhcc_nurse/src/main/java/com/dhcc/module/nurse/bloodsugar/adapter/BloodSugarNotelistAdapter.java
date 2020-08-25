package com.dhcc.module.nurse.bloodsugar.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarNotelistBean;

import java.util.List;

public class BloodSugarNotelistAdapter  extends BaseQuickAdapter<BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean, BaseViewHolder> {

    public BloodSugarNotelistAdapter(int layoutResId, @Nullable List<BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,BloodSugarNotelistBean.SugarInfoListBean.SugarDataBean item) {
        helper.setText(R.id.tv_detail_codetitle,helper.getAdapterPosition()+1+"")
                .setText(R.id.tv_detail_datetime,item.getDate()+"\n"+item.getUpdateTime())
                .setText(R.id.tv_detail_status,item.getCode())
                .setText(R.id.tv_detail_value,item.getSugar())
                .setText(R.id.tv_detail_user,item.getUpdateUser());
    }
}
