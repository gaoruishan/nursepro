package com.dhcc.nursepro.workarea.vitalsign.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.PatIconView;

import java.util.List;
import java.util.Map;

public class VitalSignPatientAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {


    public VitalSignPatientAdapter(@Nullable List<Map> data) {
        super(R.layout.item_vitalsign_patient, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Map item) {

        helper.setText(R.id.tv_vitalsign_patient_bedno, "".equals(item.get("bedCode")) ? "未分床" : item.get("bedCode") + "")
                .setText(R.id.tv_vitalsign_patient_name, (String)item.get("name"))
                .setText(R.id.tv_vitalsign_patient_carelevel,(String)item.get("careLevel"));

        ImageView patientSex = helper.getView(R.id.img_vitalsign_patient_sex);
        PatIconView patIconView = helper.getView(R.id.paticon_vitalsign);
        patIconView.setMap(item);

        //体征录入
        helper.addOnClickListener(R.id.tv_vitalsign_vitalsign_record);
        TextView tvRecord = helper.getView(R.id.tv_vitalsign_vitalsign_record);
        tvRecord.setTextColor(mContext.getResources().getColor(R.color.text_color_gray));
        for (int j = 0; j < ((List) item.get("needMeasureInfo")).size(); j++) {
            Map measureInfo = (Map) ((List) item.get("needMeasureInfo")).get(j);
            for (Object key : measureInfo.keySet()) {
                if ((measureInfo.get(key).toString()).equals("1")){
                    tvRecord.setTextColor(mContext.getResources().getColor(R.color.blue));
                }
            }
        }
        //事件登记
        helper.addOnClickListener(R.id.tv_vitalsign_event_record);
        //体温单预览
        helper.addOnClickListener(R.id.tv_vitalsign_tmp_preview);

        //item点击事件
        helper.addOnClickListener(R.id.ll_vitalsign_content);
        if ("男".equals(item.get("sex"))) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }


    }

}
