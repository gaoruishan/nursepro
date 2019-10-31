package com.dhcc.module.infusion.workarea.comm.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.infusion.R;
import com.dhcc.module.infusion.utils.RecyclerViewHelper;
import com.base.commlibs.utils.ViewUtil;
import com.dhcc.module.infusion.workarea.comm.bean.OrdInfoBean;
import com.dhcc.module.infusion.workarea.dosing.adapter.CommQuickAdapter;

import java.util.List;

/**
 * 医嘱详情-流程列表
 * @author:gaoruishan
 * @date:202019-05-07/10:44
 * @email:grs0515@163.com
 */
public class DetailLogAdapter extends CommQuickAdapter<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean, BaseViewHolder> {

    public DetailLogAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdInfoBean.OrdInfoArrBean.OrdWorkListBean item) {
        int position = helper.getAdapterPosition();
        TextView tvStateTag = helper.getView(R.id.tv_state_tag);
        RecyclerView rvItemChild = helper.getView(R.id.rv_item_child);
        RecyclerViewHelper.setDefaultRecyclerView(mContext, rvItemChild, 0, LinearLayoutManager.VERTICAL);
        rvItemChild.setAdapter(new BaseQuickAdapter<OrdInfoBean.OrdInfoArrBean.OrdWorkListBean, BaseViewHolder>(R.layout.item_ord_detail_log_child,item.getChildOrdWorkListBean()) {
            @Override
            protected void convert(BaseViewHolder helper, OrdInfoBean.OrdInfoArrBean.OrdWorkListBean item) {
                helper.setText(R.id.tv_time,item.getDateTime()).setText(R.id.tv_name,item.getWorkUser());
            }
        });
        //配液 #5DC1FD
        if ("Despensing".equals(item.getWorkCode())||
        "AuditDes".equals(item.getWorkCode())) {
            ViewUtil.setBgRadiusColor(tvStateTag, 10, "#5DC1FD");
            tvStateTag.setText(item.getWorkType());
        }
        //穿刺 #F5A623
        if ("Puncture".equals(item.getWorkCode())) {
            ViewUtil.setBgRadiusColor(tvStateTag, 10, "#F5A623");
            tvStateTag.setText(item.getWorkType());
        }
        //巡视 #8DCB45
        if ("Tour".equals(item.getWorkCode())) {
            ViewUtil.setBgRadiusColor(tvStateTag, 10, "#8DCB45");
            tvStateTag.setText(item.getWorkType());
        }
        //拔针 #16C295
        if ("Finish".equals(item.getWorkCode())) {
            ViewUtil.setBgRadiusColor(tvStateTag, 10, "#16C295");
            tvStateTag.setText(item.getWorkType());
        }
        //续液 #FF6EA4
        if ("Change".equals(item.getWorkCode())) {
            ViewUtil.setBgRadiusColor(tvStateTag, 10, "#FF6EA4");
            tvStateTag.setText(item.getWorkType());
        }
    }
}
