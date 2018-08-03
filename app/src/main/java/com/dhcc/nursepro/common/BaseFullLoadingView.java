package com.dhcc.nursepro.common;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by levis on 2018/6/5.
 */

public class BaseFullLoadingView extends ViewGroup implements View.OnClickListener {
    private View mChildOnly;
    private Rect mChildOnlyRect;
    private boolean isShowing;
    private boolean mNeedShow;
    private Runnable mShowedCallback;
    private Runnable mHidedCallback;
    private boolean mCancelable;
    private boolean mActiveHided;

    public BaseFullLoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOnClickListener(this);
        mChildOnlyRect = new Rect();
    }

    public BaseFullLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseFullLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mChildOnly = getChildAt(0);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (mChildOnly != null) {
            mChildOnly.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
            mChildOnlyRect.set(0, 0, mChildOnly.getMeasuredWidth(), mChildOnly.getMeasuredHeight());
            mChildOnlyRect.offset((width - mChildOnlyRect.width()) / 2,
                    (height - mChildOnlyRect.height()) / 2);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mChildOnly != null) {
            mChildOnly.layout(mChildOnlyRect.left, mChildOnlyRect.top,
                    mChildOnlyRect.right, mChildOnlyRect.bottom);
            if (mNeedShow) {
                show(mShowedCallback);
            }
        }
    }

    /**
     * 显示LoadingTip
     */
    public void show(final Runnable showedCallback) {
        mShowedCallback = showedCallback;
        mNeedShow = mChildOnlyRect.isEmpty();
        setVisibility(INVISIBLE);
        if (mChildOnly != null && !mChildOnlyRect.isEmpty()) {
            isShowing = true;
            final Animation anim = new AlphaAnimation(0, 1);
            anim.setDuration(400);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mNeedShow = false;
                    setVisibility(VISIBLE);
                    if (showedCallback != null) {
                        showedCallback.run();
                    }
                    if (mActiveHided) {
                        mActiveHided = false;
                        hide(mHidedCallback);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            clearAnimation();
            startAnimation(anim);
        }
    }

    /**
     * 隐藏LoadingTip
     */
    public void hide(final Runnable hidedCallback) {
        mActiveHided = true;
        mHidedCallback = hidedCallback;
        if (mChildOnly != null) {
            Animation anim = new AlphaAnimation(1, 0);
            anim.setDuration(250);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    setVisibility(INVISIBLE);
                    if (hidedCallback != null) {
                        hidedCallback.run();
                    }
                    isShowing = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            clearAnimation();
            startAnimation(anim);
        }
    }

    /**
     * 设置点击是否可以关闭
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable, Runnable hidedCallback) {
        mCancelable = cancelable;
        mHidedCallback = hidedCallback;
    }

    /**
     * 判断是否正在显示
     *
     * @return
     */
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void onClick(View v) {
        if (mCancelable) {
            hide(mHidedCallback);
        }
    }
}
