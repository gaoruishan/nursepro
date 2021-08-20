package com.dhcc.module.nurse.outmanage.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.outmanage.bean.OutManageBean;
import com.dhcc.res.infusion.CustomSkinTagView;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/8/16/11:09
 * @email:grs0515@163.com
 */
public class OutManageAdapter extends BaseQuickAdapter<OutManageBean.PatInfoListBean, BaseViewHolder> {

    public OutManageAdapter(int layoutResId, @Nullable List<OutManageBean.PatInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OutManageBean.PatInfoListBean item) {

        helper.addOnClickListener(R.id.tv_input)
                .addOnClickListener(R.id.tv_see)
                .addOnClickListener(R.id.rl_parent)
                .setText(R.id.tv_patinfo, item.getBedCode() + "    " + item.getName())
                .setText(R.id.tv_age, "" + item.getAge())
                .setText(R.id.tv_patno, "" + item.getCareLevel());
        int draw = getAccomSexRes(item.getSex());
        helper.setBackgroundRes(R.id.view_sex, draw);

        //外出
        CustomSkinTagView customSkinTagView = helper.getView(R.id.custom_skin_tag);
        boolean flag = "1".equals(item.getOutManageFlag());
        customSkinTagView.setVisibility(flag ? View.VISIBLE : View.GONE);
        helper.setGone(R.id.tv_datetime,flag); //有外出的 显示
        if (flag) {
            customSkinTagView.setText("外出", "外出", CustomSkinTagView.yangColor);
            helper.setGone(R.id.tv_datetime, !TextUtils.isEmpty(item.getOutDateTime()))
                    .setText(R.id.tv_datetime, "外出时间: "+item.getOutDateTime());
        }
    }

    private int getAccomSexRes(String ncpaInfo2) {
        if (ncpaInfo2.contains("女") || "female".equals(ncpaInfo2)) {
            return R.drawable.dhcc_sex_female;
        }
        return R.drawable.dhcc_sex_male;
    }
}
