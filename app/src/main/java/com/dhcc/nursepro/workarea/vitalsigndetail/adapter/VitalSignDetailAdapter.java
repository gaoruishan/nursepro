package com.dhcc.nursepro.workarea.vitalsigndetail.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.vitalsigndetail.bean.VitalSignDetailBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VitalSignDetailAdapter extends BaseQuickAdapter <Map,BaseViewHolder>{
    Context context;
    private List<VitalSignDetailBean.TempConfigBean> listTitle =new ArrayList<>();

    public VitalSignDetailAdapter(@Nullable List<Map> data,Context context) {
        super(R.layout.item_vital_detail,data);
        this.context = context;
    }
    public void setListTitle(List<VitalSignDetailBean.TempConfigBean> listTitle){
        this.listTitle = listTitle;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {
        helper.setText(R.id.tv_detail_code,helper.getAdapterPosition()+1+"")
                .setText(R.id.tv_detail_date,"".equals(item.get("date").toString()) ? " " : item.get("date").toString())
                .setText(R.id.tv_detail_time,"".equals(item.get("time").toString()) ? " " : item.get("time").toString());

//        Map<String, String> map = new HashMap<String, String>();
//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(item);
//        map = (Map<String, String>) gson.fromJson(jsonStr, map.getClass());

        LinearLayout lldatalist = helper.getView(R.id.ll_vital_datalist);
        lldatalist.removeAllViews();
        int height = ConvertUtils.dp2px(60);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height, height);

        for (int i = 0; i < listTitle.size(); i++) {
            TextView textView = new TextView(context);
            String tvstr = item.get((listTitle.get(i).getCode())+"")+"";
            textView.setText(tvstr);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(context,R.color.black));
            textView.setTextSize(14);
            lldatalist.addView(textView);
        }

    }
}
