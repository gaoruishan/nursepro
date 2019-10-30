package com.dhcc.res;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by xyl on 2019/2/1.
 * 防止出现内存泄漏
 */
@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText {
    private static Field mParent;

    static {
        try {
            mParent = View.class.getDeclaredField("mParent");
            mParent.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public CustomEditText(Context context) {
        super(context.getApplicationContext());
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        try {
            if (mParent != null) {
                mParent.set(this, null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDetachedFromWindow();
    }
}