package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.BedMapBean;
import com.example.dhcc_nurlink.bean.CallingHistoryBean;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2021/1/9
 * Time:10:01
 */
public class BedMapAdapter  extends BaseQuickAdapter<BedMapBean.PatListBean, BaseViewHolder> {

    public BedMapAdapter(@Nullable List<BedMapBean.PatListBean> data) {
        super(R.layout.item_bedmap, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.PatListBean item) {
        helper.setText(R.id.tv_pat_name,item.getPatInfo().getName())
                .setText(R.id.tv_pat_bed,item.getPatInfo().getBedCode().replaceAll("床","")+"床")
                .setText(R.id.tv_pat_age,item.getPatInfo().getAge())
                .setText(R.id.tv_pat_sex,item.getPatInfo().getSex());
//                .setImageDrawable(R.id.img_telstatu,item.getStatus().contains("Y")?
//                        mContext.getResources().getDrawable(R.drawable.nurlink_icon_call_out):mContext.getResources().getDrawable(R.drawable.nurlink_icon_call_reject))
//                .addOnClickListener(R.id.tv_tel_call);

    }
}
