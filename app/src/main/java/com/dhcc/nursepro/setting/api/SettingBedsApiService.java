package com.dhcc.nursepro.setting.api;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.bedselect.api.BedListApiService;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.setting.api
 * <p>
 * author Q
 * Date: 2018/9/14
 * Time:9:44
 */
public class SettingBedsApiService {


    public static void getBedList(HashMap<String, String> properties,String MethodName,final BedListApiService.ServiceCallBack callback) {

        WebServiceUtils.callWebService(MethodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }
}
