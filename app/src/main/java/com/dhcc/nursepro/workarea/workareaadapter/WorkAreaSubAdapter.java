package com.dhcc.nursepro.workarea.workareaadapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.base.commlibs.utils.HttpUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.List;


public class WorkAreaSubAdapter extends BaseQuickAdapter<MainConfigBean.MainListBean.MainSubListBean, BaseViewHolder> {

    public WorkAreaSubAdapter(@Nullable List<MainConfigBean.MainListBean.MainSubListBean> data) {
        super(R.layout.item_workarea, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainConfigBean.MainListBean.MainSubListBean item) {

        helper.setText(R.id.tv_workarea, item.getModuleDesc());
        if (item.getImgResouseId() > 0) {
            helper.setImageResource(R.id.icon_workarea, item.getImgResouseId());
        }
        if (!TextUtils.isEmpty(item.getModuleIcon())) {
           ImageView imageView = helper.getView(R.id.icon_workarea);
            HttpUtil.bindImageView(imageView, item.getModuleIcon());
        }
    }
}