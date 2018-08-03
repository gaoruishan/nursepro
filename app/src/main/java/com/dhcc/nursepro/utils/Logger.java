package com.dhcc.nursepro.utils;

import android.util.Log;

/**
 * Created by levis on 2018/5/21.
 */

public class Logger {

    public static final String LOG_TAG = "shengjing";

    public static void d(String message) {
        if (Value.s_printLog) {
            Log.d(LOG_TAG, message);
        }
    }

    public static void d(String Tag, String message) {
        if (Value.s_printLog) {
            Log.d(Tag, message);
        }
    }

    public static void w(String message) {
        if (Value.s_printLog) {
            Log.w(LOG_TAG, message);
        }
    }

    public static void w(String Tag, String message) {
        if (Value.s_printLog) {
            Log.w(Tag, message);
        }
    }

    public static void e(String message) {
        if (Value.s_printLog) {
            Log.e(LOG_TAG, message);
        }
    }

    public static void e(String Tag, String message) {
        if (Value.s_printLog) {
            Log.e(Tag, message);
        }
    }
}

