package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.DrugHandOverScanOrderList;

import java.util.List;

public class DrugHandoverScanOrderAdapter extends BaseQuickAdapter<DrugHandOverScanOrderList.OrdListBean, BaseViewHolder> {

    public DrugHandoverScanOrderAdapter(@Nullable List<DrugHandOverScanOrderList.OrdListBean> data) {
        super(R.layout.item_drughandover_orders, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugHandOverScanOrderList.OrdListBean item) {

        LinearLayout llDrugorderContent = helper.getView(R.id.ll_drugorder_content);
        RecyclerView recyDrugorders = helper.getView(R.id.recy_drugorders);
        TextView tvDrugordererror = helper.getView(R.id.tv_drugorder_error);
        ImageView imgSelectDrugorders = helper.getView(R.id.img_select_drugorders);

        recyDrugorders.setHasFixedSize(true);
        recyDrugorders.setLayoutManager(new LinearLayoutManager(mContext));

        if (item.getScan()) {
            llDrugorderContent.setSelected(true);
            imgSelectDrugorders.setVisibility(View.VISIBLE);
        } else {
            llDrugorderContent.setSelected(false);
            imgSelectDrugorders.setVisibility(View.GONE);
        }

        if ("W".equals(item.getError())) {
            tvDrugordererror.setVisibility(View.VISIBLE);
            tvDrugordererror.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_wrong));
            tvDrugordererror.setText("错发");
        } else if ("M".equals(item.getError())) {
            tvDrugordererror.setVisibility(View.VISIBLE);
            tvDrugordererror.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_miss));
            tvDrugordererror.setText("漏发");
        } else if ("Q".equals(item.getError())) {
            tvDrugordererror.setVisibility(View.VISIBLE);
            tvDrugordererror.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_quality));
            tvDrugordererror.setText("质量问题");
        } else {
            tvDrugordererror.setVisibility(View.GONE);
        }


        DrugHandoverOrderChildAdapter childAdapter = new DrugHandoverOrderChildAdapter(item.getOeoreGroup());
        recyDrugorders.setAdapter(childAdapter);

        helper.addOnClickListener(R.id.tv_drugorder_wrong)
                .addOnClickListener(R.id.tv_drugorder_missing)
                .addOnClickListener(R.id.tv_drugorder_quality);

    }
}
