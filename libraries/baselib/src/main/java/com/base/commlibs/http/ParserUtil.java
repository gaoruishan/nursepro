package com.base.commlibs.http;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.R;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.CommDialog;
import com.base.commlibs.utils.LocalTestManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;

/**
 * @author:gaoruishan
 * @date:202019-04-24/16:17
 * @email:grs0515@163.com
 */
public class ParserUtil<T extends CommResult> {

    public static final String ERR_CODE_1 = "-1";
    public static final String ERR_CODE_2 = "-2";
    public static final String ERR_CODE_3 = "-3";
    public static final String CODE_OK = "0";
    private static final String TAG = ParserUtil.class.getSimpleName();
    private Gson gson = new Gson();

    public ParserUtil() {
    }

    public  CommResult parserCommResult(String jsonStr) {
        if (jsonStr == null||jsonStr.isEmpty()) {
            showToast(null, "网络错误，请求数据为空", ERR_CODE_1);
        }else {
            try {
                CommResult t = new Gson().fromJson(jsonStr, CommResult.class);
                if (ObjectUtils.isEmpty(t)) {
                    showToast(null, "网络错误，数据解析为空", ERR_CODE_3);
                } else {
                    return t;
                }
            } catch (Exception e) {
                showToast(null, "网络错误，数据解析失败", ERR_CODE_2);
            }
        }
        return null;
    }

    /**
     * 1.1,json解析-strNull控制是否有提示
     * @param jsonStr  当返回null或者""时
     * @param callback
     * @param clz
     * @param strNull  自定义null 不提示Toast
     * @return
     */
    public T parserResult(String jsonStr, @NonNull CommonCallBack callback, Class<T> clz, String strNull) {
        if (jsonStr == null) {
            return null;
        }
        if (jsonStr.isEmpty()) {
            showToast(callback, strNull, ERR_CODE_1);
        } else {
            return parserResult(jsonStr, callback, clz);
        }
        return null;
    }

    /**
     * 错误Toast提示
     * @param callback
     * @param msg
     * @param code
     */
    private void showToast(CommonCallBack callback, String msg, String code) {
        if (callback != null) {
            callback.onFail(code, msg);
        }
        //如果为null不提示
        if (!TextUtils.isEmpty(msg)) {
            CommDialog.showCommDialog(ActivityUtils.getTopActivity(), "error  " + code + ":" + msg + "_" + SharedPreference.MethodName, "", R.drawable.icon_popup_error_patient, null, true);
        }
    }

    /**
     * 1,json解析-带有提示
     * @param jsonStr  当返回null或者""时
     * @param callback
     */
    public T parserResult(String jsonStr, @NonNull CommonCallBack callback, Class<T> clz) {
        if (jsonStr == null) {
            return null;
        }
        if (jsonStr.isEmpty()) {
            showToast(callback, "网络错误，请求数据为空", ERR_CODE_1);
        } else {
            try {
                T t = gson.fromJson(jsonStr, clz);
                if (ObjectUtils.isEmpty(t)) {
                    showToast(callback, "网络错误，数据解析为空", ERR_CODE_3);
                } else {
                    return t;
                }
            } catch (Exception e) {
                Log.e("json", "Exception= "+e.toString());
                LocalTestManager.saveLogTest(clz.getSimpleName()+"_json_exception","Exception= \n"+e.toString());
                showToast(callback, "网络错误，数据解析失败", ERR_CODE_2);
            }

        }
        return null;
    }

    /**
     * 2,解析状态码
     * @param bean
     * @param callback
     */
    public void parserStatus(T bean, @NonNull CommonCallBack<T> callback) {
        if (CODE_OK.equals(bean.getStatus())) {
            Log.e(TAG,"(ParserUtil.java:102) onSuccess");
            callback.onSuccess(bean, bean.getClass().getSimpleName());
        } else {
            Log.e(TAG,"(ParserUtil.java:105) onFail");
            callback.onFail(bean.getMsgcode(), bean.getMsg());
            CommDialog.showCommDialog(ActivityUtils.getTopActivity(), bean.getMsg(), "", R.drawable.icon_popup_error_patient, null, true);
        }
    }
}
