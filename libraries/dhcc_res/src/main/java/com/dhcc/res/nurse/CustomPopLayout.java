package com.dhcc.res.nurse;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.res.BaseView;
import com.dhcc.res.infusion.bean.SheetListBean;
import com.grs.dhcc_res.R;

import java.util.List;

/**
 * 弹框
 * @author:gaoruishan
 * @date:2018/12/19/17:07
 * @email:grs0515@163.com
 */
public class CustomPopLayout extends BaseView  {

	private LinearLayout llTypes;
	private TextView tvName;
	private PopupWindow popupWindow;

	public CustomPopLayout(Context context) {
		this(context,null);
	}

	public CustomPopLayout(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public CustomPopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setBackgroundColor(ContextCompat.getColor(context, R.color.transparency));
		tvName = findViewById(R.id.tv_name);
		llTypes = findViewById(R.id.ll_types);
	}

	@Override
	protected int setContentView() {
		return R.layout.custom_pop_layout;
	}

	public CustomPopLayout setPopLayoutData(List<SheetListBean> listBeans,String typeDesc, SimpleCallBack<SheetListBean> callBack) {
		if (listBeans != null && listBeans.size() > 0) {
			llTypes.removeAllViews();
			for (int i = 0; i < listBeans.size(); i++) {
				SheetListBean bean = listBeans.get(i);
				View view = inflate(mContext, R.layout.custom_pop_layout_item, this);
				LinearLayout llpoptype = view.findViewById(R.id.ll_pop_type);
				//默认选中第一个
				llpoptype.setSelected(typeDesc.equals(bean.getDesc()));
				llpoptype.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						llpoptype.setSelected(true);
						dismissPopWindow();
						if (callBack != null) {
							callBack.call(bean, 0);
						}
					}
				});
				TextView tvType = view.findViewById(R.id.tv_handle_type);
				tvType.setText(bean.getDesc());
				llTypes.addView(view);
			}

		}
		return this;
	}

	public PopupWindow showBottom(View parent) {
		dismissPopWindow();
		popupWindow = new PopupWindow(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
		//设置弹出窗体需要软键盘
		popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		//设置模式，和Activity的一样，覆盖，调整大小。
//		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		//解决在EditText中输入把popupwindow布局往上顶的问题
		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		//sdk > 21 解决 标题栏没有办法遮罩的问题
		popupWindow.setClippingEnabled(false);
		//动画效果
		popupWindow.setAnimationStyle(R.style.dhcc_AnimationBottomFade);

		//菜单背景色
		ColorDrawable dw = new ColorDrawable(0x8f000000);
		popupWindow.setBackgroundDrawable(dw);
		//显示位置
		popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

		//关闭事件
		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				dismissPopWindow();
			}
		});
		return popupWindow;
	}

	private void dismissPopWindow() {
		if (popupWindow != null) {
			popupWindow.dismiss();
			popupWindow = null;
		}
	}

	/**
	 * 设置View布局
	 * @param view
	 * @param anim
	 */
	private void setContentView(View view, int anim) {
		dismissView(VISIBLE);
		Animation animationIn = AnimationUtils.loadAnimation(getContext(),anim);
		view.setAnimation(animationIn);
		addView(view);
	}

	/**
	 * 移除布局
	 * @param invisible
	 */
	private void dismissView(int invisible) {
		removeAllViews();
		setVisibility(invisible);
	}

}
