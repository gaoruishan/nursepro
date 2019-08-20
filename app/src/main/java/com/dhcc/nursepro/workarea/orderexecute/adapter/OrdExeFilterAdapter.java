package com.dhcc.nursepro.workarea.orderexecute.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.base.commlibs.bean.CommBean;
import com.base.commlibs.utils.RecyclerViewHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.workareabean.MainConfigBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 筛选(一级适配器)
 */
public class OrdExeFilterAdapter extends BaseQuickAdapter<MainConfigBean.ScreenPartsBean, BaseViewHolder> {

    public OrdExeFilterAdapter(@Nullable List<MainConfigBean.ScreenPartsBean> data) {
        super(R.layout.dhcc_recycler_filter_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainConfigBean.ScreenPartsBean item) {
        RecyclerView recyclerView = helper.getView(R.id.dhcc_rv);
        helper.setText(R.id.tv_title,item.getKeyDesc());
        RecyclerViewHelper.setGridRecyclerView(mContext, recyclerView, 3, 0, false);
        if (!TextUtils.isEmpty(item.getKeyValue())) {
            List<String> values = new ArrayList<>();
            if (item.getKeyValue().contains("!")) {
                String[] split = item.getKeyValue().split("!");
                values = Arrays.asList(split);
            } else {
                values.add(item.getKeyValue());
            }
            List<CommBean> list = new ArrayList<>();
            for (String s : values) {
                list.add(new CommBean(s));
            }
            //添加到Item中,用于保存数据
            item.setListBean(list);
            OrdExeFilterChildAdapter adapter = new OrdExeFilterChildAdapter(item.getListBean());
            adapter.setDataType(item.getKeyType());
            recyclerView.setAdapter(adapter);
        }
    }
}
