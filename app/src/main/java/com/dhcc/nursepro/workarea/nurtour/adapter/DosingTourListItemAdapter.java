package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.AllTourListBean;
import com.dhcc.nursepro.workarea.nurtour.bean.DosingListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:11:09
 */
public class DosingTourListItemAdapter extends BaseQuickAdapter<DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean, BaseViewHolder> {

    private int size;
    public DosingTourListItemAdapter(@Nullable List<DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean> data,int size) {
        super(R.layout.item_tour_dosinglistitem, data);
        this.size = size;
    }

    @Override
    protected void convert(BaseViewHolder helper, DosingListBean.PatInfoListBean.OrdListBean.OeoreSubListBean item) {
        helper.setText(R.id.tv_tourallitem_type, item.getArcimDesc())
                .setText(R.id.tv_tourallitem_date, item.getDoseQtyUnit())
                .setText(R.id.tv_tourallitem_time, item.getPhOrdQtyUnit());
        TextView textView = helper.getView(R.id.tv_tourallitem_type);
        ImageView img = helper.getView(R.id.tv_tournurlist_imglast);
        img.setSelected(false);
        LinearLayout llAccount = helper.getView(R.id.ll_touramount);
        llAccount.setVisibility(View.GONE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llAccount.getVisibility() == View.VISIBLE){
                    llAccount.setVisibility(View.GONE);
                    img.setSelected(false);
                }  else {
                    llAccount.setVisibility(View.VISIBLE);
                    img.setSelected(true);
                }
            }
        });
//        if (item.getTourTypeCode().equals("Grade")){
//            textView.setBackgroundColor(Color.parseColor("#FF4081"));
//        }else if (item.getTourTypeCode().equals("Infusion")){
//            textView.setBackgroundColor(Color.parseColor("#4C95EF"));
//        }else {
//            textView.setBackgroundColor(Color.parseColor("#FFEA4300"));
//        }
    }
}
