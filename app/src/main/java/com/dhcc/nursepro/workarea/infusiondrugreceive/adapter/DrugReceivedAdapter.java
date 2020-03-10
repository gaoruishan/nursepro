package com.dhcc.nursepro.workarea.infusiondrugreceive.adapter;

import android.support.annotation.Nullable;

import com.base.commlibs.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;

import java.util.List;

/**
 * com.dhcc.nursepro.workarea.infusiondrugreceive.adapter
 * <p>
 * author Q
 * Date: 2020/3/10
 * Time:9:04
 */
public class DrugReceivedAdapter extends BaseQuickAdapter<LabOutListAllBean.LabOutListBean, BaseViewHolder> {

    public DrugReceivedAdapter(@Nullable List<LabOutListAllBean.LabOutListBean> data) {
        super(R.layout.item_drugreceived, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, LabOutListAllBean.LabOutListBean item) {

    }
}