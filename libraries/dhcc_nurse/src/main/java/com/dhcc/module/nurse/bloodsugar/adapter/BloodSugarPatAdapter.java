package com.dhcc.module.nurse.bloodsugar.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.task.bean.AllBean;

import java.util.List;

import static com.base.commlibs.BaseApplication.getContext;

/**
 * com.dhcc.module.nurse.bloodsugar.adapter
 * <p>
 * author Q
 * Date: 2020/8/20
 * Time:11:18
 */
public class BloodSugarPatAdapter extends BaseQuickAdapter<BloodSugarPatsBean.PatInfoListBean, BaseViewHolder> {

    public BloodSugarPatAdapter(int layoutResId, @Nullable List<BloodSugarPatsBean.PatInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodSugarPatsBean.PatInfoListBean item) {
        helper.setText(R.id.tv_bloodsugar_patient_name,item.getName())
                .setText(R.id.tv_bloodsugar_patient_bedno,item.getBedCode())
                .addOnClickListener(R.id.tv_sugar_record)
                .addOnClickListener(R.id.tv_sugar_list)
                .addOnClickListener(R.id.tv_sugar_preview);

            ImageView patientSex = helper.getView(R.id.img_vitalsign_patient_sex);
            if ("男".equals(item.getSex())) {
                patientSex.setImageResource(R.drawable.dhcc_sex_male);
            } else {
                patientSex.setImageResource(R.drawable.dhcc_sex_female);
            }

            TextView tvNewPat = helper.getView(R.id.tv_bedmap_patient_newpatient);
            TextView tvOperation = helper.getView(R.id.tv_bedmap_patient_operation);
            TextView tvCritical = helper.getView(R.id.tv_bedmap_patient_critical);
            TextView tvArrears = helper.getView(R.id.tv_bedmap_patient_arrears);
            TextView tvCriticalValue = helper.getView(R.id.tv_bedmap_patient_criticalvalue);
            TextView tvSkinTest = helper.getView(R.id.tv_bedmap_patient_skintest);
            TextView tvFever = helper.getView(R.id.tv_bedmap_patient_fever);
            TextView tvEpdReport = helper.getView(R.id.tv_epd_report);
            TextView tvEpdNotreport = helper.getView(R.id.tv_epd_notreport);
            if ("1".equals(item.getOperation())) {
                tvOperation.setVisibility(View.VISIBLE);
            } else {
                tvOperation.setVisibility(View.GONE);
            }

            if ("病危".equals(item.getIllState())) {
                tvCritical.setVisibility(View.VISIBLE);
                tvCritical.setBackground(mContext.getResources().getDrawable(R.drawable.bg_rectangle_red));
                tvCritical.setText("危");
            } else if ("病重".equals(item.getIllState())) {
                tvCritical.setVisibility(View.VISIBLE);
                tvCritical.setBackground(mContext.getResources().getDrawable(R.drawable.bg_rectangle_red3));
                tvCritical.setText("重");
            } else {
                tvCritical.setVisibility(View.GONE);
            }
//            GradientDrawable gradientDrawable = new GradientDrawable();
//            gradientDrawable.setColor(Color.BLUE);
//            gradientDrawable.setCornerRadius(10);
//            tvCritical.setBackground(gradientDrawable);
            if ("1".equals(item.getNewPatient())) {
                tvNewPat.setVisibility(View.VISIBLE);
            } else {
                tvNewPat.setVisibility(View.GONE);
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

            if ("1".equals(item.getGotAllergy())) {
                tvSkinTest.setVisibility(View.VISIBLE);
            } else {
                tvSkinTest.setVisibility(View.GONE);
            }

            if ("1".equals(item.getFever())) {
                tvFever.setVisibility(View.VISIBLE);
            } else {
                tvFever.setVisibility(View.GONE);
            }

            if ("1".equals(item.getEpdReport())) {
                tvEpdReport.setVisibility(View.VISIBLE);
            } else {
                tvEpdReport.setVisibility(View.GONE);
            }

            if ("1".equals(item.getEpdNotReport())) {
                tvEpdNotreport.setVisibility(View.VISIBLE);
            } else {
                tvEpdNotreport.setVisibility(View.GONE);
            }
        }

}
