package com.base.commlibs.utils;

import android.text.TextUtils;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.TimeUtils;

/**
 * 统一管理SchDateTime
 * @author:gaoruishan
 * @date:202020-06-30/09:48
 * @email:grs0515@163.com
 */
public class SchDateTimeUtil {

    public static final int INT_16 = 16;

    /**
     * 开始时间(毫秒值)
     * @return
     */
    public static Long getCurDateTime() {
        String startDateTime = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        if(!TextUtils.isEmpty(startDateTime)){
            if (startDateTime.length() == INT_16) {
                return TimeUtils.string2Millis(startDateTime,"yyyy-MM-dd HH:mm");
            }
            //"yyyy-MM-dd HH:mm:ss"
            return TimeUtils.string2Millis(startDateTime);
        }
        return System.currentTimeMillis();
    }

    public static String getCurDateTimeStr() {
        return getCurDateStr()+" "+getCurTimeStr();
    }

    /**
     * 当前日期
     * @return
     */
    public static String getCurDateStr() {
        String startDateTime = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        if(!TextUtils.isEmpty(startDateTime)){
            return startDateTime.substring(0, 10);
        }
        return TimeUtils.getNowString().substring(0,10);
    }

    /**
     * 当前时间
     * @return
     */
    public static String getCurTimeStr() {
        String startDateTime = SPStaticUtils.getString(SharedPreference.CURDATETIME);
        if(!TextUtils.isEmpty(startDateTime)){
            return startDateTime.substring(11, INT_16);
        }
        return TimeUtils.getNowString().substring(11,INT_16);
    }
    /**
     * 开始时间(毫秒值)
     * @return
     */
    public static Long getStartDateTime() {
        String startDateTime = SPStaticUtils.getString(SharedPreference.SCHSTDATETIME);
        if(!TextUtils.isEmpty(startDateTime)){
            if (startDateTime.length() == INT_16) {
                return TimeUtils.string2Millis(startDateTime,"yyyy-MM-dd HH:mm");
            }
            //"yyyy-MM-dd HH:mm:ss"
        	return TimeUtils.string2Millis(startDateTime);
        }
        return System.currentTimeMillis();
    }
    /**
     * 结束时间(毫秒值)
     * @return
     */
    public static Long getEndDateTime() {
        String startDateTime = SPStaticUtils.getString(SharedPreference.SCHENDATETIME);
        if(!TextUtils.isEmpty(startDateTime)){
            if (startDateTime.length() == INT_16) {
                return TimeUtils.string2Millis(startDateTime,"yyyy-MM-dd HH:mm");
            }
            //"yyyy-MM-dd HH:mm:ss"
        	return TimeUtils.string2Millis(startDateTime);
        }
        return System.currentTimeMillis();
    }

    public static String getStartDate() {
        return SPStaticUtils.getString(SharedPreference.SCHSTDATETIME).substring(0, 10);
    }

    public static String getStartTime() {
        return SPStaticUtils.getString(SharedPreference.SCHSTDATETIME).substring(11, INT_16);
    }


    public static String getEndDate() {
        return SPStaticUtils.getString(SharedPreference.SCHENDATETIME).substring(0, 10);
    }

    public static String getEndTime() {
        return SPStaticUtils.getString(SharedPreference.SCHENDATETIME).substring(11, INT_16);
    }

    /**
     * 保存
     * @param schStDateTime
     * @param schEndDateTime
     */
    public static void putSchStartEndDateTime(String schStDateTime, String schEndDateTime) {
        SPStaticUtils.put(SharedPreference.SCHSTDATETIME, schStDateTime);
        SPStaticUtils.put(SharedPreference.SCHENDATETIME, schEndDateTime);
    }
}
