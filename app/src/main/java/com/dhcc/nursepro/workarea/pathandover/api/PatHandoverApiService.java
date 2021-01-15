package com.dhcc.nursepro.workarea.pathandover.api;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

public class PatHandoverApiService {

    /**
     * 获取交接列表接口：getConnectList
     * 入参：startDate, endDate, wardId
     */
    public static void getConnectList(String startDate, String endDate, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("type", type);
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));

        WebServiceUtils.callWebService("getConnectList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 接口：扫码获取或者输入登记号获取getScanConnect
     * 入参：暂时没有
     */
    public static void getScanConnect(String regNo, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("regNo", regNo);

        WebServiceUtils.callWebService("getScanConnect", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 点击确认调用接口：saveConnect，入参：regNo登记号 type是getScanConnect返回的类型, userId, wardId。
     * 返回值中的recData是ID,之后的接口调用会用到。
     */
    public static void saveConnect(String regNo, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        properties.put("regNo", regNo);
        properties.put("type", type);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));

        WebServiceUtils.callWebService("saveConnect", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * getConnectAndPat，获取护士需确认的信息
     * 入参登记号：regNo
     */
    public static void getConnectAndPat(String regNo, String type, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        properties.put("regNo", regNo);
        properties.put("type", type);

        WebServiceUtils.callWebService("getConnectAndPat", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getNurseInfo(String userCode, String password, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        properties.put("userCode", userCode);
        properties.put("password", password);

        WebServiceUtils.callWebService("getNurseInfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    /**
     * 保存调用的接口：saveConnectSub，
     * 入参：parentId saveConnect返回的父表ID, date, time, regNo, type, firstUser, secondUser, thirdUser, wardId
     */
    public static void saveConnectSub(String parentId, String regNo, String type, String firstUser, String secondUser, String thirdUser, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        properties.put("parentId", parentId);
        properties.put("date", "");
        properties.put("time", "");
        properties.put("regNo", regNo);
        properties.put("type", type);
        properties.put("firstUser", firstUser);
        properties.put("secondUser", secondUser);
        properties.put("thirdUser", thirdUser);
        properties.put("wardId", SPUtils.getInstance().getString(SharedPreference.WARDID));

        WebServiceUtils.callWebService("saveConnectSub", properties, new WebServiceUtils.WebServiceCallBack() {
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
