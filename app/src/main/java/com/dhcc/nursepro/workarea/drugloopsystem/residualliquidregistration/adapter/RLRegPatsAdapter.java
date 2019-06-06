package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.constant.Action;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;

import java.util.List;

public class RLRegPatsAdapter extends BaseQuickAdapter<RLPatOrdBean.PatOrdListBean, BaseViewHolder> {
    private RecyclerView recyItemrlregPatorders;
    private RlRegPatOrdersAdapter patOrdersAdapter;

    public RLRegPatsAdapter(@Nullable List<RLPatOrdBean.PatOrdListBean> data) {
        super(R.layout.item_drugrlreg_pat, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RLPatOrdBean.PatOrdListBean item) {

        recyItemrlregPatorders = helper.getView(R.id.recy_itemrlreg_patorders);
        recyItemrlregPatorders.setHasFixedSize(true);
        recyItemrlregPatorders.setLayoutManager(new LinearLayoutManager(mContext));

        helper.setText(R.id.tv_itemrlreg_bedcode, item.getPatBed())
                .setText(R.id.tv_itemrlreg_patname, item.getPatName());

        patOrdersAdapter = new RlRegPatOrdersAdapter(item.getPatOrds(), item.getPatOrds().size());
        patOrdersAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.ll_itemrlreg_patorders) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PatOrdListBean", item);
                    bundle.putInt("position", position);
                    intent.putExtras(bundle);
                    intent.setAction(Action.DRUG_RLREG);
                    mContext.sendBroadcast(intent);
                }
            }
        });

        recyItemrlregPatorders.setAdapter(patOrdersAdapter);

    }
}
