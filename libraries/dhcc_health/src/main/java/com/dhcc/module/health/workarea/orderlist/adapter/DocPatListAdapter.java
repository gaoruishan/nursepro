package com.dhcc.module.health.workarea.orderlist.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatListBean;

import java.util.List;

/**
 * com.dhcc.module.health.workarea.patlist.adapter
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:16:11
 */
public class DocPatListAdapter extends BaseQuickAdapter<DocPatListBean.PatInfoListBean, BaseViewHolder> {
    public DocPatListAdapter(@Nullable List<DocPatListBean.PatInfoListBean> data) {
        super(R.layout.health_item_pat_list, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DocPatListBean.PatInfoListBean item) {
        helper.setText(R.id.tv_pat_info, item.getBedCode()+"-"+item.getName())
                .setGone(R.id.img_pat_select,false)
                .setText(R.id.tv_pat_admdr,item.getMrNumGet());

        ImageView imgSel = helper.getView(R.id.img_pat_select);
        if (item.getSelect().equals("1")){
            imgSel.setSelected(true);
        }else {
            imgSel.setSelected(false);
        }
    }
}