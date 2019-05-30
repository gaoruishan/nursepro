package com.dhcc.module.infusion.workarea.dosing.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.dosing.bean.OrdListBean;

import java.util.List;

/**
 * 配液
 * @author:gaoruishan
 * @date:202019-04-25/09:22
 * @email:grs0515@163.com
 */
public class CommDosingAdapter extends CommQuickAdapter<OrdListBean, BaseViewHolder> {

    public CommDosingAdapter(int layoutResId, @Nullable List<OrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdListBean item) {
        this.setCommData(helper, item);
        this.setCommItemClick(helper, R.id.ll_item);
    }

    @Override
    protected void setCommItemData(Bundle bundle, OrdListBean ordListBean) {
        super.setCommItemData(bundle, ordListBean);
        bundle.putString(ID, ordListBean.getOeoreId());
    }
}
