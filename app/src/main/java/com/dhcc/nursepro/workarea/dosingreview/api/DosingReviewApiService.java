package com.dhcc.nursepro.workarea.dosingreview.api;

import android.util.Log;

import com.base.commlibs.NurseAPI;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * DosingReviewApiService
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class DosingReviewApiService extends NurseAPI {

    public static void getInfusionOrdList(String infusionFlag, String startDate, String endDate, String pageNo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("infusionFlag", infusionFlag);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("pageNo", pageNo);

        Log.i("DosingReview", "getInfusionOrdList: " + properties.toString());

        WebServiceUtils.callWebService(getInfusionOrdList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getInfusionOrdListAfterCancel(String infusionFlag, String oeoriId, String startDate, String endDate, String pageNo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("infusionFlag", infusionFlag);
        properties.put("oeoriId", oeoriId);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("pageNo", pageNo);

        Log.i("DosingReview", "getInfusionOrdList: " + properties.toString());

        WebServiceUtils.callWebService(getInfusionOrdList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void preparedVerifyOrd(String oeoriId, String status, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        properties.put("oeoriId", oeoriId);
        properties.put("status", status);

        Log.i("DosingReview", "preparedVerifyOrd: " + properties.toString());

        WebServiceUtils.callWebService(preparedVerifyOrd, properties, new WebServiceUtils.WebServiceCallBack() {
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
