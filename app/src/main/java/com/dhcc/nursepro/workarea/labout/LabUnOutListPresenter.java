package com.dhcc.nursepro.workarea.labout;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.base.commlibs.utils.BaseHelper;
import com.base.commlibs.utils.BasePopWindow;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.utils.PopWindowUtil;
import com.dhcc.nursepro.workarea.orderexecute.adapter.OrderExecutePatientOrderAdapter;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202021/6/22/16:08
 * @email:grs0515@163.com
 */
public class LabUnOutListPresenter extends BaseHelper  {
    private View contentView;
    private RecyclerView rv;
    private OrdListAdapter ordListAdapter;

    /**
     * 获取宿主Activity
     * @param activity
     * @return
     */
    public LabUnOutListPresenter(Activity activity) {
        super(activity);
    }

    @Override
    protected void initView(Activity mContext) {
        super.initView(mContext);
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dhcc_ord_list_buttom_layout, null);
        contentView.findViewById(R.id.iv_finish_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopWindowUtil.closePopWindow();
            }
        });
        rv = contentView.findViewById(R.id.rv_ord_list);
        RecyclerViewHelper.setDefaultRecyclerView(mContext,rv,0);
        ordListAdapter = new OrdListAdapter(null);

        rv.setAdapter(ordListAdapter);
    }

    public void setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener) {
        ordListAdapter.setOnLoadMoreListener(requestLoadMoreListener, rv);
    }
    /**
     * 打开PopWindow
     * @param orderExecuteBean
     */
    public void openPopWindow(List<OrderExecuteBean.OrdersBean> orderExecuteBean) {
        ordListAdapter.setNewData(orderExecuteBean);
        PopWindowUtil.setMask(mContext, View.VISIBLE);
        PopWindowUtil.initPopupWindow(mContext, BasePopWindow.EnumLocation.BOTTOM, contentView);
    }

    /**
     * 根列表
     */
    public static class OrdListAdapter extends BaseQuickAdapter<OrderExecuteBean.OrdersBean,BaseViewHolder> {


        public OrdListAdapter( @Nullable List<OrderExecuteBean.OrdersBean> data) {
            super(R.layout.dhcc_ord_list_buttom_layout_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderExecuteBean.OrdersBean item) {
            helper.setText(R.id.tv_name, item.getName()+ "      "+item.getBedCode()+"床");
            RecyclerView rv = helper.getView(R.id.rv_ord_list_child);
            RecyclerViewHelper.setDefaultRecyclerView(mContext,rv,0);
            OrderExecutePatientOrderAdapter patientOrderAdapter = new OrderExecutePatientOrderAdapter(item.getPatOrds());
            patientOrderAdapter.setHideSelect(true);
            rv.setAdapter(patientOrderAdapter);
        }
    }

}
