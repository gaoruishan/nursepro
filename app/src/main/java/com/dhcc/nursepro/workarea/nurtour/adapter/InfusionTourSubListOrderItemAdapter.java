package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.InfusionListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:15:52
 */
public class InfusionTourSubListOrderItemAdapter extends BaseQuickAdapter<InfusionListBean.PatInfoListBean.OrdListBean.OeoreSubListBean, BaseViewHolder> {

    private Context mContext;
    public InfusionTourSubListOrderItemAdapter(@Nullable List<InfusionListBean.PatInfoListBean.OrdListBean.OeoreSubListBean> data, Context context) {
        super(R.layout.item_tour_infusionsublistorders, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InfusionListBean.PatInfoListBean.OrdListBean.OeoreSubListBean item) {
        helper.setText(R.id.tv_ordername,item.getArcimDesc())
                .setText(R.id.tv_tourinfusionsublist_time,item.getDoseQtyUnit()==""?"":"剂量："+item.getDoseQtyUnit())
                .setText(R.id.tv_tourall_nurse1,item.getPhOrdQtyUnit()==""?"":"总量："+item.getPhOrdQtyUnit());
    }
}