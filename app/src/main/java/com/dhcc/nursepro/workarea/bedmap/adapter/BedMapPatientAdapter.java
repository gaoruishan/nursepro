package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.graphics.Color;
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
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

/**
 * BedMapPatientAdapter
 * 床位图-患者adapter
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapPatientAdapter extends BaseQuickAdapter<BedMapBean.PatInfoListBean, BaseViewHolder> {

    public BedMapPatientAdapter(@Nullable List<BedMapBean.PatInfoListBean> data) {
        super(R.layout.item_bedmap_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.PatInfoListBean item) {
        helper.setText(R.id.tv_bedmap_patient_bedno, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode())
                .setText(R.id.tv_bedmap_patient_name, item.getName());

        TextView tvLongOrd = helper.getView(R.id.tv_bedmap_patient_longorder);
        TextView tvTempOrd = helper.getView(R.id.tv_bedmap_patient_temporder);
        TextView tvCareLevel = helper.getView(R.id.tv_bedmap_patient_carelevel);
        TextView tvDiet = helper.getView(R.id.tv_bedmap_patient_diet);

        View line = helper.getView(R.id.line_bedmap_patient);
        View line1 = helper.getView(R.id.line_bedmap_patient_1);
        View line2 = helper.getView(R.id.line_bedmap_patient_2);
        View line11 = helper.getView(R.id.line_bedmap_patient_11);

        ImageView patientSex = helper.getView(R.id.img_bedmap_patient_sex);

        TextView tvOperation = helper.getView(R.id.tv_bedmap_patient_operation);
        TextView tvCritical = helper.getView(R.id.tv_bedmap_patient_critical);
        TextView tvArrears = helper.getView(R.id.tv_bedmap_patient_arrears);
        TextView tvCriticalValue = helper.getView(R.id.tv_bedmap_patient_criticalvalue);
        TextView tvSkinTest = helper.getView(R.id.tv_bedmap_patient_skintest);

        LinearLayout llSkinOrder = helper.getView(R.id.ll_bedmap_patient_skinorder);
        RecyclerView recySkinOrder = helper.getView(R.id.recy_bedmap_patient_skinorder);
        //提高展示效率
        recySkinOrder.setHasFixedSize(true);
        //设置的布局管理
        recySkinOrder.setLayoutManager(new LinearLayoutManager(mContext));

        //隐藏除护理等级之外信息
        tvLongOrd.setVisibility(View.GONE);
        line1.setVisibility(View.GONE);
        tvTempOrd.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);


//        if ("1".equals(item.getLongOrd())) {
//            tvLongOrd.setVisibility(View.VISIBLE);
//        } else {
//            tvLongOrd.setVisibility(View.GONE);
//        }
//        if ("1".equals(item.getTempOrd())) {
//            tvTempOrd.setVisibility(View.VISIBLE);
//        } else {
//            tvTempOrd.setVisibility(View.GONE);
//        }


//        进食状况
//        if ("".equals(item.getDiet()) || item.getDiet().isEmpty()) {
//            line11.setVisibility(View.GONE);
//            tvDiet.setVisibility(View.GONE);
//        } else {
//            line11.setVisibility(View.VISIBLE);
//            tvDiet.setVisibility(View.VISIBLE);
//            tvDiet.setText(item.getDiet());
//        }

        if ("".equals(item.getCareLevel())) {
            tvCareLevel.setVisibility(View.GONE);
        } else {
            tvCareLevel.setVisibility(View.VISIBLE);

            if (item.getCareLevel().equals("特级护理")) {
                tvCareLevel.setTextColor(Color.parseColor("#FF8C00"));
            } else if(item.getCareLevel().equals("一级护理")){
                tvCareLevel.setTextColor(Color.parseColor("#FF0000"));
            } else if (item.getCareLevel().equals("二级护理")) {
                tvCareLevel.setTextColor(Color.parseColor("#0000FF"));
            } else {
                tvCareLevel.setTextColor(Color.parseColor("#00BD4C"));
            }

            tvCareLevel.setText(item.getCareLevel());
        }

//        if ("".equals(item.getCareLevel())) {
//            line2.setVisibility(View.GONE);
//            if ("0".equals(item.getTempOrd())) {
//                line1.setVisibility(View.GONE);
//            } else {
//                line1.setVisibility(View.VISIBLE);
//            }
//        } else {
//            line2.setVisibility(View.VISIBLE);
//            if ("0".equals(item.getTempOrd())) {
//                line1.setVisibility(View.GONE);
//            } else {
//                line1.setVisibility(View.VISIBLE);
//            }
//        }

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

        if ("1".equals(item.getGotAllergy())) {
            tvSkinTest.setVisibility(View.VISIBLE);
        } else {
            tvSkinTest.setVisibility(View.GONE);
        }

        List<BedMapBean.PatInfoListBean.SkinOrdBean> skinOrdBeanList = item.getSkinOrd();
        if (skinOrdBeanList == null || skinOrdBeanList.size() < 1) {
            line.setVisibility(View.GONE);
            llSkinOrder.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            llSkinOrder.setVisibility(View.VISIBLE);

            BedMapPatientSkinOrderAdapter skinOrderAdapter = new BedMapPatientSkinOrderAdapter(skinOrdBeanList);
            recySkinOrder.setAdapter(skinOrderAdapter);
        }


    }
}
