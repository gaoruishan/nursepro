package com.dhcc.res.util;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.grs.dhcc_res.R;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private OnDateSetListener dateListener;

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

    /**
     * 设置选择日期时间
     * @param manager
     * @param currentTimeMillis
     * @param listener
     * @return
     */
    public SelectUtil setSelectDateTime(FragmentManager manager,long currentTimeMillis, OnDateSetListener listener) {
        chooseTime(manager, currentTimeMillis, "选择日期时间", Type.ALL);
        this.dateListener = listener;
        return this;
    }

    private void chooseTime(FragmentManager manager, long currentTimeMillis, String title, final Type type) {
        long tenYears = 3L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        String datetime = getFormatYYYY_MM_DD_HH_MM(millseconds);
                        if (dateListener != null) {
                            dateListener.onDateSet(timePickerView,millseconds);
                        }
                    }
                })
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId(title)
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(ActivityUtils.getTopActivity().getResources().getColor(R.color.dhcc_blue))
                .setType(type)
                .setWheelItemTextNormalColor(ActivityUtils.getTopActivity().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ActivityUtils.getTopActivity().getResources().getColor(R.color.dhcc_blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(manager, "ALL");

    }

    public static String getFormatYYYY_MM_DD_HH_MM(long millseconds) {
        String strFormat = "yyyy-MM-dd HH:mm";
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat(strFormat);//精确到分钟
        return format.format(date);
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


    public String getSelectItem() {
        return mSelectItem == null ? "" : mSelectItem;
    }
}
