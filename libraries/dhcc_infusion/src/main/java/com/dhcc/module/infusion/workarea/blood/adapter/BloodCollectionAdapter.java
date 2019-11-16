package com.dhcc.module.infusion.workarea.blood.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;

import java.util.List;

/**
 * 采血
 * @author:gaoruishan
 * @date:202019-11-13/16:12
 * @email:grs0515@163.com
 */
public class BloodCollectionAdapter extends BaseBloodQuickAdapter<BloodOrdListBean, BaseViewHolder> {

    public BloodCollectionAdapter(int layoutResId, @Nullable List<BloodOrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodOrdListBean item) {
        setCommData(helper, item);

        RecyclerView rvItem = helper.getView(R.id.rv_item);
        RecyclerViewHelper.setGridRecyclerView(mContext, rvItem, 2, 0, false);
        BloodCollectionChildAdapter childAdapter = new BloodCollectionChildAdapter(item.getArcimDescList());
        rvItem.setAdapter(childAdapter);
    }

    private class BloodCollectionChildAdapter extends BaseQuickAdapter<BloodOrdListBean.ArcimDescListBean, BaseViewHolder> {

        BloodCollectionChildAdapter(@Nullable List<BloodOrdListBean.ArcimDescListBean> data) {
            super(R.layout.item_blood_child_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BloodOrdListBean.ArcimDescListBean item) {
            helper.setText(R.id.tv_name, item.getArcimDesc());
        }
    }
}
