package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * @author:gaoruishan
 * @date:202020-03-10/14:29
 * @email:grs0515@163.com
 */
public class CustomVitalView extends BaseView {

    private TextView  etPulse, etDia, etSys;
    private EditText etTemp;
    private View llTemp, llDia;

    public CustomVitalView(Context context) {
        this(context, null);
    }

    public CustomVitalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVitalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        llTemp = findViewById(R.id.ll_temp);
        llDia = findViewById(R.id.ll_dia);
        etTemp = findViewById(R.id.et_temp);
        etPulse = findViewById(R.id.et_pulse);
        etDia = findViewById(R.id.et_dia);
        etSys = findViewById(R.id.et_sys);
    }

    public EditText getEditTemp() {
        return etTemp;
    }

    public CustomVitalView setViewEnable(boolean enable) {
        TextView[] views = {etTemp, etPulse, etDia, etSys};

        for (TextView textView : views) {
            setTextColor(textView, R.color.text_color_black);
            setBackGroundColor(textView, enable ? R.color.transparency : R.color.bg_blue_light);
            textView.setEnabled(enable);
            textView.setClickable(enable);
            textView.setFocusable(enable);
            textView.setFocusableInTouchMode(enable);
        }

        return this;
    }


    public CustomVitalView setTemp(String s) {
        setText(etTemp, s);
        return this;
    }

    public CustomVitalView setPulse(String s) {
        setText(etPulse, s);
        return this;
    }

    public CustomVitalView setDia(String s) {
        setText(etDia, s);
        return this;
    }

    public CustomVitalView setSys(String s) {
        setText(etSys, s);
        return this;
    }

    public String getTemp() {
        return etTemp.getText().toString();
    }
    public String getPulse() {
        return etPulse.getText().toString();
    }
    public String getDia() {
        return etDia.getText().toString();
    }
    public String getSys() {
        return etSys.getText().toString();
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_vital_view;
    }
}
