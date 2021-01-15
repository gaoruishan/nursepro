package com.dhcc.module.infusion.workarea.drugreceive.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.drugreceive.IfOrdListBean;

import java.util.ArrayList;
import java.util.List;

public class DrugReceivedAdapter extends BaseQuickAdapter<IfOrdListBean.OrdListBean, BaseViewHolder> {


    public DrugReceivedAdapter(int layoutResId, @Nullable List<IfOrdListBean.OrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,IfOrdListBean.OrdListBean item) {
        helper.setText(R.id.tv_patinfo,item.getOeoreGroup().size()>0?item.getOeoreGroup().get(0).getBedCode()+" "+item.getOeoreGroup().get(0).getPatName():"")
                .setText(R.id.tv_sttdatetime,item.getOeoreGroup().size()>0?item.getOeoreGroup().get(0).getSttDateTime():"");
        RecyclerView rec = helper.getView(R.id.recy_iford);

        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(mContext));
        DrugReceivedChildAdapter drugReceivedChildAdapter = new DrugReceivedChildAdapter(new ArrayList<>());
        rec.setAdapter(drugReceivedChildAdapter);
        drugReceivedChildAdapter.setNewData(item.getOeoreGroup());
    }

    public static class DrugReceivedChildAdapter extends BaseQuickAdapter<IfOrdListBean.OrdListBean.OeoreGroupBean, BaseViewHolder> {

        public DrugReceivedChildAdapter(@Nullable List<IfOrdListBean.OrdListBean.OeoreGroupBean> data) {
            super(R.layout.item_iford_child, data);
        }
        @Override
        protected void convert(BaseViewHolder helper,IfOrdListBean.OrdListBean.OeoreGroupBean item) {
            helper.setText(R.id.tv_osporderinfo_ordername,item.getArcimDesc())
                    .setText(R.id.tv_unit,item.getDoseQtyUnit())
                    .setText(R.id.tv_allunit,item.getPhOrdQtyUnit());

        }
    }
}