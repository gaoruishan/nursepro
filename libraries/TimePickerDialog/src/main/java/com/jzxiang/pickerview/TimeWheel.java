package com.jzxiang.pickerview;

import android.content.Context;
import android.view.View;

import com.jzxiang.pickerview.adapters.NumericWheelAdapter;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.source.TimeRepository;
import com.jzxiang.pickerview.utils.PickerContants;
import com.jzxiang.pickerview.utils.Utils;
import com.jzxiang.pickerview.wheel.OnWheelChangedListener;
import com.jzxiang.pickerview.wheel.WheelView;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public class TimeWheel {
    Context mContext;

    WheelView year, month, day, hour, minute;
    NumericWheelAdapter mYearAdapter, mMonthAdapter, mDayAdapter, mHourAdapter, mMinuteAdapter;

    PickerConfig mPickerConfig;
    TimeRepository mRepository;
    private int type=0;
    private int[] a ;
    private int[] b ;
    OnWheelChangedListener yearListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateMonths();
        }
    };
    OnWheelChangedListener monthListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateDays();
        }
    };
    OnWheelChangedListener dayListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateHours();
        }
    };
    OnWheelChangedListener minuteListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateMinutes();
        }
    };

    public TimeWheel(View view, PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;

        mRepository = new TimeRepository(pickerConfig);
        mContext = view.getContext();
        initialize(view);
    }
    public TimeWheel(int type,int[] a,int[] b,View view, PickerConfig pickerConfig) {
        this.type = type;
        this.a = a;
        this.b = b;
        mPickerConfig = pickerConfig;
        mRepository = new TimeRepository(pickerConfig);
        mContext = view.getContext();
        initialize(view);
    }

    public void initialize(View view) {
        initView(view);
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();
    }


    void initView(View view) {
        year = view.findViewById(R.id.year);
        month = view.findViewById(R.id.month);
        day = view.findViewById(R.id.day);
        hour = view.findViewById(R.id.hour);
        minute = view.findViewById(R.id.minute);

        switch (mPickerConfig.mType) {
            case ALL:

                break;
            case YEAR_MONTH_DAY:
                Utils.hideViews(hour, minute);
                break;
            case YEAR_MONTH:
                Utils.hideViews(day, hour, minute);
                break;
            case MONTH_DAY_HOUR_MIN:
                Utils.hideViews(year);
                break;
            case HOURS_MINS:
                Utils.hideViews(year, month, day);
                break;
            case YEAR:
                Utils.hideViews(month, day, hour, minute);
                break;
        }

        year.addChangingListener(yearListener);
        year.addChangingListener(monthListener);
        year.addChangingListener(dayListener);
        year.addChangingListener(minuteListener);
        month.addChangingListener(monthListener);
        month.addChangingListener(dayListener);
        month.addChangingListener(minuteListener);
        day.addChangingListener(dayListener);
        day.addChangingListener(minuteListener);
        hour.addChangingListener(minuteListener);
    }

    void initYear() {
        int minYear = mRepository.getMinYear();
        int maxYear = mRepository.getMaxYear();

        mYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, PickerContants.FORMAT, mPickerConfig.mYear);
        mYearAdapter.setConfig(mPickerConfig);
        year.setViewAdapter(mYearAdapter);
        year.setCurrentItem(mRepository.getDefaultCalendar().year - minYear);
    }

    void initMonth() {
        updateMonths();
        int curYear = getCurrentYear();
        int minMonth = mRepository.getMinMonth(curYear);
        month.setCurrentItem(mRepository.getDefaultCalendar().month - minMonth);
        month.setCyclic(mPickerConfig.cyclic);
    }

    void initDay() {
        updateDays();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        int minDay = mRepository.getMinDay(curYear, curMonth);
        day.setCurrentItem(mRepository.getDefaultCalendar().day - minDay);
        day.setCyclic(mPickerConfig.cyclic);
    }

    void initHour() {
        updateHours();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mRepository.getMinHour(curYear, curMonth, curDay);
        hour.setCurrentItem(mRepository.getDefaultCalendar().hour - minHour);
        hour.setCyclic(mPickerConfig.cyclic);
    }

    void initMinute() {
        updateMinutes();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
        int minMinute = mRepository.getMinMinute(curYear, curMonth, curDay, curHour);

        minute.setCurrentItem(mRepository.getDefaultCalendar().minute - minMinute);
        minute.setCyclic(mPickerConfig.cyclic);

    }

    void updateMonths() {
        if (month.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int minMonth = mRepository.getMinMonth(curYear);
        int maxMonth = mRepository.getMaxMonth(curYear);
        mMonthAdapter = new NumericWheelAdapter(mContext, minMonth, maxMonth, PickerContants.FORMAT, mPickerConfig.mMonth);
        mMonthAdapter.setConfig(mPickerConfig);
        month.setViewAdapter(mMonthAdapter);

        if (mRepository.isMinYear(curYear)) {
            month.setCurrentItem(0, false);
        }
    }

    void updateDays() {
        if (day.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, curMonth);

        int maxDay = mRepository.getMaxDay(curYear, curMonth);
        int minDay = mRepository.getMinDay(curYear, curMonth);
        mDayAdapter = new NumericWheelAdapter(mContext, minDay, maxDay, PickerContants.FORMAT, mPickerConfig.mDay);
        mDayAdapter.setConfig(mPickerConfig);
        day.setViewAdapter(mDayAdapter);

        if (mRepository.isMinMonth(curYear, curMonth)) {
            day.setCurrentItem(0, true);
        }

        int dayCount = mDayAdapter.getItemsCount();
        if (day.getCurrentItem() >= dayCount) {
            day.setCurrentItem(dayCount - 1, true);
        }
    }

    void updateHours() {
        if (hour.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mRepository.getMinHour(curYear, curMonth, curDay);
        int maxHour = mRepository.getMaxHour(curYear, curMonth, curDay);
        int maxmore = 0;
        if (type == 1){
            if (a.length>10){
                maxmore = maxmore+10;
            }else {
                maxmore = a.length;
            }
            mHourAdapter = new NumericWheelAdapter(mContext, 100, 100+maxmore-1, PickerContants.FORMAT, mPickerConfig.mHour,a);
        }else {
            mHourAdapter = new NumericWheelAdapter(mContext, minHour, maxHour, PickerContants.FORMAT, mPickerConfig.mHour);
        }

        mHourAdapter.setConfig(mPickerConfig);
        hour.setViewAdapter(mHourAdapter);

        if (mRepository.isMinDay(curYear, curMonth, curDay))
            hour.setCurrentItem(0, false);
    }

    void updateMinutes() {
        if (minute.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();

        int minMinute = mRepository.getMinMinute(curYear, curMonth, curDay, curHour);
        int maxMinute = mRepository.getMaxMinute(curYear, curMonth, curDay, curHour);
        int maxmore = 0;
        if (type == 1){
            if (b.length>10){
                maxmore = maxmore+10;
            }else {
                maxmore = b.length;
            }
            mMinuteAdapter = new NumericWheelAdapter(mContext, 100, 100+maxmore-1, PickerContants.FORMAT, mPickerConfig.mMinute,b);
        }else {
             mMinuteAdapter = new NumericWheelAdapter(mContext, minMinute, maxMinute, PickerContants.FORMAT, mPickerConfig.mMinute);
            }
        mMinuteAdapter.setConfig(mPickerConfig);
        minute.setViewAdapter(mMinuteAdapter);

        if (mRepository.isMinHour(curYear, curMonth, curDay, curHour))
            minute.setCurrentItem(0, false);
    }

    public int getCurrentYear() {
        return year.getCurrentItem() + mRepository.getMinYear();
    }

    public int getCurrentMonth() {
        int curYear = getCurrentYear();
        return month.getCurrentItem() + +mRepository.getMinMonth(curYear);
    }

    public int getCurrentDay() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        return day.getCurrentItem() + mRepository.getMinDay(curYear, curMonth);
    }

    public int getCurrentHour() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        //qqq
        if (type == 1) {

            return a[hour.getCurrentItem()];

        }else {
            return hour.getCurrentItem() + mRepository.getMinHour(curYear, curMonth, curDay);
        }
    }

    public int getCurrentMinute() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
//qqq
        if (type == 1){
            return b[minute.getCurrentItem()];
        }else {
            return minute.getCurrentItem() + mRepository.getMinMinute(curYear, curMonth, curDay, curHour);
        }
    }


}
