package com.dhcc.module.nurse.education.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduTaskListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-17/18:05
 * @email:grs0515@163.com
 */
public class HealthEduNeedAdapter extends BaseQuickAdapter<EduTaskListBean, BaseViewHolder> {


    public HealthEduNeedAdapter(int layoutResId, @Nullable List<EduTaskListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EduTaskListBean item) {
        setTextStatus(helper, item);
        helper.setText(R.id.tv_lab_no, getTaskListTitle(item) + "")
                .setText(R.id.tv_content, "")
                .setText(R.id.tv_exetime, item.getPlanDate())
//                .setText(R.id.tv_exeuser, item.getNurseSign())
                .setVisible(R.id.bl_tv_exe, false);
        LinearLayout llSelect = helper.getView(R.id.ll_select);
        llSelect.setSelected(item.isSelect());
        helper.setVisible(R.id.ll_select, true).setOnClickListener(R.id.ll_select, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelect(!item.isSelect());
                notifyDataSetChanged();
            }
        });

    }

    private void setTextStatus(BaseViewHolder helper, EduTaskListBean item) {
        TextView blTvStatus = helper.getView(R.id.bl_tv_status);
        blTvStatus.setVisibility(View.GONE);
//        String labColor = "#1CC498";
//        blTvStatus.setText("常规任务");
//        Drawable drawable = new DrawableCreator.Builder()
//                .setSolidColor(Color.parseColor(labColor))
//                .setCornersRadius(mContext.getResources().getDimension(R.dimen.dp_20))
//                .build();
//        blTvStatus.setBackground(drawable);
    }

    private String getTaskListTitle(EduTaskListBean item) {
        String title = "";
        if (item.getEduContents() != null) {
            for (EduTaskListBean.EduContentsBean eduContent : item.getEduContents()) {
//                title += eduContent.getTitle() + ", ";
                title += getEduSubject(eduContent.getTitle(), 0) + ", ";
            }
        }
        return title;
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
        } else {
            return eduSubject;
        }
        return titleList.toString()
                .replace("[", "")
                .replace("]", "");
    }

    private String getTaskListContent(EduTaskListBean item) {
        String title = "";
        if (item.getEduContents() != null) {
            for (EduTaskListBean.EduContentsBean eduContent : item.getEduContents()) {
//                title += eduContent.getTitle() + ", ";
                title += getEduSubject(eduContent.getTitle(), 1) + ", ";
            }
        }
        return title;
    }

}
