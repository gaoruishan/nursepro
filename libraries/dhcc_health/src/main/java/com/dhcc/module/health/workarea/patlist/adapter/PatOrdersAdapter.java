package com.dhcc.module.health.workarea.patlist.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.patlist.bean.PatListBean;
import com.dhcc.module.health.workarea.patlist.bean.PatOrdersBean;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.adapter
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:16:11
 */
public class PatOrdersAdapter extends BaseQuickAdapter<PatOrdersBean.CureInfoListBean, BaseViewHolder> {

        public PatOrdersAdapter(@Nullable List<PatOrdersBean.CureInfoListBean> data) {
                super(R.layout.health_item_order_list, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, PatOrdersBean.CureInfoListBean item) {
                helper.setText(R.id.tv_order_info, item.getOrderStatus())
                        .setText(R.id.tv_pat_info,item.getPatientName())
                        .setText(R.id.tv_pat_status,item.getOrderStatusCode())
                        .setText(R.id.tv_order_type,item.getDCAOEORIDR())
                        .setText(R.id.tv_order_amount,item.getAdmType())
                        .setText(R.id.tv_order_admstatus,item.getAdmVisitStatus());
                LinearLayout llSel = helper.getView(R.id.ll_oepat_orderselect);
                if (item.getSelect().equals("1")){
                        llSel.setSelected(true);
                }else {
                        llSel.setSelected(false);
                }

        }
}