package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202020-03-06/16:06
 * @email:grs0515@163.com
 */
public class CustomStatusView extends BaseView {

    private  TextView tv_status;
    private  TextView tv_user;
    private  TextView tv_time;

    public CustomStatusView(Context context) {
        this(context,null);
    }

    public CustomStatusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparency));
        tv_status = findViewById(R.id.tv_status);
        tv_user = findViewById(R.id.tv_user);
        tv_time = findViewById(R.id.tv_time);
    }

    public CustomStatusView setStatus(String s) {
        setText(tv_status, s);
        return this;
    }

    public CustomStatusView setUser(String s) {
        setText(tv_user, s);
        return this;
    }

    public CustomStatusView setTime(String s) {
        setText(tv_time, s);
        return this;
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_status_view;
    }
}
