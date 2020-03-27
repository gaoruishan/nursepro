package com.base.commlibs.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 帮助类的基础
 */
public class BaseHelper {
    //String类型的
    public static final int TAG_STR = 10000;
    //int类型的
    public static final int TAG_INT = 10001;
    //对象类型的
    public static final int TAG_OBJ = 10002;
    protected Activity mContext;
    private SparseArray<View> mViews;
    protected static final String TAG = "BaseHelper";

    /**
     * 获取宿主Activity
     * @return
     */
    public BaseHelper(Activity activity) {
        this.mContext = activity;
        mViews = new SparseArray<View>();
        initView(mContext);
    }

    /**
     * 初始化View
     * @param mContext
     */
    protected void initView(Activity mContext) {

    }

    /**
     * 查找View
     * @param resId View的id
     * @param t     指定View类类型
     * @return
     */
    public static <T extends View> T f(Activity mContext, @IdRes int resId, Class<T> t) {
        return (T) mContext.findViewById(resId);
    }

    /**
     * 清空保存View
     */
    public void removeView() {
        if (mViews != null) {
            mViews.clear();
        }
    }

    /**
     * 对象是否为数组对象
     * @param obj 对象
     * @return 是否为数组对象，如果为{@code null} 返回false
     */
    public boolean isArray(Object obj) {
        if (null == obj) {
            return false;
        }
        //反射 获得类型
        return obj.getClass().isArray();
    }

    /**
     * 不为空
     * @param o
     * @return
     */
    public boolean notNull(Object o) {
        if (o != null && o instanceof List) {
            return ((List) o).size() > 0;
        }
        if (o != null && o instanceof String) {
            return !TextUtils.isEmpty((String) o);
        }
        return o != null;
    }

    /**
     * 获取颜色
     * @param id
     * @return
     */
    public int getColor(@ColorRes int id) {
        return ContextCompat.getColor(mContext, id);
    }

    /**
     * 获取字体大小
     * @param id
     * @return
     */
    public float getDimension(@DimenRes int id) {
        return mContext.getResources().getDimension(id);
    }

