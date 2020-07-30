package com.dhcc.module.nurse.education.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduOrdListBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/18:05
 * @email:grs0515@163.com
 */
public class TaskOverviewAdapter extends BaseQuickAdapter<EduOrdListBean.OrdListBean, BaseViewHolder> {


    public TaskOverviewAdapter(int layoutResId, @Nullable List<EduOrdListBean.OrdListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EduOrdListBean.OrdListBean item) {
        setTextStatus(helper, item);
        helper.setText(R.id.tv_content, item.getArcimDesc()+ "")
                .setText(R.id.tv_exetime, item.getOrdDateTime())
                .setText(R.id.tv_exeuser, item.getPhcfrCode()+"  "+item.getOrdCat())
                .setGone(R.id.bl_tv_exe, false);
        LinearLayout llSelect = helper.getView(R.id.ll_select);
        llSelect.setSelected(item.isSelect());
        helper.setVisible(R.id.ll_select, true).addOnClickListener(R.id.ll_select);
    }

    private void setTextStatus(BaseViewHolder helper, EduOrdListBean.OrdListBean item) {
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setMinWidth((int) mContext.getResources().getDimension(R.dimen.dp_40));
        helper.setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getPhcinDesc()));
        String labColor = "#62ABFF";
        blTvStatus.setText(item.getPhcinDesc());
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_20))
                .build();
        blTvStatus.setBackground(drawable);
    }

}
