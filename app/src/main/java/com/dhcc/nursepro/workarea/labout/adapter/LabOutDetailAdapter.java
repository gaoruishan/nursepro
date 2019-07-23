package com.dhcc.nursepro.workarea.labout.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;

import java.util.List;

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
                .setText(R.id.tv_laboutdetail_status,item.getTranStatus())
                .setText(R.id.tv_labout_specname,item.getSpecName())
                .addOnClickListener(R.id.messagerightmenu);

        //生成:P,采集:C,发送:S,台收:T,岗收:R,审定:V,查看:L,打印:U,退还:D,拒绝:F

        TextView tvStatus = helper.getView(R.id.tv_laboutdetail_status);
        if (item.getTranStatus().equals("退还")){
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv2);
        }else if (item.getTranStatus().equals("拒绝")){
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv5);
        }else{
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv4);
        }


    }
}
