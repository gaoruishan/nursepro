package com.dhcc.nursepro.workarea.labresult.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;

import java.util.List;

public class LabResultDetailAdapter extends BaseQuickAdapter <LabReslutDetailBean.ResultDetailBean,BaseViewHolder>{

    public LabResultDetailAdapter(@Nullable List<LabReslutDetailBean.ResultDetailBean> data) {
        super(R.layout.item_lab_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabReslutDetailBean.ResultDetailBean item) {

        helper.setText(R.id.tv_labdetail_item,item.getResultName())
                .setText(R.id.tv_labdetail_result,item.getResultValue());
        ImageView imglabdetail = helper.getView(R.id.img_labdetail);
        TextView tvresult = helper.getView(R.id.tv_labdetail_result);
        if ((item.getResultAbFlag()).equals("L")){
            imglabdetail.setVisibility(View.VISIBLE);
            imglabdetail.setImageResource(R.drawable.icon_lowvalue);
            tvresult.setTextColor(mContext.getResources().getColor(R.color.lab_warning_red));
        }else if ((item.getResultAbFlag()).equals("H")){
            imglabdetail.setVisibility(View.VISIBLE);
            imglabdetail.setImageResource(R.drawable.icon_highvalue);
            tvresult.setTextColor(mContext.getResources().getColor(R.color.lab_warning_red));
        }else if ((item.getResultAbFlag()).equals("M")||item.getResultAbFlag().equals("A")){
            imglabdetail.setVisibility(View.VISIBLE);
            imglabdetail.setImageResource(R.drawable.icon_positive);
            tvresult.setTextColor(mContext.getResources().getColor(R.color.lab_warning_red));
        }else {
            imglabdetail.setVisibility(View.GONE);
            tvresult.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        TextView tvresultref = helper.itemView.findViewById(R.id.tv_labdetail_resultref);
        if (item.getResultRefRanges().equals("")){
            tvresultref.setVisibility(View.GONE);
        }else {
            tvresultref.setVisibility(View.VISIBLE);
            tvresultref.setText(item.getResultRefRanges());
        }

    }
}
