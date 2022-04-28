package com.dhcc.module.nurse.custom;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.res.custom.bean.ListViewBean;

import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202022/2/8/17:16
 * @email:grs0515@163.com
 */
public class CustomOrderAdapter extends BaseQuickAdapter<Map<String, Object>, BaseViewHolder> {


    private ListViewBean.ListBean.ContentBean config;

    public CustomOrderAdapter(@Nullable List<Map<String, Object>> data) {
        super(R.layout.custom_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, Object> item) {
        //医嘱类
        if ("order".equals(config.getType())) {
            String code1 = config.getLine1Content().getCode();
            String code2 = config.getLine2Content().getCode();
            String code3 = config.getLine3Content().getCode();
            Log.e(TAG, "(.java:33) " + item.toString());
            helper.setText(R.id.bl_tv_status, item.get(code1) + "");
            helper.setText(R.id.tv_content, item.get(code3) + "");
            Object arcimDesc = "";
            //list类型 -分割
            if (code2.contains("-")) {
                arcimDesc = getArcimDesc(item, code2, arcimDesc);
            } else {
                arcimDesc = item.get(code2);
            }

            helper.setText(R.id.tv_title, arcimDesc + "");
        }
    }

    private Object getArcimDesc(Map<String, Object> item, String code2, Object arcimDesc) {
        String[] split = code2.split("-");
        Object obj = item.get(split[0]);
        String key = split[1];
        if (obj instanceof List) {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                if (o instanceof Map) {
                    Map<String, Object> mp = (Map<String, Object>) o;
                    if (list.size() - 1 > i) {
                        arcimDesc += mp.get(key) + "\n\n";
                    } else {
                        arcimDesc += mp.get(key) + "";
                    }
                }
            }
        }
        return arcimDesc;
    }

    public void setListConfig(ListViewBean.ListBean config) {
        this.config = config.getContent();
    }
}


