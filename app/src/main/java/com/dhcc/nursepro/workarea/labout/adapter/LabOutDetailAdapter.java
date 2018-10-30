package com.dhcc.nursepro.workarea.labout.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

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
        helper.setText(R.id.tv_laboutdetail_patinfo,item.getBedNo()+"  "+item.getPatName())
                .setText(R.id.tv_laboutdetail_no,item.getCarryNo())
                .setText(R.id.tv_laboutdetail_desc,item.getCarryLabDesc())
                .setText(R.id.tv_laboutdetail_status,item.getStatusDesc())
                .setText(R.id.tv_labout_specname,item.getSpecName())
                .addOnClickListener(R.id.messagerightmenu);

        TextView tvStatus = helper.getView(R.id.tv_laboutdetail_status);
        if (item.getStatusDesc().equals("建单")){
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv1);
        }else if (item.getStatusDesc().equals("已交接")){
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv2);
        }else if (item.getStatusDesc().equals("部分处理")){
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv3);
        }else{
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv4);
        }


    }
}
