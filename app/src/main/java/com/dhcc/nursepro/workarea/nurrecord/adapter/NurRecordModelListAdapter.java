package com.dhcc.nursepro.workarea.nurrecord.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordModelListBean;

import java.util.List;

public class NurRecordModelListAdapter extends BaseQuickAdapter<NurRecordModelListBean.MenuListBean, BaseViewHolder>{
    private Context context ;

    public NurRecordModelListAdapter(@Nullable List<NurRecordModelListBean.MenuListBean> data) {
        super(R.layout.item_nurrecord_modellist, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, NurRecordModelListBean.MenuListBean item) {
        helper.setText(R.id.tv_modellist,item.getMenuName())
                .addOnClickListener(R.id.tv_modellist);

    }
}
