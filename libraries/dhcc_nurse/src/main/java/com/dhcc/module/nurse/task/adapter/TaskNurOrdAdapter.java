package com.dhcc.module.nurse.task.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.task.bean.NormalOrdTaskBean;
import com.dhcc.module.nurse.task.bean.NurOrdTaskBean;
import com.noober.background.drawable.DrawableCreator;

import java.util.List;

/**
 * com.dhcc.module.nurse.task.adapter
 * <p>
 * author Q
 * Date: 2020/8/12
 * Time:14:54
 */
public class TaskNurOrdAdapter extends BaseQuickAdapter<NurOrdTaskBean.TaskDataListBean, BaseViewHolder> {

    public TaskNurOrdAdapter(int layoutResId, @Nullable List<NurOrdTaskBean.TaskDataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NurOrdTaskBean.TaskDataListBean item) {
        helper.setText(R.id.tv_patinfo,item.getBedCode()+" "+item.getName()+" "+item.getSex())
                .setText(R.id.tv_type,item.getInterventionTypeName())
                .setText(R.id.tv_2,item.getExecuteItemName())
                .setText(R.id.tv_datetime,item.getInsertDateTime())
                .setText(R.id.tv_orderoperate,item.getFreqName())
                .setText(R.id.bl_tv_status,item.getTkStatusName());

    }
}
