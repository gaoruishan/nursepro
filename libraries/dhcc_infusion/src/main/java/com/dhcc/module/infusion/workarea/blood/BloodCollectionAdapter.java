package com.dhcc.module.infusion.workarea.blood;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * 采血
 * @author:gaoruishan
 * @date:202019-11-13/16:12
 * @email:grs0515@163.com
 */
public class BloodCollectionAdapter extends BaseQuickAdapter<BloodCollectBean.OrdListBean, BaseViewHolder> {
    public BloodCollectionAdapter(@Nullable List<BloodCollectBean.OrdListBean> data) {
        super(R.layout.item_blood_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BloodCollectBean.OrdListBean item) {
        helper.setText(R.id.tv_lab_no, item.getLabNo())
                .setText(R.id.tv_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_creator, item.getCtcpDesc())
                .setText(R.id.tv_operate,item.getSpecDesc())
                .setText(R.id.tv_time, item.getCreateDateTime());
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        String labColor = item.getLabColor();
        if(TextUtils.isEmpty(item.getLabColor())){
            labColor = "#62ABFF";
        }
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                .build();
        blTvStatus.setBackground(drawable);
        helper.setGone(R.id.tv_time, !TextUtils.isEmpty(item.getCreateDateTime()));

        RecyclerView rvItem = helper.getView(R.id.rv_item);
        RecyclerViewHelper.setGridRecyclerView(mContext, rvItem, 2, 0, false);
        BloodCollectionChildAdapter childAdapter = new BloodCollectionChildAdapter(item.getArcimDescList());
        rvItem.setAdapter(childAdapter);
    }

    private class BloodCollectionChildAdapter extends BaseQuickAdapter<BloodCollectBean.OrdListBean.ArcimDescListBean,BaseViewHolder> {

        public BloodCollectionChildAdapter( @Nullable List<BloodCollectBean.OrdListBean.ArcimDescListBean> data) {
            super(R.layout.item_blood_child_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BloodCollectBean.OrdListBean.ArcimDescListBean item) {
            helper.setText(R.id.tv_name, item.getArcimDesc());
        }
    }
}
