package com.dhcc.module.infusion.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.module.infusion.R;


/**
 * 扫码界面
 * @author:gaoruishan
 * @date:202019-04-26/11:16
 * @email:grs0515@163.com
 */
public class CustomScanView extends LinearLayout {

    private LinearLayout view;
    private TextView tvTitle, tvWarning;
    private ImageView ivIcon;

    public CustomScanView(Context context) {
        this(context, null);
    }

    public CustomScanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomScanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = (LinearLayout) View.inflate(context, R.layout.custom_scan_view, null);
        addView(view);
        // 默认属性
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context,R.color.white));
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
    public CustomScanView setTitle(String titleText) {
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
    public CustomScanView setWarning(String warningText) {
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
    public CustomScanView setIcon(@DrawableRes int resId) {
        if (resId != 0) {
            this.ivIcon.setImageResource(resId);
        }
        return this;
    }
}
