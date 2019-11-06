package com.dhcc.res.infusion.bean;

import android.view.View;

/**
 * @author:gaoruishan
 * @date:202019-11-04/17:16
 * @email:grs0515@163.com
 */
public class ClickBean {
    private String text;
    private View.OnClickListener listener;

    public ClickBean(String text, View.OnClickListener listener) {
        this.text = text;
        this.listener = listener;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
