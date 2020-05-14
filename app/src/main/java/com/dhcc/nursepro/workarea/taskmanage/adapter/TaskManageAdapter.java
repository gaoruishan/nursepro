package com.dhcc.nursepro.workarea.taskmanage.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-12/11:43
 * @email:grs0515@163.com
 */
public class TaskManageAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public TaskManageAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TaskManageBean.TYPE_1, R.layout.item_task_manage_1);
        addItemType(TaskManageBean.TYPE_2, R.layout.item_task_manage_2);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if (TaskManageBean.TYPE_1 == item.getItemType()) {
            TaskManageBean.TaskPatListBean bean = (TaskManageBean.TaskPatListBean) item;
            helper.setText(R.id.tv_bed, bean.getBedCode()).setText(R.id.tv_name, bean.getPatName())
                    .setText(R.id.tv_regno, "登记号:" + bean.getPatRegNo()).setText(R.id.tv_num, "数量: " + bean.getSheetOrdNum());
        }
        if (TaskManageBean.TYPE_2 == item.getItemType()) {
            TaskManageBean.TaskSheetListBean bean = (TaskManageBean.TaskSheetListBean) item;
            helper.setText(R.id.tv_type,"类别: "+ bean.getSheetDesc()).setText(R.id.tv_num, "数量: " + bean.getSheetOrdNum());
        }
    }
}