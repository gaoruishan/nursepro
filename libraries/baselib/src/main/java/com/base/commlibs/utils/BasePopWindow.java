package com.base.commlibs.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.reflect.Method;

/**
 * @author:gaoruishan
 * @date:202019-08-19/09:20
 * @email:grs0515@163.com
 */
public class BasePopWindow {

    public static final String TAG = BasePopWindow.class.getSimpleName();
    /**
     * 获取是否存在NavigationBar，是否有虚拟按钮
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass);
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    /**
     * 获取屏幕尺寸
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return new Point(display.getWidth(), display.getHeight());
        } else {
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    /**
     * 获取虚拟按钮ActionBar的高度
     * @param activity activity
     * @return ActionBar高度
     */
    public static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity mCtx, float bgAlpha) {
        WindowManager.LayoutParams lp = mCtx.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mCtx.getWindow().setAttributes(lp);
    }

    /**
     * 设置不可取消
     * @param popupWindow2
     */
    public static void setsetOutsideTouchable(final PopupWindow popupWindow2) {
        popupWindow2.setOutsideTouchable(false);
        popupWindow2.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!popupWindow2.isOutsideTouchable()) {
                    View mView = popupWindow2.getContentView();
                    if (null != mView) {
                        mView.dispatchTouchEvent(event);
                    }
                }
                return popupWindow2.isFocusable() && !popupWindow2.isOutsideTouchable();
            }
        });
    }

    /**
     * 菜单弹出方向
     */
    public enum EnumLocation {

        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

    }

}
