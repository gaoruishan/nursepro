package com.dhcc.nursepro.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.base.commlibs.utils.BasePopWindow;
import com.dhcc.nursepro.R;

/**
 * 简单的popWindow
 * @作者:gaoruishan
 * @时间:2018/8/14/17:22
 * @邮箱:grs0515@163.com
 */
public class PopWindowUtil extends BasePopWindow {

    //width的屏幕占比
    public static final double SCREEN_PRESENT = 0.75;
    private static PopupWindow popupWindow;

    public static PopupWindow initPopupWindow(Activity mContext, EnumLocation from, @NonNull View popupWindowView) {
        popupWindow = getPopupWindow(mContext, from, popupWindowView);
        return popupWindow;
    }

    @NonNull
    private static PopupWindow getPopupWindow(final Activity mContext, final EnumLocation from, View popupWindowView) {
//        boolean hasNavigationBar = checkDeviceHasNavigationBar(mContext);
        boolean hasNavigationBar = false;
        //销毁
        closePopWindow();
        //内容，高度，宽度
        if (EnumLocation.BOTTOM == from) {
            popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        } else {
            popupWindow = new PopupWindow(popupWindowView
                    , (int) (getScreenSize(mContext).x * SCREEN_PRESENT)
                    , ViewGroup.LayoutParams.MATCH_PARENT
                    , true);
        }
        //虚拟键-多出一点
        popupWindowView.setPadding(0, 0, 0, hasNavigationBar ? getActionBarHeight(mContext) - 22 : 0);
        //设置弹出窗体需要软键盘
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        //设置模式，和Activity的一样，覆盖，调整大小。
//		popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //解决在EditText中输入把popupwindow布局往上顶的问题
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //sdk > 21 解决 标题栏没有办法遮罩的问题
        popupWindow.setClippingEnabled(false);
        //动画效果
        if (EnumLocation.LEFT == from) {
            popupWindow.setAnimationStyle(R.style.dhcc_AnimationLeftFade);
        } else if (EnumLocation.RIGHT == from) {
            popupWindow.setAnimationStyle(R.style.dhcc_AnimationRightFade);
        } else if (EnumLocation.BOTTOM == from) {
            popupWindow.setAnimationStyle(R.style.dhcc_AnimationBottomFade);
        }
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(0xff000000);
        popupWindow.setBackgroundDrawable(dw);
        //显示位置
        if (EnumLocation.LEFT == from) {
            //显示在界面0,0位置上
            popupWindow.showAtLocation(mContext.getWindow().getDecorView(), Gravity.LEFT, 0, 0);
        } else if (EnumLocation.RIGHT == from) {
            popupWindow.showAtLocation(mContext.getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
//			popupWindow.showAtLocation(mContext.getLayoutInflater().inflate(showAtLocation, null), Gravity.RIGHT, 0, 0);
        } else if (EnumLocation.BOTTOM == from) {
            popupWindow.showAtLocation(mContext.getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        //设置背景半透明
//        backgroundAlpha(mContext, 0.5f);
        //关闭事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setMask(mContext, View.INVISIBLE);
                backgroundAlpha(mContext, 1f);
            }
        });

        popupWindowView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
				/*if( popupWindow!=null && popupWindow.isShowing()){
					popupWindow.dismiss();
					popupWindow=null;
				}*/
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        return popupWindow;
    }


    public static void setMask(Activity mContext, int invisible) {
        View mask = mContext.findViewById(R.id.mask);
        if (mask != null) {
            mask.setVisibility(invisible);
        }
    }


    public static void closePopWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
