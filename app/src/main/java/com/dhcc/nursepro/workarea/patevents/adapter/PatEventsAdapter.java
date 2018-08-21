package com.dhcc.nursepro.workarea.patevents.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

public class PatEventsAdapter extends BaseQuickAdapter <String,BaseViewHolder>{

    public PatEventsAdapter(@Nullable List<String> data) {
        super(R.layout.item_patevents,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_patevents_eventtype,"出院")
        .setText(R.id.tv_patevents_eventmaker,"qqq")
        .setText(R.id.tv_patevents_eventdate,item);

        TextView tvdel = helper.getView(R.id.tv_patevents_eventdel);
        tvdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
