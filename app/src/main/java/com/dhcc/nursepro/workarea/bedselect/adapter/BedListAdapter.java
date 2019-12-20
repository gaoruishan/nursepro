package com.dhcc.nursepro.workarea.bedselect.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.List;

/**
 * BedListAdapter
 *
 * @author DevLix126
 * @date 2018/8/30
 */
public class BedListAdapter extends BaseQuickAdapter<BedSelectListBean.BedListBean.GroupListBean, BaseViewHolder> {

    public BedListAdapter(@Nullable List<BedSelectListBean.BedListBean.GroupListBean> data) {
        super(R.layout.item_bedselect_bed, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedSelectListBean.BedListBean.GroupListBean item) {

        LinearLayout llBedSelect = helper.getView(R.id.ll_bedselect);
        TextView tvBedSelectBed = helper.getView(R.id.tv_bedselect_bed);
        ImageView imgBedSex = helper.getView(R.id.img_bedselect_sex);
        if ("0".equals(item.getSelect())) {
            llBedSelect.setSelected(false);
            tvBedSelectBed.setSelected(false);
            if ("男".equals(item.getPatSex())) {
                imgBedSex.setVisibility(View.VISIBLE);
                imgBedSex.setImageResource(R.drawable.man);
            } else if ("女".equals(item.getPatSex()))  {
                imgBedSex.setVisibility(View.VISIBLE);
                imgBedSex.setImageResource(R.drawable.woman);
            }else {
                imgBedSex.setVisibility(View.INVISIBLE);
            }
        } else {
            llBedSelect.setSelected(true);
            tvBedSelectBed.setSelected(true);
            if ("男".equals(item.getPatSex())) {
                imgBedSex.setVisibility(View.VISIBLE);
                imgBedSex.setImageResource(R.drawable.man);
            } else if ("女".equals(item.getPatSex()))  {
                imgBedSex.setVisibility(View.VISIBLE);
                imgBedSex.setImageResource(R.drawable.woman);
            }else {
                imgBedSex.setVisibility(View.INVISIBLE);
            }
        }



        tvBedSelectBed.setText(item.getBedCode());
    }
}
