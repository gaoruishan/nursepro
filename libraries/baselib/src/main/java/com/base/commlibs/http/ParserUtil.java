package com.base.commlibs.http;

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
            showToast(callback, "网络错误，请求数据为空", "-1");
        } else {
            try {
                T t = gson.fromJson(jsonStr, clz);
                if (ObjectUtils.isEmpty(t)) {
                    showToast(callback, "网络错误，数据解析为空", "-3");
                } else {
                    return t;
                }
            } catch (Exception e) {
                showToast(callback, "网络错误，数据解析失败", "-2");
            }

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
        ToastUtils.showShort("error  " + code + ":" + msg + "_" + SharedPreference.MethodName);
    }

    /**
     * 解析状态码
     * @param bean
     * @param callback
     */
    public void parserStatus(T bean, CommonCallBack<T> callback) {
        if ("0".equals(bean.getStatus())) {
            if (callback != null) {
                callback.onSuccess(bean, bean.getClass().getSimpleName());
            }
        } else {
            if (callback != null) {
                callback.onFail(bean.getMsgcode(), bean.getMsg());
                ToastUtils.showShort(bean.getMsg());
            }
        }
    }
}
