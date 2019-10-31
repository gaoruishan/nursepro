package com.base.commlibs.http;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;

/**
 * 公共请求
 * @author:gaoruishan
 * @date:202019-09-27/10:21
 * @email:grs0515@163.com
 */
public class CommHttp {
    /**
     * 检查用户/密码
     * @param userCode
     * @param password
     * @param callBack
     */
    public static void checkUser(String userCode, String password, CommonCallBack callBack) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userCode", userCode);
        properties.put("password", password);
        properties.put("logonWardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        String url = BaseWebServiceUtils.getServiceUrl(BaseWebServiceUtils.NUR_PDA_SERVICE);
        BaseWebServiceUtils.callWebService(url,"Logon", properties, new BaseWebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                CommWebService.parserCommResult(result, callBack);
            }
        });
    }
}
