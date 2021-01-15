package com.dhcc.nursepro.workarea.pathandover.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;

/**
 * 筛选
 */
public class PatHandoverFilterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int selectPosition = -1;

    public PatHandoverFilterAdapter(@Nullable List<String> data) {
        super(R.layout.dhcc_select_text_view, data);
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.dhcc_tv, item.split(":")[0])
                .addOnClickListener(R.id.dhcc_tv);

        TextView tv = helper.getView(R.id.dhcc_tv);
        if (helper.getLayoutPosition() == selectPosition) {
            tv.setSelected(true);
        } else {
            tv.setSelected(false);
        }
    }
}