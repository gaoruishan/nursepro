package com.dhcc.res.util;

/**
 * 公共回调接口
 * @author:gaoruishan
 * @date:202019-04-24/15:05
 * @email:grs0515@163.com
 */
public interface CommonCallBack<T> {
    /**
     * 失败回调
     * @param code
     * @param msg
     */
    void onFail(String code, String msg);

    /**
     * 成功回调
     * @param bean
     */
    void onSuccess(T bean,String type);

}