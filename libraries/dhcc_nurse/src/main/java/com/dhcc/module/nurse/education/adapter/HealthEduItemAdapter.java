package com.dhcc.module.nurse.education.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduItemListBean;
import com.dhcc.res.infusion.CustomCheckGroupView;
import com.dhcc.res.infusion.CustomRadioGroupView;
import com.dhcc.res.infusion.bean.SheetListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoruishan
 * @date:202020-07-22/15:01
 * @email:grs0515@163.com
 */
public class HealthEduItemAdapter extends BaseMultiItemQuickAdapter<EduItemListBean, BaseViewHolder> {


    public HealthEduItemAdapter(List<EduItemListBean> data) {
        super(data);
        addItemType(EduItemListBean.TYPE_0, R.layout.item_health_education_item_0);
        addItemType(EduItemListBean.TYPE_1, R.layout.item_health_education_item_1);
    }

    @Override
    protected void convert(BaseViewHolder helper, EduItemListBean item) {
        helper.setText(R.id.tv_title, item.getName());
        if (EduItemListBean.TYPE_0 == item.getItemType()) {
            List<SheetListBean> list = getOptionListBeans(item);
            CustomRadioGroupView customCheckGroup = helper.getView(R.id.custom_radio_group);
            customCheckGroup.setGroupData(list);
        }
        if (EduItemListBean.TYPE_1 == item.getItemType()) {
            List<SheetListBean> list = getOptionListBeans(item);
            CustomCheckGroupView customCheckGroup = helper.getView(R.id.custom_check_group);
            boolean flag = "1".equals(item.getBlankFlag());
            if (flag) {
                SheetListBean bean = new SheetListBean();
                bean.setDesc("其他");
                list.add(bean);
            }
            customCheckGroup.setGroupData(list);
            helper.setGone(R.id.bl_et_other, flag);
        }

    }

    private List<SheetListBean> getOptionListBeans(EduItemListBean item) {
        List<SheetListBean> list = new ArrayList<>();
        String options = item.getOptions();
        if (options.contains("/")) {
            String[] split = options.split("/");
            for (String s : split) {
                SheetListBean bean = new SheetListBean();
                bean.setDesc(s);
                list.add(bean);
            }
        }else {
            SheetListBean bean = new SheetListBean();
            bean.setDesc(options);
            list.add(bean);
        }
        return list;
    }
}
