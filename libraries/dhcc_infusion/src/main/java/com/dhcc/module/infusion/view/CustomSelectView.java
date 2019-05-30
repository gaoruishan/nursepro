package com.dhcc.module.infusion.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhcc.module.infusion.R;
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
 * @date:202019-04-27/14:57
 * @email:grs0515@163.com
 */
public class CustomSelectView extends LinearLayout {

    private static final String TAG = CustomSelectView.class.getSimpleName();
    private View view;
    private TextView tvTitle;
    private TextView tvPart;
    private RelativeLayout rlItem;
    private List<String> mSelectData;
    private OptionPicker.OnOptionPickListener listener;

    public CustomSelectView(Context context) {
        this(context, null);
    }

    public CustomSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        view = View.inflate(context, R.layout.custom_select_view, null);
        addView(view);
        setOrientation(VERTICAL);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rlItem = view.findViewById(R.id.rl_punct_part);
        tvTitle = view.findViewById(R.id.tv_title);
        tvPart = view.findViewById(R.id.tv_part);
    }

    /**
     * 设置标题
     * @param titleText
     */
    public CustomSelectView setTitle(String titleText) {
        if (!TextUtils.isEmpty(titleText)) {
            this.tvTitle.setVisibility(VISIBLE);
            this.tvTitle.setText(titleText);
        }
        return this;
    }

    /**
     * 设置选择文本
     * @param activity
     * @param mSelectData
     * @param listener
     */
    public CustomSelectView setSelectData(final Activity activity, List<String> mSelectData, OptionPicker.OnOptionPickListener listener) {
        this.mSelectData = mSelectData;
        if (mSelectData != null) {
            setSelect(mSelectData.get(0));
        }
        this.listener = listener;
        rlItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions(activity);
            }
        });
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
                setSelect(item);
                if (listener != null) {
                    listener.onOptionPicked(index, item);
                }
            }
        });
        picker.show();

    }

    /**
     * 设置选择文本
     * @param manager
     * @param listener
     */
    public CustomSelectView setSelectTime(final FragmentManager manager, final String date, String time, OptionPicker.OnOptionPickListener listener) {
        if (!TextUtils.isEmpty(date)) {
            setSelect(date + " " + time);
        } else {
            setSelect(time);
        }
        this.listener = listener;
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(date)) {
                    chooseTime(manager, System.currentTimeMillis(), "选择日期", Type.ALL);
                } else {
                    chooseTime(manager, System.currentTimeMillis(), "选择时间", Type.HOURS_MINS);
                }
            }
        };
        rlItem.setOnClickListener(onClickListener);

        return this;
    }

    /**
     * 设置提示文字
     * @param warningText
     */
    public CustomSelectView setSelect(String warningText) {
        if (!TextUtils.isEmpty(warningText)) {
            this.tvPart.setVisibility(VISIBLE);
            this.tvPart.setText(warningText);
        }
        return this;
    }

    /**
     * 获取选择时间
     * @return
     */
    public String getSelect() {
        if (this.tvPart.getText() != null) {
            return this.tvPart.getText().toString();
        }
        return "";
    }

    private void chooseTime(FragmentManager manager, long currentTimeMillis, String title, final Type type) {
        long tenYears = 3L * 365 * 1000 * 60 * 60 * 24L;

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        String strFormat = "yyyy-MM-dd HH:mm:ss";
                        if (Type.HOURS_MINS == type) {
                            strFormat = "HH:mm:ss";
                        }
                        Date date = new Date(millseconds);
                        SimpleDateFormat format = new SimpleDateFormat(strFormat);//精确到分钟
                        String datetime = format.format(date);
                        setSelect(datetime);
                        Log.e(TAG, "(PunctureFragment.java:129) " + datetime);
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
                .setThemeColor(getResources().getColor(R.color.blue))
                .setType(type)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.blue))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(manager, "ALL");

    }


}
