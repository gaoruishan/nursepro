package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.res.util.ViewUtil;
import com.grs.dhcc_res.R;

/**
 * 简单空布局
 * @author:gaoruishan
 * @date:202019-10-29/10:39
 * @email:grs0515@163.com
 */
public class CustomEmptyLayout extends LinearLayout {
    private TextView tvEmpty;
    private View llEmpty;

    public CustomEmptyLayout(Context context) {
        this(context, null);
    }

    public CustomEmptyLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEmptyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(ViewUtil.inflate(context, R.layout.custom_empty_layout));
        setOrientation(VERTICAL);
        setPadding(0, (int) getResources().getDimension(R.dimen.dp_100),0,0);
        llEmpty = findViewById(R.id.ll_empty);
        tvEmpty = findViewById(R.id.tv_empty);
    }

    public CustomEmptyLayout setEmptyText(String s) {
        if (!TextUtils.isEmpty(s)) {
            tvEmpty.setText(s);
        }
        return this;
    }

}
