package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetTakeOrdListBean;

import java.util.List;

public class DrugPreTakeOrderChildAdapter extends BaseQuickAdapter<GetTakeOrdListBean.OrdListBean.OeoreGroupBean, BaseViewHolder> {
    public DrugPreTakeOrderChildAdapter(@Nullable List<GetTakeOrdListBean.OrdListBean.OeoreGroupBean> data) {
        super(R.layout.item_drugpre_takeorderchild, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTakeOrdListBean.OrdListBean.OeoreGroupBean item) {
        helper.setText(R.id.tv_drugpre_takeorderchild_name, item.getArcimDesc())
                .setText(R.id.tv_drugpre_takeorderchild_dose, item.getDoseQtyUnit());
    }
}
