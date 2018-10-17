package com.dhcc.nursepro.workarea.motherbabylink.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.milkloopsystem.api.MilkLoopService;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.motherbabylink.api
 * <p>
 * author Q
 * Date: 2018/10/17
 * Time:15:49
 */
public class LinkApiService {

    public interface ServiceCallBack{
        void onResult(String jsonStr);
    }

    public static void getLinkMsg(HashMap<String,String> map, String MethodName, final ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
}
