package com.dhcc.nursepro.workarea.labout.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;

import java.util.List;
import java.util.Map;

public class LabOutDetailAdapter extends BaseQuickAdapter<LabOutDetailBean.DetailListBean, BaseViewHolder>{

    private String FlagNo;

    public LabOutDetailAdapter(@Nullable List<LabOutDetailBean.DetailListBean> data) {
        super(R.layout.item_addlabout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, LabOutDetailBean.DetailListBean item) {
        helper.setText(R.id.tv_laboutdetail_patinfo,item.getBedNo()+"床  "+item.getPatName())
                .setText(R.id.tv_laboutdetail_no,item.getCarryNo())
                .setText(R.id.tv_laboutdetail_desc,item.getCarryLabDesc())
                .setText(R.id.tv_laboutdetail_status,item.getCarryStatus())
                .addOnClickListener(R.id.messagerightmenu);



    }
}
