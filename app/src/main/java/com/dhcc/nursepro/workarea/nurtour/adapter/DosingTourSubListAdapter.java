package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.constant.Action;
import com.dhcc.nursepro.workarea.nurtour.bean.DosingListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:8:51
 */
public class DosingTourSubListAdapter extends BaseQuickAdapter<DosingListBean.PatInfoListBean.OrdListBean, BaseViewHolder> {

    private Context mContext;
    public DosingTourSubListAdapter(@Nullable List<DosingListBean.PatInfoListBean.OrdListBean> data, Context context) {
        super(R.layout.item_tour_dosingsublist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DosingListBean.PatInfoListBean.OrdListBean item) {
        helper.setText(R.id.tv_orderid,item.getOeoreId()+"--");

        Log.d(TAG, "convert: ");
        RecyclerView recOrderList = helper.getView(R.id.rec_tourdosingsublist_ordlist);
        //提高展示效率
        recOrderList.setHasFixedSize(true);
        //设置的布局管理
        recOrderList.setLayoutManager(new LinearLayoutManager(mContext));
        DosingTourSubListOrderItemAdapter dosingTourSubListOrderItemAdapter = new DosingTourSubListOrderItemAdapter(new ArrayList<DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean>(),mContext);
        recOrderList.setAdapter(dosingTourSubListOrderItemAdapter);
        dosingTourSubListOrderItemAdapter.setNewData(item.getOeoreSubList());
        dosingTourSubListOrderItemAdapter.notifyDataSetChanged();

        RecyclerView recTourList = helper.getView(R.id.rec_tourdosingsublist_tourlist);
        //提高展示效率
        recTourList.setHasFixedSize(true);
        //设置的布局管理
        recTourList.setLayoutManager(new LinearLayoutManager(mContext));
        DosingTourSubListTourItemAdapter dosingTourSubListTourItemAdapter = new DosingTourSubListTourItemAdapter(new ArrayList<DosingListBean.PatInfoListBean.OrdListBean.TourListBean>(),mContext);
        recTourList.setAdapter(dosingTourSubListTourItemAdapter);
        dosingTourSubListTourItemAdapter.setNewData(item.getTourList());
        dosingTourSubListTourItemAdapter.notifyDataSetChanged();
        dosingTourSubListTourItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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