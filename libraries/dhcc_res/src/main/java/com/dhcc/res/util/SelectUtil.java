package com.dhcc.res.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

/**
 * @author:gaoruishan
 * @date:202020-11-04/14:03
 * @email:grs0515@163.com
 */
public class SelectUtil {

    private List<String> mSelectData;
    private OptionPicker.OnOptionPickListener listener;
    private int mPst;
    private String mSelectItem;

    /**
     * 设置选择文本
     * @param activity
     * @param mSelectData
     * @param listener
     */
    public SelectUtil setSelectData(final Activity activity, List<String> mSelectData, OptionPicker.OnOptionPickListener listener) {
        this.mSelectData = mSelectData;
        this.listener = listener;
        showOptions(activity);
        return this;
    }

    private void showOptions(Activity activity) {
        if (mSelectData == null) {
            mSelectData = new ArrayList<String>();
        }
        OptionPicker picker = new OptionPicker(activity, mSelectData);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerRatio(WheelView.DividerConfig.FILL);
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                mSelectItem = item;
                mPst = index;
                if (listener != null) {
                    listener.onOptionPicked(index, item);
                }
            }
        });
        picker.show();
    }

    public int getSelectPosition() {
        return mPst;
    }

    public String getSelectItem() {
        return mSelectItem == null ? "" : mSelectItem;
    }
}
