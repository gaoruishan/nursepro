package com.dhcc.nursepro.workarea.bedselect.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.List;

/**
 * BedListAdapter
 *
 * @author DevLix126
 * @date 2018/8/30
 */
public class BedListAdapter extends BaseQuickAdapter<BedSelectListBean.BedListBean.GroupListBean, BaseViewHolder> {

    public BedListAdapter(@Nullable List<BedSelectListBean.BedListBean.GroupListBean> data) {
        super(R.layout.item_bedselect_bed, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedSelectListBean.BedListBean.GroupListBean item) {
        TextView tvBedSelectBed = helper.getView(R.id.tv_bedselect_bed);

        if ("0".equals(item.getSelect())) {
            tvBedSelectBed.setSelected(false);
        } else {
            tvBedSelectBed.setSelected(true);
        }

        tvBedSelectBed.setText(item.getBedCode());
    }
}
