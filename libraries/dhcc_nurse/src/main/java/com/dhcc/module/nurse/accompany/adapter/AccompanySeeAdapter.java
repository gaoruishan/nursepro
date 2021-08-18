package com.dhcc.module.nurse.accompany.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.bean.AccompanyConfigBean;

import java.util.List;
import java.util.Map;

import static com.dhcc.module.nurse.accompany.fragment.AccompanySeeFragment.INT_No;

/**
 * @author:gaoruishan
 * @date:202021/8/17/10:30
 * @email:grs0515@163.com
 */
public class AccompanySeeAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {

    private List<AccompanyConfigBean> configTEMPList;

    public AccompanySeeAdapter(int layoutResId, @Nullable List<Map> data) {
        super(layoutResId, data);
    }

    public void setConfigTemp(List<AccompanyConfigBean> configTEMPList) {
        this.configTEMPList = configTEMPList;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {
        LinearLayout llSub = helper.getView(R.id.ll_parent);
        //移除所有
        llSub.removeAllViews();
        for (int i = 0; i < configTEMPList.size(); i++) {
            AccompanyConfigBean configBean = configTEMPList.get(i);
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_accompany_sub_sheet, null);
            TextView tvShort = view.findViewById(R.id.tv_info_short);
            TextView tv = view.findViewById(R.id.tv_info);
            //序号
            if (i == 0) {
                tv.setVisibility(View.GONE);
                tvShort.setVisibility(View.VISIBLE);
                tvShort.setText("" + item.get(INT_No));
            } else {
                //大于2个字符
                boolean b = configBean.getTitle().length() > 2;
                tv.setVisibility(b ? View.VISIBLE : View.GONE);
                tvShort.setVisibility(!b ? View.VISIBLE : View.GONE);
                if (!b) {
                    tv = tvShort;
                }
                tv.setText("" + item.get(configBean.getField()));
            }

            llSub.addView(view);
        }
    }
}
