package com.dhcc.res.infusion;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.grs.dhcc_res.R;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

/**
 * @author:gaoruishan
 * @date:202019-07-12/14:40
 * @email:grs0515@163.com
 */
public class CustomDateTimeView extends LinearLayout implements View.OnClickListener {

    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;
    private TextView tvChooseDateStart;
    private TextView tvChooseDateEnd;
    private TextView tvZhi;
    private boolean isShowTime;
    private long startDateTime;
    private long endDateTime;
    private OnDateSetListener startClick,endClick;
    public static final String SP_CUSTOM_DATE_START="SP_CUSTOM_DATE_START";
    public static final String SP_CUSTOM_DATE_END="SP_CUSTOM_DATE_END";

    public CustomDateTimeView(Context context) {
        this(context, null);
    }

    public CustomDateTimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDateTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.custom_date_time_view, null);
        addView(view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvChooseDateStart = findViewById(R.id.tv_choose_date_start);
        tvChooseDateStart.setOnClickListener(this);
        tvChooseDateEnd = findViewById(R.id.tv_choose_date_end);
        tvChooseDateEnd.setOnClickListener(this);
        tvZhi = findViewById(R.id.tv_zhi);
        setChooseText(getStartDateTime(), tvChooseDateStart);
        setChooseText(getEndDateTime(), tvChooseDateEnd);

    }

    public void showOnlyOne(){
        tvChooseDateEnd.setVisibility(GONE);
        tvZhi.setVisibility(GONE);
    }

    private void setChooseText(long millseconds, TextView tv) {
        String s = TimeUtils.millis2String(millseconds).substring(0, 10);
        if (isShowTime) {
            s = TimeUtils.millis2String(millseconds).substring(0, 16);
        }
        tv.setText(s);
        //保存sp
        if (tv == tvChooseDateStart) {
            SPStaticUtils.put(SP_CUSTOM_DATE_START,s);
        }
        if (tv == tvChooseDateEnd) {
            SPStaticUtils.put(SP_CUSTOM_DATE_END,s);
        }
    }

    public String getStartDateTimeText() {
        if (tvChooseDateStart != null) {
           return tvChooseDateStart.getText().toString();
        }
        return "";
    }

    public String getEndDateTimeText() {
        if (tvChooseDateEnd != null) {
           return tvChooseDateEnd.getText().toString();
        }
        return "";
    }

    private long getStartDateTime() {
        if (startDateTime == 0) {
            return System.currentTimeMillis() - ONE_DAY;
        }
        return startDateTime;
    }

    private long getEndDateTime() {
        if (endDateTime == 0) {
            return System.currentTimeMillis();
        }
        return endDateTime;
    }

    public CustomDateTimeView setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
        setChooseText(getEndDateTime(), tvChooseDateEnd);
        return this;
    }

    public CustomDateTimeView setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
        setChooseText(getStartDateTime(), tvChooseDateStart);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_choose_date_start) {
            this.chooseTime(getStartDateTime(), new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    setChooseText(millseconds, tvChooseDateStart);
                    if (startClick != null) {
                        startClick.onDateSet(timePickerView,millseconds);
                    }
                }
            });
        }
        if (v.getId() == R.id.tv_choose_date_end) {
            this.chooseTime(getEndDateTime(), new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    setChooseText(millseconds, tvChooseDateEnd);
                    if (endClick != null) {
                        endClick.onDateSet(timePickerView,millseconds);
                    }
                }
            });
        }
    }

    private void chooseTime(long currentTimeMillis, OnDateSetListener listener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        TimePickerDialog.Builder builder = new TimePickerDialog.Builder();
        if (isShowTime) {
            builder.setType(Type.ALL);
        } else {
            builder.setType(Type.YEAR_MONTH_DAY);
        }
        TimePickerDialog mDialogAll = builder.setCallBack(listener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(currentTimeMillis - tenYears)
                .setMaxMillseconds(currentTimeMillis + tenYears)
                .setCurrentMillseconds(currentTimeMillis)
                .setThemeColor(getResources().getColor(R.color.colorPrimary))
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        if (getContext() instanceof FragmentActivity) {
            FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
            mDialogAll.show(fragmentManager, "ALL");
        }

    }

    public void setShowTime(boolean showTime) {
        isShowTime = showTime;
        setChooseText(getStartDateTime(), tvChooseDateStart);
        setChooseText(getEndDateTime(), tvChooseDateEnd);
    }

    public CustomDateTimeView setOnDateSetListener(OnDateSetListener startClick,OnDateSetListener endClick) {
        this.startClick = startClick;
        this.endClick = endClick;
        return this;
    }
}
