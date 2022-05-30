package com.dhcc.res.item.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.item.layout.CustomPatItemLayout;
import com.dhcc.res.item.bean.CustomPatItem;
import com.grs.dhcc_res.R;

import java.util.List;


/**
 * 封装自己为一个适配器Item
 */
public class CustomPatAdapter extends BaseQuickAdapter<CustomPatItem, BaseViewHolder> {

    private int layoutResId;

    public CustomPatAdapter(@Nullable List<CustomPatItem> data) {
        this(R.layout.custom_pat_item_adapter, data);
    }

    public CustomPatAdapter(int layoutResId, @Nullable List<CustomPatItem> data) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomPatItem item) {
        CustomPatItemLayout patItemLayout = helper.getView(R.id.custom_pat_item);
        patItemLayout.setBedno(item.getTvBedno())
                .setName(item.getTvName())
                .setAge(item.getTvAge())
                .setImgSex(item.getImgSex())
                .setCarelevel(item.getTvCarelevel());
        View line = helper.getView(R.id.line_other);
//            line.setVisibility(layoutResId != 0 ? View.VISIBLE : GONE);
    }
}
