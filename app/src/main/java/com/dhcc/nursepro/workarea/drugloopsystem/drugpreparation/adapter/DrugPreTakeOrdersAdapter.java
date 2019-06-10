package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.bean.GetTakeOrdListBean;

import java.util.List;

public class DrugPreTakeOrdersAdapter extends BaseQuickAdapter<GetTakeOrdListBean.OrdListBean, BaseViewHolder> {
    private RecyclerView recyDrugpreTakeorders;
    private DrugPreTakeOrderChildAdapter orderChildAdapter;

    private LinearLayout llDrugpreTakeordersTake;
    private TextView tvDrugpreTakeordersTime;
    private TextView tvDrugpreTakeordersName;
    private LinearLayout llDrugpreTakeordersReview;
    private TextView tvDrugpreTakeordersReviewtime;
    private TextView tvDrugpreTakeordersReviewname;

    private TextView tvDrugpreStatus;


    public DrugPreTakeOrdersAdapter(@Nullable List<GetTakeOrdListBean.OrdListBean> data) {
        super(R.layout.item_drugpre_takeorders, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetTakeOrdListBean.OrdListBean item) {

        recyDrugpreTakeorders = helper.getView(R.id.recy_drugpre_takeorders);
        recyDrugpreTakeorders.setHasFixedSize(true);
        recyDrugpreTakeorders.setLayoutManager(new LinearLayoutManager(mContext));
        orderChildAdapter = new DrugPreTakeOrderChildAdapter(item.getOeoreGroup());
        recyDrugpreTakeorders.setAdapter(orderChildAdapter);

        llDrugpreTakeordersTake = helper.getView(R.id.ll_drugpre_takeorders_take);
        tvDrugpreTakeordersTime = helper.getView(R.id.tv_drugpre_takeorders_time);
        tvDrugpreTakeordersName = helper.getView(R.id.tv_drugpre_takeorders_name);
        llDrugpreTakeordersReview = helper.getView(R.id.ll_drugpre_takeorders_review);
        tvDrugpreTakeordersReviewtime = helper.getView(R.id.tv_drugpre_takeorders_reviewtime);
        tvDrugpreTakeordersReviewname = helper.getView(R.id.tv_drugpre_takeorders_reviewname);
        tvDrugpreStatus = helper.getView(R.id.tv_drugpre_status);


        tvDrugpreTakeordersTime.setText(item.getTakeDateTime());
        tvDrugpreTakeordersName.setText(item.getTakeUser());

        if (StringUtils.isEmpty(item.getTakeAuditDateTime())) {
            llDrugpreTakeordersReview.setVisibility(View.GONE);
            tvDrugpreStatus.setText("已取药");
            tvDrugpreStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_drugpre_1));
        } else {
            llDrugpreTakeordersReview.setVisibility(View.VISIBLE);
            tvDrugpreStatus.setText("已复核");
            tvDrugpreStatus.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_drugpre_2));
            tvDrugpreTakeordersReviewtime.setText(item.getTakeAuditDateTime());
            tvDrugpreTakeordersReviewname.setText(item.getTakeAuditUser());
        }

    }
}
