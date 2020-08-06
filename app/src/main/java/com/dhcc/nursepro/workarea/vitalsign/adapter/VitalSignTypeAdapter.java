package com.dhcc.nursepro.workarea.vitalsign.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VitalSignTypeAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {

    private List<Integer> filterList = new ArrayList<>();
    private Boolean ifNumChange=true;

    public void setIfNumChange(Boolean ifNumChange) {
        this.ifNumChange = ifNumChange;
    }

    public VitalSignTypeAdapter(@Nullable List<Map> data) {
        super(R.layout.item_vitalsign_type, data);
    }

    public List<Integer> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Integer> filterList) {
        this.filterList = filterList;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {
        if (ifNumChange){
            helper.setText(R.id.tv_num,item.get("temNum") !=null? item.get("temNum")+"":"0");
            if (item.get("temNum")==null || item.get("temNum").equals("0")){
                helper.setVisible(R.id.tv_num,false);
            }else {
                helper.setVisible(R.id.tv_num,true);
            }
        }

        TextView tvVitalSignType = helper.getView(R.id.tv_vitalsign_type);
        tvVitalSignType.setText((String) item.get("desc"));
        //        tvVitalSignType.setText(item.getDesc());

        if (isSelectedFilter(helper.getAdapterPosition())) {
            tvVitalSignType.setSelected(true);
            tvVitalSignType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_selected_text));
//            tvVitalSignType.setTypeface(Typeface.DEFAULT_BOLD);
            tvVitalSignType.setGravity(Gravity.CENTER);
        } else {
            tvVitalSignType.setSelected(false);
            tvVitalSignType.setTextColor(mContext.getResources().getColor(R.color.vital_sign_type_normal_text));
//            tvVitalSignType.setTypeface(Typeface.DEFAULT);
            tvVitalSignType.setGravity(Gravity.LEFT);
        }

    }

    public boolean isSelectedFilter(int position) {
        for (int i = 0; i < filterList.size(); i++) {
            if (filterList.get(i).intValue() == position) {
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤项被选中后调用此方法，会判断是否需要加入/移除该过滤项的index
     *
     * @param position
     */
    public void filterClicked(int position) {
        boolean bInArray = false;
        for (int i = 0; i < filterList.size(); i++) {
            Integer integer = filterList.get(i);

            if (integer.intValue() == position) {
                bInArray = true;
                filterList.remove(integer);
                break;
            }
        }

        if (bInArray == false) {
            filterList.add(Integer.valueOf(position));
        }
    }

    public void removeClick(int position){
        filterList.remove(position);
    }

}
