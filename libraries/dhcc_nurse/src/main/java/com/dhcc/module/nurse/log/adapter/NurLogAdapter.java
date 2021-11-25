package com.dhcc.module.nurse.log.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/11/25/11:17
 * @email:grs0515@163.com
 */
public class NurLogAdapter extends BaseQuickAdapter<NurLogBean, BaseViewHolder> {

    public NurLogAdapter(@Nullable List<NurLogBean> data) {
        super(R.layout.item_nur_log,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurLogBean item) {
        helper.addOnClickListener(R.id.ll_item);
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_no,item.getLogType());
    }
}
