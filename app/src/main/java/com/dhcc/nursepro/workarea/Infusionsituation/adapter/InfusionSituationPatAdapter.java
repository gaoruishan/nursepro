package com.dhcc.nursepro.workarea.Infusionsituation.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;

import java.util.List;

public class InfusionSituationPatAdapter extends BaseQuickAdapter<GetInfusionByWardBean.PatInfusionListBean, BaseViewHolder> {

    public InfusionSituationPatAdapter(@Nullable List<GetInfusionByWardBean.PatInfusionListBean> data) {
        super(R.layout.item_infusionsituation_pat, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, GetInfusionByWardBean.PatInfusionListBean item) {
        GetInfusionByWardBean.PatInfusionListBean.PatInfoBean patInfoBean = item.getPatInfo();
        String patInfo = patInfoBean.getBedCode() + "-" + patInfoBean.getName() + "-" + patInfoBean.getSex() + "-" + patInfoBean.getAge();

        RecyclerView recyWay = helper.getView(R.id.recy_ways_is);
        recyWay.setHasFixedSize(true);
        recyWay.setLayoutManager(new LinearLayoutManager(mContext));

        InfusionSituationWayAdapter wayAdapter = new InfusionSituationWayAdapter(item.getWayNoOrdList());
        recyWay.setAdapter(wayAdapter);
        recyWay.setLayoutFrozen(true);


        helper.setText(R.id.tv_patinfo_is, patInfo);

    }

}
