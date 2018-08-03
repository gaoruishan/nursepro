package com.dhcc.nursepro.common;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by levis on 2018/6/5.
 */

public class BaseTopLoadingView extends ViewGroup {
    private View mChildOnly;
    private Rect mChildOnlyRect;
    private boolean isShowing;
    private boolean mNeedShow;
    private Runnable mShowedCallback;
    private Runnable mHidedCallback;
    private boolean mActiveHided;

    public BaseTopLoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mChildOnlyRect = new Rect();
    }

    public BaseTopLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTopLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int height = dp2px(48);

        if (mChildOnly != null) {
            mChildOnly.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
            mChildOnlyRect.set(0, 0, mChildOnly.getMeasuredWidth(), mChildOnly.getMeasuredHeight());
            mChildOnlyRect.offset((width - mChildOnlyRect.width()) / 2, 0);
        }

        setMeasuredDimension(width, height);
    }

    public int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
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
            final Animation anim = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);
            anim.setDuration(250);
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
            Animation anim = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);
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
     * 判断是否正在显示
     *
     * @return
     */
    public boolean isShowing() {
        return isShowing;
    }
}

