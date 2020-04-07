package com.dhcc.module.infusion.message;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.base.commlibs.MessageEvent;
import com.dhcc.module.infusion.R;
import com.dhcc.res.infusion.CountView;

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
    public static boolean setCountTime(Context mContext, CountView cvCount, String testStartTime, String formatEndTime, boolean otherOk,CountView.OnCountViewStatusListener listener) {
        SimpleDateFormat formatter1 = new SimpleDateFormat(HH_MM_SS);
        String formatNowTime = formatter1.format(new Date(System.currentTimeMillis()));
        //结束时间> 现在时间
        long offTime = getFormatTime(formatEndTime) - getFormatTime(formatNowTime);
        //现在时间> 开始时间
        long offTime2 = getFormatTime(formatNowTime) - getFormatTime(testStartTime);

        //没有复核/复核人 并且时间大于0
        boolean isOk = offTime >= 0 && offTime2 >= 0 && otherOk;
        if (isOk) {
            cvCount.getTitleName().setVisibility(View.GONE);
            cvCount.getOneDay().setTextColor(Color.parseColor("#FFFF6EA4"));
            cvCount.getOneDay().setTextSize(mContext.getResources().getDimension(R.dimen.dp_13));
            cvCount.start(offTime, CountView.ONE_DAY);
            cvCount.setOnCountViewStatusListener(new CountView.OnCountViewStatusListener() {
                @Override
                public void onStop() {
                    if (listener != null) {
                        listener.onStop();
                    }
                    EventBus.getDefault().post(new MessageEvent(MessageEvent.MessageType.NOTIFY_MESSAGE));
                }
            });
        }
        return isOk;
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

}
