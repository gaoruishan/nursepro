package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * DrugHandoverApiService
 *
 * @author Devlix126
 * created at 2019/5/28 11:23
 */
public class DrugHandoverApiService {

    public static void getOrdListByBarCode(String barCode, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("barCode", barCode);

        Log.i("DrugHandover", "getOrdListByBarCode: " + properties.toString());

        WebServiceUtils.callWebService("getOrdListByBarCode", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void batchSave(String parr, String carryUser, String barCode, String type, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("parr", parr);
        properties.put("carryUser", carryUser);
        properties.put("barCode", barCode);
        properties.put("type", type);


        Log.i("DrugHandover", "BatchSave: " + properties.toString());

        WebServiceUtils.callWebService("BatchSave", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getOrdRecieveList(String startdate, String enddate, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("startDate", startdate);
        properties.put("endDate", enddate);

        Log.i("DrugHandover", "getOrdRecieveList: " + properties.toString());

        WebServiceUtils.callWebService("getOrdRecieveList", properties, new WebServiceUtils.WebServiceCallBack() {
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
