package com.dhcc.module.health.workarea.patlist.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.patlist.bean.PatListBean;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.adapter
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:16:11
 */
public class PatListAdapter extends BaseQuickAdapter<PatListBean.PatInfoListBean, BaseViewHolder> {
    public PatListAdapter(@Nullable List<PatListBean.PatInfoListBean> data) {
        super(R.layout.health_item_pat_list, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, PatListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_pat_info, item.getBedCode()+"-"+item.getName());

        ImageView imgSel = helper.getView(R.id.img_pat_select);
        if (item.getSelect().equals("1")){
            imgSel.setSelected(true);
        }else {
            imgSel.setSelected(false);
        }
    }
}