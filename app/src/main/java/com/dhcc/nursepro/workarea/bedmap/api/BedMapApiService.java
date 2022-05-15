package com.dhcc.nursepro.workarea.bedmap.api;

import com.base.commlibs.NurseAPI;
import com.blankj.utilcode.util.SPUtils;
import com.base.commlibs.constant.SharedPreference;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class BedMapApiService extends NurseAPI {

    public static void getBedMap(final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        WebServiceUtils.callWebService(getWardPatList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void getScanInfo(String barcode, final ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();

        properties.put("episodeId", "");
        properties.put("barcode", barcode);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", spUtils.getString(SharedPreference.LOCID));

        WebServiceUtils.callWebService(getScanInfo, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }
    public static void getDayPayList(HashMap<String,String> map,String MethodName, final BedMapApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
    public static void getAfterPayMsg(HashMap<String,String> map,String MethodName, final BedMapApiService.ServiceCallBack callback ){

        WebServiceUtils.callWebService(MethodName, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }
    public static void getPatInfoDetail(String regNo, final ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo", regNo);

        WebServiceUtils.callWebService(getPatInfoDetail, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }
    public interface ServiceCallBack {
        void onResult(String jsonStr);
    }

}
