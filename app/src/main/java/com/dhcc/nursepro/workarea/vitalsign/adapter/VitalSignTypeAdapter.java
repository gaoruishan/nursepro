package com.dhcc.nursepro.workarea.vitalsign.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsign.bean.VitalSignBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VitalSignTypeAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {

    private List<Integer> filterList = new ArrayList<>();

    public List<Integer> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Integer> filterList) {
        this.filterList = filterList;
    }

    public VitalSignTypeAdapter(@Nullable List<Map> data) {
        super(R.layout.item_vitalsign_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {

        TextView tvVitalSignType = helper.getView(R.id.tv_vitalsign_type);
        tvVitalSignType.setText((String)item.get("desc"));
//        tvVitalSignType.setText(item.getDesc());

        if (isSelectedFilter(helper.getAdapterPosition())){
            tvVitalSignType.setSelected(true);
            tvVitalSignType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_selected_text));
        }else{
            tvVitalSignType.setSelected(false);
            tvVitalSignType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_normal_text));
        }
    }

    /**
     * 过滤项被选中后调用此方法，会判断是否需要加入/移除该过滤项的index
     * @param position
     */
    public void filterClicked(int position){
        boolean bInArray = false;
        for (int i = 0; i < filterList.size(); i ++){
            Integer integer = filterList.get(i);

            if( integer.intValue() == position){
                bInArray = true;
                filterList.remove(integer);
                break;
            }
        }

        if (bInArray == false){
            filterList.add(Integer.valueOf(position));
        }
    }

    public boolean isSelectedFilter(int position){
        for (int i = 0; i < filterList.size(); i ++){
            if (filterList.get(i).intValue() == position){
                return true;
            }
        }
        return false;
    }

}
