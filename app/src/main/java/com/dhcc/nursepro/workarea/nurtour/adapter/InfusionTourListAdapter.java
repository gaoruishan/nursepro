package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.InfusionListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:8:51
 */
public class InfusionTourListAdapter extends BaseQuickAdapter<InfusionListBean.PatInfoListBean, BaseViewHolder> {

    private Context mContext;
    public InfusionTourListAdapter(@Nullable List<InfusionListBean.PatInfoListBean> data, Context context) {
        super(R.layout.item_tour_infusionlist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InfusionListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_tournurlist_name,item.getName());

        RecyclerView recAll = helper.getView(R.id.rec_tourinfusionlist_allorders);
        //提高展示效率
        recAll.setHasFixedSize(true);
        //设置的布局管理
        recAll.setLayoutManager(new LinearLayoutManager(mContext));

        Log.d(TAG, "convert: "+item.getOrdList().size());
        InfusionTourSubListAdapter infusionTourSubListAdapter = new InfusionTourSubListAdapter(new ArrayList<InfusionListBean.PatInfoListBean.OrdListBean>(),mContext);
        recAll.setAdapter(infusionTourSubListAdapter);
        infusionTourSubListAdapter.setNewData(item.getOrdList());
        infusionTourSubListAdapter.notifyDataSetChanged();


    }
}