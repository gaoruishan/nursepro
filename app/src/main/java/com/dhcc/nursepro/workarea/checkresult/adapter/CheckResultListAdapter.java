package com.dhcc.nursepro.workarea.checkresult.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;

import java.util.List;

public class CheckResultListAdapter extends BaseQuickAdapter <CheckResultListBean.RisOrdListBean,BaseViewHolder>{

    public CheckResultListAdapter(@Nullable List<CheckResultListBean.RisOrdListBean> data) {
        super(R.layout.item_check_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckResultListBean.RisOrdListBean item) {

        helper.setText(R.id.tv_checklist_ordname,item.getOrderName())
                .setText(R.id.tv_checklist_datetime,item.getReportDateTime())
                .setText(R.id.tv_checklist_doc,item.getReportDoc())
                .addOnClickListener(R.id.ll_checkreport);;
        TextView tvresult = helper.getView(R.id.tv_checklist_result);
        TextView tvreport = helper.getView(R.id.tv_checklist_report);
        if (item.getReportStat().equals("Y")){
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
