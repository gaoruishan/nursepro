package com.dhcc.nursepro.workarea.bloodtransfusionsystem.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationListBean;

import java.util.List;

/**
 * BloodOperationListAdapter
 *
 * @author DevLix126
 * @date 2018/9/20
 */
public class BloodOperationListAdapter extends BaseQuickAdapter<BloodOperationListBean.BloodListBean, BaseViewHolder> {
    private String searchType;

    public BloodOperationListAdapter(@Nullable List<BloodOperationListBean.BloodListBean> data) {
        super(R.layout.item_blood_operationlist, data);
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodOperationListBean.BloodListBean item) {

        TextView tvStatus = helper.getView(R.id.tv_item_bloodop_opstatus);
        View line = helper.getView(R.id.line_item_bloodop_endreason);
        TextView bloodStopReason = helper.getView(R.id.tv_item_bloodop_endreason);

        if (item.getStatus().equals("签收")) {
            tvStatus.setText("已签收");
            tvStatus.setBackgroundResource(R.drawable.bg_oval_blue);
        } else if (item.getStatus().equals("正在输血")) {
            tvStatus.setText("输注中");
            tvStatus.setBackgroundResource(R.drawable.bg_oval_yellow);
        } else if (item.getStatus().equals("输血结束")) {
            if (TextUtils.isEmpty(item.getStopReason())) {
                tvStatus.setText("已完成");
                tvStatus.setBackgroundResource(R.drawable.bg_oval_greenlight);
            } else {
                tvStatus.setText("已终止");
                tvStatus.setBackgroundResource(R.drawable.bg_oval_red);
            }
        } else if (item.getStatus().equals("已回收")) {
            tvStatus.setText("已回收");
            tvStatus.setBackgroundResource(R.drawable.bg_oval_green);

        }

        if (TextUtils.isEmpty(item.getStopReason())) {
            line.setVisibility(View.GONE);
            bloodStopReason.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            bloodStopReason.setVisibility(View.VISIBLE);
            bloodStopReason.setText("终止原因："+item.getStopReason());
        }

        helper.setText(R.id.tv_item_bloodop_patinfo, item.getBedCode() + "床  " + item.getPatName())
                .setText(R.id.tv_item_bloodop_productdesc, item.getBloodProducDesc())
                .setText(R.id.tv_item_bloodop_bloodtype, item.getBldTyp())
                .setText(R.id.tv_item_bloodop_patbloodtype, item.getPatBldTyp());
        /*
          type
          R 查询签收列表
          S 查询血液输注列表
          E 查询输血结束列表
          Re 查询回收列表
         */
        if ("R".equals(searchType)) {
            helper.setText(R.id.tv_item_bloodop_datetime, item.getReciveDate() + " " + item.getReciveTime());
        } else if ("S".equals(searchType)) {
            helper.setText(R.id.tv_item_bloodop_datetime, item.getTransStartDate() + " " + item.getTransStartTime());
        } else if ("E".equals(searchType)) {
            helper.setText(R.id.tv_item_bloodop_datetime, item.getTransEdDate() + " " + item.getTransEdTime());
        } else if ("Re".equals(searchType)) {
            helper.setText(R.id.tv_item_bloodop_datetime, item.getTransRecycleDate() + " " + item.getTransRecycleTime());

        }

    }
}
