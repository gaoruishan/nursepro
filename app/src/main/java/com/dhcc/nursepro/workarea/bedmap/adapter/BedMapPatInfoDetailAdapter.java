package com.dhcc.nursepro.workarea.bedmap.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2018/10/12
 * Time:15:12
 */
public class BedMapPatInfoDetailAdapter extends BaseQuickAdapter<HashMap<String,String>, BaseViewHolder> {
    public BedMapPatInfoDetailAdapter(@Nullable List<HashMap<String,String>> data) {
        super(R.layout.item_patinfodetail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String,String> item) {
        helper.setText(R.id.patinfodetail_title, item.get("title"))
                .setText(R.id.patinfodetail_content, item.get("content"));
        View view1 = helper.getView(R.id.view_patdetail_device);
        View view2 = helper.getView(R.id.view_patdetail_bluedevice);
        int i = (helper.getAdapterPosition()+1)%4;
        if (i == 0){
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
        }else {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.GONE);
        }

    }
}
