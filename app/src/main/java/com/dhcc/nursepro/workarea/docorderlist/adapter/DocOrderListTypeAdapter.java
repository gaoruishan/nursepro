package com.dhcc.nursepro.workarea.docorderlist.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.docorderlist.adapter
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:10:09
 */
public class DocOrderListTypeAdapter  extends BaseQuickAdapter<DocOrderListBean.OrdTypeBean, BaseViewHolder> {

    private int selectItem;

    public DocOrderListTypeAdapter(@Nullable List<DocOrderListBean.OrdTypeBean> data) {
        super(R.layout.item_docorder_priority, data);
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    protected void convert(BaseViewHolder helper, DocOrderListBean.OrdTypeBean item) {
        helper.setText(R.id.tv_docorder_priority, item.getDesc());
        TextView textView = helper.getView(R.id.tv_docorder_priority);
        if (selectItem == helper.getAdapterPosition()){
            textView.setSelected(true);
        }else {
            textView.setSelected(false);
        }
    }
}