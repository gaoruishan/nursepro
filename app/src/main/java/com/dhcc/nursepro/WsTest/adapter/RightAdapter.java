package com.dhcc.nursepro.WsTest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.WsTest.bean.RightItemBean;

import java.util.List;

/**
 * RightAdapter
 *
 * @author DevLix126
 * @date 2018/8/8
 */
public class RightAdapter extends BaseQuickAdapter<RightItemBean, BaseViewHolder> {
    public RightAdapter(@Nullable List<RightItemBean> data) {
        super(R.layout.item_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RightItemBean item) {
        helper.setText(R.id.itemright_textview, item.getDetail())
                .addOnClickListener(R.id.itemright_textview);
    }
}
