package com.base.commlibs.comm;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 通用的BaseViewHolder
 * @author:gaoruishan
 * @date:202020-03-23/15:36
 * @email:grs0515@163.com
 */
public class BaseCommViewHolder extends BaseViewHolder {

    public BaseCommViewHolder(View view) {
        super(view);
    }

    /**
     * 获取颜色
     * @param id
     * @return
     */
    public int getColor(Context mContext, @ColorRes int id) {
        return ContextCompat.getColor(mContext, id);
    }

    public void setTextSize(Context mContext, TextView textView, @DimenRes int dimen) {
        if (textView != null && mContext != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(dimen));
        }
    }

    public BaseCommViewHolder setSelect(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        if (view != null) {
            view.setSelected(visible);
        }
        return this;
    }

    public BaseCommViewHolder setTextData(@IdRes int viewId, String txt, String txtPre, String txtAfter, @ColorInt int... color) {
        if (!TextUtils.isEmpty(txt)) {
            setTextData(viewId, txtPre + txt + txtAfter, color);
        }
        return this;
    }


    public BaseCommViewHolder setTextData(@IdRes int viewId, String txt, @ColorInt int... color) {
        View textView = getView(viewId);
        if (textView instanceof TextView) {
            textView.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(txt)) {
                textView.setVisibility(View.VISIBLE);
                ((TextView) textView).setText(txt);
                if (color != null && color.length > 0) {
                    ((TextView) textView).setTextColor(color[0]);
                }
            }
        }
        return this;
    }

    /**
     * 获取TextView或EditText的文本
     * @param id
     * @return
     */
    public String getTextData(@IdRes int id) {
        View view = getView(id);
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
}
