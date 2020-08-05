package com.dhcc.nursepro.workarea.workareaadapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea
 * <p>
 * author Q
 * Date: 2020/8/5
 * Time:15:39
 */
public class WorkAreaAdapter extends BaseQuickAdapter<HashMap, BaseViewHolder> {

    public WorkAreaAdapter(@Nullable List<HashMap> data) {
        super(R.layout.item_workarea, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap item) {
        TextView tvItem = helper.getView(R.id.tv_workarea);
        ImageView imageView = helper.getView(R.id.icon_workarea);
        tvItem.setText(item.get("desc").toString());
        try{
            int iconPath = (int) item.get("fragicon");
            imageView.setImageResource(iconPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}