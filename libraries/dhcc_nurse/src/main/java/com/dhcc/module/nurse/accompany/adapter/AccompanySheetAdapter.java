package com.dhcc.module.nurse.accompany.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/8/17/10:30
 * @email:grs0515@163.com
 */
public class AccompanySheetAdapter extends BaseQuickAdapter<AccompanyConfigBean, BaseViewHolder> {


    public AccompanySheetAdapter(int layoutResId, @Nullable List<AccompanyConfigBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccompanyConfigBean item) {
        String title = item.getTitle();
        //大于2个字符
        boolean b = title.length() > 2;
        int id = b ? R.id.tv_info : R.id.tv_info_short;
        helper.setGone(R.id.tv_info, b).setGone(R.id.tv_info_short, !b);
        helper.setText(id, title)
                .setTextColor(id, mContext.getResources().getColor(R.color.white))
                .setBackgroundColor(id, mContext.getResources().getColor(R.color.blue));
    }


}
