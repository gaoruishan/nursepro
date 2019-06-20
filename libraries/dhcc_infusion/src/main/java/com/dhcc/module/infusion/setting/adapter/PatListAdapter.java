package com.dhcc.module.infusion.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 患者列表适配器
 * @author:gaoruishan
 * @date:202019-06-20/16:32
 * @email:grs0515@163.com
 */
public class PatListAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public PatListAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
