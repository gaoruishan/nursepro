package com.dhcc.module.health.workarea.vitalsign.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.health.R;
import com.dhcc.res.nurse.CustomLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设置标题
 * @author:gaoruishan
 * @date:202019-10-29/15:01
 * @email:grs0515@163.com
 */
public class VitalListAdapter extends BaseQuickAdapter<List<String>, BaseViewHolder> {

    public VitalListAdapter(@Nullable List<List<String>> data) {
        super(R.layout.health_item_vital, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, List<String> item) {
        CustomLinearLayout customLinear = helper.getView(R.id.custom_linear);
        customLinear.setItemTextColor(R.color.black);
        customLinear.setFirstSpecial(true).setNewData(item);
    }

    public void setCustomData(List<String> codes, List<Map<String, Object>> newList) {
        List<List<String>> lists = new ArrayList<>();
        int index = 1;
        for (Map<String, Object> objectMap : newList) {
            List<String> nameList = new ArrayList<>();
            nameList.add(""+index);
            index++;//添加序号
            for (String code : codes) {
                Object o = objectMap.get(code);
                String str = "";
                if (o instanceof String) {
                    str += (String) o;
                }
                nameList.add(str);
            }
            lists.add(nameList);
        }
        setNewData(lists);
    }
}
