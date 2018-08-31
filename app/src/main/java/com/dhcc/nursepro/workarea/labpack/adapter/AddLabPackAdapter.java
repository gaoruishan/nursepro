package com.dhcc.nursepro.workarea.labpack.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;
import java.util.Map;

public class AddLabPackAdapter extends BaseQuickAdapter<Map, BaseViewHolder>{

    public AddLabPackAdapter(@Nullable List<Map> data) {
        super(R.layout.item_addlabpack, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Map item) {
//        helper.setText(R.id.tv_operation_bedno,"01")
//                .setText(R.id.tv_operation_pat,"tom")
//                .setText(R.id.tv_operation_name,"手术")
//                .setText(R.id.tv_operation_statu,"安排");
        View view = helper.getView(R.id.messagerightmenu);
        if (helper.getAdapterPosition() == 1){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }

    }
}
