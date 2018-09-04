package com.dhcc.nursepro.workarea.operation.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.operation.bean.OperationBean;

import java.util.List;
import java.util.Map;

public class OperationAdapter extends BaseQuickAdapter<OperationBean.OpListBean, BaseViewHolder>{

    public OperationAdapter(@Nullable List<OperationBean.OpListBean> data) {
        super(R.layout.item_operation, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OperationBean.OpListBean item) {
        helper.setText(R.id.tv_operation_patinfo,item.getPatinfo())
                .setText(R.id.tv_operation_name,item.getOpnName())
                .setText(R.id.tv_operation_doc,"手术医生："+item.getOpDoctor())
                .setText(R.id.tv_operation_state,item.getState())
                .setText(R.id.tv_op_applytime,"申请时间："+item.getApplyTime())
                .setText(R.id.tv_op_room,item.getOpRoom());
        TextView tvstate = helper.getView(R.id.tv_operation_state);
        if (item.getState().equals("安排")){
            tvstate.setTextColor(Color.parseColor("#FF16C295"));
        }else if (item.getState().equals("申请")){
            tvstate.setTextColor(mContext.getResources().getColor(R.color.blue));
        }else {
            tvstate.setTextColor(Color.parseColor("#FF838282"));
        }

    }
}
