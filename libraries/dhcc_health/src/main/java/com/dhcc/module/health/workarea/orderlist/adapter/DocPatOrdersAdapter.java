package com.dhcc.module.health.workarea.orderlist.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatOrdersBean;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.adapter
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:16:11
 */
public class DocPatOrdersAdapter extends BaseQuickAdapter<DocPatOrdersBean.CureInfoListBean, BaseViewHolder> {

        public DocPatOrdersAdapter(@Nullable List<DocPatOrdersBean.CureInfoListBean> data) {
                super(R.layout.health_item_order_list, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, DocPatOrdersBean.CureInfoListBean item) {
                helper.setText(R.id.tv_order_info, item.getArcimDesc())
                        .setText(R.id.tv_pat_info,item.getPatientName())
                        .setText(R.id.tv_pat_status,item.getOrderStatus())
                        .setGone(R.id.ll_oepat_orderselect,false);
//                        .setText(R.id.tv_order_type,item.getDCAOEORIDR())
//                        .setText(R.id.tv_order_amount,item.getAdmType())
//                        .setText(R.id.tv_order_admstatus,item.getAdmVisitStatus());
                LinearLayout llSel = helper.getView(R.id.ll_oepat_orderselect);
                if (item.getSelect().equals("1")){
                        llSel.setSelected(true);
                }else {
                        llSel.setSelected(false);
                }

        }
}