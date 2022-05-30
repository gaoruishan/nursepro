package com.base.commlibs.utils;

/**
 * 回调接口
 * @param <T>
 */
public interface SimpleCallBack<T> {
    void call(T result, int type);
}