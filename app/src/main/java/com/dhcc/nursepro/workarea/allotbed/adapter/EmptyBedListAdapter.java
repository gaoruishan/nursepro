package com.dhcc.nursepro.workarea.allotbed.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.allotbed.bean.AllotBedInfoBean;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.allotbed.adapter
 * <p>
 * author Q
 * Date: 2018/9/6
 * Time:10:51
 */
public class EmptyBedListAdapter extends BaseQuickAdapter<AllotBedInfoBean.EmptyBedListBean, BaseViewHolder> {

    private int selectItem;
    public EmptyBedListAdapter(@Nullable List<AllotBedInfoBean.EmptyBedListBean> data) {
        super(R.layout.item_bed_allot, data);
    }

    public void setSelectItem(int selectItem){
        this.selectItem = selectItem;
    }

    @Override
    protected void convert(BaseViewHolder helper, AllotBedInfoBean.EmptyBedListBean item) {
        helper.setText(R.id.tv_bed_allot,item.getBedCode()+" ");
        TextView textView = helper.getView(R.id.tv_bed_allot);
        if (selectItem == helper.getAdapterPosition()){
            textView.setSelected(true);
        }else {
            textView.setSelected(false);
        }

    }
}
