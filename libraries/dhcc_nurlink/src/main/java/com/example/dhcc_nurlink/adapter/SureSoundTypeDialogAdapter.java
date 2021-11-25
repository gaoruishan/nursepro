package com.example.dhcc_nurlink.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dhcc_nurlink.R;
import com.example.dhcc_nurlink.bean.BedMapBean;

import java.util.List;

/**
 * com.example.dhcc_nurlink.adapter
 * <p>
 * author Q
 * Date: 2021/1/9
 * Time:10:01
 */
public class SureSoundTypeDialogAdapter extends BaseQuickAdapter<BedMapBean.TypeListBean, BaseViewHolder> {

    public SureSoundTypeDialogAdapter(@Nullable List<BedMapBean.TypeListBean> data) {
        super(R.layout.item_bedmap_sountype, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BedMapBean.TypeListBean item) {

        helper.setText(R.id.tv_type1,item.getVDTDesc())
               ;
        TextView textView = helper.getView(R.id.tv_type1);
        textView.setSelected(item.getSelect()==0?false:true);

    }
}
