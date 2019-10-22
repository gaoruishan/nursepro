package com.dhcc.res.nurse;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.grs.dhcc_res.R;

/**
 * 自定义登陆输入
 * @author:gaoruishan
 * @date:202019-10-18/14:07
 * @email:grs0515@163.com
 */
public class CustomLoginEditText extends LinearLayout implements View.OnClickListener {


    private EditText etLoginPasswordCa;
    private ImageView imgLoginPasswordClearCa;
    private ImageView imgLoginPasswordShowhideCa;
    private TextView tvName;
    private SimpleCallBack<String> callBack;

    public CustomLoginEditText(Context context) {
        this(context, null);
    }

    public CustomLoginEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLoginEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_login_edit_text, null);
        addView(view);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvName = findViewById(R.id.tv_name);
        etLoginPasswordCa = findViewById(R.id.et_login_password_ca);
        imgLoginPasswordClearCa = findViewById(R.id.img_login_password_clear_ca);
        imgLoginPasswordShowhideCa = findViewById(R.id.img_login_password_showhide_ca);
        imgLoginPasswordClearCa.setOnClickListener(this);
        imgLoginPasswordShowhideCa.setOnClickListener(this);
        etLoginPasswordCa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示clear按钮
                String password = s + "";
                if (password.length() > 0) {
                    imgLoginPasswordClearCa.setVisibility(View.VISIBLE);
                } else {
                    imgLoginPasswordClearCa.setVisibility(View.INVISIBLE);
                }
                if (callBack != null) {
                    callBack.call(password, 0);
                }
            }
        });
    }

    public CustomLoginEditText setTextChangedListener(SimpleCallBack<String> callBack) {
        this.callBack = callBack;
        return this;
    }

    public String getTextString() {
        String s = etLoginPasswordCa.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            return s;
        }
        return "";
    }

    /**
     * 设置输入类型
     * @param type InputType.TYPE_CLASS_TEXT
     *             InputType.TYPE_TEXT_VARIATION_PASSWORD
     */
    public CustomLoginEditText setEditInputType(int type) {
        if (type != 0) {
            imgLoginPasswordShowhideCa.setVisibility(VISIBLE);
            if (InputType.TYPE_CLASS_TEXT == type) {
                imgLoginPasswordShowhideCa.setVisibility(GONE);
            }
            etLoginPasswordCa.setInputType(type);
        }

        return this;
    }

    public CustomLoginEditText setTextName(String str) {
        if (!TextUtils.isEmpty(str)) {
            tvName.setText(str);
            etLoginPasswordCa.setHint("请输入" + str);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_login_password_clear_ca) {
            etLoginPasswordCa.setText("");
        }
        if (v.getId() == R.id.img_login_password_showhide_ca) {
            if (imgLoginPasswordShowhideCa.isSelected()) {
                imgLoginPasswordShowhideCa.setSelected(false);
                etLoginPasswordCa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                imgLoginPasswordShowhideCa.setSelected(true);
                etLoginPasswordCa.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        }
    }

    public CustomLoginEditText checkRememberMe(boolean remem, String rememUserCode) {
        boolean isText = etLoginPasswordCa.getInputType() == InputType.TYPE_CLASS_TEXT;
        if (remem) {
            if (isText) {
                etLoginPasswordCa.setText(rememUserCode);
            }else {
                etLoginPasswordCa.requestFocus();
            }
        }
        return this;
    }
}
