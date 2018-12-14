package com.dhcc.nursepro.workarea.nurrecord.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecord.bean.NurRecordBean;

import java.util.List;

public class NurRecordAdapter extends BaseQuickAdapter<NurRecordBean.ModelListBean, BaseViewHolder>{
    private Context context ;

    public NurRecordAdapter(@Nullable List<NurRecordBean.ModelListBean> data, Context context) {
        super(R.layout.item_labout, data);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, NurRecordBean.ModelListBean item) {

    }
}
