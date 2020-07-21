package com.dhcc.module.nurse.education.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EducationListBean;
import com.dhcc.module.nurse.education.bean.HealthEduBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/18:05
 * @email:grs0515@163.com
 */
public class HealthEduAdapter extends BaseQuickAdapter<EducationListBean.DataBean, BaseViewHolder> {


    public HealthEduAdapter(int layoutResId, @Nullable List<EducationListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EducationListBean.DataBean item) {

        setTextStatus(helper, item);

        if (HealthEduBean.TYPE_1 == item.getItemType()) {
            String eduSubject = item.getEduSubject();
            helper.setText(R.id.tv_lab_no, getEduSubject(eduSubject, 0) + "")
                    .setText(R.id.tv_content, getEduSubject(eduSubject, 1) + "")
                    .setText(R.id.tv_exetime, item.getEduDateTime())
                    .setText(R.id.tv_exeuser, item.getNurseSign())
                    .setVisible(R.id.bl_tv_exe, true);
        } else {
        }
    }

    private void setTextStatus(BaseViewHolder helper, EducationListBean.DataBean item) {
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        String labColor = "#1CC498";
        if (HealthEduBean.TYPE_1 == item.getItemType()) {
            blTvStatus.setText("已执行");
            labColor = "#CCCCCC";
        } else {
            blTvStatus.setText("常规任务");
        }
        Drawable drawable = new DrawableCreator.Builder()
                .setSolidColor(Color.parseColor(labColor))
                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_20))
                .build();
        blTvStatus.setBackground(drawable);
    }

    private String getEduSubject(String eduSubject, int i) {
        List<String> titleList = new ArrayList<>();
        if (eduSubject.contains("、")) {
            String[] split = eduSubject.split("、");
            for (String s : split) {
                if (s.contains("-")) {
                    String[] split2 = s.split("-");
                    if (!titleList.contains(split2[i])) {
                        titleList.add(split2[i]);
                    }
                }
            }
        } else if (eduSubject.contains("-")) {
            String[] split2 = eduSubject.split("-");
            if (!titleList.contains(split2[i])) {
                titleList.add(split2[i]);
            }
        }else {
            return eduSubject;
        }
        return titleList.toString()
                .replace("[", "")
                .replace("]", "");
    }

    public void setEduDatas(List<EducationListBean.DataBean> data, int type) {
    }
}
