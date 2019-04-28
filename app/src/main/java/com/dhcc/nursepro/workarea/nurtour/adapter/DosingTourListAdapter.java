package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
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
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.DosingListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:8:51
 */
public class DosingTourListAdapter extends BaseQuickAdapter<DosingListBean.PatInfoListBean, BaseViewHolder> {

    private Context mContext;
    public DosingTourListAdapter(@Nullable List<DosingListBean.PatInfoListBean> data, Context context) {
        super(R.layout.item_tour_dosinglist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, DosingListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_tournurlist_name,item.getName()+"--");

        RecyclerView recAll = helper.getView(R.id.rec_tourdosinglist_allorders);
        //提高展示效率
        recAll.setHasFixedSize(true);
        //设置的布局管理
        recAll.setLayoutManager(new LinearLayoutManager(mContext));

        Log.d(TAG, "convert: "+item.getOrdList().size());
        DosingTourSubListAdapter dosingTourSubListAdapter = new DosingTourSubListAdapter(new ArrayList<DosingListBean.PatInfoListBean.OrdListBean>(),mContext);
        recAll.setAdapter(dosingTourSubListAdapter);
        dosingTourSubListAdapter.setNewData(item.getOrdList());
        dosingTourSubListAdapter.notifyDataSetChanged();


    }
}