package com.dhcc.nursepro.utils;

import android.text.TextUtils;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPStaticUtils;

import static com.dhcc.nursepro.workarea.workareautils.WorkareaOrdExeUtil.INT_8;

/**
 * @author:gaoruishan
 * @date:202021/12/30/11:22
 * @email:grs0515@163.com
 */
public class IfUtil {


    public static boolean isDevice(String scanInfo) {
        if (TextUtils.isEmpty(scanInfo)) {
            return false;
        }
        boolean b1 = scanInfo.length() == INT_8 && !scanInfo.contains("-") && !scanInfo.contains("REG");
        boolean b2 = !TextUtils.isEmpty(SPStaticUtils.getString(SharedPreference.deviceFlag));
        return b1 && b2;
    }

    public static boolean isShow(boolean equals) {
        boolean b2 = !TextUtils.isEmpty(SPStaticUtils.getString(SharedPreference.deviceFlag));
        if (equals && b2) {
            return true;
        }
        return false;
    }
}
