package com.dhcc.nursepro.utils.wsutils;

import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;

import java.util.HashMap;


public class WebServiceUtils {

    //    public static final String WEB_SERVER_URL = "http://10.1.5.87/dthealth/web/Nur.PDA.WebService.cls";

    /**
     * WebService服务器地址
     *
     * @param methodName         WebService的调用方法名
     * @param properties         WebService的参数
     * @param webServiceCallBack 回调接口
     */
    public static void callWebService(final String methodName,
                                      HashMap<String, String> properties,
                                      final WebServiceCallBack webServiceCallBack) {
        // 创建HttpTransportSE对象，传递WebService服务器地址
        String url = "http://" + SPUtils.getInstance().getString(SharedPreference.WEBIP) + "/imedical/web/Nur.PDA.WebService.cls";
        BaseWebServiceUtils.callWebService(url,methodName,properties,webServiceCallBack);
    }



    /**
     * @author xiaanming
     */
    public interface WebServiceCallBack extends BaseWebServiceUtils.WebServiceCallBack{
        @Override
        public void callBack(String result);
    }


}
