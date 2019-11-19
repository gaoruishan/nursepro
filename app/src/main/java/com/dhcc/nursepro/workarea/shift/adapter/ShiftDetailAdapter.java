package com.dhcc.nursepro.workarea.shift.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.shift.bean.ShiftBean;
import com.dhcc.nursepro.workarea.shift.bean.ShiftDetailBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.shift.adapter
 * <p>
 * author Q
 * Date: 2019/11/18
 * Time:16:27
 */
public class ShiftDetailAdapter extends BaseQuickAdapter<ShiftDetailBean.DetailListBean, BaseViewHolder> {

    public ShiftDetailAdapter(@Nullable List<ShiftDetailBean.DetailListBean> data) {
        super(R.layout.item_shiftdetail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShiftDetailBean.DetailListBean item) {
        helper.setText(R.id.shift_reason,item.getReason())
                .setText(R.id.shift_datafrom,item.getDatasrc())
                .setText(R.id.shift_bedno,item.getBedNo())
                .setText(R.id.shift_patname,item.getName())
                .setText(R.id.shift_diagnois,item.getDiagnois());

    }
}
