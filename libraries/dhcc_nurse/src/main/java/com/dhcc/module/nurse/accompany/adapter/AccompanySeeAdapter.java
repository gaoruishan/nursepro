package com.dhcc.module.nurse.accompany.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.module.nurse.R;
import com.dhcc.module.nurse.accompany.bean.ConfigSheetBean;

import java.util.List;
import java.util.Map;

import static com.dhcc.module.nurse.accompany.fragment.AccompanySeeFragment.INT_No;

/**
 * @author:gaoruishan
 * @date:202021/8/17/10:30
 * @email:grs0515@163.com
 */
public class AccompanySeeAdapter extends BaseQuickAdapter<Map, BaseViewHolder> {

    private View view;
    private List<ConfigSheetBean> configTEMPList;

    public AccompanySeeAdapter(int layoutResId, @Nullable List<Map> data) {
        super(layoutResId, data);
    }

    public void setConfigTemp(List<ConfigSheetBean> configTEMPList) {
        this.configTEMPList = configTEMPList;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
        Log.e(TAG, "(AccompanySeeAdapter.java:42) " + viewType);
        return baseViewHolder;
    }

    @Override
    protected void convert(BaseViewHolder helper, Map item) {
        helper.addOnClickListener(R.id.ll_parent);
        LinearLayout llSub = helper.getView(R.id.ll_parent);
        //移除所有
        llSub.removeAllViews();
        for (int i = 0; i < configTEMPList.size(); i++) {
            ConfigSheetBean configBean = configTEMPList.get(i);
            View view = mLayoutInflater.inflate(R.layout.item_accompany_sub_sheet, null);
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
                tvShort.setVisibility(b ? View.GONE : View.VISIBLE);
                if (b) {
                    tv.setText("" + item.get(configBean.getField()));
                } else {
                    tvShort.setText("" + item.get(configBean.getField()));
                }
                Log.e(TAG, "(AccompanySeeAdapter.java:60) " + configBean.getField());

            }

            llSub.addView(view);
        }
    }
}
