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

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/20
 * Time:9:37
 */
public class TourPatslistAdapter extends BaseQuickAdapter<AllTourListBean.PatInfoListBean, BaseViewHolder> {

    private int selectItem;

    public TourPatslistAdapter(@Nullable List<AllTourListBean.PatInfoListBean> data) {
        super(R.layout.item_tour_pat, data);
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    protected void convert(BaseViewHolder helper, AllTourListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_docorder_patinfo, item.getBedCode()+" "+item.getName());
        LinearLayout llPatientType = helper.getView(R.id.ll_tour_patinfo);
        View viewPatientType = helper.getView(R.id.view_docorders_patinfo);
        TextView tvPat = helper.getView(R.id.tv_docorder_patinfo);
//
        if (selectItem == helper.getAdapterPosition()) {
            llPatientType.setSelected(true);
            tvPat.setTextColor(mContext.getResources().getColor(R.color.docorderlist_left_text_selected_color));
            tvPat.setTypeface(Typeface.DEFAULT_BOLD);
            viewPatientType.setVisibility(View.VISIBLE);

        } else {
            llPatientType.setSelected(false);
            tvPat.setTextColor(mContext.getResources().getColor(R.color.docorderlist_left_text_normal_color));
            tvPat.setTypeface(Typeface.DEFAULT);
            viewPatientType.setVisibility(View.INVISIBLE);
        }
//
    }
}