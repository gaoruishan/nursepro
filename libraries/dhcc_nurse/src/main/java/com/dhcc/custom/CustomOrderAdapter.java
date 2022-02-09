package com.dhcc.custom;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.custom.bean.CustomListData;
import com.dhcc.module.nurse.R;
import com.dhcc.res.custom.bean.ListViewBean;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:202022/2/8/17:16
 * @email:grs0515@163.com
 */
public class CustomOrderAdapter extends BaseQuickAdapter<CustomListData.OrdListBean, BaseViewHolder> {


    private ListViewBean.ListBean.ContentBean config;

    public CustomOrderAdapter(@Nullable List<CustomListData.OrdListBean> data) {
        super(R.layout.custom_order_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomListData.OrdListBean item) {

        if ("order".equals(config.getType())) {
            String code = config.getLine1Content().getCode();
            String code2 = config.getLine2Content().getCode();
            Log.e(TAG,"(.java:33) "+item.getMap().toString());
            helper.setText(R.id.bl_tv_status, item.getMap().get(code)+"");
            helper.setText(R.id.tv_content, item.getMap().get(code2)+"");
            String arcimDesc = "";
            for (CustomListData.OrdListBean.ArcimDescListBean arcimDescListBean : item.getArcimDescList()) {
                arcimDesc += arcimDescListBean.ArcimDesc + "\n\n";
            }
            helper.setText(R.id.tv_title, arcimDesc);
        }
    }

    public void setListConfig(ListViewBean.ListBean config) {
        this.config = config.getContent();
    }
}


