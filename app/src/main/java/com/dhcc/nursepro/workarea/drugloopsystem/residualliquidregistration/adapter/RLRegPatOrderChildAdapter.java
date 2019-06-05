package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;

import java.util.List;

public class RLRegPatOrderChildAdapter extends BaseQuickAdapter<RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean, BaseViewHolder> {
    private TextView tvRlregCname;
    private TextView tvRlregCdose;

    public RLRegPatOrderChildAdapter(@Nullable List<RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean> data) {
        super(R.layout.item_rlreg_patorderschild, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RLPatOrdBean.PatOrdListBean.PatOrdsBean.OeoreGroupBean item) {

        tvRlregCname = helper.getView(R.id.tv_rlreg_cname);
        tvRlregCdose = helper.getView(R.id.tv_rlreg_cdose);
        if (item.getJmFlag().equals("1")) {
            tvRlregCname.setSelected(true);
            tvRlregCdose.setSelected(true);
        } else {
            tvRlregCname.setSelected(false);
            tvRlregCdose.setSelected(false);
        }
        tvRlregCname.setText(item.getArcimDesc());
        tvRlregCdose.setText(item.getDoseQtyUnit());
    }
}
