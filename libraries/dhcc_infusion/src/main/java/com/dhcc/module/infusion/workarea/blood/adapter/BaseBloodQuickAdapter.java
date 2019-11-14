package com.dhcc.module.infusion.workarea.blood.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.BloodOrdListBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-14/15:26
 * @email:grs0515@163.com
 */
public abstract class BaseBloodQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public BaseBloodQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }
    protected void setCommData(BaseViewHolder helper, BloodOrdListBean item) {
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
    }

}
