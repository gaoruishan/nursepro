package com.dhcc.nursepro.workarea.nurrecordnew.adapter;

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
import com.dhcc.nursepro.workarea.nurrecordnew.bean.CareRecCommListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MPGDListDetailAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {
    Context context;
    private List<CareRecCommListBean.TitleListBean> listTitle = new ArrayList<>();

    public MPGDListDetailAdapter(@Nullable List<Map> data, Context context) {
        super(R.layout.item_mpgdlist_detail, data);
        this.context = context;
    }

    public void setListTitle(List<CareRecCommListBean.TitleListBean> listTitle) {
        this.listTitle = listTitle;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {

        LinearLayout lldatalist = helper.getView(R.id.ll_mpgdlist_datalist);
        lldatalist.removeAllViews();
        int width = ConvertUtils.dp2px(80);
        int height = ConvertUtils.dp2px(60);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        for (int i = 0; i < listTitle.size(); i++) {
            TextView textView = new TextView(context);
            String tvstr = item.get((listTitle.get(i).getTitleCode()) + "") + "";
            textView.setText(tvstr);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(context, R.color.black));
            textView.setTextSize(14);
            lldatalist.addView(textView);
        }

    }
}
