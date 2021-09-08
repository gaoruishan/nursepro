package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.bedmap.bean.PdaArcListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2020/9/29
 * Time:10:19
 */
public class AfterPayAdapter extends BaseQuickAdapter<PdaArcListBean.ArcItemListBean, BaseViewHolder> {
    private Boolean isAdd = true;

    public void setAdd(Boolean add) {
        isAdd = add;
    }

    public AfterPayAdapter(@Nullable List<PdaArcListBean.ArcItemListBean> data) {
        super(R.layout.item_afterpay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PdaArcListBean.ArcItemListBean item) {
        helper.setGone(R.id.ll_message_rightmenu,isAdd);
        helper.setText(R.id.tv_ord_name, item.getItemDesc())
                .setText(R.id.tv_ord_no,(helper.getAdapterPosition()+1)+"")
                .setText(R.id.tv_ord_num,item.getItemNum()+"")
                .addOnClickListener(R.id.ll_message_rightmenu);
        try{
            Double d1 = Double.parseDouble(item.getItemPrice()+"");
            int i1 = Integer.parseInt(item.getItemNum()+"");
            helper.setText(R.id.tv_ord_price,d1*i1+"");
        }catch (Exception e){}


    }
}