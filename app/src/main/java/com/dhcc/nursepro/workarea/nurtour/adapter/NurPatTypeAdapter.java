package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/23
 * Time:10:19
 */
public class NurPatTypeAdapter extends BaseQuickAdapter<GradeTourListBean.TopFilterBean, BaseViewHolder> {

    private int selectItem;

    public NurPatTypeAdapter(@Nullable List<GradeTourListBean.TopFilterBean> data) {
        super(R.layout.item_tour_pattype, data);
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    protected void convert(BaseViewHolder helper, GradeTourListBean.TopFilterBean item) {
        helper.setText(R.id.tv_docorder_pattype, item.getDesc());
        LinearLayout llPatientType = helper.getView(R.id.ll_tour_pattype);
        View viewPatientType = helper.getView(R.id.view_docorders_pattype);
        TextView tvPat = helper.getView(R.id.tv_docorder_pattype);
//
        if (selectItem == helper.getAdapterPosition()) {
            llPatientType.setSelected(true);
            tvPat.setTextColor(mContext.getResources().getColor(R.color.docorderlist_left_text_selected_color));
            tvPat.setTypeface(Typeface.DEFAULT_BOLD);
            viewPatientType.setVisibility(View.GONE);

        } else {
            llPatientType.setSelected(false);
            tvPat.setTextColor(mContext.getResources().getColor(R.color.docorderlist_left_text_normal_color));
            tvPat.setTypeface(Typeface.DEFAULT);
            viewPatientType.setVisibility(View.GONE);
        }
//
    }
}