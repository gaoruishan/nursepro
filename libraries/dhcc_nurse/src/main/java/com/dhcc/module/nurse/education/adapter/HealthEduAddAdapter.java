package com.dhcc.module.nurse.education.adapter;

import android.support.annotation.Nullable;

import com.base.commlibs.utils.ViewUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.HealthEduAddBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-20/15:51
 * @email:grs0515@163.com
 */
public class HealthEduAddAdapter extends BaseQuickAdapter<HealthEduAddBean.OrdListBean, BaseViewHolder> {

    public HealthEduAddAdapter(int layoutResId, @Nullable List<HealthEduAddBean.OrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthEduAddBean.OrdListBean item) {
        helper.setText(R.id.tv, item.getName());
        ViewUtil.setItemVisibility(helper.getConvertView(),!item.isHide());
    }
}
