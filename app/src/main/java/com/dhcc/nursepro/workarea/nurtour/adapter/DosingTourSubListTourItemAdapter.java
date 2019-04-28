package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.DosingListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:15:52
 */
public class DosingTourSubListTourItemAdapter extends BaseQuickAdapter<DosingListBean.PatInfoListBean.OrdListBean.TourListBean, BaseViewHolder> {

    private Context mContext;
    public DosingTourSubListTourItemAdapter(@Nullable List<DosingListBean.PatInfoListBean.OrdListBean.TourListBean> data, Context context) {
        super(R.layout.item_tour_dosingsublistorders, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DosingListBean.PatInfoListBean.OrdListBean.TourListBean item) {
        helper.setText(R.id.tv_ordername,item.getTourType())
                .setText(R.id.tv_tourdosingsublist_time,item.getDHCNurTourDate()+"  "+item.getDHCNurTourTime())
                .setText(R.id.tv_tourall_nurse1,item.getDHCNurTourUser());
    }
}