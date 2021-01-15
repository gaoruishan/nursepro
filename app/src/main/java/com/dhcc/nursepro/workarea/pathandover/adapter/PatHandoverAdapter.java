package com.dhcc.nursepro.workarea.pathandover.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectListBean;

import java.util.List;

public class PatHandoverAdapter extends BaseQuickAdapter<GetConnectListBean.ConnectAllListBean, BaseViewHolder> {

    public PatHandoverAdapter(@Nullable List<GetConnectListBean.ConnectAllListBean> data) {
        super(R.layout.item_pat_handover, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, GetConnectListBean.ConnectAllListBean item) {
        helper.setText(R.id.tv_item_pathandover_type, item.getRigType())
                .setText(R.id.tv_item_pathandover_bedcode, item.getBedCode().endsWith("床") ? item.getBedCode() : item.getBedCode() + "床")
                .setText(R.id.tv_item_pathandover_patname, item.getName())
                .setText(R.id.tv_item_pathandover_regno, item.getPatRegNo())
                .setText(R.id.tv_item_pathandover_lastdatetime, item.getRigDate() + " " + item.getRigTime())
                .setText(R.id.tv_item_pathandover_lastuser, item.getRigUser())
                .addOnClickListener(R.id.tv_item_pathandover_type)
                .addOnClickListener(R.id.ll_item_pathandover_patinfo)
                .addOnClickListener(R.id.ll_item_pathandover_record)
                .addOnClickListener(R.id.ll_item_pathandover_rightmenu);

        RecyclerView recyPathandoverRecord = helper.getView(R.id.recy_pathandover_record);
        //提高展示效率
        recyPathandoverRecord.setHasFixedSize(true);
        //设置的布局管理
        recyPathandoverRecord.setLayoutManager(new LinearLayoutManager(mContext));

        PatHandoverRecordAdapter patHandoverRecordAdapter = new PatHandoverRecordAdapter(item.getRecord());

        recyPathandoverRecord.setAdapter(patHandoverRecordAdapter);


        ImageView imgItemPathandoverRecord = helper.getView(R.id.img_item_pathandover_record);

        if (item.getRecordShow() == 0) {
            recyPathandoverRecord.setVisibility(View.GONE);
            imgItemPathandoverRecord.setSelected(false);

        } else {
            recyPathandoverRecord.setVisibility(View.VISIBLE);
            imgItemPathandoverRecord.setSelected(true);
        }


    }
}
