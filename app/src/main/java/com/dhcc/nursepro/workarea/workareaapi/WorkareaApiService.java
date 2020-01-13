package com.dhcc.nursepro.workarea.workareaapi;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class WorkareaApiService {

    public static void getMainConfig(final WorkareaApiService.ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("locId",SPUtils.getInstance().getString(SharedPreference.LOCID));
        properties.put("groupId",SPUtils.getInstance().getString(SharedPreference.GROUPID));
        WebServiceUtils.callWebService("getMainConfig", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 主页扫码
     *
     * @author Devlix126
     * created at 2019/11/27 16:21
     */
    public static void getOrdersMsgByMain(String episodeId, String curOeordId, String scanInfo, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        if (!"".equals(episodeId)) {
            properties.put("episodeId", episodeId);
        }
        properties.put("curOeordId", curOeordId);
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

    /**
     * 配液复核
     *
     * @author Devlix126
     * created at 2019/11/28 9:27
     */
    public static void preparedVerifyOrd(String oeoriId, String status, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        properties.put("oeoriId", oeoriId);
        properties.put("status", status);

        WebServiceUtils.callWebService("preparedVerifyOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 巡视
     *
     * @author Devlix126
     * created at 2019/11/27 16:27
     */
    public static void tourOrd(String speed,String reason,String oeoreId, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed",speed);
        properties.put("reason",reason);
        properties.put("oeoreId", oeoreId);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        WebServiceUtils.callWebService("tourOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 暂停
     *
     * @author Devlix126
     * created at 2019/11/27 16:24
     */
    public static void suspendOrd(String speed,String oeoreId, String infusionState, String infusionReason, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed",speed);
        properties.put("oeoreId", oeoreId);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("infusionState", infusionState);
        properties.put("infusionReason", infusionReason);

        WebServiceUtils.callWebService("suspendOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 继续
     *
     * @author Devlix126
     * created at 2019/11/27 16:28
     */
    public static void continueOrd(String speed,String oeoreId, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("oeoreId", oeoreId);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        WebServiceUtils.callWebService("continueOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 停止
     *
     * @author Devlix126
     * created at 2019/11/27 16:30
     */
    public static void stopOrd(String speed,String oeoreId, String infusionState, String ResidualQty, String infusionReason, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed",speed);
        properties.put("oeoreId", oeoreId);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("infusionState", infusionState);
        properties.put("ResidualQty", ResidualQty);
        properties.put("infusionReason", infusionReason);

        WebServiceUtils.callWebService("stopOrd", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });

    }

    /**
     * 结束
     *
     * @author Devlix126
     * created at 2019/11/27 16:31
     */
    public static void endOrd(String speed,String reason,String oeoreId, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed",speed);
        properties.put("reason",reason);
        properties.put("oeoreId", oeoreId);
        properties.put("userId", spUtils.getString(SharedPreference.USERID));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));

        WebServiceUtils.callWebService("finishOrd", properties, new WebServiceUtils.WebServiceCallBack() {
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
