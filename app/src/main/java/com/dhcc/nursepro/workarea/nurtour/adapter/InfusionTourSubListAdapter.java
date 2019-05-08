package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
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
public class InfusionTourSubListAdapter extends BaseQuickAdapter<InfusionListBean.PatInfoListBean.OrdListBean, BaseViewHolder> {

    private Context mContext;
    public InfusionTourSubListAdapter(@Nullable List<InfusionListBean.PatInfoListBean.OrdListBean> data, Context context) {
        super(R.layout.item_tour_infusionsublist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InfusionListBean.PatInfoListBean.OrdListBean item) {
        helper.setText(R.id.tv_orderid,item.getOeoreId())
                .setText(R.id.tv_tourinfusionsublist_time,item.getTourList().get(item.getTourList().size()-1).getDHCNurTourDate()+" "+item.getTourList().get(item.getTourList().size()-1).getDHCNurTourTime());

        Log.d(TAG, "convert: ");
        RecyclerView recOrderList = helper.getView(R.id.rec_tourinfusionsublist_ordlist);
        //提高展示效率
        recOrderList.setHasFixedSize(true);
        //设置的布局管理
        recOrderList.setLayoutManager(new LinearLayoutManager(mContext));
        InfusionTourSubListOrderItemAdapter infusionTourSubListOrderItemAdapter = new InfusionTourSubListOrderItemAdapter(new ArrayList<InfusionListBean.PatInfoListBean.OrdListBean.OeoreSubListBean>(),mContext);
        recOrderList.setAdapter(infusionTourSubListOrderItemAdapter);
        infusionTourSubListOrderItemAdapter.setNewData(item.getOeoreSubList());
        infusionTourSubListOrderItemAdapter.notifyDataSetChanged();

        RecyclerView recTourList = helper.getView(R.id.rec_tourinfusionsublist_tourlist);
        //提高展示效率
        recTourList.setHasFixedSize(true);
        //设置的布局管理
        recTourList.setLayoutManager(new LinearLayoutManager(mContext));
        InfusionTourSubListTourItemAdapter infusionTourSubListTourItemAdapter = new InfusionTourSubListTourItemAdapter(new ArrayList<InfusionListBean.PatInfoListBean.OrdListBean.TourListBean>(),mContext);
        recTourList.setAdapter(infusionTourSubListTourItemAdapter);
        infusionTourSubListTourItemAdapter.setNewData(item.getTourList());
        infusionTourSubListTourItemAdapter.notifyDataSetChanged();
        infusionTourSubListTourItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("data", item.getTourList().get(position).getDetailId());
                Intent tbIntent = new Intent();
                tbIntent.setAction(Action.TOUR_DOSINGID);
                tbIntent.putExtras(bundle);
                mContext.sendBroadcast(tbIntent);
            }
        });



        ImageView img = helper.getView(R.id.tv_tournurlist_img);
        img.setSelected(false);
        recTourList.setVisibility(View.GONE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recTourList.getVisibility() == View.VISIBLE){
                    recTourList.setVisibility(View.GONE);
                    img.setSelected(false);
                }  else {
                    recTourList.setVisibility(View.VISIBLE);
                    img.setSelected(true);
                }
            }
        });

    }
}