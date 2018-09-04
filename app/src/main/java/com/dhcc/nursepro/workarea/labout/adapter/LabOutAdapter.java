package com.dhcc.nursepro.workarea.labout.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;

import java.util.List;

public class LabOutAdapter extends BaseQuickAdapter<LabOutListAllBean.LabOutListBean, BaseViewHolder>{

    public LabOutAdapter(@Nullable List<LabOutListAllBean.LabOutListBean> data) {
        super(R.layout.item_labout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, LabOutListAllBean.LabOutListBean item) {
        helper.setText(R.id.tv_labout_count,item.getCarryCount())
                .setText(R.id.tv_labout_no,item.getCarryNo())
                .setText(R.id.tv_labout_patname,item.getCarryUser())
                .setText(R.id.tv_labout_time,item.getCarryTime())
                .setText(R.id.tv_labout_statu,item.getStatus())
                .addOnClickListener(R.id.tv_lapack_del)
                .addOnClickListener(R.id.messagecontentll);
        com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout swipeLabout = helper.getView(R.id.swipe_labout);
        if (item.getStatus().startsWith("建单")){
            swipeLabout.setCanLeftSwipe(true);
        }else {
            swipeLabout.setCanLeftSwipe(false);
        }
        LinearLayout llAccount = helper.getView(R.id.ll_labout_num);
        if (item.getStatus().equals("建单")){
            llAccount.setBackgroundColor(Color.parseColor("#FFF5A623"));
        }else if (item.getStatus().equals("已交接")){
            llAccount.setBackgroundColor(Color.parseColor("#FFF5A623"));
        }else if (item.getStatus().equals("部分处理")){
            llAccount.setBackgroundColor(Color.parseColor("#FF75D477"));
        }else{
            llAccount.setBackgroundColor(Color.parseColor("#FF62ABFF"));
        }

    }
}