package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurtour.bean.GradeTourListBean;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.nurtour.adapter
 * <p>
 * author Q
 * Date: 2019/4/26
 * Time:8:52
 */
public class BloodTourListAdapter extends BaseQuickAdapter<GradeTourListBean.PatInfoListBean, BaseViewHolder> {

    private Context mContext;
    public BloodTourListAdapter(@Nullable List<GradeTourListBean.PatInfoListBean> data, Context context) {
        super(R.layout.item_tour_nurlist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, GradeTourListBean.PatInfoListBean item) {
        helper
                .setText(R.id.tv_tournurlist_name,item.getName()+"--");
        com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout swipeLabout = helper.getView(R.id.swipe_labout);
//        if (item.getStatus().startsWith("建单")){
//            swipeLabout.setCanLeftSwipe(true);
//        }else {
//            swipeLabout.setCanLeftSwipe(false);
//        }
        FlowLayout flTourDetail = helper.getView(R.id.fl_modeldetail);
        flTourDetail.removeAllViews();
        //统一名称textview
        if (item.getLastTourInfo().getTourDetailList() != null) {
            for (int i = 0; i < item.getLastTourInfo().getTourDetailList().size(); i++) {
                TextView titleTV = new TextView(mContext);
                titleTV.setText(item.getLastTourInfo().getTourDetailList().get(i).getTourDataName() + ": " + item.getLastTourInfo().getTourDetailList().get(0).getTourDataValue());
                LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                titleParams.setMargins(ConvertUtils.dp2px(5), 0, 5, 0);
                titleTV.setLayoutParams(titleParams);
                titleTV.setGravity(Gravity.TOP);
                flTourDetail.addView(titleTV);
            }
        }

        LinearLayout llAccount = helper.getView(R.id.ll_tournurlist_detail);
        ImageView img = helper.getView(R.id.tv_tournurlist_img);
        RelativeLayout rlImg = helper.getView(R.id.rl_img);
        LinearLayout llImg = helper.getView(R.id.messagecontentl2);
        img.setSelected(false);
        llAccount.setVisibility(View.GONE);
        llImg.setOnClickListener(new View.OnClickListener() {
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

    }
}