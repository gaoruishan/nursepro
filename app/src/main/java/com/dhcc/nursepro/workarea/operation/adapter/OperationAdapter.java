package com.dhcc.nursepro.workarea.operation.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;
import java.util.Map;

public class OperationAdapter extends BaseQuickAdapter<Map, BaseViewHolder>{

    public OperationAdapter(@Nullable List<Map> data) {
        super(R.layout.item_operation, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Map item) {
        helper.setText(R.id.tv_operation_bedno,"01")
                .setText(R.id.tv_operation_pat,"tom")
                .setText(R.id.tv_operation_name,"手术")
                .setText(R.id.tv_operation_statu,"安排");

    }
}
