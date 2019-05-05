package com.dhcc.nursepro.workarea.nurtour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Date: 2019/4/22
 * Time:15:20
 */
public class NurTourListAdapter extends BaseQuickAdapter<GradeTourListBean.PatInfoListBean, BaseViewHolder> {

    private Context mContext;
    public NurTourListAdapter(@Nullable List<GradeTourListBean.PatInfoListBean> data, Context context) {
        super(R.layout.item_tour_nurlist, data);
        mContext = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, GradeTourListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_tournurlist_bedcode,item.getBedCode())
                .setText(R.id.tv_tournurlist_name,item.getName())
                .setText(R.id.tv_tourall_nurse1,item.getLastTourInfo().getTourTypeDesc())
                .setText(R.id.tv_tournurlist_date,item.getLastTourInfo().getDHCNurTourDate())
                .setText(R.id.tv_tournurlist_time,item.getLastTourInfo().getDHCNurTourTime())
                .setText(R.id.tv_tourall_nurse2,item.getLastTourInfo().getDHCNurTourUser());
        com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout swipeLabout = helper.getView(R.id.swipe_labout);

        ImageView imgsex = helper.getView(R.id.img_tour_sex);
        if (item.getSex().equals("男")){
            imgsex.setImageDrawable(mContext.getResources().getDrawable(R.drawable.sex_male));
        }else {
            imgsex.setImageDrawable(mContext.getResources().getDrawable(R.drawable.sex_female));
        }


        FlowLayout flTourDetail = helper.getView(R.id.fl_modeldetail);
        flTourDetail.removeAllViews();
        //统一名称textview
        if (item.getLastTourInfo().getTourDetailList() != null) {
            for (int i = 0; i < item.getLastTourInfo().getTourDetailList().size(); i++) {
                TextView titleTV = new TextView(mContext);
                titleTV.setText(item.getLastTourInfo().getTourDetailList().get(i).getTourDataName() + ": " + item.getLastTourInfo().getTourDetailList().get(0).getTourDataValue());
                LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                if (i ==1){
                    titleParams.setMargins(ConvertUtils.dp2px(8), 0, 5, 0);
                }else {
                    titleParams.setMargins(ConvertUtils.dp2px(15), 0, 5, 0);
                }
                titleTV.setLayoutParams(titleParams);
                titleTV.setGravity(Gravity.TOP);
                flTourDetail.addView(titleTV);
            }
        }

        LinearLayout llAccount = helper.getView(R.id.ll_tournurlist_detail);
        ImageView img = helper.getView(R.id.tv_tournurlist_img);
        img.setSelected(false);
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

    }
}