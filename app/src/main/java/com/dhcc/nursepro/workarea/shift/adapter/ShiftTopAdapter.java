package com.dhcc.nursepro.workarea.shift.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.dhcc.nursepro.workarea.shift.bean.ShiftBean;

import java.util.List;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.shift.adapter
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:17:28
 */
public class ShiftTopAdapter extends BaseQuickAdapter<ShiftBean.ShiftDataBean.DataBean, BaseViewHolder> {

    public ShiftTopAdapter(@Nullable List<ShiftBean.ShiftDataBean.DataBean> data) {
        super(R.layout.item_shift, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShiftBean.ShiftDataBean.DataBean item) {
        helper.setText(R.id.tv_shift_key,item.getItemName())
                .setText(R.id.tv_shift_value,item.getItemDR());

    }
}
