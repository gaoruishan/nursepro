package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.base.commlibs.utils.SimpleCallBack;
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

    public CustomOnOffView(Context context) {
        this(context, null);
    }

    public CustomOnOffView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomOnOffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        btnChange = findViewById(R.id.btn_change);
        tvChange = findViewById(R.id.tv_change);
        btnChange.setSelected(true);
        btnChange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnChange.setSelected(!btnChange.isSelected());
                changeText();
                if (callBack != null) {
                    callBack.call(btnChange.isSelected(),0);
                }
            }
        });
        changeText();
    }

    private void changeText() {
        tvChange.setText(btnChange.isSelected() ? textOn : textOff);
    }

    public boolean isSelect() {
        return btnChange.isSelected();
    }

    public CustomOnOffView setShowSelectText(String on, String off) {
        this.textOn = on;
        this.textOff = off;
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
