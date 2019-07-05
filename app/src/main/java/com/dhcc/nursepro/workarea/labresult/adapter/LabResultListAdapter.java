package com.dhcc.nursepro.workarea.labresult.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.List;

public class LabResultListAdapter extends BaseQuickAdapter <LabResultListBean.LabOrderListBean,BaseViewHolder>{

    public LabResultListAdapter(@Nullable List<LabResultListBean.LabOrderListBean> data) {
        super(R.layout.item_lab_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabResultListBean.LabOrderListBean item) {

        helper.setText(R.id.tv_lablist_ordname,item.getOrderName())
                .setText(R.id.tv_lablist_datetime,item.getResultDateTime())
                .setText(R.id.tv_lablist_labno,"标本号  "+item.getLabNo())
                .addOnClickListener(R.id.ll_labreport);
        TextView tvresult = helper.getView(R.id.tv_lablist_result);
        TextView tvreport = helper.getView(R.id.tv_lablist_report);
        if (item.getResultStatus().equals("Y")){
            tvreport.setTextColor(mContext.getResources().getColor(R.color.blue));
            tvresult.setText("已出");
            tvresult.setBackground(mContext.getResources().getDrawable(R.drawable.bg_cheklab_getresult));
        }else {
            tvreport.setTextColor(mContext.getResources().getColor(R.color.lab_un_report));
            tvresult.setBackground(mContext.getResources().getDrawable(R.drawable.bg_checklab_noresult));
            tvresult.setText("未出");
        }

    }
}
