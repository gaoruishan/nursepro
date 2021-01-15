package com.dhcc.nursepro.workarea.pathandover.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.pathandover.bean.GetConnectListBean;

import java.util.List;

public class PatHandoverRecordAdapter extends BaseQuickAdapter<GetConnectListBean.ConnectAllListBean.RecordBean, BaseViewHolder> {
    public PatHandoverRecordAdapter(@Nullable List<GetConnectListBean.ConnectAllListBean.RecordBean> data) {
        super(R.layout.item_pat_handover_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetConnectListBean.ConnectAllListBean.RecordBean item) {

        helper.setText(R.id.tv_item_pathandover_record_subtype, item.getRigSubType())
                .setText(R.id.tv_item_pathandover_record_datetime, item.getRigSubDate() + " " + item.getRigSubTime())
                .setText(R.id.tv_item_pathandover_record_user, item.getFirstUser());
    }
}
