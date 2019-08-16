package com.base.commlibs.http;

import android.text.TextUtils;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
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
    private static final String TAG = ParserUtil.class.getSimpleName();
    public static final String CODE_OK = "0";
    private Gson gson = new Gson();

    public ParserUtil() {
    }

    /**
     * json解析
     * @param jsonStr
     * @param callback
     * @param clz
     * @param strNull  自定义null提示
     * @return
     */
    public T parserResult(String jsonStr, CommonCallBack callback, Class<T> clz, String strNull) {
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
        callback.onFail(code, msg);
        if (!TextUtils.isEmpty(msg)) {//如果为null不提示
            ToastUtils.showShort("error  " + code + ":" + msg + "_" + SharedPreference.MethodName);
        }
    }

    /**
     * json解析
     * @param jsonStr
     * @param callback
     */
    public T parserResult(String jsonStr, CommonCallBack callback, Class<T> clz) {
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
                showToast(callback, "网络错误，数据解析失败", ERR_CODE_2);
            }

        }
        return null;
    }

    /**
     * 解析状态码
     * @param bean
     * @param callback
     * @param dealOnFail 默认提示, 当dealOnFail=true 不吐司
     */
    public void parserStatus(T bean, CommonCallBack<T> callback, boolean... dealOnFail) {
        if (CODE_OK.equals(bean.getStatus())) {
            if (callback != null) {
                callback.onSuccess(bean, bean.getClass().getSimpleName());
            }
        } else {
            if (callback != null) {
                callback.onFail(bean.getMsgcode(), bean.getMsg());
                boolean isDealOnFail = dealOnFail != null && dealOnFail.length > 0 && dealOnFail[0];
                if (!isDealOnFail) {//有数据 且为true-->不提示
                    ToastUtils.showShort(bean.getMsg());
                }
            }
        }
    }
}
