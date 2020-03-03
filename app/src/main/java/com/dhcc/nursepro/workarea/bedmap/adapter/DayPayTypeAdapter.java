package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.DayPayListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2019/11/7
 * Time:9:17
 */
public class DayPayTypeAdapter extends BaseQuickAdapter<DayPayListBean.PriceListBean, BaseViewHolder> {

    private DayPayListAdapter dayPayListAdapter;
    private String inHosTime,prePay,balance,totalSum,inFee;
    public DayPayTypeAdapter(@Nullable List<DayPayListBean.PriceListBean> data) {
        super(R.layout.item_daypay_type, data);
    }
    public void setPayInfo(String inHosTime,String prePay,String balance,String totalSum,String inFee){
        this.inHosTime = inHosTime;
        this.prePay = prePay;
        this.balance = balance;
        this.totalSum = totalSum;
        this.inFee = inFee;
    }

    @Override
    protected void convert(BaseViewHolder helper, DayPayListBean.PriceListBean item) {
        helper.setText(R.id.day_pay_type, item.getType())
                .setText(R.id.tv_typepaysum,item.getSubTotal());

        LinearLayout llPayInfo = helper.getView(R.id.day_pay_info);
        if (helper.getAdapterPosition() == 0){
            llPayInfo.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_inhostime,inHosTime)
                    .setText(R.id.tv_prepay,prePay)
                    .setText(R.id.tv_balance,balance)
                    .setText(R.id.tv_payed,totalSum)
                    .setText(R.id.tv_infee,inFee);
        }else {
            llPayInfo.setVisibility(View.GONE);
        }

        RecyclerView recyType = helper.getView(R.id.recy_daypay_type);
        //提高展示效率
        recyType.setHasFixedSize(true);
        //设置的布局管理
        recyType.setLayoutManager(new LinearLayoutManager(mContext));
        dayPayListAdapter = new DayPayListAdapter(new ArrayList<DayPayListBean.PriceListBean.SubPriceListBean>());
        recyType.setAdapter(dayPayListAdapter);
        if (item.getSubPriceList().size()>0){
            dayPayListAdapter.setNewData(item.getSubPriceList());
            dayPayListAdapter.notifyDataSetChanged();
        }

    }
}
