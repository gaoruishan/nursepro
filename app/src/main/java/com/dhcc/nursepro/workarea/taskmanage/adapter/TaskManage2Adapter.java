package com.dhcc.nursepro.workarea.taskmanage.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.taskmanage.bean.SheetPatListBean;
import com.dhcc.nursepro.workarea.taskmanage.bean.TaskManageBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-05-12/15:56
 * @email:grs0515@163.com
 */
public class TaskManage2Adapter extends BaseQuickAdapter<SheetPatListBean, BaseViewHolder> {

    private int itemType;

    public TaskManage2Adapter(@Nullable List<SheetPatListBean> data, int itemType) {
        super(R.layout.item_task_manage_3,data);
        this.itemType = itemType;
    }

    @Override
    protected void convert(BaseViewHolder helper, SheetPatListBean bean) {
        helper.setGone(R.id.ll_type_1,false).setGone(R.id.ll_type_2,false);
        if (TaskManageBean.TYPE_1 == itemType) {
            helper.setImageResource(R.id.img_sex, "M".equals(bean.getPatSex()) ? R.drawable.man : R.drawable.sex_female);
            helper.setGone(R.id.ll_type_1, true).setText(R.id.tv_bed1, bean.getBedCode()+"床").setText(R.id.tv_name1, bean.getPatName())
                    .setText(R.id.tv_regno1, "登记号:" + bean.getPatRegNo()).setText(R.id.tv_num1, "数量: " + bean.getSheetPatOrdNum());
        }
        if (TaskManageBean.TYPE_2 == itemType) {
            helper.setGone(R.id.ll_type_2, true).setText(R.id.tv_type2,"类别: "+ bean.getSheetDesc()).setText(R.id.tv_num2, "数量: " + bean.getSheetPatOrdNum());
        }
    }
}
