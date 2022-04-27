package com.dhcc.res.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.base.commlibs.MessageEvent;
import com.blankj.utilcode.util.TimeUtils;
import com.dhcc.res.infusion.CountView;
import com.grs.dhcc_res.R;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author:gaoruishan
 * @date:202020-04-03/14:41
 * @email:grs0515@163.com
 */
public class MessageUtil {
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static boolean setCountTime(Context mContext, CountView cvCount, String testStartTime, String formatEndTime, boolean otherOk,CountView.OnCountViewStatusListener listener,String ... arg) {
        SimpleDateFormat formatter1;
        if (testStartTime.contains(" ")) {
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter1 = new SimpleDateFormat(HH_MM_SS);
        }
        String formatNowTime = formatter1.format(new Date(System.currentTimeMillis()));
        Log.e("TAG", "(MessageUtil:36) formatNowTime:" + formatNowTime);
        long offTime = 0, offTime2 = 0;
        if (testStartTime.contains(" ")) {
            //结束时间> 现在时间
            offTime = getBetweenTime(formatEndTime, formatNowTime);
            //现在时间> 开始时间
            offTime2 = getBetweenTime(formatNowTime, testStartTime);
        } else {
        //结束时间> 现在时间
            offTime = getFormatTime(formatEndTime) - getFormatTime(formatNowTime);
        //现在时间> 开始时间
            offTime2 = getFormatTime(formatNowTime) - getFormatTime(testStartTime);
        }

        //没有复核/复核人 并且时间大于0
        boolean isOk = offTime >= 0 && offTime2 >= 0 && otherOk;

        Log.e("TAG",isOk +"  开始时间: "+testStartTime+", 结束:"+formatEndTime+", "+formatNowTime+", offTime="+offTime+", offTime2="+offTime2);
        if (isOk) {
            startCountTime(mContext, cvCount, listener, offTime);
        }
        //优化一下
        if (!isOk && arg.length > 0 && !TextUtils.isEmpty(arg[0])) {
            long off = Long.parseLong(arg[0]);
            Log.e("TAG","(MessageUtil.java:46) 优化一下="+off);
            startCountTime(mContext, cvCount, listener, off);
            return true;
        }
        return isOk;
    }

    public static void startCountTime(Context mContext, CountView cvCount, CountView.OnCountViewStatusListener listener, long offTime) {
        cvCount.getTitleName().setVisibility(View.GONE);
        cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
        cvCount.getOneDay().setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.dp_22));
        cvCount.start(offTime, CountView.ONE_DAY);
        cvCount.setOnCountViewStatusListener(new CountView.OnCountViewStatusListener() {
            @Override
            public void onStop() {
                if (listener != null) {
                    listener.onStop();
                }
                EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.NOTIFY_MESSAGE));
                EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.REQUEST_APP_MESSAGE_LIST));
            }
        });
    }

    public static boolean setCountTime(Context mContext, CountView cvCount, String testStartTime, String formatEndTime, boolean otherOk) {
            return setCountTime(mContext, cvCount, testStartTime, formatEndTime, otherOk, null);
    }

    public static long getFormatTime(String formatTime) {
        if (!TextUtils.isEmpty(formatTime)) {
            if (formatTime.contains(" ")) {
                String[] s = formatTime.split(" ");
                formatTime = s[1];
            }
            if (formatTime.contains(":")) {
                String[] splitEndTime = formatTime.split(":");
                return Integer.parseInt(splitEndTime[0]) * 3600
                        + Integer.parseInt(splitEndTime[1]) * 60 + Integer.parseInt(splitEndTime[2]);
            }
        }
        return 0;
    }

    private static long getBetweenTime(String formatEndTime, String formatNowTime) {
        if (!TextUtils.isEmpty(formatEndTime)) {
            //获取日期时间毫秒值
            long l = TimeUtils.string2Millis(formatEndTime)-TimeUtils.string2Millis(formatNowTime);
            if (l > 0) {
                return l / 1000;
            }
        }
        return 0;
    }
}
