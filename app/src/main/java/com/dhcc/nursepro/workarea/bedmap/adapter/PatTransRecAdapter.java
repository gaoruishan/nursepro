package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.BedMapBean;

import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2021/6/24
 * Time:9:44
 */
public class PatTransRecAdapter extends BaseQuickAdapter<BedMapBean.TransRecListBean, BaseViewHolder> {
    public PatTransRecAdapter(@Nullable List<BedMapBean.TransRecListBean> data) {
        super(R.layout.item_patinf_transrec, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.TransRecListBean item) {
        helper.setText(R.id.tv_title, item.getTransDate()+" "+item.getTransTime()+"|"+item.getTransUser())
                .setText(R.id.tv_from, "原:"+item.getTransFrom())
                .setText(R.id.tv_to, "现:"+item.getTransTo())
                .setText(R.id.tv_type, item.getTransType())
                .setTextColor(R.id.tv_title, mContext.getResources().getColor(R.color.bg_green))
                .setTextColor(R.id.tv_type, mContext.getResources().getColor(R.color.bg_green))
                .setGone(R.id.tv_from, !TextUtils.isEmpty(item.getTransFrom()))
        ;

    }
}
