package com.dhcc.nursepro.message.api;

import android.text.TextUtils;

import com.base.commlibs.http.CommWebService;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class MessageApiService {

    /**
     * Description: 置皮试结果
     * Input： oeoreId:执行记录ID
     * other:	w ##class(Nur.OPPDA.Order).setSkinTestResult("656||4||1","Y",1)
     */
    public static void setSkinTestResult(String oeoreId, String skinTest,String auditUserId,String auditPassword, com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        if(!TextUtils.isEmpty(oeoreId)){
            properties.put("oeoreId",oeoreId);
            properties.put("skinTest", skinTest);
            properties.put("auditUserId", auditUserId);
            properties.put("auditPassword", auditPassword);
            WebServiceUtils.callWebService("setSkinTestResult", properties, new WebServiceUtils.WebServiceCallBack(){
                @Override
                public void callBack(String result) {
                    callBack.onResult(result);
                }
            });
        }
    }
    /**
     * Description: 获取皮试列表  阳性:Y/+ 阴性:N/-
     * Input： locId:科室ID
     * other:	w ##class(Nur.OPPDA.Message).getSkinTestMessage("7")
     */
    public static void getSkinTestMessage(com.base.commlibs.http.ServiceCallBack callBack) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        WebServiceUtils.callWebService("getSkinTestMessage", properties, new WebServiceUtils.WebServiceCallBack(){
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getMessage(final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        WebServiceUtils.callWebService("getNotifyList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void readMessage(String episodeId, final ServiceCallBack callback ){

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("episodeId", episodeId);
        WebServiceUtils.callWebService("readMessage", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
