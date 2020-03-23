package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.grs.dhcc_res.R;


/**
 * 滴速界面
 * @author:gaoruishan
 * @date:202019-04-26/16:04
 * @email:grs0515@163.com
 */
public class CustomSpeedView extends LinearLayout implements View.OnClickListener {


    private View view;
    private LinearLayout llRemove, llAdd;
    private EditText etSpeed;

    public CustomSpeedView(Context context) {
        this(context, null);
    }

    public CustomSpeedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSpeedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = LayoutInflater.from(context).inflate(R.layout.custom_speed_view, this, false);
        addView(view);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        llRemove = view.findViewById(R.id.ll_remove);
        llRemove.setOnClickListener(this);
        llAdd = view.findViewById(R.id.ll_add);
        llAdd.setOnClickListener(this);
        etSpeed = view.findViewById(R.id.et_speed);
        etSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !TextUtils.isEmpty(s.toString())) {
                    if ("0".equals(s.toString()) || "00".equals(s.toString())) {
                        etSpeed.setText("");
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        int speed = getSpeed();
        if (v.getId() == R.id.ll_remove) {
            speed = speed > 0 ? speed - 1 : 0;
        }
        if (v.getId() == R.id.ll_add) {
            speed = speed + 1;
        }
        setSpeed(String.valueOf(speed));
    }

    /**
     * 获取速度
     * @return
     */
    public int getSpeed() {
        String s = etSpeed.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            int integer = Integer.valueOf(s);
            return integer > 0 ? integer : 0;
        }
        return 0;
    }

    /**
     * 设置滴速
     * @param speed
     */
    public CustomSpeedView setSpeed(String speed) {
        setVisibility(GONE);
        if (!TextUtils.isEmpty(speed)) {
            setVisibility(VISIBLE);
            etSpeed.setText(speed);
        }
        return this;
    }

    public boolean isNotSpeed() {
        int speed = getSpeed();
        if (speed <= 0 && getVisibility() == VISIBLE) {
            ToastUtils.showShort("请调节滴速");
            return true;
        }
        return false;
    }
}
