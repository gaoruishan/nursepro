package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetTakeOrdListBean;

import java.util.List;

public class DrugPreTakeOrdersAdapter extends BaseQuickAdapter<GetTakeOrdListBean.OrdListBean, BaseViewHolder> {
    private RecyclerView recyDrugpreTakeorders;
    private DrugPreTakeOrderChildAdapter orderChildAdapter;


    public DrugPreTakeOrdersAdapter(@Nullable List<GetTakeOrdListBean.OrdListBean> data) {
        super(R.layout.item_drugpre_takeorders, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTakeOrdListBean.OrdListBean item) {

        recyDrugpreTakeorders = helper.getView(R.id.recy_drugpre_takeorders);
        recyDrugpreTakeorders.setHasFixedSize(true);
        recyDrugpreTakeorders.setLayoutManager(new LinearLayoutManager(mContext));
        orderChildAdapter = new DrugPreTakeOrderChildAdapter(item.getOeoreGroup());
        recyDrugpreTakeorders.setAdapter(orderChildAdapter);


        helper.setText(R.id.tv_drugpre_takeorders_name, item.getTakeUser())
                .setText(R.id.tv_drugpre_takeorders_time, item.getTakeDateTime())
                .setText(R.id.tv_drugpre_takeorders_reviewname, item.getTakeAuditUser())
                .setText(R.id.tv_drugpre_takeorders_reviewtime, item.getTakeAuditDateTime());

    }
}
