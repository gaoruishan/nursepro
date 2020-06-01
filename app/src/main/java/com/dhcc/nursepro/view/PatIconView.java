package com.dhcc.nursepro.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckPatsListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;

import java.util.Map;

/**
 * com.dhcc.nursepro.view
 * <p>
 * author Q
 * Date: 2020/6/1
 * Time:9:58
 */
public class PatIconView extends LinearLayout {
    private LinearLayout llView;
    public PatIconView(Context context) {
        super(context,null);
    }
    public PatIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setOrientation(LinearLayout.HORIZONTAL);
//        setBackgroundResource(R.color.white);
//        setGravity(Gravity.END);
//        int height = ConvertUtils.dp2px(110);
//        int width = 0;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
//        params.weight = 1;
//        setLayoutParams(params);
//        TextView titleTV = new TextView(getContext());
//        titleTV.setText("ru");
//        titleTV.setTextColor(Color.parseColor("#000000"));
        View view = LayoutInflater.from(context).inflate(R.layout.pat_icon_linelayout, this, false);
        llView = (LinearLayout) view;
        addView(view);
    }
    public void setIconShow(BedMapBean.PatInfoListBean item){
        if (llView!=null){
            TextView tvNewPat = getView(R.id.tv_bedmap_patient_newpatient);
            TextView tvOperation = getView(R.id.tv_bedmap_patient_operation);
            TextView tvCritical = getView(R.id.tv_bedmap_patient_critical);
            TextView tvArrears = getView(R.id.tv_bedmap_patient_arrears);
            TextView tvCriticalValue = getView(R.id.tv_bedmap_patient_criticalvalue);
            TextView tvSkinTest = getView(R.id.tv_bedmap_patient_skintest);
            TextView tvFever = getView(R.id.tv_bedmap_patient_fever);
            TextView tvEpdReport = getView(R.id.tv_epd_report);
            TextView tvEpdNotreport = getView(R.id.tv_epd_notreport);
            if ("1".equals(item.getOperation())) {
                tvOperation.setVisibility(View.VISIBLE);
            } else {
                tvOperation.setVisibility(View.GONE);
            }

            if ("病危".equals(item.getIllState())) {
                tvCritical.setVisibility(View.VISIBLE);
                tvCritical.setBackground(getContext().getResources().getDrawable(R.drawable.bg_rectangle_red));
                tvCritical.setText("危");
            } else if ("病重".equals(item.getIllState())) {
                tvCritical.setVisibility(View.VISIBLE);
                tvCritical.setBackground(getContext().getResources().getDrawable(R.drawable.bg_rectangle_red3));
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

    public void setMap(Map item){
        BedMapBean.PatInfoListBean patInfoListBean = new BedMapBean.PatInfoListBean();
        patInfoListBean.setOperation(item.get("operation").toString());
        patInfoListBean.setIllState(item.get("illState").toString());
        patInfoListBean.setArreag(item.get("arreag").toString());
        patInfoListBean.setCriticalValue(item.get("criticalValue").toString());
        patInfoListBean.setGotAllergy(item.get("gotAllergy").toString());
        patInfoListBean.setFever(item.get("fever").toString());
        patInfoListBean.setNewPatient(item.get("newPatient").toString());
        patInfoListBean.setEpdReport(item.get("epdReport").toString());
        patInfoListBean.setEpdNotReport(item.get("epdNotReport").toString());

        setIconShow(patInfoListBean);
    }
    public void setCheckPat(CheckPatsListBean.PatInfoListBean item){
        BedMapBean.PatInfoListBean patInfoListBean = new BedMapBean.PatInfoListBean();
        patInfoListBean.setOperation(item.getOperation());
        patInfoListBean.setIllState(item.getIllState());
        patInfoListBean.setArreag(item.getArreag());
        patInfoListBean.setCriticalValue(item.getCriticalValue());
        patInfoListBean.setGotAllergy(item.getGotAllergy());
        patInfoListBean.setFever(item.getFever());
        patInfoListBean.setNewPatient(item.getNewPatient());
        patInfoListBean.setEpdReport(item.getEpdReport());
        patInfoListBean.setEpdNotReport(item.getEpdNotReport());

        setIconShow(patInfoListBean);
    }
    public void setLabPat(PatsListBean.PatInfoListBean item){
        BedMapBean.PatInfoListBean patInfoListBean = new BedMapBean.PatInfoListBean();
        patInfoListBean.setOperation(item.getOperation());
        patInfoListBean.setIllState(item.getIllState());
        patInfoListBean.setArreag(item.getArreag());
        patInfoListBean.setCriticalValue(item.getCriticalValue());
        patInfoListBean.setGotAllergy(item.getGotAllergy());
        patInfoListBean.setFever(item.getFever());
        patInfoListBean.setNewPatient(item.getNewPatient());
        patInfoListBean.setEpdReport(item.getEpdReport());
        patInfoListBean.setEpdNotReport(item.getEpdNotReport());

        setIconShow(patInfoListBean);
    }


    public void showLeft(){
        llView.findViewById(R.id.paticon_view_left).setVisibility(GONE);
    }

    private TextView getView(int viewId){
        TextView textView = llView.findViewById(viewId);
        return  textView;
    }

}
