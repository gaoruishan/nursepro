package com.dhcc.nursepro.workarea.nurrecordnew.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.nursepro.R;
import com.dhcc.nursepro.workarea.nurrecordnew.bean.VitalSignDataBean;

import java.util.List;

public class VSDataAdapter extends BaseQuickAdapter<List<VitalSignDataBean>, BaseViewHolder> {
    private int selectNum = -1;

    public VSDataAdapter(@Nullable List<List<VitalSignDataBean>> data) {
        super(R.layout.item_nurrecord_vsdata, data);
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<VitalSignDataBean> item) {
        LinearLayout lldatalist = helper.getView(R.id.ll_vsdata);
        lldatalist.removeAllViews();
        int width = ConvertUtils.dp2px(80);
        int height = ConvertUtils.dp2px(50);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        for (int i = 0; i < item.size(); i++) {
            TextView textView = new TextView(mContext);
            String tvstr = item.get(i).getValue() + "";
            textView.setText(tvstr);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
            textView.setTextSize(14);
            lldatalist.addView(textView);
        }
        if (helper.getLayoutPosition() == selectNum) {
            lldatalist.setBackgroundColor(mContext.getResources().getColor(R.color.blue_light));
        }else{
            lldatalist.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
}
