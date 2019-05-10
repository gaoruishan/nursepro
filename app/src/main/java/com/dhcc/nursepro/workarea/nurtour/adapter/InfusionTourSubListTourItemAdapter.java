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
public class InfusionTourSubListTourItemAdapter extends BaseQuickAdapter<InfusionListBean.PatInfoListBean.OrdListBean.TourListBean, BaseViewHolder> {

    private Context mContext;
    public InfusionTourSubListTourItemAdapter(@Nullable List<InfusionListBean.PatInfoListBean.OrdListBean.TourListBean> data, Context context) {
        super(R.layout.item_tour_infusionsublisttours, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InfusionListBean.PatInfoListBean.OrdListBean.TourListBean item) {
        helper.setText(R.id.tv_tourid,item.getDetailId())
                .setText(R.id.tv_infusiontour_name,item.getTourType())
                .setText(R.id.tv_infusiontour_time,item.getDHCNurTourDate()+"   "+item.getDHCNurTourTime())
                .setText(R.id.tv_infusiontour_nurse,item.getDHCNurTourUser());
    }
}