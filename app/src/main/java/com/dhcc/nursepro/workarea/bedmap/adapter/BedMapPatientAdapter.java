package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

/**
 * BedMapPatientAdapter
 *
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapPatientAdapter extends BaseQuickAdapter<BedMapBean.PatInfoListBean,BaseViewHolder> {

    public BedMapPatientAdapter(@Nullable List<BedMapBean.PatInfoListBean> data) {
        super(R.layout.item_bedmap_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.PatInfoListBean item) {
        helper.setText(R.id.tv_bedmap_patient_bedno, item.getBedCode() + "床")
                .setText(R.id.tv_bedmap_patient_name, item.getName());

        ImageView patientSex = helper.getView(R.id.img_bedmap_patient_sex);

        if (item.getSex().equals("男")) {
            patientSex.setImageResource(R.drawable.sex_male);
        } else {
            patientSex.setImageResource(R.drawable.sex_female);
        }
    }
}
