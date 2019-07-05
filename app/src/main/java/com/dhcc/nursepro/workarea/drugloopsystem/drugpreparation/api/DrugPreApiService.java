package com.dhcc.nursepro.workarea.drugloopsystem.drugpreparation.api;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * DrugPreApiService
 *
 * @author Devlix126
 * created at 2019/6/6 9:51
 */
public class DrugPreApiService {

    public static void getOrdInfo(String oeoreId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId", oeoreId);

        WebServiceUtils.callWebService("getOrdInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    public static void takeOrd(String oeoreId, String type, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("oeoreId", oeoreId);
        properties.put("type", type);

        WebServiceUtils.callWebService("TakeOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getTakeOrdList(String startDate, String endDate, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("type", "");

        WebServiceUtils.callWebService("getTakeOrdList", properties, new WebServiceUtils.WebServiceCallBack() {
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
