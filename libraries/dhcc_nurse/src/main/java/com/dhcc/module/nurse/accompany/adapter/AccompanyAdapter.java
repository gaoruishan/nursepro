package com.dhcc.module.nurse.accompany.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.bean.AccompanyBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/8/16/11:09
 * @email:grs0515@163.com
 */
public class AccompanyAdapter extends BaseQuickAdapter<AccompanyBean.AccompanyListBean, BaseViewHolder> {

    public AccompanyAdapter(int layoutResId, @Nullable List<AccompanyBean.AccompanyListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccompanyBean.AccompanyListBean item) {

        helper.addOnClickListener(R.id.tv_input)
                .addOnClickListener(R.id.tv_see)
                .setText(R.id.tv_patinfo, item.getBedCode() + "    " + item.getNCPAInfo1())
                .setText(R.id.tv_patno, "" + item.getNCPAInfo4())
                .setGone(R.id.tv_idcard, !TextUtils.isEmpty(item.getNCPARIdNo()))
                .setText(R.id.tv_idcard, "身份证号: " + item.getNCPARIdNo());
        int draw = getAccomSexRes(item.getNCPAInfo2());
        helper.setBackgroundRes(R.id.view_sex, draw);
    }

    private int getAccomSexRes(String ncpaInfo2) {
        if (ncpaInfo2.contains("女") || "female".equals(ncpaInfo2)) {
            return R.drawable.dhcc_sex_female;
        }
        return R.drawable.dhcc_sex_male;
    }
}
