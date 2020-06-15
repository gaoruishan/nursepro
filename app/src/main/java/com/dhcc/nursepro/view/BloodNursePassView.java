package com.dhcc.nursepro.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.nursepro.R;

/**
 * com.dhcc.nursepro.view
 * <p>
 * author Q
 * Date: 2020/6/15
 * Time:9:43
 */
public class BloodNursePassView extends LinearLayout {
    private EditText etNurse,etPass;
    private LinearLayout llDevice;
    private Listener mListener;
    public BloodNursePassView(Context context) {
        super(context,null);
    }
    public BloodNursePassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_blood_nursepass, this, false);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NursepassBlood);
        String nurseText = ta.getString(R.styleable.NursepassBlood_nursehint);
        String passText = ta.getString(R.styleable.NursepassBlood_passhint);
        addView(view);
        initView();
        initNurseHint(nurseText,passText);
    }

    private void initView(){
        etNurse = findViewById(R.id.et_blood_nurse);
        etPass = findViewById(R.id.et_blood_pass);
        llDevice = findViewById(R.id.ll_blood_device);
        etNurse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示选中
                if (etNurse.getText().toString().length()>0){
                    etNurse.setSelected(true);
                }else {
                    etNurse.setSelected(false);
                    setPassText("");
                }
                changeDeviceImg();
                tvChangUpdate(s.toString());
            }
        });
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //如果有输入内容长度大于0那么显示选中
                if (etPass.getText().toString().length()>0){
                    etPass.setSelected(true);
                }else {
                    etPass.setSelected(false);
                }
                changeDeviceImg();
                tvChangUpdate(s.toString());
            }
        });


    }

    private void  initNurseHint(String hintText,String pass){
        etNurse.setHint(hintText);
        etPass.setHint(pass);
    }
    public String getNurseText(){
        if (etNurse!=null){
            return etNurse.getText().toString();
        }else {
            return "";
        }
    }
    public void setNurseText(String nurseText){
        if (etNurse!=null){
            etNurse.setText(nurseText);
        }
    }
    public String getPassText(){
        if (etPass!=null){
            return etPass.getText().toString();
        }else {
            return "";
        }
    }
    public void setPassText(String nurseText){
        if (etPass!=null){
            etPass.setText(nurseText);
        }
    }
    private void changeDeviceImg() {
        if (getNurseText().length() > 0 && getPassText().length() > 0) {
            llDevice.setBackgroundResource(R.drawable.img_separate4);
        } else if (getNurseText().length() > 0 && getPassText().length() == 0) {
            llDevice.setBackgroundResource(R.drawable.img_separate2);
        } else if (getNurseText().length()== 0 && getPassText().length() > 0) {
            llDevice.setBackgroundResource(R.drawable.img_separate3);
        } else if (getNurseText().length() == 0 && getPassText().length() == 0) {
            llDevice.setBackgroundResource(R.drawable.img_separate1);
        }

    }


    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void tvChangUpdate(String string) {
        if (mListener != null) {
            mListener.update(string);
        }
    }

    public interface Listener {
        public void update(String string);
    }

}
