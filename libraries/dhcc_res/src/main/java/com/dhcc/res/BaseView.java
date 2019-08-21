package com.dhcc.res;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.grs.dhcc_res.R;

/**
 * 基础类View 的关键生命周期为 [改变可见性] --> 构造View
 * --> onFinishInflate --> onAttachedToWindow
 * --> onMeasure --> onSizeChanged --> onLayout --> onDraw --> onDetackedFromWindow
 * @author:gaoruishan
 * @date:202019-08-20/14:16
 * @email:grs0515@163.com
 */
public abstract class BaseView extends LinearLayout {

    protected String TAG = BaseView.class.getSimpleName();
    protected View view;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TAG = this.getClass().getSimpleName();
        int contentView = setContentView();
        if (contentView != 0) {
            view = View.inflate(context, contentView, null);
            addView(view);
        }
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    /**
     * 重写-添加布局
     * @return
     */
    protected abstract @LayoutRes
    int setContentView();

    /**
     * 布局加载完成
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    /**
     * View销毁时
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /**
     * 可见的view发生变化时触发
     * @param changedView
     * @param visibility
     */
    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    /**
     * 触屏事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        return super.onTouchEvent(event);

    }
}
