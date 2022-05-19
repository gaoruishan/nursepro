package com.base.commlibs.http;

/**
 * Service数据回调接口
 * @author:gaoruishan
 * @date:202019-04-24/15:05
 * @email:grs0515@163.com
 */
public interface ServiceCallBack {
    /**
     * json数据
     * @param jsonStr
     */
    void onResult(String jsonStr);
}
