package com.dhcc.nursepro.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.setting.bean.SettingBedListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.setting.adapter
 * <p>
 * author Q
 * Date: 2018/9/14
 * Time:9:43
 */
public class SettingBedsAdapter extends BaseQuickAdapter<SettingBedListBean.BedListBean.GroupListBean, BaseViewHolder> {

    public SettingBedsAdapter(@Nullable List<SettingBedListBean.BedListBean.GroupListBean> data) {
        super(R.layout.item_settingbeds_bed, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingBedListBean.BedListBean.GroupListBean item) {
        LinearLayout llBed = helper.getView(R.id.ll_settingbeds);
        TextView tvBedSelectBed = helper.getView(R.id.tv_settingbeds_bed);
        ImageView imgBedSex = helper.getView(R.id.img_settingbeds_sex);
        if ("0".equals(item.getSelect())) {
            llBed.setSelected(false);
            tvBedSelectBed.setSelected(false);
        } else {
            llBed.setSelected(true);
            tvBedSelectBed.setSelected(true);
        }

        if (item.getPatSex() == null || "".equals(item.getPatSex())) {
            imgBedSex.setVisibility(View.INVISIBLE);
        } else if ("男".equals(item.getPatSex())) {
            imgBedSex.setImageResource(R.drawable.man);
        } else {
            imgBedSex.setImageResource(R.drawable.woman);
        }

        tvBedSelectBed.setText(item.getBedCode());
    }
}
