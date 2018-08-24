package com.dhcc.nursepro.workarea.checkresult.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;

import java.util.List;

public class PatListAdapter extends BaseQuickAdapter <PatsListBean.PatInfoListBean,BaseViewHolder>{

    public PatListAdapter(@Nullable List<PatsListBean.PatInfoListBean> data) {
        super(R.layout.item_pats_lab,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatsListBean.PatInfoListBean item) {

        helper.setText(R.id.tv_pats_lab_bedno, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode() + "床")
                .setText(R.id.tv_pats_lab_name, item.getName());

        TextView tvLongOrd = helper.getView(R.id.tv_bedmap_patient_longorder);
        TextView tvTempOrd = helper.getView(R.id.tv_bedmap_patient_temporder);
        TextView tvCareLevel = helper.getView(R.id.tv_bedmap_patient_carelevel);

        View line = helper.getView(R.id.line_bedmap_patient);
        View line1 = helper.getView(R.id.line_bedmap_patient_1);
        View line2 = helper.getView(R.id.line_bedmap_patient_2);

        ImageView patientSex = helper.getView(R.id.img_bedmap_patient_sex);

        TextView tvOperation = helper.getView(R.id.tv_bedmap_patient_operation);
        TextView tvCritical = helper.getView(R.id.tv_bedmap_patient_critical);
        TextView tvArrears = helper.getView(R.id.tv_bedmap_patient_arrears);
        TextView tvCriticalValue = helper.getView(R.id.tv_bedmap_patient_criticalvalue);
        if ("1".equals(item.getLongOrd())) {
            tvLongOrd.setVisibility(View.VISIBLE);
        } else {
            tvLongOrd.setVisibility(View.GONE);
        }
        if ("1".equals(item.getTempOrd())) {
            tvTempOrd.setVisibility(View.VISIBLE);
        } else {
            tvTempOrd.setVisibility(View.GONE);
        }
        if ("".equals(item.getCareLevel())) {
            tvCareLevel.setVisibility(View.GONE);

        } else {
            tvCareLevel.setVisibility(View.VISIBLE);
            tvCareLevel.setText(item.getCareLevel());
        }

        if ("".equals(item.getCareLevel())) {
            line2.setVisibility(View.GONE);
            if ("0".equals(item.getTempOrd())) {
                line1.setVisibility(View.GONE);
            } else {
                line1.setVisibility(View.VISIBLE);
            }
        } else {
            line2.setVisibility(View.VISIBLE);
            if ("0".equals(item.getTempOrd())) {
                line1.setVisibility(View.GONE);
            } else {
                line1.setVisibility(View.VISIBLE);
            }
        }

        if ("男".equals(item.getSex())) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }

        if ("1".equals(item.getOperation())) {
            tvOperation.setVisibility(View.VISIBLE);
        } else {
            tvOperation.setVisibility(View.GONE);
        }

        if ("病危".equals(item.getIllState())) {
            tvCritical.setVisibility(View.VISIBLE);
            tvCritical.setText("危");
        } else if ("病重".equals(item.getIllState())) {
            tvCritical.setVisibility(View.VISIBLE);
            tvCritical.setText("重");
        } else {
            tvCritical.setVisibility(View.GONE);
        }

        if ("1".equals(item.getArreag())) {
            tvArrears.setVisibility(View.VISIBLE);
        } else {
            tvArrears.setVisibility(View.GONE);
        }

        if ("1".equals(item.getCriticalValue())) {
            tvCriticalValue.setVisibility(View.VISIBLE);
        } else {
            tvCriticalValue.setVisibility(View.GONE);
        }


    }
}
