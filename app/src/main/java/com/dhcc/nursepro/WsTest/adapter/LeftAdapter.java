package com.dhcc.nursepro.WsTest.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.WsTest.bean.LeftItemBean;

import java.util.List;

/**
 * LeftAdapter
 *
 * @author DevLix126
 * @date 2018/8/8
 */
public class LeftAdapter extends BaseQuickAdapter<LeftItemBean, BaseViewHolder> {

    private int selectedPostion;

    public LeftAdapter(@Nullable List<LeftItemBean> data) {
        super(R.layout.item_left, data);
    }

    public int getSelectedPostion() {
        return selectedPostion;
    }

    public void setSelectedPostion(int selectedPostion) {
        this.selectedPostion = selectedPostion;
    }

    @Override
    protected void convert(BaseViewHolder helper, LeftItemBean item) {
        TextView textView = helper.getView(R.id.itemleft_textview);
        if (selectedPostion == helper.getAdapterPosition()) {
            textView.setSelected(true);
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            textView.setTextColor(Color.parseColor("#FF0000"));
        } else {
            textView.setSelected(false);
            textView.setBackgroundColor(Color.parseColor("#F7F7F7"));
            textView.setTextColor(Color.parseColor("#0000FF"));
        }

        helper.setText(R.id.itemleft_textview, item.getType())
                .addOnClickListener(R.id.itemleft_textview);
    }
}
