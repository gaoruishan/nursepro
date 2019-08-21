package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.base.commlibs.bean.CommBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.List;

/**
 * 筛选(二级适配器)
 */
public class OrdExeFilterChildAdapter extends BaseQuickAdapter<CommBean, BaseViewHolder> {

    private String keyType;

    public OrdExeFilterChildAdapter(@Nullable List<CommBean> data) {
        super(R.layout.dhcc_select_text_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommBean item) {
        helper.setText(R.id.dhcc_tv, item.getName());
        TextView tv = helper.getView(R.id.dhcc_tv);
        tv.setSelected(item.isShow());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Single".equals(keyType)) {
                    for (CommBean b : getData()) {
                        if (!b.getName().equals(item.getName())) {
                            b.setShow(false);
                        }
                    }
                    item.setShow(!item.isShow());
                }
                if ("Multiple".equals(keyType)) {
                    item.setShow(!item.isShow());
                }
                notifyDataSetChanged();
            }
        });
    }

    public void setDataType(String keyType) {
        this.keyType = keyType;
    }
}