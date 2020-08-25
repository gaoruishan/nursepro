package com.dhcc.module.nurse.bloodsugar.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.bloodsugar.bean.BloodSugarPatsBean;
import com.dhcc.module.nurse.task.bean.AllBean;

import java.util.List;

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
    }
}
