package com.dhcc.nursepro.workarea.vitalsigndetail.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;

public class VitalSignDetailCodeAdapter extends BaseQuickAdapter <String,BaseViewHolder>{

    public VitalSignDetailCodeAdapter(@Nullable List<String> data) {
        super(R.layout.item_vital_detailcode,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

//        Toast.makeText(get,"ooo",Toast.LENGTH_LONG).show();
        helper.setText(R.id.tv_detail_code,item);

//
//        TextView tvdel = helper.getView(R.id.tv_patevents_eventdel);
//        tvdel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
