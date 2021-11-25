package com.example.dhcc_nurlink.Utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.base.commlibs.R;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * com.dhcc.nursepro.utils
 * <p>
 * author Q
 * Date: 2019/4/29
 * Time:10:41
 */
public class DateUtils {


    public static String getDateTimeByFromat(Long millseconds,String formatstr) {
        if (formatstr == null || formatstr.isEmpty()) {
            formatstr = "yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        String time = format.format(date);
        return time;
    }

    /**
     * Gets date by millisecond.
     *
     * @param millseconds the millseconds
     * @return the date by millisecond
     */
    public static String getDateByMillisecond(Long millseconds) {

        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /**
     * Gets time by millisecond.
     *
     * @param millseconds the millseconds
     * @return the time by millisecond
     */
    public static String getTimeByMillisecond(Long millseconds) {

        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(date);
        return time;
    }

    /**
     * Gets date time by millisecond.
     *
     * @param millseconds the millseconds
     * @return the date time by millisecond
     */
    public static String getDateTimeByMillisecond(Long millseconds) {

        Date date = new Date(millseconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(date);
        return time;
    }


    /**
     * Gets date from system.
     *
     * @return the date from system
     */
    public static String getDateFromSystem() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /**
     * Gets time from system.
     *
     * @return the time from system
     */
    public static String getTimeFromSystem() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(date);
        return time;
    }

    /**
     * Gets date time from system.
     *
     * @return the date time from system
     */
    public static String getDateTimeFromSystem() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(date);
        return time;
    }

    /**
     * 计算服务器与pda系统的时间差，返回时间戳的差值
     *
     * @param serveTimeMillis the serve time millis
     * @return the difference system from cache
     */
    public static Long getDifferenceSystemFromServe(String serveTimeMillis) {

        return  Long.valueOf(serveTimeMillis) - System.currentTimeMillis();
    }

    /**
     * 选择日期---年月日
     * @param context           the context
     * @param fragmentManager   the fragment manager
     * @param onDateSetListener the on date set listener
     */
    public static void chooseDate(Context context,FragmentManager fragmentManager,OnDateSetListener onDateSetListener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "ALL");

    }

    /**
     * 选择日期---年月日
     * @param context           the context
     * @param fragmentManager   the fragment manager
     * @param onDateSetListener the on date set listener
     *                          currMills 默认选中事件
     */
    public static void chooseDate(long currMills,Context context,FragmentManager fragmentManager,OnDateSetListener onDateSetListener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setCurrentMillseconds(currMills)
                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "ALL");

    }



    /**
     * 选择时间---时分
     * @param context           the context
     * @param fragmentManager   the fragment manager
     * @param onDateSetListener the on date set listener
     */
    public static void chooseTime(Context context, FragmentManager fragmentManager, OnDateSetListener onDateSetListener) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                .setType(Type.HOURS_MINS)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "ALL");
    }

    /**
     * 选择时间---年月日时分
     * @param context           the context
     * @param fragmentManager   the fragment manager
     * @param onDateSetListener the on date set listener
     */
    public static void chooseDateTime(Context context, FragmentManager fragmentManager, OnDateSetListener onDateSetListener) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId("取消")
                .setSureStringId("确认")
                .setTitleStringId("时间")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(true)
                .setCurrentMillseconds(calendar.getTimeInMillis())
                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();
        mDialogAll.show(fragmentManager, "ALL");
    }


    /**
     * 选择时间---年月日时分
     * @param currentTimeMillis           外部传入事件
     * @param context           the context
     * @param fragmentManager   the fragment manager
     * @param onDateSetListener the on date set listener
     */
    public static void chooseDateTime(long currentTimeMillis,Context context, FragmentManager fragmentManager, OnDateSetListener onDateSetListener) {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
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
                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setWheelItemTextSize(12)
                .build();

        mDialogAll.show(fragmentManager, "ALL");

    }

    /**
     * Date 2 time stamp string.
     * 时间转成时间戳
     * @param datetime   the date
     * @param format the format
     * @return the string
     */
    public static long date2TimeStamp(String datetime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Date date = simpleDateFormat.parse(datetime);
            long ts = date.getTime();
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }


    public static String getDateTimeAgo(String dateTime,int nDay){
       return getDateTimeByFromat(date2TimeStamp(dateTime,null)-86400000*nDay,null);
    }
}
