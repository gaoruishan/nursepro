package com.dhcc.module.nurse.task.bean;

import java.io.Serializable;

/**
 * com.dhcc.module.nurse.task.bean
 * <p>
 * author Q
 * Date: 2020/8/10
 * Time:16:28
 */
public class TimesListBean implements Serializable {
    /**
     * timeEnd : 2020-08-05 23:59
     * timeStt : 2020-08-05 00:00
     * timesDesc : 全天
     */

    private String timeEnd;
    private String timeStt;
    private String timesDesc;

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeStt() {
        return timeStt;
    }

    public void setTimeStt(String timeStt) {
        this.timeStt = timeStt;
    }

    public String getTimesDesc() {
        return timesDesc;
    }

    public void setTimesDesc(String timesDesc) {
        this.timesDesc = timesDesc;
    }
}
