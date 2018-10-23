package com.dhcc.nursepro.workarea.patevents.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.patevents.PatEventsDetailFragment;
import com.dhcc.nursepro.workarea.patevents.bean.PatEventsBean;

import java.util.HashMap;
import java.util.List;

public class PatEventsAdapter extends BaseQuickAdapter<PatEventsBean.EventListBean, BaseViewHolder> {

    public PatEventsAdapter(@Nullable List<PatEventsBean.EventListBean> data) {
        super(R.layout.item_patevents, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PatEventsBean.EventListBean item) {

        helper.setText(R.id.tv_patevents_eventtype, item.getEventDesc())
                .setText(R.id.tv_patevents_eventmaker, item.getAddUser())
                .setText(R.id.tv_patevents_eventdate, item.getEventDate())
                .setText(R.id.tv_patevents_eventtime, item.getEventTime())
                .addOnClickListener(R.id.messagecontentll)
                .addOnClickListener(R.id.tv_patevents_eventdel);
        TextView tvdesc = helper.getView(R.id.tv_patevents_eventtype);
        switch (item.getEventDesc()) {
            case "外出":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_1));
                break;
            case "死亡":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_2));
                break;
            case "转出":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_3));
                break;
            case "出院":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_4));
                break;
            case "分娩":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_5));
                break;
            case "手术":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_6));
                break;
            case "转入":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_7));
                break;
            case "入院":
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_8));
                break;
            default:
                tvdesc.setBackground(mContext.getResources().getDrawable(R.drawable.bg_eventcircle_default));
                break;

        }

    }
}
