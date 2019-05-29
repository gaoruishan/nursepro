package com.dhcc.nursepro.workarea.drugloopsystem.drughandover.api;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.constant.SharedPreference;
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
        SPUtils spUtils = SPUtils.getInstance();
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

    public static void BatchSave(String parr, String carryUser, String barCode, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("parr", parr);
        properties.put("carryUser", carryUser);
        properties.put("barCode", barCode);
        properties.put("type", "");


        Log.i("DrugHandover", "BatchSave: " + properties.toString());

        WebServiceUtils.callWebService("BatchSave", properties, new WebServiceUtils.WebServiceCallBack() {
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
