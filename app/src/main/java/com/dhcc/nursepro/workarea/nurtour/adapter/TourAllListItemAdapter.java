package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.base.commlibs.constant.Action;
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
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_tourtype_grade));
        }else if (item.getTourTypeCode().equals("Infusion")){
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_tourtype_infusion));
        }else {
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_tourtype_blood));
        }
        LinearLayout linearLayout = helper.getView(R.id.messagerightmenu);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("data", item.getDetailDR());
                bundle.putString("type", item.getTourType());
                Intent tbIntent = new Intent();
                tbIntent.setAction(Action.TOUR_DOSINGID);
                tbIntent.putExtras(bundle);
                mContext.sendBroadcast(tbIntent);
            }
        });

    }
}