package com.dhcc.res.util;

import android.util.Log;

import com.blankj.utilcode.util.ThreadUtils;

/**
 * 线程帮助类
 * @author:gaoruishan
 * @date:202019-09-03/11:40
 * @email:grs0515@163.com
 */
public class ThreadUtil {

    private static final String TAG = ThreadUtil.class.getSimpleName();

    /**
     * 执行异步线程
     * @param task
     * @param <T>
     */
    public static <T> void execute(final Task<T> task) {
        ThreadUtils.executeByIo(task);
    }



    public static abstract class Task<T> extends ThreadUtils.Task<T> {

        @Override
        public void onCancel() {
            Log.e(TAG, "(Task.java:21) 取消了");
        }

        @Override
        public void onFail(Throwable t) {
            Log.e(TAG, "(Task.java:23) " + t.toString());
        }
    }
}
