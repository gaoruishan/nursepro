package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * 自定义下划线输入
 * @author:gaoruishan
 * @date:202020-04-20/10:24
 * @email:grs0515@163.com
 */
public class CustomInputLineView extends BaseView {

    private TextView tvUnit;
    private EditText etTxt;
    private TextView tvName;

    public CustomInputLineView(Context context) {
        this(context, null);
    }

    public CustomInputLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomInputLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tvName = findViewById(R.id.tv_name);
        etTxt = findViewById(R.id.et_txt);
        tvUnit = findViewById(R.id.tv_unit);
    }

    public CustomInputLineView setUnit(String unit) {
        setText(this.tvUnit, unit);
        return this;
    }

    public CustomInputLineView setText(String etTxt) {
        setText(this.etTxt, etTxt);
        return this;
    }

    public EditText getEditText() {
        return etTxt;
    }

    public CustomInputLineView setName(String tvName) {
        setText(this.tvName, tvName);
        return this;
    }

    @Override
    protected int setContentView() {
        return R.layout.custom_input_line_view;
    }
}
