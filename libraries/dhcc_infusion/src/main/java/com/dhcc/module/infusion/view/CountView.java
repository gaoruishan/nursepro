package com.dhcc.module.infusion.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dhcc.module.infusion.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 活动-时分秒倒计时
 * @author:gaoruishan
 * @date:2018/8/30/14:48
 * @email:grs0515@163.com
 */
public class CountView extends LinearLayout {

    public static final String HH_MM_SS = "HH:mm:ss";
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final int STOP = 400;
    public static final int ONE_DEFULT = 1;
    //延迟1s
    private static final int DELAY_MILLIS = 1000;
    //只显示一个
    public static final int ONE_DAY = DELAY_MILLIS * 60 * 60 * 24;
    public static final int ONE_MIN = DELAY_MILLIS * 60;
    private static final String TAG = CountView.class.getSimpleName();
    //偏移量
    private static final int OFFSET = 60;
    private TextView tvCountUnit;
    private View llSecond;
    private TextView tvTitleName;
    //默认Handler
    private Handler handler = new Handler();
    private TextView tvTime1;//时
    private TextView tvTime2;//分
    private TextView tvTime3;//秒
    private long mTime = 400;
    private TextView tvDay;
    private LinearLayout llDayCounting;
    private LinearLayout rootView;
    private int dayNum;
    private OnCountViewStatusListener listener;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mTime--;
            //判断停止
            if (mTime == 0) {
                Log.e(TAG, "run: " + mTime);
                handlerStop.sendEmptyMessage(STOP);
                return;
            }
            //时分秒倒计时
            if (llDayCounting.getVisibility() == View.VISIBLE) {
                setFillTimeText();
            } else {
                tvDay.setText(mTime / OFFSET + "");
            }

            if (mTime > 0) {
                handler.postDelayed(this, DELAY_MILLIS);
            }
        }
    };
    private Handler handlerStop = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == STOP) {
                stop();
                if (listener != null) {
                    listener.onStop();
                }
                Log.e(TAG, "handleMessage: ");
            }
        }
    };

    public CountView(Context context) {
        this(context, null);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_count_view, null);
        addView(rootView);

        tvTime1 = rootView.findViewById(R.id.tv_time1);
        tvTime2 = rootView.findViewById(R.id.tv_time2);
        tvTime3 = rootView.findViewById(R.id.tv_time3);
        llDayCounting = rootView.findViewById(R.id.ll_day_counting);
        llSecond = rootView.findViewById(R.id.ll_second);
        tvDay = rootView.findViewById(R.id.tv_day);
        tvTitleName = rootView.findViewById(R.id.tv_title_name);
        tvCountUnit = rootView.findViewById(R.id.tv_count_unit);
//		handler.postDelayed(runnable, 1000);
    }

    private void setFillTimeText() {
        String formatLongToTimeStr = formatLongToTimeStr(mTime);
        if (formatLongToTimeStr.contains(":")) {
            String[] split = formatLongToTimeStr.split(":");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    tvTime1.setText(split[0]);
                }
                if (i == 1) {
                    tvTime2.setText(split[1]);
                }
                if (i == 2) {
                    tvTime3.setText(split[2]);
                }

            }
        }
    }

    /**
     * 获取指定格式数据
     * @param totalTime
     */
    private String formatLongToTimeStr(long totalTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS);
        formatter.setTimeZone(GMT);
        //totalTime单位是秒
        String format = formatter.format(new Date(totalTime * DELAY_MILLIS));
//		Log.e(TAG, "formatDataTest: " + format);
        return format;
        /*
         * 日期转期望格式的字符串
         */
        //HH 和 hh 的差别：前者是24小时制，后者是12小时制。
//		StringBuilder sb = new StringBuilder();
//		sb.append("yyyy年MM月dd日 HH:mm:ss")
//				.append(" 上下午标志 a")
//				.append(" E")
//				.append(" 一年中的第D天")
//				.append(" 一月中的第F个星期")
//				.append(" 一年中的第w个星期")
//				.append(" 一月中的第W个星期")
//				.append(" Z")
//				.append(" z");
//		SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
//		String dateString = sdf.format(new Date());
        /*
         * 字符串转日期
         */
//		Date date;
//		try {
//			date = sdf.parse(dateString);
//			Log.e(TAG, "formatDataTest: " + date);
//		} catch (ParseException e) {
//		}
    }

    /**
     * View销毁时
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();// 停止
    }

    /**
     * 停止
     */
    public void stop() {
        if (listener != null) {
            listener.onStop();
        }
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        Log.e(TAG, "stop: " + mTime);
    }

    /**
     * 设置状态监听
     * @param listener
     */
    public void setOnCountViewStatusListener(OnCountViewStatusListener listener) {
        this.listener = listener;
    }

    /**
     * 开始
     * @param time 毫秒值
     */
    public void start(Long time, int type) {
        if (time <= 0) {
            Log.e(TAG, "start: time < 0");
            return;
        }
        // 先停止
        stop();
        llDayCounting.setVisibility(VISIBLE);
        //单个显示
        if (type == ONE_DAY) {
            tvDay.setVisibility(VISIBLE);//隐藏秒布局
            tvDay.setText(time / OFFSET + "");//把单位变为分钟
            toStart(time, GONE);
        }
        // 显示时分
        if (type == ONE_MIN) {// 时分秒显示
            llSecond.setVisibility(GONE);//隐藏秒布局
            toStart(time / DELAY_MILLIS, VISIBLE);
        }
        if (type == ONE_DEFULT) {
            llSecond.setVisibility(VISIBLE);//显示秒布局
            toStart(time / DELAY_MILLIS, VISIBLE);
        }
    }

    private void toStart(long time, int isLLCounting) {
        mTime = time;
        llDayCounting.setVisibility(isLLCounting);
        handler.postDelayed(runnable, DELAY_MILLIS);
    }

    /**
     * 设置时间前面提示语
     * @return
     */
    public TextView getTitleName() {
        return tvTitleName;
    }

    public void setTitleName(String txt,@Size(min = 1) String color,@DimenRes int  size) {
        if(!TextUtils.isEmpty(txt)){
            tvTitleName.setVisibility(VISIBLE);
            tvTitleName.setText(txt);
        }
        if(!TextUtils.isEmpty(color)){
            tvTitleName.setTextColor(Color.parseColor(color));
        }
        if (size != 0) {
            tvTitleName.setTextSize(getContext().getResources().getDimension(size));
        }
    }
    /***
     * OneDay模式下TextView
     * @return
     */
    public TextView getOneDay() {
        return tvDay;
    }

    /**
     * 设置单位
     * @return
     */
    public TextView getCountUnit() {
        return tvCountUnit;
    }

    public interface OnCountViewStatusListener {
        void onStop();
    }

}
