package com.dhcc.res.infusion;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;
import com.noober.background.drawable.DrawableCreator;

/**
 * 自定义勾选框
 * @author:gaoruishan
 * @date:202019-04-27/14:57
 * @email:grs0515@163.com
 */
public class CustomCheckView extends BaseView {

    private Button btn_change;
    private TextView tvChange;
    private SimpleCallBack<Boolean> callBack;

    public CustomCheckView(Context context) {
        this(context,null);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparency));
        tvChange = findViewById(R.id.tv_change);
        btn_change = findViewById(R.id.btn_change);
        btn_change.setSelected(false);
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_change.setSelected(!btn_change.isSelected());
                btn_change.setFocusable(true);
                btn_change.setFocusableInTouchMode(true);
                if (callBack != null) {
                    callBack.call(btn_change.isSelected(), 0);
                }
            }
        };
        btn_change.setOnClickListener(onClickListener);
        tvChange.setOnClickListener(onClickListener);
    }

    public void setOnSelectListener(SimpleCallBack<Boolean> callBack) {
        this.callBack = callBack;
    }
    @Override
    protected int setContentView() {
        return R.layout.custom_check_view;
    }

    public boolean getSelect() {
       return btn_change.isSelected();
    }

    public void setSelect(boolean select) {
        btn_change.setSelected(select);
    }

    public void setSelectIcon(@DrawableRes int select,@DrawableRes int unselect) {
        Drawable drawable = new DrawableCreator.Builder()
                .setSelectedDrawable(getResources().getDrawable(select))
//                .setCheckedDrawable(getResources().getDrawable(select))
                .setUnSelectedDrawable(getResources().getDrawable(unselect))
//                .setUnCheckedDrawable(getResources().getDrawable(unselect))
                .build();
        btn_change.setBackground(drawable);
    }

    public void setShowText(String txt) {
        if (tvChange != null) {
            tvChange.setVisibility(!TextUtils.isEmpty(txt) ? VISIBLE : GONE);
            if (!TextUtils.isEmpty(txt)) {
                tvChange.setText(txt);
            }
        }
    }
}
