package com.base.commlibs.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.R;


/**
 * 扫码界面
 * @author:gaoruishan
 * @date:202019-04-26/11:16
 * @email:grs0515@163.com
 */
public class XScanView extends LinearLayout {

    private LinearLayout view;
    private TextView tvTitle, tvWarning;
    private ImageView ivIcon;

    public XScanView(Context context) {
        this(context, null);
    }

    public XScanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XScanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = (LinearLayout) View.inflate(context, R.layout.x_scan_view, null);
        addView(view);
        // 默认属性
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context,R.color.white));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //不可点击
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvTitle = view.findViewById(R.id.tv_title);
        tvWarning = view.findViewById(R.id.tv_warning);
        ivIcon = view.findViewById(R.id.iv_icon);
    }
    /**
     * 设置标题
     * @param titleText
     */
    public XScanView setTitle(String titleText) {
        if (!TextUtils.isEmpty(titleText)) {
            this.tvTitle.setVisibility(VISIBLE);
            this.tvTitle.setText(titleText);
        }
        return this;
    }

    /**
     * 设置提示文字
     * @param warningText
     */
    public XScanView setWarning(String warningText) {
        if (!TextUtils.isEmpty(warningText)) {
            this.tvWarning.setVisibility(VISIBLE);
            this.tvWarning.setText(warningText);
        }
        return this;
    }

    /**
     * 设置图片
     * @param resId
     */
    public XScanView setIcon(@DrawableRes int resId) {
        if (resId != 0) {
            this.ivIcon.setImageResource(resId);
        }
        return this;
    }
}
