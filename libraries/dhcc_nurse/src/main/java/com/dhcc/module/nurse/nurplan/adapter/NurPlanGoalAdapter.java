package com.dhcc.module.nurse.nurplan.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.nurplan.bean.NurPlanGoalBean;

import java.util.List;

/**
 * 计划目标
 * @author:gaoruishan
 * @date:202020-08-20/14:17
 * @email:grs0515@163.com
 */
public class NurPlanGoalAdapter extends BaseQuickAdapter<NurPlanGoalBean.GoalListBean, BaseViewHolder> {

    public NurPlanGoalAdapter(int layoutResId, @Nullable List<NurPlanGoalBean.GoalListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurPlanGoalBean.GoalListBean item) {
        helper.getView(R.id.ll_select).setSelected(item.isSelect());
        helper.setGone(R.id.ll_rightmenu,false);
        helper.addOnClickListener(R.id.ll_select)
                .addOnClickListener(R.id.ll_content)
                .addOnClickListener(R.id.tv_title);
        boolean hasCreate = !TextUtils.isEmpty(item.getCreateAt());
        helper.setText(R.id.tv_title, item.getGoalName())
                .setGone(R.id.ll_content, false)
                .setGone(R.id.bl_tv_status, hasCreate)
                .setText(R.id.bl_tv_status, item.getStatus())
                .setGone(R.id.tv_exeuser, !TextUtils.isEmpty(item.getSign()))
                .setText(R.id.tv_exeuser, item.getSign())
                .setGone(R.id.tv_starttime, hasCreate)
                .setVisible(R.id.ll_select, !hasCreate)
                .setText(R.id.tv_starttime, "创建: " + item.getCreateAt());

    }
}
