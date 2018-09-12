package com.dhcc.nursepro.workarea.docorderlist.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        if (item.size() > 0){
            helper.setText(R.id.tv_item_order_priority, item.get(0).getOrdPriority())
                    .setText(R.id.tv_item_order_start,item.get(0).getOrdstarttime())
                    .setText(R.id.tv_item_order_end,item.get(0).getOrdstoptime())
                    .setText(R.id.tv_item_order_ordphcin,item.get(0).getPhcinDesc())
                    .setText(R.id.tv_item_order_orddoc,item.get(0).getCtcpDesc())
                    .setText(R.id.tv_item_order_orddose,item.get(0).getOrddose());
            TextView textView = helper.getView(R.id.tv_item_order_priority);
            if (item.get(0).getOrdPriority().equals("长期")){
                textView.setBackgroundResource(R.drawable.bg_priority_long);
            }else {
                textView.setBackgroundResource(R.drawable.bg_priority_short);
            }

            RecyclerView recyclerView = helper.getView(R.id.rec_item_order_name);
            DocOrderListChildAdapter docOrderListChildAdapter = new DocOrderListChildAdapter(item);
            //提高展示效率
            recyclerView.setHasFixedSize(true);
            //设置的布局管理
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(docOrderListChildAdapter);
            View view = helper.getView(R.id.view_docorders_namedev);
            if (item.size() == 1){
                view.setVisibility(View.GONE);
            }else {
                view.setVisibility(View.VISIBLE);
            }

        }

    }
}