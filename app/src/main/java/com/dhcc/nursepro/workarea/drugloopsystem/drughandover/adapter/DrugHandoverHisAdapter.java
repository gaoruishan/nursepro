package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drughandover.bean.GetOrdRecListBean;

import java.util.List;

public class DrugHandoverHisAdapter extends BaseQuickAdapter<GetOrdRecListBean.RecListBean, BaseViewHolder> {
    private TextView tvDrughandoverhisWrong;
    private TextView tvDrughandoverhisMiss;
    private TextView tvDrughandoverhisQuality;


    public DrugHandoverHisAdapter(@Nullable List<GetOrdRecListBean.RecListBean> data) {
        super(R.layout.item_drughandover_his, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetOrdRecListBean.RecListBean item) {
        helper.setText(R.id.tv_drughandoverhis_count, item.getRecieveSum())
                .setText(R.id.tv_drughandoverhis_receivedatetime, item.getRecieveDateTime())
                .setText(R.id.tv_drughandoverhis_receiveuser, item.getRecieveUser());

        tvDrughandoverhisWrong = helper.getView(R.id.tv_drughandoverhis_wrong);
        tvDrughandoverhisMiss = helper.getView(R.id.tv_drughandoverhis_miss);
        tvDrughandoverhisQuality = helper.getView(R.id.tv_drughandoverhis_quality);

        if (item.getNoRecieveFlag().contains("W")) {
            tvDrughandoverhisWrong.setVisibility(View.VISIBLE);
        } else {
            tvDrughandoverhisWrong.setVisibility(View.GONE);
        }
        if (item.getNoRecieveFlag().contains("M")) {
            tvDrughandoverhisMiss.setVisibility(View.VISIBLE);
        } else {
            tvDrughandoverhisMiss.setVisibility(View.GONE);
        }
        if (item.getNoRecieveFlag().contains("Q")) {
            tvDrughandoverhisQuality.setVisibility(View.VISIBLE);
        } else {
            tvDrughandoverhisQuality.setVisibility(View.GONE);
        }

    }
}
