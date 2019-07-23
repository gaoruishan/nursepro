package com.dhcc.nursepro.workarea.nurrecordold.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordold.bean.RecModelListBean;

import java.util.List;

public class NurRecordModelListAdapter extends BaseQuickAdapter<RecModelListBean.MenuListBean.ModelListBean, BaseViewHolder>{

    public NurRecordModelListAdapter(@Nullable List<RecModelListBean.MenuListBean.ModelListBean> data) {
        super(R.layout.item_nurrecord_modellist, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RecModelListBean.MenuListBean.ModelListBean item) {
        helper.setText(R.id.tv_modellist,item.getModelName())
                .addOnClickListener(R.id.tv_modellist);

    }
}
