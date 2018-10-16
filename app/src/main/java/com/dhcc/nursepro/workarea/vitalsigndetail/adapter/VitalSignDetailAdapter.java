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
        helper.setText(R.id.tv_detail_date,"".equals(item.getDate()) ? " " : item.getDate())
                .setText(R.id.tv_detail_Barthel,"".equals(item.getBarthel()) ? " " : item.getBarthel())
                .setText(R.id.tv_detail_Fallbed,"".equals(item.getFallbed()) ? " " : item.getFallbed())
                .setText(R.id.tv_detail_Fallrisk,"".equals(item.getFallrisk()) ? " " : item.getFallrisk())
                .setText(R.id.tv_detail_Item34,"".equals(item.getItem34()) ? " " : item.getItem34())
                .setText(R.id.tv_detail_Item34_Title,"".equals(item.getItem34_Title()) ? " " : item.getItem34_Title())
                .setText(R.id.tv_detail_Reason,"".equals(item.getReason()) ? " " : item.getReason())
                .setText(R.id.tv_detail_breath,"".equals(item.getBreath()) ? " " : item.getBreath())
                .setText(R.id.tv_detail_defFreq,"".equals(item.getDefFreq()) ? " " : item.getDefFreq())
                .setText(R.id.tv_detail_degrBlood,"".equals(item.getDegrBlood()) ? " " : item.getDegrBlood())
                .setText(R.id.tv_detail_diaPressure,"".equals(item.getDiaPressure()) ? " " : item.getDiaPressure())
                .setText(R.id.tv_detail_heartbeat,"".equals(item.getHeartbeat()) ? " " : item.getHeartbeat())
                .setText(R.id.tv_detail_height,"".equals(item.getHeight()) ? " " : item.getHeight())
                .setText(R.id.tv_detail_liquidOut,"".equals(item.getLiquidOut()) ? " " : item.getLiquidOut())
                .setText(R.id.tv_detail_painInten,"".equals(item.getPainInten()) ? " " : item.getPainInten())
                .setText(R.id.tv_detail_phyCooling,"".equals(item.getPhyCooling()) ? " " : item.getPhyCooling())
                .setText(R.id.tv_detail_pulse,"".equals(item.getPulse()) ? " " : item.getPulse())
                .setText(R.id.tv_detail_rectemperature,"".equals(item.getRectemperature()) ? " " : item.getRectemperature())
                .setText(R.id.tv_detail_sysPressure,"".equals(item.getSysPressure()) ? " " : item.getSysPressure())
                .setText(R.id.tv_detail_temperature,"".equals(item.getTemperature()) ? " " : item.getTemperature())
                .setText(R.id.tv_detail_time,"".equals(item.getTime()) ? " " : item.getTime())
                .setText(R.id.tv_detail_uriVolume,"".equals(item.getUriVolume()) ? " " : item.getUriVolume())
                .setText(R.id.tv_detail_weight,"".equals(item.getWeight()) ? " " : item.getWeight())
                .setText(R.id.tv_detail_Bedsore,"".equals(item.getBedsore()) ? " " : item.getBedsore());;


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
