package com.dhcc.nursepro.workarea.vitalsigndetail.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;

import java.util.List;

public class VitalSignDetailAdapter extends BaseQuickAdapter <VitalSignDetailBean.TempDataListBean,BaseViewHolder>{

    public VitalSignDetailAdapter(@Nullable List<VitalSignDetailBean.TempDataListBean> data) {
        super(R.layout.item_vital_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VitalSignDetailBean.TempDataListBean item) {

//        Toast.makeText(get,"ooo",Toast.LENGTH_LONG).show();
        helper.setText(R.id.tv_detail_date,"".equals(item.getDate()) ? "无" : item.getDate())
                .setText(R.id.tv_detail_Barthel,"".equals(item.getBarthel()) ? "无" : item.getBarthel())
                .setText(R.id.tv_detail_Fallbed,"".equals(item.getFallbed()) ? "无" : item.getFallbed())
                .setText(R.id.tv_detail_Fallrisk,"".equals(item.getFallrisk()) ? "无" : item.getFallrisk())
                .setText(R.id.tv_detail_Item34,"".equals(item.getItem34()) ? "无" : item.getItem34())
                .setText(R.id.tv_detail_Item34_Title,"".equals(item.getItem34_Title()) ? "无" : item.getItem34_Title())
                .setText(R.id.tv_detail_Reason,"".equals(item.getReason()) ? "无" : item.getReason())
                .setText(R.id.tv_detail_breath,"".equals(item.getBreath()) ? "无" : item.getBreath())
                .setText(R.id.tv_detail_defFreq,"".equals(item.getDefFreq()) ? "无" : item.getDefFreq())
                .setText(R.id.tv_detail_degrBlood,"".equals(item.getDegrBlood()) ? "无" : item.getDegrBlood())
                .setText(R.id.tv_detail_diaPressure,"".equals(item.getDiaPressure()) ? "无" : item.getDiaPressure())
                .setText(R.id.tv_detail_heartbeat,"".equals(item.getHeartbeat()) ? "无" : item.getHeartbeat())
                .setText(R.id.tv_detail_height,"".equals(item.getHeight()) ? "无" : item.getHeight())
                .setText(R.id.tv_detail_liquidOut,"".equals(item.getLiquidOut()) ? "无" : item.getLiquidOut())
                .setText(R.id.tv_detail_painInten,"".equals(item.getPainInten()) ? "无" : item.getPainInten())
                .setText(R.id.tv_detail_phyCooling,"".equals(item.getPhyCooling()) ? "无" : item.getPhyCooling())
                .setText(R.id.tv_detail_pulse,"".equals(item.getPulse()) ? "无" : item.getPulse())
                .setText(R.id.tv_detail_rectemperature,"".equals(item.getRectemperature()) ? "无" : item.getRectemperature())
                .setText(R.id.tv_detail_sysPressure,"".equals(item.getSysPressure()) ? "无" : item.getSysPressure())
                .setText(R.id.tv_detail_temperature,"".equals(item.getTemperature()) ? "无" : item.getTemperature())
                .setText(R.id.tv_detail_time,"".equals(item.getTime()) ? "无" : item.getTime())
                .setText(R.id.tv_detail_uriVolume,"".equals(item.getUriVolume()) ? "无" : item.getUriVolume())
                .setText(R.id.tv_detail_weight,"".equals(item.getWeight()) ? "无" : item.getWeight())
                .setText(R.id.tv_detail_Bedsore,"".equals(item.getBedsore()) ? "无" : item.getBedsore());;


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
