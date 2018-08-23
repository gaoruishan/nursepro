package com.dhcc.nursepro.workarea.vitalsigndetail.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;
import java.util.Map;

public class VitalSignTitleAdapter extends BaseQuickAdapter <Map<String,String>,BaseViewHolder>{

    public VitalSignTitleAdapter(@Nullable List<Map<String,String>> data) {
        super(R.layout.item_vital_detailtitle,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String,String> item) {

//        Toast.makeText(get,"ooo",Toast.LENGTH_LONG).show();
        helper.setText(R.id.tv_detail_date,"日期时间")
                .setText(R.id.tv_detail_Barthel,item.get("Barthel"))
                .setText(R.id.tv_detail_Fallbed,item.get("Fallbed"))
                .setText(R.id.tv_detail_Fallrisk,item.get("Fallrisk"))
                .setText(R.id.tv_detail_Item34,item.get("Item34"))
                .setText(R.id.tv_detail_Item34_Title,item.get("Item34_Title"))
                .setText(R.id.tv_detail_Reason,item.get("Reason"))
                .setText(R.id.tv_detail_breath,item.get("breath"))
                .setText(R.id.tv_detail_defFreq,item.get("defFreq"))
                .setText(R.id.tv_detail_degrBlood,item.get("degrBlood"))
                .setText(R.id.tv_detail_diaPressure,item.get("diaPressure"))
                .setText(R.id.tv_detail_heartbeat,item.get("heartbeat"))
                .setText(R.id.tv_detail_height,item.get("height"))
                .setText(R.id.tv_detail_liquidOut,item.get("liquidOut"))
                .setText(R.id.tv_detail_painInten,item.get("painInten"))
                .setText(R.id.tv_detail_phyCooling,item.get("phyCooling"))
                .setText(R.id.tv_detail_pulse,item.get("pulse"))
                .setText(R.id.tv_detail_rectemperature,item.get("rectemperature"))
                .setText(R.id.tv_detail_sysPressure,item.get("sysPressure"))
                .setText(R.id.tv_detail_temperature,item.get("temperature"))
                .setText(R.id.tv_detail_uriVolume,item.get("uriVolume"))
                .setText(R.id.tv_detail_weight,item.get("weight"))
                .setText(R.id.tv_detail_Bedsore,item.get("Bedsore"));


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
