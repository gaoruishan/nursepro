package com.dhcc.module.infusion.utils;

import android.text.TextUtils;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:gaoruishan
 * @date:202019-06-22/14:33
 * @email:grs0515@163.com
 */
public class AppUtil {
    /**
     * 获取科室窗口
     * @return
     */
    public static String getWindowName() {
        String windName = SPUtils.getInstance().getString(SharedPreference.WINDOWNAME);
        if (TextUtils.isEmpty(windName)) {//如果为空从WINSLISTJSON中获取
            String LOCDESC = SPUtils.getInstance().getString(SharedPreference.LOCDESC);
            java.lang.reflect.Type typeWin = new TypeToken<HashMap<String, List>>() {
            }.getType();
            String WinJson = SPUtils.getInstance().getString(SharedPreference.WINSLISTJSON);
            Map<String, List> mapWins = new Gson().fromJson(WinJson, typeWin);
            //默认第一个窗口
            return mapWins.get(LOCDESC).get(0).toString();
        }
        return windName;
    }
}
