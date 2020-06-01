package com.dhcc.nursepro.workarea.checkresult.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.view.PatIconView;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckPatsListBean;

import java.util.List;

public class CheckPatListAdapter extends BaseQuickAdapter<CheckPatsListBean.PatInfoListBean, BaseViewHolder> {

    public CheckPatListAdapter(@Nullable List<CheckPatsListBean.PatInfoListBean> data) {
        super(R.layout.item_pats_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckPatsListBean.PatInfoListBean item) {

        helper.setText(R.id.tv_pats_lab_bedno, "".equals(item.getBedCode()) ? "未分床" : item.getBedCode())
                .setText(R.id.tv_pats_lab_name, item.getName())
                .setText(R.id.tv_bedmap_patient_carelevel,item.getCareLevel());

        ImageView patientSex = helper.getView(R.id.img_bedmap_patient_sex);
        PatIconView patIconView = helper.getView(R.id.pacicon_check);
        patIconView.setCheckPat(item);

        if ("男".equals(item.getSex())) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }



    }
}
