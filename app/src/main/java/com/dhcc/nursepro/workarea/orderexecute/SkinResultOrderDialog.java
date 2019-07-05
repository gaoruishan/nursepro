package com.dhcc.nursepro.workarea.orderexecute;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;

/**
 * com.dhcc.nursepro.workarea.orderexecute
 * <p>
 * author Q
 * Date: 2018/12/28
 * Time:14:32
 */
public class SkinResultOrderDialog extends Dialog {
    private Context context;

    private TextView tvSkinNeg;
    private TextView tvSkinPos;
    private TextView tvSkinPat;
    private EditText etSkinNum;
    private LinearLayout llNurinfo;
    private EditText etNurname;
    private EditText etNurpass;

    private TextView tvSkinSure;
    private TextView tvSkinCancle;

    private String patInfo;
    private String skinResult;
    private String skinNum;
    private String nurName = "";
    private String nurPass = "";
    private String singleFlag = "N";

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;


    public SkinResultOrderDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    public void setPatInfo(String patInfo) {
        this.patInfo = patInfo;
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
        setContentView(R.layout.orderexecorder_skin_dialog_layout);
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

        tvSkinNeg = findViewById(R.id.tv_skinresult_negtive);
        tvSkinPos = findViewById(R.id.tv_skinresult_posivive);
        tvSkinPat = findViewById(R.id.tv_skin_patinfo);
        etSkinNum = findViewById(R.id.et_skin_num);
        llNurinfo = findViewById(R.id.ll_skin_nurinfo);
        etNurname = findViewById(R.id.et_skin_nurname);
        etNurpass = findViewById(R.id.et_skin_nurpass);
        tvSkinSure = findViewById(R.id.tv_skinresult_sure);
        tvSkinCancle = findViewById(R.id.tv_skinresult_cancle);
        if (singleFlag.equals("Y")){}else {
            llNurinfo.setVisibility(View.GONE);
        }
    }

    public String getSkinResult(){
        return skinResult;
    }
    public String getSkinNum(){
        return etSkinNum.getText().toString();
    }
    public String getNurName(){
        return etNurname.getText().toString();
    }
    public String getNurPass(){
        return etNurpass.getText().toString();
    }

    public void setSingleFlag(String singleFlag){
        this.singleFlag = singleFlag;
    }



    private void initData() {

        if (patInfo != null) {
            tvSkinPat.setText(patInfo);
        }


    }

    private void initEvent() {
        tvSkinSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(skinResult)){
                    Toast.makeText(context,"请选择皮试结果",Toast.LENGTH_LONG).show();
                    return;
                }
                if (StringUtils.isEmpty(etSkinNum.getText())){
                    Toast.makeText(context,"未书写批号",Toast.LENGTH_LONG).show();
                }
                if (singleFlag.equals("Y")){
                    if (TextUtils.isEmpty(etNurname.getText()) || TextUtils.isEmpty(etNurpass.getText())){
                        Toast.makeText(context,"工号或密码未填写",Toast.LENGTH_LONG).show();
                        return;
                    }
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

        tvSkinNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skinResult = "N";
                tvSkinNeg.setSelected(true);
                tvSkinPos.setSelected(false);
            }
        });

        tvSkinPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skinResult = "Y";
                tvSkinNeg.setSelected(false);
                tvSkinPos.setSelected(true);
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