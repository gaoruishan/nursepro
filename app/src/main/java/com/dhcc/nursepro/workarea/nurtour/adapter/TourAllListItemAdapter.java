package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
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
 * Date: 2019/4/23
 * Time:14:31
 */
public class TourAllListItemAdapter  extends BaseQuickAdapter<AllTourListBean.TourDataListBeanX.TourDataListBean, BaseViewHolder> {

    public TourAllListItemAdapter(@Nullable List<AllTourListBean.TourDataListBeanX.TourDataListBean> data) {
        super(R.layout.item_touralllistitem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllTourListBean.TourDataListBeanX.TourDataListBean item) {
        helper.setText(R.id.tv_tourallitem_type, item.getTourTypeDesc())
                .setText(R.id.tv_tourallitem_date, item.getDHCNurTourDate())
                .setText(R.id.tv_tourallitem_time, item.getDHCNurTourTime())
                .setText(R.id.tv_tourallitem_nurse, item.getDHCNurTourUser());
        TextView textView = helper.getView(R.id.tv_tourallitem_type);
        if (item.getTourTypeCode().equals("Grade")){
            textView.setBackgroundColor(Color.parseColor("#FF4081"));
        }else if (item.getTourTypeCode().equals("Infusion")){
            textView.setBackgroundColor(Color.parseColor("#4C95EF"));
        }else {
            textView.setBackgroundColor(Color.parseColor("#FFEA4300"));
        }
    }
}