package com.dhcc.nursepro.workarea.orderexecute.api;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * OrderSearchApiService
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderExecuteApiService {
    /**
     * Description: 皮试计时
     * Input：	oeoriId 执行记录Id
     * Return： w ##class(Nur.PDA.Order).skinTime("194||57||1",1,"50分钟",149)
     */
    public static void skinTime(String oeoriId, String observeTime, String note, com.base.commlibs.http.ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("oeoriId", oeoriId);
        properties.put("observeTime", observeTime);
        properties.put("note", note);

        WebServiceUtils.callWebService("skinTime", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }

    public static void getOrder(String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime, String screenParts,final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));
        properties.put("groupId", spUtils.getString(SharedPreference.GROUPID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("bedStr", "");
        properties.put("pageNo", "1");

        properties.put("regNo", regNo);
        properties.put("sheetCode", sheetCode);

        properties.put("startDate", startDate);
        properties.put("startTime", startTime);
        properties.put("endDate", endDate);
        properties.put("endTime", endTime);
        properties.put("screenParts", screenParts);

        WebServiceUtils.callWebService("getOrders", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void execOrSeeOrder(String barCode,String scanFlag, String batch, String auditUserCode, String auditUserPass, String oeoreId, String execStatusCode, final ServiceCallBack callBack) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("scanFlag", scanFlag);
        properties.put("batch", batch);
        properties.put("auditUserCode ", auditUserCode);
        properties.put("auditUserPass ", auditUserPass);
        properties.put("oeoreId", oeoreId);
        properties.put("execStatusCode", execStatusCode);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", spUtils.getString(SharedPreference.LOCID));
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("barCode",barCode);

        WebServiceUtils.callWebService("execOrSeeOrder", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callBack.onResult(result);
            }
        });
    }


    public static void getOrdersMsg(String episodeId, String scanInfo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("barcode", scanInfo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", "");

        WebServiceUtils.callWebService("getScanInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    public static void getOrdersMsgByMain(String episodeId, String scanInfo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("barcode", scanInfo);
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("userDeptId", "");

        WebServiceUtils.callWebService("getScanInfoByMain", properties, new WebServiceUtils.WebServiceCallBack() {
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
