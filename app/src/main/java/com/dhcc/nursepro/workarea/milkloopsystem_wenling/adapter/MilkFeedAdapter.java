package com.dhcc.nursepro.workarea.milkloopsystem_wenling.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkColdPatinfoBean;
import com.dhcc.nursepro.workarea.milkloopsystem_wenling.bean.MilkFeedExeListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.milkloopsystem_wenling.adapter
 * <p>
 * author Q
 * Date: 2019/7/8
 * Time:15:32
 */
public class MilkFeedAdapter extends BaseQuickAdapter<MilkFeedExeListBean.ExeListBean, BaseViewHolder> {

    public MilkFeedAdapter(@Nullable List<MilkFeedExeListBean.ExeListBean> data) {
        super(R.layout.item_milk_feed,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MilkFeedExeListBean.ExeListBean item) {
        helper.setText(R.id.item_milkfeed_name, item.getPatInfoDetail().getName())
                .setText(R.id.item_milkfeed_bedcode, item.getPatInfoDetail().getBedCode())
                .setText(R.id.item_milkfeed_bagid, item.getPatInfoDetail().getAge())
                .setText(R.id.item_milkfeed_amount, item.getPatInfoDetail().getAdmReason())
                .setText(R.id.item_milkfeed_time, item.getPatInfoDetail().getBedCode());
//        ;


         }
    }
