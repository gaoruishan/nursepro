package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SPStaticUtils;
import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * 处理ViewGlobal侧边导航问题
 * @author:gaoruishan
 * @date:202020-07-08/09:18
 * @email:grs0515@163.com
 */
public class CustomGlobalBlock extends BaseView {

    public CustomGlobalBlock(Context context) {
        this(context, null);
    }

    public CustomGlobalBlock(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGlobalBlock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.dhcc_infusion_global_view;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //判断是否开启
        boolean showGlobalView = !TextUtils.isEmpty(SPStaticUtils.getString("GLOBAL_VIEW_FLAG",""));;
        setVisibility(showGlobalView ? INVISIBLE : GONE);
    }
}
