package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.bean.RLPatOrdBean;

import java.util.List;

public class RlRegPatOrdersAdapter extends BaseQuickAdapter<RLPatOrdBean.PatOrdListBean.PatOrdsBean, BaseViewHolder> {
    private RecyclerView recyItemrlregPatorderschild;
    private TextView tvItemrlregRl;
    private View lineItemrlreg;

    private int size;

    private RLRegPatOrderChildAdapter patOrderChildAdapter;

    public RlRegPatOrdersAdapter(@Nullable List<RLPatOrdBean.PatOrdListBean.PatOrdsBean> data) {
        super(R.layout.item_drugrlreg_patorders, data);
    }

    public RlRegPatOrdersAdapter(@Nullable List<RLPatOrdBean.PatOrdListBean.PatOrdsBean> data, int size) {
        super(R.layout.item_drugrlreg_patorders, data);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, RLPatOrdBean.PatOrdListBean.PatOrdsBean item) {

        helper.setText(R.id.tv_itemrlreg_orderdatetime, item.getSttdateTime())
                .setText(R.id.tv_itemrlreg_orderoperate, item.getMethDesc())
                .addOnClickListener(R.id.ll_itemrlreg_patorders);

        recyItemrlregPatorderschild = helper.getView(R.id.recy_itemrlreg_patorderschild);
        recyItemrlregPatorderschild.setHasFixedSize(true);
        recyItemrlregPatorderschild.setLayoutManager(new LinearLayoutManager(mContext));
        recyItemrlregPatorderschild.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return helper.getConvertView().onTouchEvent(event);
            }
        });
        patOrderChildAdapter = new RLRegPatOrderChildAdapter(item.getOeoreGroup());
        recyItemrlregPatorderschild.setAdapter(patOrderChildAdapter);

        tvItemrlregRl = helper.getView(R.id.tv_itemrlreg_rl);
        if (StringUtils.isEmpty(item.getResidualQty())) {
            tvItemrlregRl.setVisibility(View.GONE);
        } else {
            tvItemrlregRl.setVisibility(View.VISIBLE);
            tvItemrlregRl.setText("余液：" + item.getResidualQty());
        }

        lineItemrlreg = helper.getView(R.id.line_itemrlreg);
        if (helper.getLayoutPosition() < size - 1) {
            lineItemrlreg.setVisibility(View.VISIBLE);
        } else {
            lineItemrlreg.setVisibility(View.GONE);
        }


    }
}
