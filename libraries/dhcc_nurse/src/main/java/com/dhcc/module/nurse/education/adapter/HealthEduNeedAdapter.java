package com.dhcc.module.nurse.education.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduSubjectListBean;
import com.dhcc.module.nurse.education.bean.EduTaskListBean;

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
        helper.setText(R.id.tv_lab_no, item.getSubjectTitle() + "")
                .setText(R.id.tv_content, getTaskContents(item))
                .setText(R.id.tv_exetime, item.getPlanDate())
//                .setText(R.id.tv_exeuser, item.getNurseSign())
                .setGone(R.id.bl_tv_exe, false);
        LinearLayout llSelect = helper.getView(R.id.ll_select);
        llSelect.setSelected(item.isSelect());
        helper.setVisible(R.id.ll_select, true).addOnClickListener(R.id.ll_select);
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

    private String getTaskContents(EduTaskListBean item) {
        String title = "";
        if (item.getEduContents() != null) {
            for (EduTaskListBean.EduContentsBean eduContent : item.getEduContents()) {
                title += eduContent.getTitle() + ", ";
            }
        }
        if(!TextUtils.isEmpty(title)){
            title = title.substring(0, title.length() - 1);
        }
        return title;
    }


    public void setCustomDatas(List<EduTaskListBean> eduTaskList, List<EduSubjectListBean> eduSubjectList) {
        for (EduTaskListBean taskListBean : eduTaskList) {
            String subjectId = taskListBean.getSubjectId();
            String pid = "";
            String title = "";
            for (EduSubjectListBean bean : eduSubjectList) {
                if (bean.getId().equals(subjectId)) {
                    pid = bean.getPid();
                    break;
                }
            }
            for (EduSubjectListBean bean : eduSubjectList) {
                if (pid.equals(bean.getId())) {
                    title = bean.getDesc();
                    break;
                }
            }
            taskListBean.setSubjectTitle(title);
        }
        setNewData(eduTaskList);
    }
}
