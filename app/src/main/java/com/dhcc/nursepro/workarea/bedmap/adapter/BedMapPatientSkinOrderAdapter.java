package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.List;

/**
 * BedMapPatientSkinOrderAdapter
 * 床位图-患者-皮试单adapter
 * @author DevLix126
 * @date 2018/8/13
 */
public class BedMapPatientSkinOrderAdapter extends BaseQuickAdapter<BedMapBean.PatInfoListBean.SkinOrdBean, BaseViewHolder> {

    public BedMapPatientSkinOrderAdapter(@Nullable List<BedMapBean.PatInfoListBean.SkinOrdBean> data) {
        super(R.layout.item_bedmap_patient_skinorder, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.PatInfoListBean.SkinOrdBean item) {

        helper.setText(R.id.item_skinorder_stinfo, item.getSkinTestInfo())
                .setText(R.id.item_skinorder_arcimname, item.getArcimName())
                .setText(R.id.item_skinorder_stdate, item.getStDate());
    }
}
