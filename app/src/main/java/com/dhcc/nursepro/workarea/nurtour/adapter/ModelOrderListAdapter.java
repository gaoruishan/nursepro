package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.InfusionListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.ModelDataBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:15:52
 */
public class ModelOrderListAdapter extends BaseQuickAdapter<ModelDataBean.InfusionOrdInfoBean, BaseViewHolder> {

    private Context mContext;
    public ModelOrderListAdapter(@Nullable List<ModelDataBean.InfusionOrdInfoBean> data, Context context) {
        super(R.layout.item_tour_modelorderlist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, ModelDataBean.InfusionOrdInfoBean item) {
        helper.setText(R.id.tv_ordername,item.getArcimDesc())
                .setText(R.id.tv_tourinfusionsublist_time,item.getDoseQtyUnit())
                .setText(R.id.tv_tourall_nurse1,item.getPhOrdQtyUnit());
    }
}