package com.dhcc.nursepro.setting.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
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
        TextView tvBedSelectBed = helper.getView(R.id.tv_settingbeds_bed);
        if ("0".equals(item.getSelect())) {
            tvBedSelectBed.setSelected(false);
        } else {
            tvBedSelectBed.setSelected(true);
        }

        tvBedSelectBed.setText(item.getBedCode());
    }
}
