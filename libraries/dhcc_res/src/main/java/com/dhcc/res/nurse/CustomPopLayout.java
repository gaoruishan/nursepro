package com.dhcc.res.nurse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * 弹框
 * @author:gaoruishan
 * @date:2018/12/19/17:07
 * @email:grs0515@163.com
 */
public class CustomPopLayout extends RelativeLayout implements View.OnTouchListener, View.OnClickListener {

	private View contentView;

	public CustomPopLayout(Context context) {
		this(context,null);
	}

	public CustomPopLayout(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public CustomPopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOnTouchListener(this);
	}

	/**
	 * 设置View布局
	 * @param view
	 * @param anim
	 */
	public void setContentView(View view, int anim) {
		dismissView(VISIBLE);
		contentView = view;
		Animation animationIn = AnimationUtils.loadAnimation(getContext(),anim);
		view.setAnimation(animationIn);
		view.setOnClickListener(this);
		addView(view);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		dismissView(INVISIBLE);
		return true;
	}

	/**
	 * 移除布局
	 * @param invisible
	 */
	public void dismissView(int invisible) {
		removeAllViews();
		setVisibility(invisible);
	}

	@Override
	public void onClick(View v) {

	}
}
