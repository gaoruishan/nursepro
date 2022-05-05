package com.dhcc.nursepro.workarea.labout.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;

import java.util.List;

public class LabOutDetailAdapter extends BaseQuickAdapter<LabOutDetailBean.DetailListBean, BaseViewHolder> {

    private String FlagNo;
    private Boolean ifCheck = false;

    public void setIfCheck(Boolean ifCheck) {
        this.ifCheck = ifCheck;
    }

    public LabOutDetailAdapter(@Nullable List<LabOutDetailBean.DetailListBean> data) {
        super(R.layout.item_addlabout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabOutDetailBean.DetailListBean item) {
        Boolean ifLabChecked = item.getAuditFlag() != null && item.getAuditFlag().equals("1");
        TextView tv = helper.getView(R.id.tv_labout_receiver);
        helper.setText(R.id.tv_laboutdetail_patinfo, item.getBedNo() + "  " + item.getPatName())
                .setText(R.id.tv_laboutdetail_labno, "标本号：" + item.getCarryLabNo())
                .setText(R.id.tv_laboutdetail_no, "单号：" + item.getCarryNo())
                .setText(R.id.tv_laboutdetail_desc, item.getCarryLabDesc())
                .setText(R.id.tv_laboutdetail_status, item.getTranStatus())
                .setText(R.id.tv_labout_specname, item.getSpecName())
                .addOnClickListener(R.id.messagerightmenu)
                .setVisible(R.id.ll_lab_check, ifCheck)
                .setBackgroundRes(R.id.ll_lab_check, ifLabChecked ? R.drawable.bg_lab_checked : R.drawable.bg_lab_uncheck)
                .setText(R.id.tv_lab_check, ifLabChecked ? "已核" : "未核")
                .setTextColor(R.id.tv_lab_check, ifLabChecked ? mContext.getResources().getColor(R.color.bg_green) : mContext.getResources().getColor(R.color.text_color_gray_8));
        //生成:P,采集:C,发送:S,台收:T,岗收:R,审定:V,查看:L,打印:U,退还:D,拒绝:F
        tv.setVisibility(item.getRecUserName().equals("") ? View.GONE : View.VISIBLE);
        if (!item.getRecUserName().equals("")) {
            tv.setText("签收人:" + item.getRecUserName());
        }
        TextView tvStatus = helper.getView(R.id.tv_laboutdetail_status);
        if (item.getTranStatus().equals("退还")) {
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv2);
        }
        if (item.getTranStatus().equals("拒绝")) {
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv5);
        }
        if (item.getTranStatus().equals("接收")) {
            tvStatus.setBackgroundResource(R.drawable.bg_labout_tv4);
        }


    }
}
