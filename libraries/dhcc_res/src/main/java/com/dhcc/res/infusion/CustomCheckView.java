package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202019-04-27/14:57
 * @email:grs0515@163.com
 */
public class CustomCheckView extends CustomOnOffView {

    public CustomCheckView(Context context) {
        super(context);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_check_view;
    }
}
