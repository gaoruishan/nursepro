package com.dhcc.nursepro.workarea.drugloopsystem.residualliquidregistration.api;

import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * RLRegApiService
 *
 * @author Devlix126
 * created at 2019/5/29 16:03
 */
public class RLRegApiService {

    public static void getResidualQtyList(String startDate, String endDate, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("startDate", startDate);
        properties.put("endDate", endDate);

        Log.i("RLReg", "getResidualQtyList: " + properties.toString());

        WebServiceUtils.callWebService("getResidualQtyList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    public static void residualQtyReg(String oeoreId, String barCode, String type, String regQty, String regQtyUnit, String wayDesc, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("oeoreId", oeoreId);
        properties.put("barCode", barCode);
        properties.put("type", type);
        properties.put("regQty", regQty);
        properties.put("regQtyUnit", regQtyUnit);
        properties.put("wayDesc", wayDesc);

        Log.i("RLReg", "residualQtyReg: " + properties.toString());

        WebServiceUtils.callWebService("residualQtyReg", properties, new WebServiceUtils.WebServiceCallBack() {
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
