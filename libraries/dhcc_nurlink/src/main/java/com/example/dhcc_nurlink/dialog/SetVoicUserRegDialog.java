package com.example.dhcc_nurlink.dialog;


import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.example.dhcc_nurlink.R;
import com.facebook.common.media.MediaUtils;


/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SetVoicUserRegDialog extends Dialog {
    private Context context;


    private TextView etNurname;
    private EditText etNurpass;

    private TextView tvSkinSure;
    private TextView tvSkinCancle;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public SetVoicUserRegDialog(Context context) {
        super(context, R.style.SplashAppTheme);
        this.context = context;
    }

    /**
     * 设置确定按钮的显示内容和监听
     */
    public void setSureOnclickListener(onSureOnclickListener onSureOnclickListener) {

        this.sureOnclickListener = onSureOnclickListener;
    }

    /**
     * 设置取消按钮的显示内容和监听
     */
    public void setCancelOnclickListener(onCancelOnclickListener onCancelOnclickListener) {

        this.cancelOnclickListener = onCancelOnclickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dialog);
        //按空白处不能取消动画
        //            setCanceledOnTouchOutside(false);
        setCanceledOnTouchOutside(true);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        etNurname = findViewById(R.id.et_skin_nurname);
        etNurname.setText(SPUtils.getInstance().getString(SharedPreference.USERNAME));
        etNurpass = findViewById(R.id.et_skin_nurpass);
        tvSkinSure = findViewById(R.id.tv_skinresult_sure);
        tvSkinCancle = findViewById(R.id.tv_skinresult_cancle);
        etNurpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etNurpass.getText().toString().isEmpty()){
                    tvSkinSure.setSelected(false);
                }else {
                    tvSkinSure.setSelected(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public String getNurName(){
        return etNurname.getText().toString();
    }
    public String getNurPass(){
        return etNurpass.getText().toString();
    }



    private void initData() {
    }

    private void initEvent() {
        tvSkinSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNurpass.getText())){
                    Toast.makeText(context,"密码未填写",Toast.LENGTH_LONG).show();
                    return;
                }
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvSkinCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });

    }

    /**
     * 设置确定按钮被点击的接口
     */
    public interface onSureOnclickListener {
        void onSureClick();
    }

    /**
     * 设置取消按钮被点击的接口
     */
    public interface onCancelOnclickListener {
        void onCancelClick();
    }

}