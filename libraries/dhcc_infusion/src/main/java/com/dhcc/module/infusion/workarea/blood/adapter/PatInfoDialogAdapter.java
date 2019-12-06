package com.dhcc.module.infusion.workarea.blood.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.base.commlibs.utils.UserUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.workarea.comm.bean.PatOrdersBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202019-11-16/17:04
 * @email:grs0515@163.com
 */
public class PatInfoDialogAdapter extends BaseQuickAdapter<PatOrdersBean, BaseViewHolder> {

    private String labNo;

    public PatInfoDialogAdapter(@Nullable List<PatOrdersBean> data) {
        super(R.layout.item_dialog_pat_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatOrdersBean item) {
        helper.setText(R.id.tv_popup_info, item.getArcimDesc())
                .setText(R.id.tv_status, item.getExeStatus())
                .setText(R.id.tv_popup_unit, item.getDoseQtyUnit());
        helper.setGone(R.id.tv_status, !TextUtils.isEmpty(item.getExeStatus()));
        //背景颜色
        if (!TextUtils.isEmpty(item.getTubeColorCode()) && UserUtil.isBloodCheckFlag()) {
            helper.setBackgroundColor(R.id.ll_bg, Color.parseColor(item.getTubeColorCode()));
        }
        if (!TextUtils.isEmpty(item.getExeStatus()) && !TextUtils.isEmpty(item.getExeStColor())) {
            Drawable drawable = new DrawableCreator.Builder()
                    .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_5))
                    .setSolidColor(Color.parseColor(item.getExeStColor()))
                    .build();
            helper.getView(R.id.tv_status).setBackground(drawable);
        }
    }
}
