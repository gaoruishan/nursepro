package com.dhcc.nursepro.workarea.pathandover.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dhcc.nursepro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建自定义的dialog
 * Created by Q on 2018/3/22.
 */
public class ConnectTypeDialog extends Dialog implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private int dialogType;
    private TextView tvTitle;
    private EditText edRegNo;
    private LinearLayout llConnecttype;
    private LinearLayout llButton;
    private LinearLayout llSure;
    private TextView tvSure;
    private LinearLayout llCancel;
    private TextView tvCancel;

    private onSureOnclickListener sureOnclickListener;
    private onCancelOnclickListener cancelOnclickListener;

    private String titleStr;
    private String regNo;
    private int regNoVisible;
    private List<String> typeList = new ArrayList<>();
    private String nowType = "";
    private String selectType = "";
    private String typeCode = "";

    private String tvSureText, tvCancelText;
    private int sureVisible, cancelVisible;
    private boolean sureClick = true, cancelClick = true;

    private List<CheckBox> checkList = new ArrayList<>();

    public ConnectTypeDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    public int getDialogType() {
        return dialogType;
    }

    public void setDialogType(int dialogType) {
        this.dialogType = dialogType;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
        if (tvTitle != null) {
            tvTitle.setText(titleStr);
        }
    }

    public String getRegNo() {
        if (edRegNo.getVisibility() == View.VISIBLE) {
            regNo = edRegNo.getText().toString();
        }
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setRegNoVisible(int regNoVisible) {
        this.regNoVisible = regNoVisible;
        if (edRegNo != null) {
            edRegNo.setVisibility(regNoVisible);
        }
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.nowType = selectType;
        this.selectType = selectType;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setSure(int sureVisible, String tvSureText, boolean sureClick) {
        this.sureVisible = sureVisible;
        this.tvSureText = tvSureText;
        this.sureClick = sureClick;
        if (llSure != null) {
            llSure.setVisibility(sureVisible);
        }
        if (tvSure != null) {
            tvSure.setText(tvSureText);
            tvSure.setClickable(sureClick);
            if (sureClick) {
                tvSure.setBackgroundResource(R.drawable.bg_dialog_sure);
            } else {
                tvSure.setBackgroundResource(R.drawable.bg_dialog_unclick);
            }
        }
    }

    public void setSureVisible(int sureVisible) {
        this.sureVisible = sureVisible;
        if (llSure != null) {
            llSure.setVisibility(sureVisible);
        }
    }

    public void setCancel(int cancelVisible, String tvCancelText, boolean cancelClick) {
        this.cancelVisible = cancelVisible;
        this.tvCancelText = tvCancelText;
        this.cancelClick = cancelClick;
        if (llCancel != null) {
            llCancel.setVisibility(cancelVisible);
        }
        if (tvCancel != null) {
            tvCancel.setText(tvCancelText);
            tvCancel.setClickable(cancelClick);
            if (cancelClick) {
                tvCancel.setBackgroundResource(R.drawable.bg_dialog_sure);
            } else {
                tvCancel.setBackgroundResource(R.drawable.bg_dialog_unclick);
            }
        }
    }

    public void setCancelVisible(int cancelVisible) {
        this.cancelVisible = cancelVisible;
        if (llCancel != null) {
            llCancel.setVisibility(cancelVisible);
        }
    }

    public void setTvSureText(String tvSureText) {
        this.tvSureText = tvSureText;
        if (tvSure != null) {
            tvSure.setText(tvSureText);
        }
    }

    public void setTvCancelText(String tvCancelText) {
        this.tvCancelText = tvCancelText;
        if (tvCancel != null) {
            tvCancel.setText(tvCancelText);
        }
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
        setContentView(R.layout.dialog_pathandover_connecttype);
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
        tvTitle = findViewById(R.id.tv_title);
        edRegNo = findViewById(R.id.ed_regno);
        llConnecttype = findViewById(R.id.ll_connecttype);
        llButton = findViewById(R.id.ll_button);
        llSure = findViewById(R.id.ll_sure);
        tvSure = findViewById(R.id.tv_sure);
        llCancel = findViewById(R.id.ll_cancel);
        tvCancel = findViewById(R.id.tv_cancel);
    }

    private void initData() {

        if (!StringUtils.isEmpty(titleStr)) {
            tvTitle.setText(titleStr);
        }

        edRegNo.setVisibility(regNoVisible);

        if (typeList.size() > 0) {
            llConnecttype.setVisibility(View.VISIBLE);
            LinearLayout llRadio = getRadioView();
            llConnecttype.addView(llRadio);
        } else {
            llConnecttype.setVisibility(View.GONE);
        }

        llSure.setVisibility(sureVisible);
        llCancel.setVisibility(cancelVisible);

        if (!StringUtils.isEmpty(tvSureText)) {
            tvSure.setText(tvSureText);
        }
        if (!StringUtils.isEmpty(tvCancelText)) {
            tvCancel.setText(tvCancelText);
        }

        if (sureClick) {
            tvSure.setClickable(true);
            tvSure.setBackgroundResource(R.drawable.bg_dialog_sure);
        } else {
            tvSure.setClickable(false);
            tvSure.setBackgroundResource(R.drawable.bg_dialog_unclick);
        }

        if (cancelClick) {
            tvCancel.setClickable(true);
            tvCancel.setBackgroundResource(R.drawable.bg_dialog_sure);
        } else {
            tvCancel.setClickable(false);
            tvCancel.setBackgroundResource(R.drawable.bg_dialog_unclick);
        }


    }

    private void initEvent() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sureOnclickListener != null) {
                    sureOnclickListener.onSureClick();
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onCancelClick();
                }
            }
        });
    }

    private LinearLayout getRadioView() {
        LinearLayout llRadio = new LinearLayout(context);
        LinearLayout.LayoutParams tvparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRadio.setLayoutParams(tvparams);
        llRadio.setOrientation(LinearLayout.VERTICAL);
        llRadio.setGravity(Gravity.CENTER);

        for (int i = 0; i < typeList.size(); i++) {
            CheckBox checkBox = new CheckBox(context);

            checkBox.setText(typeList.get(i).split(":")[0]);
            checkBox.setTag(typeList.get(i).split(":")[1]);

            if (selectType.equals(checkBox.getText())) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }

            checkBox.setOnCheckedChangeListener(this);

            llRadio.addView(checkBox);
            checkList.add(checkBox);
        }
        return llRadio;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {

            for (int i = 0; i < checkList.size(); i++) {
                if (!checkList.get(i).getTag().toString().equals(buttonView.getTag().toString())) {
                    checkList.get(i).setChecked(false);
                }
            }
        }

        int position;
        for (position = 0; position < checkList.size(); position++) {
            if (checkList.get(position).isChecked()) {
                selectType = buttonView.getText().toString();
                typeCode = buttonView.getTag().toString();
                break;
            }
        }
        if (position >= checkList.size()) {
            selectType = "";
            typeCode = "";
        }

        if (dialogType == 2) {
            if (StringUtils.isEmpty(typeCode)) {
                setSureClick(false);
                setCancelClick(false);
            } else {
                if (nowType.equals(selectType)) {
                    setSureClick(true);
                    setCancelClick(true);
                } else {
                    setSureClick(true);
                    setCancelClick(false);
                }
            }

        }

    }

    public void setSureClick(boolean sureClick) {
        this.sureClick = sureClick;
        if (tvSure != null) {
            tvSure.setClickable(sureClick);
            if (sureClick) {
                tvSure.setBackgroundResource(R.drawable.bg_dialog_sure);
            } else {
                tvSure.setBackgroundResource(R.drawable.bg_dialog_unclick);
            }
        }
    }

    public void setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
        if (tvCancel != null) {
            tvCancel.setClickable(cancelClick);
            if (cancelClick) {
                tvCancel.setBackgroundResource(R.drawable.bg_dialog_sure);
            } else {
                tvCancel.setBackgroundResource(R.drawable.bg_dialog_unclick);
            }
        }

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