package com.dhcc.nursepro.workarea.nurrecord.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.BaseFragment;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecord.NurRecordFragment;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;
import com.dhcc.nursepro.workarea.vitalsign.VitalSignFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NurRecordMenuListAdapter extends BaseQuickAdapter<NurRecordModelListBean.MenuListBean, BaseViewHolder>{
    private Context context ;
    private BaseFragment baseFragment;
    private String episodeId;
    private NurRecordModelListAdapter modelListAdapter;
    private List<NurRecordModelListBean.MenuListBean.ModelListBean> ModelList = new ArrayList<>();

    public NurRecordMenuListAdapter(@Nullable List<NurRecordModelListBean.MenuListBean> data, BaseFragment baseFragment,String episodeId) {
        super(R.layout.item_nurrecord_menullist, data);
        this.baseFragment = baseFragment;
        this.episodeId = episodeId;
    }
    @Override
    protected void convert(BaseViewHolder helper, NurRecordModelListBean.MenuListBean item) {
        helper.setText(R.id.tv_menulist,item.getMenuName());

        RecyclerView recyclerView = helper.getView(R.id.recy_modellistall);

        //提高展示效率
        recyclerView.setHasFixedSize(true);
        //设置的布局管理
        recyclerView.setLayoutManager(new LinearLayoutManager(baseFragment.getActivity()));

        recyclerView.setNestedScrollingEnabled(false);

        modelListAdapter = new NurRecordModelListAdapter(new ArrayList<NurRecordModelListBean.MenuListBean.ModelListBean>());
        modelListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.tv_modellist) {
                    Bundle bundle = new Bundle();
                    bundle.putString("EmrCode",ModelList.get(position).getModelCode());
                    bundle.putString("episodeId",episodeId );
                    baseFragment.startFragment(NurRecordFragment.class,bundle);
                }
            }
        });
        recyclerView.setAdapter(modelListAdapter);
        ModelList = item.getModelList();
        modelListAdapter.setNewData(ModelList);



    }
}
