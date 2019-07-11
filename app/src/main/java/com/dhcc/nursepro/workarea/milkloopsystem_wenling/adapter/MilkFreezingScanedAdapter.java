package com.dhcc.nursepro.workarea.milkloopsystem_wenling.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFreezingBagInfoBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem.adapter
 * <p>
 * author Q
 * Date: 2018/9/25
 * Time:8:38
 */
public class MilkFreezingScanedAdapter extends BaseQuickAdapter<MilkFreezingBagInfoBean,BaseViewHolder> {

    public MilkFreezingScanedAdapter(@Nullable List<MilkFreezingBagInfoBean> data) {
        super(R.layout.item_milkfreezing_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MilkFreezingBagInfoBean item) {

        helper.setText(R.id.item_milkfreezing_name,item.getPatInfo().getPatName())
                .setText(R.id.item_milkfreezing_bedcode,item.getPatInfo().getBedCode().equals("")?"未分床":item.getPatInfo().getBedCode())
                .setText(R.id.item_milkfreezing_bagid,item.getPatInfo().getBagNo())
                .setText(R.id.item_milkfreezing_amount,item.getPatInfo().getAmount())
                .setText(R.id.item_milkfreezing_time,item.getPatInfo().getCollectDate()+" "+item.getPatInfo().getCollectTime())
                . addOnClickListener(R.id.ll_milkfreezing_right);;

    }
}