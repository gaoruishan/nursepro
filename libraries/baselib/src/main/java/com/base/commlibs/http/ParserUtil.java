package com.base.commlibs.http;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;

/**
 * @author:gaoruishan
 * @date:202019-04-24/16:17
 * @email:grs0515@163.com
 */
public class ParserUtil<T extends CommResult> {

    private static final String TAG = ParserUtil.class.getSimpleName();
    private Gson gson = new Gson();

    public ParserUtil() {
    }

    /**
     * json解析
     * @param jsonStr
     * @param callback
     */
    public T parserResult(String jsonStr, CommonCallBack callback, Class<T> clz) {
        if (jsonStr.isEmpty()) {
            callback.onFail("-1", "网络错误，请求数据为空");
        } else {
            try {
                T t = gson.fromJson(jsonStr, clz);
                if (ObjectUtils.isEmpty(t)) {
                    Log.e(TAG, "(ParserUtil.java:33) T is null");
                    callback.onFail("-3", "网络错误，数据解析为空");
                } else {
                    return t;
                }
            } catch (Exception e) {
                callback.onFail("-2", "网络错误，数据解析失败");
            }

        }
        return null;
    }

    /**
     * 解析状态码
     * @param bean
     * @param callback
     */
    public void parserStatus(T bean, CommonCallBack callback) {
        if ("0".equals(bean.getStatus())) {
            if (callback != null) {
                callback.onSuccess(bean, bean.getClass().getSimpleName());
            }
        } else {
            if (callback != null) {
                callback.onFail(bean.getMsgcode(), bean.getMsg());
            }
        }
    }
}
