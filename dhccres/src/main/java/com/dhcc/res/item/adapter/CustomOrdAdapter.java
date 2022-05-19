package com.dhcc.res.item.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.item.layout.CustomOrdItemLayout;
import com.dhcc.res.item.bean.CustomOrdItem;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 封装自己为一个适配器Item----custom_ord_item_adapter
 */
public class CustomOrdAdapter extends BaseQuickAdapter<CustomOrdItem, BaseViewHolder> {

    private int layoutResId;

    public CustomOrdAdapter(@Nullable List<CustomOrdItem> data) {
        this(R.layout.custom_ord_item_adapter, data);
    }

    public CustomOrdAdapter(int layoutResId, @Nullable List<CustomOrdItem> data) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomOrdItem item) {
        CustomOrdItemLayout ordItemLayout = helper.getView(R.id.custom_ord_item);
        ordItemLayout.setTvTag(item.getTvTag())
                .setTvNote(item.getTvNote())
                .setRecycleList(item.getList());
    }
}
