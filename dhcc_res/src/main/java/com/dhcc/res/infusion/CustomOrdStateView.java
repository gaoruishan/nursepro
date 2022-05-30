package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grs.dhcc_res.R;


/**
 * 医嘱状态
 * @author:gaoruishan
 * @date:202019-05-05/09:08
 * @email:grs0515@163.com
 */
public class CustomOrdStateView extends LinearLayout {

    private ImageView ivIcon;
    private TextView tvName;
    private LinearLayout llParent;
    private CountView cvState;

    public CustomOrdStateView(Context context) {
        this(context, null);
    }

    public CustomOrdStateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOrdStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_ord_state_view, null);
        addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        llParent = findViewById(R.id.ll_parent);
        ivIcon = findViewById(R.id.iv_icon);
        tvName = findViewById(R.id.tv_name);
        cvState = findViewById(R.id.cv_state);
    }
    public void stop() {
        cvState.stop();
    }
    public CustomOrdStateView setCountStart(Long l) {
        if (l > 0) {
            cvState.setVisibility(VISIBLE);
            cvState.getTitleName().setVisibility(VISIBLE);
            cvState.getCountUnit().setVisibility(VISIBLE);
            cvState.start(l,CountView.ONE_MIN);
        }
        return this;
    }
    public CustomOrdStateView setBgColor(@ColorRes int id) {
        if (id != 0) {
            llParent.setBackgroundColor(ContextCompat.getColor(getContext(), id));
        }
        return this;
    }
    public CustomOrdStateView setImageIcon(@DrawableRes int resId) {
        if (resId != 0) {
            ivIcon.setImageResource(resId);
        }
        return this;
    }

    public CustomOrdStateView setTextColor(@ColorRes int id) {
        if (id != 0) {
            tvName.setTextColor(ContextCompat.getColor(getContext(), id));
        }
        return this;
    }
    public CustomOrdStateView setTextName(String s) {
        if(!TextUtils.isEmpty(s)){
            tvName.setText(s);
        }
        return this;
    }

}
