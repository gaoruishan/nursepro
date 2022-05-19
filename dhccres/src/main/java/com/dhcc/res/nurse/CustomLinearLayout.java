package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dhcc.res.util.RecyclerViewHelper;
import com.dhcc.res.util.ViewUtil;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 简单线性布局
 * @author:gaoruishan
 * @date:202019-10-29/15:38
 * @email:grs0515@163.com
 */
public class CustomLinearLayout extends LinearLayout {


    private VitalTitleAdapter titleAdapter;
    private int textColor;
    private boolean special;

    public CustomLinearLayout(Context context) {
        this(context, null);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(ViewUtil.inflate(context, R.layout.custom_linear_layout));
        RecyclerView rvTitle = findViewById(R.id.rv_title);
        RecyclerViewHelper.setDefaultRecyclerView(context, rvTitle, 0, LinearLayoutManager.HORIZONTAL);
        titleAdapter = new VitalTitleAdapter(null);
        rvTitle.setAdapter(titleAdapter);
    }

    public void setItemTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
    }

    /**
     * 设置数据
     * @param titles
     */
    public CustomLinearLayout setNewData(List<String> titles) {
        titleAdapter.setNewData(titles);
        return this;
    }

    public CustomLinearLayout setFirstSpecial(boolean special) {
        this.special = special;
        return this;
    }

    public class VitalTitleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        VitalTitleAdapter(@Nullable List<String> data) {
            super(R.layout.dhcc_item_tv, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int tvId = R.id.tv_name;
            if (special) {
                TextView textView = helper.getView(tvId);
                ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
                int width = layoutParams.width;
                layoutParams.width = helper.getAdapterPosition() == 0 ? width / 2 : width;
                textView.setLayoutParams(layoutParams);
            }
            helper.setText(tvId, item);
            if (textColor != 0) {
                helper.setTextColor(tvId, ContextCompat.getColor(mContext, textColor));
            }
        }
    }
}
