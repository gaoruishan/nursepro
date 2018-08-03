package com.dhcc.nursepro.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dhcc.nursepro.NApplication;

/**
 * Created by levis on 2018/5/21.
 */

public class PreferenceDataHelper {

    public static final String KEY_DEFAULT_PREFERENCE = "com.hdcc";

    private static SharedPreferences settingPref = NApplication.getApp()
            .getSharedPreferences(KEY_DEFAULT_PREFERENCE, Context.MODE_MULTI_PROCESS);

    private static SharedPreferences.Editor editor = settingPref.edit();


    /**
     * 获取当前Url设置
     *
     * @return
     */
    public static int getCurrentUrlSetting() {
        //COORDINATE_URL_SETTING
        return settingPref.getInt("current_url", Value.COORDINATE_URL_SETTING);
        //return settingPref.getInt("current_url", Value.RELEASE_URL_SETTING);
    }

    /**
     * 获取当前保存在本地的登录错误码。如果错误码为9999，说明当前是已登录状态
     *
     * @return
     */
    public static String getLoginErrorCode() {
        String errorCode = settingPref.getString("login_error_code", "");
        return errorCode;
    }

    /**
     * 保存当前登录失败状态码
     *
     * @param errorCode 9001或9003
     */
    public static void saveLoginErrorCode(String errorCode) {
        editor.putString("login_error_code", errorCode);
        editor.commit();
    }

    /**
     * 保存完善信息code
     *
     * @param code
     */
    public static void saveUserInfoNeedAddCode(int code) {
        editor.putInt("info_need_add_code", code).commit();
        editor.commit();
    }

}
