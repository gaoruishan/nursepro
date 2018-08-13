package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

/**
 * BedMapPatientTypeAdapter
 * 床位图-患者类型（左侧筛选）
 * @author DevLix126
 * @date 2018/8/11
 */
public class BedMapPatientTypeAdapter extends BaseQuickAdapter<BedMapBean.LeftFilterBean, BaseViewHolder> {
    private int selectedPostion;

    public int getSelectedPostion() {
        return selectedPostion;
    }

    public void setSelectedPostion(int selectedPostion) {
        this.selectedPostion = selectedPostion;
    }

    public BedMapPatientTypeAdapter(@Nullable List<BedMapBean.LeftFilterBean> data) {
        super(R.layout.item_bedmap_patienttype, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.LeftFilterBean item) {

        TextView tvPatientType = helper.getView(R.id.tv_bedmap_patienttype);

        if (selectedPostion == helper.getAdapterPosition()) {
            tvPatientType.setSelected(true);
        } else {
            tvPatientType.setSelected(false);
        }

        helper.setText(R.id.tv_bedmap_patienttype, item.getDesc())
                .addOnClickListener(R.id.tv_bedmap_patienttype);


    }
}
