package com.dhcc.module.nurse.nurplan.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.QuestionListBean;
import com.dhcc.res.infusion.CustomSkinTagView;
import com.noober.background.drawable.DrawableCreator;
import com.noober.background.view.BLTextView;

import java.util.List;

/**
 * 问题列表
 * @author:gaoruishan
 * @date:202020-08-17/14:18
 * @email:grs0515@163.com
 */
public class NurPlanAdapter extends BaseQuickAdapter<QuestionListBean, BaseViewHolder> {

    public NurPlanAdapter(int layoutResId, @Nullable List<QuestionListBean> data) {
        super(layoutResId, data);
    }

    /**
     * 显示“相关因素”的表示为评估带出的问题；
     * 未显示“相关因素”的表示为手动添加的问题；
     * 计划到期判断条件：当日为计划到期日，即：计划到期日=开始日期+目标达成天数-1
     * 计划已制定（绿色）、计划未制定（黑色）、计划到期（红色）、未评价（黑色）、触发数据已调整（红色）、已撤销（红色）
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, QuestionListBean item) {

        helper.addOnClickListener(R.id.tv_item_ques_undo)
                .addOnClickListener(R.id.tv_item_ques_stop)
                .addOnClickListener(R.id.ll_content)
                .addOnClickListener(R.id.tv_item_ques_copy);
        //侧滑
        helper.setGone(R.id.tv_item_ques_stop, !"已停止".equals(item.getStatus()));
        helper.setGone(R.id.tv_item_ques_undo, !"已撤销".equals(item.getStatus()));


        String planStaColor = item.getPlanStatus().contains("未") ? "#000000" : "#1cc498";
        String cStaColor = item.getCStatus().contains("未") ? "#000000" : "#1cc498";
        String exprColor = "#FF4500";

        List<QuestionListBean.AssessListBean> assessList = item.getAssessList();
        boolean hasAssess = assessList != null && assessList.size() > 0;
        if (hasAssess) {
            String str = "";
            for (QuestionListBean.AssessListBean bean : assessList) {
                str += bean.getAssessSystemName() + "-" + bean.getAssessContent();
            }
            helper.setText(R.id.tv_content, "相关因素:" + str);
        }
        helper.setGone(R.id.tv_content, hasAssess);
        helper.setText(R.id.tv_ques_name, item.getQueName())
                .setGone(R.id.tv_create_time, !TextUtils.isEmpty(item.getCreateUser()))
                .setText(R.id.tv_create_time, "创建:" + item.getCreateDateTime() + "  " + item.getCreateUser())

                .setGone(R.id.tv_cancel_time, !TextUtils.isEmpty(item.getCancelUser()))
                .setText(R.id.tv_cancel_time, "取消:" + item.getCancelDateTime() + "  " + item.getCancelUser() + "  " + item.getCancelReason())

                .setGone(R.id.tv_stop_time, !TextUtils.isEmpty(item.getStopUser()))
                .setText(R.id.tv_stop_time, "停止:" + item.getStopDateTime() + "  " + item.getStopUser() + "  " + item.getStopReason())

                .setGone(R.id.bl_plan_status, !TextUtils.isEmpty(item.getPlanStatus()))
                .setText(R.id.bl_plan_status, item.getPlanStatus())

                .setGone(R.id.bl_plan_cstatus, !TextUtils.isEmpty(item.getCStatus()))
                .setText(R.id.bl_plan_cstatus, item.getCStatus())

                .setGone(R.id.bl_plan_expr, !TextUtils.isEmpty(item.getExpireFlag()))
                .setText(R.id.bl_plan_expr, item.getExpireFlag())

        ;

        BLTextView tvPlan = helper.getView(R.id.bl_plan_status);
        tvPlan.setBackground(getBgDrawable(planStaColor));
        tvPlan.setTextColor(Color.parseColor(planStaColor));

        BLTextView tvCSta = helper.getView(R.id.bl_plan_cstatus);
        tvCSta.setBackground(getBgDrawable(cStaColor));
        tvCSta.setTextColor(Color.parseColor(cStaColor));

        BLTextView tvExpr = helper.getView(R.id.bl_plan_expr);
        tvExpr.setBackground(getBgDrawable(exprColor));
        tvExpr.setTextColor(Color.parseColor(exprColor));


        CustomSkinTagView customSkinTagView = helper.getView(R.id.custom_skin_tag);
        customSkinTagView.setText(item.getStatus(), R.dimen.dp_14);
        boolean hasColor = !TextUtils.isEmpty(item.getColor()) && item.getColor().contains("#");
        if (hasColor) {
            customSkinTagView.setCircleColor(item.getColor());
            customSkinTagView.setTextColor(item.getColor());
        } else {
            String stColor = item.getStatus().contains("已") ? "#1cc498" : "#888888";
            customSkinTagView.setCircleColor(stColor);
            customSkinTagView.setTextColor(stColor);
        }
        //未停止->评价
        boolean isStop = "未停止".equals(item.getStatus()) && "未评价".equals(item.getCStatus());
        helper.setGone(R.id.ll_select, isStop);
        customSkinTagView.setVisibility(isStop ? View.GONE : View.VISIBLE);
        helper.getView(R.id.ll_select).setSelected(item.isSelect());
        helper.setGone(R.id.ll_select, isStop).addOnClickListener(R.id.ll_select);

    }

    private Drawable getBgDrawable(String planStaColor) {
        Drawable drawable = new DrawableCreator.Builder()
                .setStrokeColor(Color.parseColor(planStaColor))
                .setUnPressedTextColor(Color.parseColor(planStaColor))
                .setUnSelectedTextColor(Color.parseColor(planStaColor))
                .setStrokeWidth(mContext.getResources().getDimension(R.dimen.dp_1))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_6))
                .build();
        return drawable;
    }
}
