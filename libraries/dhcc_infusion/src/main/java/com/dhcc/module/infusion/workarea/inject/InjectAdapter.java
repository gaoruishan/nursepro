package com.dhcc.module.infusion.workarea.inject;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.dhcc.module.infusion.workarea.blood.adapter.BaseBloodQuickAdapter;

import java.util.List;

/**
 * 注射
 * @author:gaoruishan
 * @date:202019-11-14/15:24
 * @email:grs0515@163.com
 */
public class InjectAdapter extends BaseBloodQuickAdapter<BloodOrdListBean, BaseViewHolder> {

    public InjectAdapter(int layoutResId, @Nullable List<BloodOrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodOrdListBean item) {
        setCommData(helper, item);
        setInjectDosingData(helper,item);
        helper.setGone(R.id.tv_lab_no, !TextUtils.isEmpty(item.getLabNo()))
                .setText(R.id.tv_operate, item.getPhcinDesc());
        RecyclerView rvItem = helper.getView(R.id.rv_item);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvItem, 0, LinearLayoutManager.VERTICAL);
        InjectChildAdapter childAdapter = new InjectChildAdapter(item.getArcimDescList());
        rvItem.setAdapter(childAdapter);
    }

    private class InjectChildAdapter extends BaseQuickAdapter<BloodOrdListBean.ArcimDescListBean, BaseViewHolder> {

        InjectChildAdapter(@Nullable List<BloodOrdListBean.ArcimDescListBean> data) {
            super(R.layout.item_inject_child_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BloodOrdListBean.ArcimDescListBean item) {
            helper.setText(R.id.tv_title, item.getArcimDesc())
                    .setText(R.id.tv_content, item.getDoseQtyUnit() + "      " + item.getPhOrdQtyUnit());
        }
    }
}
