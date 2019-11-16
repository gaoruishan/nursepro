package com.dhcc.module.infusion.workarea.blood.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.blood.bean.BloodOrdListBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-14/15:26
 * @email:grs0515@163.com
 */
public abstract class BaseBloodQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    private String scanInfo;

    public BaseBloodQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    /**
     * 当前扫码信息
     * @param scanInfo
     */
    public void setCurrentScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    /**
     * 设置公共数据-采血/注射
     * @param helper
     * @param item
     */
    protected void setCommData(BaseViewHolder helper, BloodOrdListBean item) {
        helper.setText(R.id.tv_lab_no, item.getLabNo())
                .setText(R.id.tv_dose, item.getDoseQtyUnit())
                .setText(R.id.tv_creator, item.getCtcpDesc())
                .setText(R.id.tv_operate, item.getSpecDesc())
                .setText(R.id.tv_time, item.getCreateDateTime());
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setText(item.getDisposeStatDesc());
        String labColor = item.getLabColor();
        if (TextUtils.isEmpty(item.getLabColor())) {
            labColor = "#62ABFF";
        }
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_10))
                .build();
        blTvStatus.setBackground(drawable);
        helper.setGone(R.id.tv_time, !TextUtils.isEmpty(item.getCreateDateTime()));
        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getDisposeStatDesc()));
    }

    protected void setInjectDosingData(BaseViewHolder helper, BloodOrdListBean item) {
        //选中
        RecyclerView rvItem = helper.getView(R.id.rv_item);
        if (rvItem != null) {
            int select = ContextCompat.getColor(mContext, R.color.blue_dark2);
            int unselect = ContextCompat.getColor(mContext, R.color.white);
            rvItem.setBackgroundColor(item.getOeoriId().equals(scanInfo) ? select : unselect);
        }
        //配液状态
        helper.setGone(R.id.tv_status, !TextUtils.isEmpty(item.getDesUser()));
        helper.setText(R.id.tv_status, "配");
        if(!TextUtils.isEmpty(item.getAuditUser())){
            helper.setText(R.id.tv_status, "复");
        }
    }

}
