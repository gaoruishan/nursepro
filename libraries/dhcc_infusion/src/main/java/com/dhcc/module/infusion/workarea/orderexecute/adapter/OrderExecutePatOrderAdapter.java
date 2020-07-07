package com.dhcc.module.infusion.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-06/15:04
 * @email:grs0515@163.com
 */
public class OrderExecutePatOrderAdapter extends BaseBloodQuickAdapter<BloodOrdListBean, BaseViewHolder> {

    public OrderExecutePatOrderAdapter(int layoutResId, @Nullable List<BloodOrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodOrdListBean item) {
        setCommData(helper, item);
        setInjectDosingData(helper, item);
        helper.setGone(R.id.tv_lab_no, !TextUtils.isEmpty(item.getLabNo()))
                .setText(R.id.tv_operate, item.getPhcinDesc());

        setInjectChildAdapter(helper, item.getArcimDescList());
    }
}
