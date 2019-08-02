package com.dhcc.nursepro.workarea.nurrecordold.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.commlibs.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.NurRecordJLDListFragment;
import com.dhcc.nursepro.workarea.nurrecordold.NurRecordMPGDListFragment;
import com.dhcc.nursepro.workarea.nurrecordold.NurRecordPGDFragment;
import com.dhcc.nursepro.workarea.nurrecordold.bean.InWardPatListBean;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NurRecordMenuListAdapter extends BaseQuickAdapter<RecModelListBean.MenuListBean, BaseViewHolder> {

    private BaseFragment baseFragment;
    private InWardPatListBean.PatInfoListBean patInfoListBean;
    private NurRecordModelListAdapter modelListAdapter;
    private List<RecModelListBean.MenuListBean.ModelListBean> ModelList = new ArrayList<>();

    public NurRecordMenuListAdapter(@Nullable List<RecModelListBean.MenuListBean> data, BaseFragment baseFragment, InWardPatListBean.PatInfoListBean patInfoListBean) {
        super(R.layout.item_nurrecord_menullist, data);
        this.baseFragment = baseFragment;
    }


    public void setPatInfoListBean(InWardPatListBean.PatInfoListBean patInfoListBean) {
        this.patInfoListBean = patInfoListBean;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecModelListBean.MenuListBean item) {
        helper.setText(R.id.tv_menulist, item.getMenuName());

        RecyclerView recyclerView = helper.getView(R.id.recy_modellistall);

        //提高展示效率
        recyclerView.setHasFixedSize(true);
        //设置的布局管理
        recyclerView.setLayoutManager(new LinearLayoutManager(baseFragment.getActivity()));

        recyclerView.setNestedScrollingEnabled(false);

        modelListAdapter = new NurRecordModelListAdapter(new ArrayList<>());
        modelListAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RecModelListBean.MenuListBean.ModelListBean modelListBean = (RecModelListBean.MenuListBean.ModelListBean) adapter.getItem(position);
                if (view.getId() == R.id.tv_modellist) {
                    Bundle bundle = new Bundle();
                    bundle.putString("EpisodeID", patInfoListBean.getEpisodeId());
                    bundle.putString("BedNo", patInfoListBean.getBedCode());
                    bundle.putString("PatName", patInfoListBean.getName());

                    bundle.putString("EMRCode", modelListBean.getModelCode());
                    bundle.putString("ModelNum", modelListBean.getModelNum());
                    bundle.putSerializable("ModelListBean", modelListBean);
                    if (Objects.requireNonNull(modelListBean).getModelType().equals("1")) {
                        baseFragment.startFragment(NurRecordJLDListFragment.class, bundle);
                    } else if (Objects.requireNonNull(modelListBean).getModelType().equals("2")) {
                        baseFragment.startFragment(NurRecordPGDFragment.class, bundle);
                    } else if (Objects.requireNonNull(modelListBean).getModelType().equals("3")) {
                        baseFragment.startFragment(NurRecordMPGDListFragment.class, bundle);

                    }
                }
            }
        });
        recyclerView.setAdapter(modelListAdapter);
        ModelList = item.getModelList();
        modelListAdapter.setNewData(ModelList);


    }
}