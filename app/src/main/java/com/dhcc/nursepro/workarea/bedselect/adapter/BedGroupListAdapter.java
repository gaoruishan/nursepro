package com.dhcc.nursepro.workarea.bedselect.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.List;

/**
 * BedListAdapter
 *
 * @author DevLix126
 * @date 2018/8/29
 */
public class BedGroupListAdapter extends BaseQuickAdapter<BedSelectListBean.BedListBean, BaseViewHolder> {


    public BedGroupListAdapter(List<BedSelectListBean.BedListBean> data) {
        super(R.layout.item_bedselect_group, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedSelectListBean.BedListBean item) {
        ImageView imgBedSelectGroup = helper.getView(R.id.img_bedselect_group);

        if ("0".equals(item.getSelect())) {
            imgBedSelectGroup.setSelected(false);
        } else {
            imgBedSelectGroup.setSelected(true);
        }

        helper.setText(R.id.tv_bedselect_group, item.getGroupCode() + "区")
                .addOnClickListener(R.id.ll_bedselect_group);

        RecyclerView rectBedSelectBed = helper.getView(R.id.recy_bedselect_bed);
        //提高展示效率
        rectBedSelectBed.setHasFixedSize(true);
        //设置的布局管理
        rectBedSelectBed.setLayoutManager(new GridLayoutManager(mContext,3));
    }
}
