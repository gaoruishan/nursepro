package com.dhcc.nursepro.workarea.nurrecordold.api;

import com.base.commlibs.constant.SharedPreference;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.wsutils.WebServiceUtils;

import java.util.HashMap;

/**
 * NurRecordOldApiService
 *
 * @author Devlix126
 * created at 2019/7/5 10:35
 */
public class NurRecordOldApiService {

    public static void getInWardPatList(final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));


        WebServiceUtils.callWebService("getInWardPatList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getModelList(String episodeID, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("episodeId", episodeID);

        WebServiceUtils.callWebService("getModelList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取评估单Id
    public static void getPGDId(String episodeID, String emrCode, final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeID);
        properties.put("emrCode", emrCode);

        WebServiceUtils.callWebService("getPGDId", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取评估单Value
    public static void getPGDVal(String pgdId, final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("pgdId", pgdId);

        WebServiceUtils.callWebService("getPGDVal", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取View XML
    public static void getEmrXML(String episodeID, String emrCode, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();

        HashMap<String, String> properties = new HashMap<>();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("episodeId", episodeID);
        properties.put("emrCode", emrCode);

        WebServiceUtils.callWebService("getEmrXml", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取服务器日期时间
    public static void getDateTime(final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();

        WebServiceUtils.callWebService("getDateTime", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取患者信息
    public static void getEmrPatinfo(String episodeID, String emrCode, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeID);

        WebServiceUtils.callWebService("getEmrPatinfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //保存评估单内容
    public static void savePGDData(String parr, String pgdId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);
        properties.put("pgdId", pgdId);

        WebServiceUtils.callWebService("savePGDData", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取记录单列表
    public static void getCareRecComm(String parr, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);

        WebServiceUtils.callWebService("getCareRecComm", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取记录单数据
    public static void getJLDVal(String par, String rw, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("par", par);
        properties.put("rw", rw);

        WebServiceUtils.callWebService("getJLDData", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //保存记录单数据
    public static void saveJLDData(String parr, String episodeID, String emrCode, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        //episodeID, parr, userId, recTyp, userGroup
        properties.put("parr", parr);
        properties.put("episodeID", episodeID);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("recTyp", emrCode);
        properties.put("userGroup", "");

        WebServiceUtils.callWebService("saveJLDData", properties, new WebServiceUtils.WebServiceCallBack() {
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
