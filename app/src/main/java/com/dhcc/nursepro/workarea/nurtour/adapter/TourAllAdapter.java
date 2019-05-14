package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/20
 * Time:9:37
 */
public class TourAllAdapter extends BaseQuickAdapter<AllTourListBean.TourDataListBeanX, BaseViewHolder> {

    private Context mContext;
    public TourAllAdapter(@Nullable List<AllTourListBean.TourDataListBeanX> data, Context context) {
        super(R.layout.item_tourall, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, AllTourListBean.TourDataListBeanX item) {
        helper.setText(R.id.tv_item_tourall_time,item.getIntTime())
                .addOnClickListener(R.id.tv_lapack_del)
                .addOnClickListener(R.id.messagecontentll);
        if (item.getTourDataList() != null) {
            if (item.getTourDataList().size() < 1) {
                LinearLayout ll = helper.getView(R.id.ll_item_tourall);
                ll.setVisibility(View.GONE);
            }else {
                LinearLayout ll = helper.getView(R.id.ll_item_tourall);
                ll.setVisibility(View.VISIBLE);
            }
        }else {
            LinearLayout ll = helper.getView(R.id.ll_item_tourall);
            ll.setVisibility(View.GONE);
        }
        RecyclerView recAll = helper.getView(R.id.recy_item_tourall);
        TourAllListItemAdapter tourAllListItemAdapter = new TourAllListItemAdapter(new ArrayList<AllTourListBean.TourDataListBeanX.TourDataListBean>());
        //提高展示效率
        recAll.setHasFixedSize(true);
        //设置的布局管理
        recAll.setLayoutManager(new LinearLayoutManager(mContext));
        recAll.setAdapter(tourAllListItemAdapter);
        tourAllListItemAdapter.setNewData(item.getTourDataList());
        tourAllListItemAdapter.notifyDataSetChanged();


    }
}