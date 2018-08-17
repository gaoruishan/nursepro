package com.dhcc.nursepro.workarea.vitalsign.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;

import java.util.List;
import java.util.Map;

public class VitalSignPatientAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {


    public VitalSignPatientAdapter(@Nullable List<Map> data) {
        super(R.layout.item_vitalsign_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {

        helper.setText(R.id.tv_vitalsign_patient_bedno, "".equals(item.get("bedCode")) ? "未分床" : item.get("bedCode") + "床")
                .setText(R.id.tv_vitalsign_patient_name, (String)item.get("name"));

        TextView tvLongOrd = helper.getView(R.id.tv_vitalsign_patient_longorder);
        TextView tvTempOrd = helper.getView(R.id.tv_vitalsign_patient_temporder);
        TextView tvCareLevel = helper.getView(R.id.tv_vitalsign_patient_carelevel);

        View line = helper.getView(R.id.line_vitalsign_patient);
        View line1 = helper.getView(R.id.line_vitalsign_patient_1);
        View line2 = helper.getView(R.id.line_vitalsign_patient_2);

        ImageView patientSex = helper.getView(R.id.img_vitalsign_patient_sex);

        //
        TextView tvOperation = helper.getView(R.id.tv_vitalsign_patient_operation);
        TextView tvCritical = helper.getView(R.id.tv_vitalsign_patient_critical);
        TextView tvArrears = helper.getView(R.id.tv_vitalsign_patient_arrears);
        TextView tvCriticalValue = helper.getView(R.id.tv_vitalsign_patient_criticalvalue);


        //体征录入
        helper.addOnClickListener(R.id.tv_vitalsign_vitalsign_record);
        //事件登记
        helper.addOnClickListener(R.id.tv_vitalsign_event_record);
        //体温单预览
        helper.addOnClickListener(R.id.tv_vitalsign_tmp_preview);

        //item点击事件
        helper.addOnClickListener(R.id.ll_vitalsign_content);

        if ("1".equals(item.get("lognOrd"))) {
            tvLongOrd.setVisibility(View.VISIBLE);
        } else {
            tvLongOrd.setVisibility(View.GONE);
        }
        if ("1".equals(item.get("tempOrd"))) {
            tvTempOrd.setVisibility(View.VISIBLE);
        } else {
            tvTempOrd.setVisibility(View.GONE);
        }
        if ("".equals(item.get("cateLevel"))) {
            tvCareLevel.setVisibility(View.GONE);

        } else {
            tvCareLevel.setVisibility(View.VISIBLE);
            tvCareLevel.setText((String)item.get("cateLevel"));
        }

        if ("".equals(item.get("cateLevel"))) {
            line2.setVisibility(View.GONE);
            if ("0".equals(item.get("tempOrd"))) {
                line1.setVisibility(View.GONE);
            } else {
                line1.setVisibility(View.VISIBLE);
            }
        } else {
            line2.setVisibility(View.VISIBLE);
            if ("0".equals(item.get("tempOrd"))) {
                line1.setVisibility(View.GONE);
            } else {
                line1.setVisibility(View.VISIBLE);
            }
        }

        if ("男".equals(item.get("sex"))) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }

        if ("1".equals(item.get("operation"))) {
            tvOperation.setVisibility(View.VISIBLE);
        } else {
            tvOperation.setVisibility(View.GONE);
        }

        if ("病危".equals(item.get("illState"))) {
            tvCritical.setVisibility(View.VISIBLE);
            tvCritical.setText("危");
        } else if ("病重".equals( item.get("illState"))) {
            tvCritical.setVisibility(View.VISIBLE);
            tvCritical.setText("重");
        } else {
            tvCritical.setVisibility(View.GONE);
        }

        if ("1".equals(item.get("arreag"))) {
            tvArrears.setVisibility(View.VISIBLE);
        } else {
            tvArrears.setVisibility(View.GONE);
        }

        if ("1".equals(item.get("criticalValue"))) {
            tvCriticalValue.setVisibility(View.VISIBLE);
        } else {
            tvCriticalValue.setVisibility(View.GONE);
        }

    }

}
