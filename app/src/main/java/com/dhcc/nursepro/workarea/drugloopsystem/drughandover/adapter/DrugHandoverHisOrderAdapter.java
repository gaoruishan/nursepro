package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.GetOrdRecListBean;

import java.util.List;

public class DrugHandoverHisOrderAdapter extends BaseQuickAdapter<GetOrdRecListBean.RecListBean.RecSubListBean, BaseViewHolder> {
    private TextView tvDrughisorderError;
    private RecyclerView recyDrughisorders;


    public DrugHandoverHisOrderAdapter(@Nullable List<GetOrdRecListBean.RecListBean.RecSubListBean> data) {
        super(R.layout.item_drughandover_hisorders, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrdRecListBean.RecListBean.RecSubListBean item) {
        tvDrughisorderError = helper.getView(R.id.tv_drughisorder_error);

        if ("W".equals(item.getNoRecieveDesc())) {
            tvDrughisorderError.setVisibility(View.VISIBLE);
            tvDrughisorderError.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_wrong));
            tvDrughisorderError.setText("错发");
        } else if ("M".equals(item.getNoRecieveDesc())) {
            tvDrughisorderError.setVisibility(View.VISIBLE);
            tvDrughisorderError.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_miss));
            tvDrughisorderError.setText("漏发");
        } else if ("Q".equals(item.getNoRecieveDesc())) {
            tvDrughisorderError.setVisibility(View.VISIBLE);
            tvDrughisorderError.setBackground(mContext.getResources().getDrawable(R.drawable.bg_drugorder_error_quality));
            tvDrughisorderError.setText("质量问题");
        } else {
            tvDrughisorderError.setVisibility(View.GONE);
        }


        recyDrughisorders = helper.getView(R.id.recy_drughisorders);
        recyDrughisorders.setHasFixedSize(true);
        recyDrughisorders.setLayoutManager(new LinearLayoutManager(mContext));

        DrugHandoverHisOrderChildAdapter hisOrderChildAdapter = new DrugHandoverHisOrderChildAdapter(item.getOeoreGroup());
        recyDrughisorders.setAdapter(hisOrderChildAdapter);
    }
}
