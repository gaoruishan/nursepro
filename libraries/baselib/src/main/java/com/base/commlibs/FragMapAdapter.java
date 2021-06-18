package com.base.commlibs;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;

/**
 * com.dhcc.nursepro.workarea.bedmap.adapter
 * <p>
 * author Q
 * Date: 2020/06/17
 * Time:14:17
 */
public class FragMapAdapter extends BaseQuickAdapter<HashMap, BaseViewHolder> {

    public FragMapAdapter(@Nullable List<HashMap> data) {
        super(R.layout.item_frag_map, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap item) {
        helper.setText(R.id.tv_desc, item.get("desc").toString());
        ImageView view = helper.getView(R.id.img_mapicon);
        int iconPath = (int) item.get("fragicon");
        view.setImageResource(iconPath);

    }
}
