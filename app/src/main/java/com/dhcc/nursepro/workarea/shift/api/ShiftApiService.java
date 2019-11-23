package com.dhcc.nursepro.workarea.shift.api;

import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;
import com.dhcc.nursepro.workarea.bedmap.api.BedMapApiService;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.shift.api
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:15:41
 */
public class ShiftApiService {
    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }

    public static void getShiftList(HashMap<String,String> map, String MethodName, final ShiftApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

}
