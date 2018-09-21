package com.dhcc.nursepro.uiplugs;

import android.app.Activity;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

public class OptionView extends android.support.v7.widget.AppCompatTextView {

    private String[] options;

    private int index;

    private int size;

    private String value;

    private Activity mActivity;

    private OptionPicker picker;

    public OptionView(Activity activity, List<String> options) {
        super(activity);

        mActivity = activity;
        size = options.size();
        this.options = new String[size];

        for (int i = 0; i < size; i++) {
            this.options[i] = options.get(i);
        }

        initPicker();

    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    /**
     * 更新选中内容及UI
     * @param index
     * @param item
     */
    private void optionsUpdated(int index, String item){
        this.index = index;
        this.value = item;
        setText(value);
    }

    /**
     * 初始化picker
     */
    private void initPicker(){
        picker = new OptionPicker(mActivity, options);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                optionsUpdated(index,item);
            }
        });
    }

    /**
     * 显示picker
     */
    public void showPicker(){
        picker.show();
    }


}
