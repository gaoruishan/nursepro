package com.dhcc.module.infusion.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dhcc.module.infusion.R;


/**
 * 图片选择器
 * @author:gaoruishan
 * @date:2018/8/20/14:05
 * @email:grs0515@163.com
 */
public class SelectorImageView extends android.support.v7.widget.AppCompatImageView {

	private boolean isCheck;
	//drawableRes
	private int unselectedImage, selectedImage;
	private Context mContext;

	public SelectorImageView(Context context) {
		this(context, null);
	}

	public SelectorImageView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SelectorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SelectorImageView);
		selectedImage = attributes.getResourceId(R.styleable.SelectorImageView_selectedImage, 0);
		unselectedImage = attributes.getResourceId(R.styleable.SelectorImageView_unselectedImage, 0);
		isCheck = attributes.getBoolean(R.styleable.SelectorImageView_isCheck, false);
		setImageResource(isCheck ? selectedImage : unselectedImage);
	}

	public void toggle(boolean isSelect) {
		isCheck = isSelect;
		setImageResource(isCheck ? selectedImage : unselectedImage);
	}

	/**
	 * 选择监听
	 * @param l
	 */
	public void setOnSelectorListener(@Nullable final OnClickListener l) {
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
				if (l != null) {
					l.onClick(v);
				}
			}
		});

	}


	/**
	 * 切换
	 */
	public void toggle() {
		isCheck = !isCheck;
		setImageResource(isCheck ? selectedImage : unselectedImage);
	}

	/**
	 * 是否选中
	 * @return
	 */
	public boolean isCheck() {
		return isCheck;
	}
}
