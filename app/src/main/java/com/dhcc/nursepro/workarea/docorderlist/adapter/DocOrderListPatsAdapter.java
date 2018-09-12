package com.dhcc.nursepro.workarea.docorderlist.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrdersPatsListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.docorderlist.adapter
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:10:08
 */
public class DocOrderListPatsAdapter extends BaseQuickAdapter<DocOrdersPatsListBean.PatInfoListBean, BaseViewHolder> {

    private int selectItem;

    public DocOrderListPatsAdapter(@Nullable List<DocOrdersPatsListBean.PatInfoListBean> data) {
        super(R.layout.item_docorders_pat, data);
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    protected void convert(BaseViewHolder helper, DocOrdersPatsListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_docorder_patinfo, item.getBedCode()+" "+item.getName());
        LinearLayout llPatientType = helper.getView(R.id.ll_docorders_patinfo);
        View viewPatientType = helper.getView(R.id.view_docorders_patinfo);

        if (selectItem == helper.getAdapterPosition()) {
            llPatientType.setSelected(true);
            viewPatientType.setVisibility(View.VISIBLE);

        } else {
            llPatientType.setSelected(false);
            viewPatientType.setVisibility(View.INVISIBLE);
        }

    }
}