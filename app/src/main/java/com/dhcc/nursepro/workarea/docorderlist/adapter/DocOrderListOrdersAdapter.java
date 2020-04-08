package com.dhcc.nursepro.workarea.docorderlist.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.docorderlist.bean.DocOrderListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.docorderlist.adapter
 * <p>
 * author Q
 * Date: 2018/9/11
 * Time:10:08
 */
public class DocOrderListOrdersAdapter extends BaseQuickAdapter<List<DocOrderListBean.OrdListBean>, BaseViewHolder> {

    private int selectItem;

    public DocOrderListOrdersAdapter(@Nullable List<List<DocOrderListBean.OrdListBean>> data) {
        super(R.layout.item_docorder_orders, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, List<DocOrderListBean.OrdListBean> item) {
        if (item.size() > 0) {
            helper.setText(R.id.tv_item_order_priority, item.get(0).getOrdPriority())
                    .setText(R.id.tv_item_order_start, item.get(0).getOrdstarttime())
                    .setText(R.id.tv_item_order_ordphcin, item.get(0).getPhcinDesc())
                    .setText(R.id.tv_item_order_ordunit, item.get(0).getPhcfrCode())
                    .setText(R.id.tv_item_order_orddoc, item.get(0).getCtcpDesc())
                    .setText(R.id.tv_item_order_stopdate,item.get(0).getStopDate())
                    .setText(R.id.tv_item_order_stoptime,item.get(0).getStopTime())
                    .setText(R.id.tv_item_order_stopdoc,item.get(0).getStopDoctor()+"");
            TextView textView = helper.getView(R.id.tv_item_order_priority);
            TextView tvstop = helper.getView(R.id.tv_item_order_stop);
            LinearLayout llstop = helper.getView(R.id.ll_item_stoporder);
            TextView tvJp = helper.getView(R.id.tv_item_order_jp);
            if (item.get(0).getFilteFlagExtend() != null && item.get(0).getFilteFlagExtend().equals("JP")){
                tvJp.setVisibility(View.VISIBLE);
            }else {
                tvJp.setVisibility(View.GONE);
            }
            if ("长期".equals(item.get(0).getOrdPriority())) {
                textView.setBackgroundResource(R.drawable.bg_priority_long);
            } else {
                textView.setBackgroundResource(R.drawable.bg_priority_short);
            }
            if ("".equals(item.get(0).getStopDoctor())){
                llstop.setVisibility(View.GONE);
                tvstop.setVisibility(View.GONE);
            }else {
                llstop.setVisibility(View.VISIBLE);
                tvstop.setVisibility(View.VISIBLE);
            }

            RecyclerView recyclerView = helper.getView(R.id.rec_item_order_name);
            DocOrderListChildAdapter docOrderListChildAdapter = new DocOrderListChildAdapter(item);
            //提高展示效率
            recyclerView.setHasFixedSize(true);
            //设置的布局管理
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(docOrderListChildAdapter);
            View view = helper.getView(R.id.view_docorders_namedev);
            if (item.size() == 1) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.VISIBLE);
            }

        }

    }
}