    /**
     * 设置隐藏-前缀
     * @param id
     * @param txt
     */
    public BaseHelper setTextDataWithGone(@IdRes int id, String txt, String... preFix) {
        TextView textView = f(id);
        if (textView != null) {
            if (!TextUtils.isEmpty(txt)) {
                textView.setVisibility(View.VISIBLE);
                if (preFix != null && preFix.length > 0) {
                    txt = preFix[0] + txt;
                }
                textView.setText(txt);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 查找View
     * @param resId View的id
     * @param t     指定View类类型
     * @return
     */
    protected <T extends View> T f(@IdRes int resId, Class<T>... t) {
        View view = mViews.get(resId);
        if (view == null) {
            view = mContext.findViewById(resId);
            if (view == null) {
                return null;
            }
            mViews.put(resId, view);
        }
        return (T) view;
    }

    /**
     * 使用时:res 和txt数组 必须对应一直
     * @param id
     * @param res
     * @param txt
     */
    public BaseHelper setTextDataWithGone(@IdRes int id, @StringRes int res, Object[] txt) {
        TextView textView = f(id);
        if (textView != null && txt != null) {
            if (txt.length > 0 && txt instanceof String[]) {
                //判断
                String[] strings = (String[]) txt;
                for (String s : strings) {
                    if (TextUtils.isEmpty(s)) {
                        textView.setVisibility(View.GONE);
                        break;
                    }
                }
                textView.setVisibility(View.VISIBLE);
                String s = mContext.getResources().getString(res);
                String format = String.format(s, txt);
                textView.setText(format);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 添加点击事件
     * @param l
     * @param viewId
     * @return
     */
    public BaseHelper addOnClickListener(@Nullable View.OnClickListener l, @IdRes final int viewId) {
        f(viewId).setOnClickListener(l);
        return this;
    }

    /**
     * 添加多个点击事件
     * @param l
     * @param viewIds
     * @return
     */
    public BaseHelper addOnClickListener(@Nullable View.OnClickListener l, @IdRes final int... viewIds) {
        if (viewIds != null && viewIds.length > 0) {
            for (int viewId : viewIds) {
                f(viewId).setOnClickListener(l);
            }
        }
        return this;
    }

    /**
     * 设置文本数据-带View的
     * @param parent
     * @param id
     * @param txt
     */
    public BaseHelper setTextData(@NonNull View parent, @IdRes int id, String txt, @ColorInt int... color) {
      return setTextData(parent.findViewById(id), txt, color);
    }

    private BaseHelper setTextData(View view, String txt, @ColorInt int... color) {
        if (view == null) {
            return this;
        }
        if (view instanceof TextView) {
            setText(txt, (TextView) view, color);
        }
        if (view instanceof EditText) {
            setText(txt, (EditText) view, color);
        }
        return this;
    }

    private BaseHelper setText(String txt, TextView textView, @ColorInt int... color) {
        if (textView != null && !TextUtils.isEmpty(txt)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(txt);
            if (color != null && color.length > 0) {
                textView.setTextColor(color[0]);
            }
        }
        return this;
    }

    /**
     * 设置文本数据
     * @param id
     * @param txt
     */
    public BaseHelper setTextData(@IdRes int id, String txt, @ColorInt int... color) {
       return setTextData(f(id), txt, color);
    }

    /**
     * 设置背景颜色-格式:"#FFFFFFFF"
     * @param viewId
     * @param color  "#FFFFFFFF"
     * @return
     */
    public BaseHelper setBackgroundColor(@IdRes int viewId, @Size(min = 1) String color) {
        View view = f(viewId);
        if (view != null && !TextUtils.isEmpty(color) && color.length() >= 7) {
            view.setBackgroundColor(Color.parseColor(color));
        }
        return this;
    }

    /**
     * 设置背景颜色
     * @param viewId id
     * @param id R.color.write
     * @return
     */
    public BaseHelper setBackgroundColor(@IdRes int viewId, @ColorRes int id) {
        View view = f(viewId);
        if (view != null && id != 0) {
            view.setBackgroundColor(ContextCompat.getColor(mContext, id));
        }
        return this;
    }

    /**
     * 设置背景图片
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public BaseHelper setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = f(viewId);
        if (view != null) {
            view.setBackgroundResource(backgroundRes);
        }
        return this;
    }

    protected <T extends View> T f(@NonNull View parent, @IdRes int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = parent.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    /**
     * 隐藏或显示
     * @param viewId
     * @param visible
     */
    public BaseHelper setVisible(@IdRes int viewId, boolean visible) {
        View view = f(viewId);
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
        return this;
    }
    public BaseHelper setSelect(@IdRes int viewId, boolean visible) {
        View view = f(viewId);
        if (view != null) {
            view.setSelected(visible);
        }
        return this;
    }

    /**
     * 设置URL图片
     * @param id
     * @param url
     */
    public BaseHelper setImageUrl(@IdRes int id, String url) {
        ImageView imageView = f(id);
        return setImage(url, imageView);
    }

    private BaseHelper setImage(String url, ImageView imageView) {
        if (imageView != null && !TextUtils.isEmpty(url)) {
            // TODO 添加图片库
//			ImageUtil.getInstance().setView(imageView).setUrl(url).build();
        }
        return this;
    }

    public BaseHelper setImageResource(@IdRes int id, @DrawableRes int resId) {
        ImageView imageView = f(id);
        if (resId != 0) {
            imageView.setImageResource(resId);
        }
        return this;
    }

    /**
     * 设置URL图片- 带View的
     * @param parent
     * @param id
     * @param url
     */
    public BaseHelper setImageUrl(@NonNull View parent, @IdRes int id, String url) {
        ImageView imageView = parent.findViewById(id);
        return setImage(url, imageView);
    }

    /**
     * 设置点击事件
     * @param id
     * @param l
     */
    public BaseHelper setOnClickListener(@IdRes int id, @Nullable View.OnClickListener l) {
        View view = f(id);
        if (view != null) {
            view.setOnClickListener(l);
        }
        return this;
    }

    /**
     * 获取TextView或EditText的文本
     * @param id
     * @return
     */
    public String getTextData(@IdRes int id) {
        View view = f(id);
        if (view != null) {
            if (view instanceof TextView && ((TextView) view).getText() != null) {
                return ((TextView) view).getText().toString();
            }
            if (view instanceof EditText && ((EditText) view).getText() != null) {
                return ((EditText) view).getText().toString();
            }
        }
        return "";
    }

    /**
     * 回调接口
     */
    public interface OnCallBackListener<T> {
        void onCall(int tag, T t);
    }
}
