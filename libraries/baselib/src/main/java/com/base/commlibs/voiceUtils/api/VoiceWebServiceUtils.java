package com.base.commlibs.voiceUtils.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPUtils;

import java.util.HashMap;


public class VoiceWebServiceUtils {


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
        String url = "http://" + SPUtils.getInstance().getString(SharedPreference.WEBIP) +SPUtils.getInstance().getString(SharedPreference.WEBPATH)+"/Nur.PDA.WebService.cls";
        com.base.commlibs.wsutils.BaseWebServiceUtils.callWebService(url,methodName,properties,webServiceCallBack);
    }



    /**
     * @author xiaanming
     */
    public interface WebServiceCallBack extends BaseWebServiceUtils.WebServiceCallBack{
        @Override
        void callBack(String result);
    }


}