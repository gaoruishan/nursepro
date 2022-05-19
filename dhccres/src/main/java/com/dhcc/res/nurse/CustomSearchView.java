package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202020/12/9/16:46
 * @email:grs0515@163.com
 */
public class CustomSearchView extends BaseView {
    public CustomSearchView(Context context) {
        this(context,null);
    }

    public CustomSearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_search_view;
    }
}
