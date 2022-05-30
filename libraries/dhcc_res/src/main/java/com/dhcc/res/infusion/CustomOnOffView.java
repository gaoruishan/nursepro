package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
import com.blankj.utilcode.util.ToastUtils;
import com.dhcc.res.BaseView;
import com.grs.dhcc_res.R;

/**
 * 开关切换
 * @author:gaoruishan
 * @date:202019-11-14/10:08
 * @email:grs0515@163.com
 */
public class CustomOnOffView extends BaseView {

    private TextView tvChange;
    private Button btnChange;
    private String textOff = "", textOn = "";
    private SimpleCallBack<Boolean> callBack;
    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //不可点击
            if (disEnable) {
                if(!TextUtils.isEmpty(tips)){
                    ToastUtils.showShort(tips);
                }
                return;
            }
            btnChange.setSelected(!btnChange.isSelected());
            changeText();
            if (callBack != null) {
                callBack.call(btnChange.isSelected(), 0);
            }
        }
    };
    private boolean disEnable;
    private String tips;

    public CustomOnOffView(Context context) {
        this(context, null);
    }

    public CustomOnOffView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOnOffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        btnChange = findViewById(R.id.btn_change);
        tvChange = findViewById(R.id.tv_change);
        btnChange.setSelected(true);
        btnChange.setOnClickListener(onClickListener);
        setOnClickListener(onClickListener);
        changeText();
    }

    private void changeText() {
        tvChange.setText(btnChange.isSelected() ? textOn : textOff);
    }

    public boolean isSelect() {
        return btnChange.isSelected();
    }

    public void setSelect(boolean select) {
        btnChange.setSelected(select);
    }

    public void setDisEnable(boolean disEnable) {
        btnChange.setEnabled(disEnable);
        setEnabled(disEnable);
    }

    public void setDisEnable(boolean disEnable, String tips) {
        this.disEnable = disEnable;
        this.tips = tips;
    }

    public CustomOnOffView setShowSelectText(String on, String off) {
        if (on != null) {
            this.textOn = on;
        }
        if (off != null) {
            this.textOff = off;
        }
        changeText();
        return this;
    }

    public CustomOnOffView setOnSelectListener(SimpleCallBack<Boolean> callBack) {
        this.callBack = callBack;
        return this;
    }


    @Override
    protected int setContentView() {
        return R.layout.custom_on_off_view;
    }

}
