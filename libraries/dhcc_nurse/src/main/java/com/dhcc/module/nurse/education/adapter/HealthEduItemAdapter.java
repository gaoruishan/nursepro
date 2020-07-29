package com.dhcc.module.nurse.education.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.education.bean.EduItemListBean;
import com.dhcc.res.infusion.CustomCheckGroupView;
import com.dhcc.res.infusion.CustomRadioGroupView;
import com.dhcc.res.infusion.bean.SheetListBean;

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
            List<SheetListBean> list = item.getSheetList();
            CustomRadioGroupView customCheckGroup = helper.getView(R.id.custom_radio_group);
            customCheckGroup.setGroupData(list);
        }
        if (EduItemListBean.TYPE_1 == item.getItemType()) {
            List<SheetListBean> list = item.getSheetList();
            CustomCheckGroupView customCheckGroup = helper.getView(R.id.custom_check_group);
            //获取 "其他"
            boolean flag = "1".equals(item.getBlankFlag());
            if (flag) {
                SheetListBean bean = new SheetListBean();
                bean.setDesc("其他");
                list.add(bean);
            }
            customCheckGroup.setGroupData(list);
            helper.setGone(R.id.bl_et_other, flag);
            EditText blEtOther = helper.getView(R.id.bl_et_other);
            blEtOther.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    item.setOther(s.toString());
                }
            });
        }

    }


}
