package com.dhcc.res;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.grs.dhcc_res.R;
import com.noober.background.drawable.DrawableCreator;

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
    protected SimpleCallBack callBack;

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
            view = inflate(context, contentView,this);
            addView(view);
        }
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }
    /**
     * PatName : lh041101
     * PatRegNo : 0000000435
     * patSex : 女
     */
    public static int getPatSexDrawable(String patSex) {
        if ("男".equals(patSex)) {
            return R.drawable.dhcc_sex_male;
        } else if ("女".equals(patSex)) {
            return R.drawable.dhcc_sex_female;
        } else {
            return R.drawable.dhcc_sex_male;
        }
    }

    /**
     * 设置图片
     * @param imageView
     * @param resId
     */
    public void setImageResource(ImageView imageView, @DrawableRes int resId) {
        if (imageView != null && resId != 0) {
            imageView.setImageResource(resId);
        }
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
    public void setTextColor(TextView[] views, @ColorRes int color) {
        if (views != null) {
            for (TextView textView : views) {
                setTextColor(textView, color);
            }
        }
    }
    /**
     * 设置背景颜色
     * @param view
     * @param color
     */
    public void setTextColor(TextView view, @ColorRes int color) {
        if (view != null && color != 0) {
            view.setTextColor(ContextCompat.getColor(mContext, color));
            view.setHintTextColor(ContextCompat.getColor(mContext, color));
        }
    }
    public void setSelectDrawable(View view,@DrawableRes int selectId, @DrawableRes int unselectId) {
        Drawable selectedDrawable = ContextCompat.getDrawable(mContext, selectId);
        Drawable unSelectedDrawable = ContextCompat.getDrawable(mContext, unselectId);
        Drawable drawable = new DrawableCreator.Builder()
                .setSelectedDrawable(selectedDrawable)
                .setUnSelectedDrawable(unSelectedDrawable)
                .build();
        view.setBackground(drawable);
    }
    /**
     * 加载布局
     * @param context
     * @param layoutRes
     * @return
     */
    public static View inflate(Context context, @LayoutRes int layoutRes, @Nullable ViewGroup root) {
        return LayoutInflater.from(context).inflate(layoutRes,root,false);
    }

    @SafeVarargs
    protected final <T extends View> T inflate(@LayoutRes int layoutRes, @Nullable ViewGroup root, Class<T>... t) {
        return (T) LayoutInflater.from(mContext).inflate(layoutRes,root,false);
    }

    protected  View inflate(Context context,@LayoutRes int layoutRes,int width, int height, float... weight) {
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
    public int getDimenPixel(@DimenRes int  dimen) {
        return (int) getResources().getDimensionPixelSize(dimen);
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
    protected BaseView setText(TextView view, String s) {
        if (!TextUtils.isEmpty(s)) {
            view.setVisibility(VISIBLE);
            view.setText(s);
        }
        return this;
    }

    protected String getText(TextView tv) {
        String s = tv.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            return s;
        }
        return "";
    }

    public void setCustomCallBackListener(SimpleCallBack callBack) {
        this.callBack = callBack;
    }
}
