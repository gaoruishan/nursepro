package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
public class DosingTourSubListOrderItemAdapter extends BaseQuickAdapter<DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean, BaseViewHolder> {

    private Context mContext;
    public DosingTourSubListOrderItemAdapter(@Nullable List<DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean> data, Context context) {
        super(R.layout.item_tour_dosingsublistorders, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean item) {
        helper.setText(R.id.tv_ordername,item.getArcimDesc())
                .setText(R.id.tv_tourdosingsublist_time,item.getDoseQtyUnit())
                .setText(R.id.tv_tourall_nurse1,item.getPhOrdQtyUnit());
    }
}