package com.dhcc.nursepro.workarea.plyout.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutListAllBean;

import java.util.List;

public class PlyOutAdapter extends BaseQuickAdapter<PlyOutListAllBean.LabOutListBean, BaseViewHolder>{

    public PlyOutAdapter(@Nullable List<PlyOutListAllBean.LabOutListBean> data) {
        super(R.layout.item_labout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, PlyOutListAllBean.LabOutListBean item) {
        helper.setText(R.id.tv_labout_count,item.getCarryCount())
                .setText(R.id.tv_labout_no,item.getCarryNo())
                .setText(R.id.tv_labout_patname,item.getCarryUser())
                .setText(R.id.tv_labout_time,item.getCarryTime())
                .setText(R.id.tv_labout_statu,item.getStatus())
                .addOnClickListener(R.id.tv_lapack_del)
                .addOnClickListener(R.id.messagecontentll);
        com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout swipeLabout = helper.getView(R.id.swipe_labout);
//        if (item.getStatus().startsWith("建单")){
//            swipeLabout.setCanLeftSwipe(true);
//        }else {
//            swipeLabout.setCanLeftSwipe(false);
//        }
        LinearLayout llAccount = helper.getView(R.id.ll_labout_num);
        if (item.getStatus().equals("建单")){
            llAccount.setBackgroundColor(Color.parseColor("#FF62ABFF"));
        }else if (item.getStatus().equals("已交接")){
            llAccount.setBackgroundColor(Color.parseColor("#FFF5A623"));
        }else if (item.getStatus().equals("部分处理")){
            llAccount.setBackgroundColor(Color.parseColor("#B8DA64"));
        }else{
            llAccount.setBackgroundColor(Color.parseColor("#16C295"));
        }
        //加急
        helper.setGone(R.id.tv_labout_urgent, !TextUtils.isEmpty(item.getUrgentFlag()));
    }
}
