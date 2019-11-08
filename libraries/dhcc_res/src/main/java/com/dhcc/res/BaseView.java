package com.dhcc.res;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    protected Context mContext;
    protected String TAG = BaseView.class.getSimpleName();
    protected View view;

    public BaseView(Context context) {
        this(context, null);
    }

    /**
     * 当使用xml初始化view时,其他构造方法基本都是用new关键字使用
     * @param context
     * @param attrs
     */
    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TAG = this.getClass().getSimpleName();
        int contentView = setContentView();
        if (contentView != 0) {
            view = inflate(context, contentView);
            addView(view);
        }
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }
    /**
     * 设置背景颜色
     * @param view
     * @param color
     */
    public void setBackGroundColor(View view, @ColorRes int color) {
        if (view != null && color != 0) {
            view.setBackgroundColor(ContextCompat.getColor(mContext, color));
        }
    }

    /**
     * 加载布局
     * @param context
     * @param layoutRes
     * @return
     */
    public  View inflate(Context context,@LayoutRes int layoutRes) {
        View view = View.inflate(context,layoutRes, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParams);
        return view;
    }
    public  View inflate(Context context,@LayoutRes int layoutRes,int width, int height, float... weight) {
        View view = View.inflate(context,layoutRes, null);
        LinearLayout.LayoutParams layoutParams;
        if (weight != null && weight.length > 0) {
            layoutParams = new LinearLayout.LayoutParams(width, height, 1);
        }else {
            layoutParams = new LinearLayout.LayoutParams(width, height);
        }
        view.setLayoutParams(layoutParams);
        return view;
    }

    /**
     * 获取dp
     * @param dimen
     * @return
     */
    public int getDimen(@DimenRes int  dimen) {
        return (int) getResources().getDimension(dimen);
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
