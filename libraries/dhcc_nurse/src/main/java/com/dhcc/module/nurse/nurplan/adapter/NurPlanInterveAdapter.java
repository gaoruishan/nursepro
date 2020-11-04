package com.dhcc.module.nurse.nurplan.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.InterveFreqBean;
import com.dhcc.module.nurse.nurplan.bean.NurPlanInterveBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * 计划措施
 * @author:gaoruishan
 * @date:202020-08-20/14:17
 * @email:grs0515@163.com
 */
public class NurPlanInterveAdapter extends BaseQuickAdapter<NurPlanInterveBean.InterventionListBean, BaseViewHolder> {

    public NurPlanInterveAdapter(int layoutResId, @Nullable List<NurPlanInterveBean.InterventionListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurPlanInterveBean.InterventionListBean item) {
        helper.getView(R.id.ll_select).setSelected(item.isSelect());
        helper.addOnClickListener(R.id.ll_select)
                .addOnClickListener(R.id.ll_content)
                .addOnClickListener(R.id.tv_item_ques_undo)
                .addOnClickListener(R.id.tv_item_ques_stop)
                .addOnClickListener(R.id.bl_tv_fre_select)
                .addOnClickListener(R.id.tv_title);

        helper.setText(R.id.tv_title, item.getIntervName())
                .setGone(R.id.tv_exeuser, !TextUtils.isEmpty(item.getSign()))
                .setText(R.id.tv_exeuser, "护士: " + item.getSign())
                .setGone(R.id.tv_createtime, !TextUtils.isEmpty(item.getCreatedA()))
                .setText(R.id.tv_createtime, "创建: " + item.getCreatedA())
                .setGone(R.id.tv_exetime, !TextUtils.isEmpty(item.getExectime()))
                .setText(R.id.tv_exetime, "执行: " + item.getExectime())
                .setGone(R.id.tv_starttime, !TextUtils.isEmpty(item.getStartDatetime()))
                .setText(R.id.tv_starttime, " " + item.getStartDatetime())
                .setGone(R.id.bl_tv_fre_select, !TextUtils.isEmpty(item.getFreqDR()))
                .setGone(R.id.tv_fre, !TextUtils.isEmpty(item.getFreqDR()))
                .setText(R.id.tv_fre, "频率: " + InterveFreqBean.getFreq(item.getFreqDR()))
                .setGone(R.id.tv_soure, !TextUtils.isEmpty(item.getDataSource()))
                .setText(R.id.tv_soure, "来源: " + item.getDataSource())
                .setGone(R.id.bl_tv_status, !TextUtils.isEmpty(item.getStatusName()))
                .setText(R.id.bl_tv_status, item.getStatusName());

        //停止/作废
        boolean commits = item.getStatusName().contains("提交");
        boolean isStop = item.getStatusName().contains("停止");
        boolean isCancel = item.getStatusName().contains("作废");
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        String color = "#4A95EF";
        if (isCancel) {
            color = "#FFD700";
        } else if (isStop) {
            color = "#FF4500";
        }
        blTvStatus.setBackground(getBgDrawable(color));
        helper.setGone(R.id.tv_item_ques_undo, commits)
                .setGone(R.id.tv_item_ques_stop, commits)
                .setVisible(R.id.ll_select, !( isCancel || isStop));

    }

    private Drawable getBgDrawable(String planStaColor) {
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(planStaColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_3))
                .build();
        return drawable;
    }
}
