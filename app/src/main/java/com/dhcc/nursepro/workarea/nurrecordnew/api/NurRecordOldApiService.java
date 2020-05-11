package com.dhcc.nursepro.workarea.nurrecordnew.api;

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
    public static void getPGDVal(String pgdId, String methodName, final ServiceCallBack callback) {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("pgdId", pgdId);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
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
        properties.put("emrCode", emrCode);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));

        WebServiceUtils.callWebService("getEmrPatinfo", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //点击按钮同步信息
    public static void linkEmrData(String episodeID, String emrCode, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeID);
        properties.put("emrCode", emrCode);

        WebServiceUtils.callWebService("linkEmrData", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    //保存评估单内容
    public static void savePGDData(String parr, String pgdId, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);
        properties.put("pgdId", pgdId);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取记录单列表
    public static void getCareRecComm(String parr, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取记录单数据
    public static void getJLDVal(String par, String rw, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("par", par);
        properties.put("rw", rw);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //保存记录单数据
    public static void saveJLDData(String parr, String episodeID, String emrCode, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        //episodeID, parr, userId, recTyp, userGroup
        properties.put("parr", parr);
        properties.put("episodeId", episodeID);
        properties.put("userId", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("recTyp", emrCode);
        properties.put("userGroup", "");

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取多次评估单列表
    public static void getMPGDList(String parr, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取多次评估单数据
    public static void getMPGDVal(String par, String rw, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("par", par);
        properties.put("rw", rw);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //保存评估单内容
    public static void saveMPGDData(String parr, String pgdId, String methodName, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);
        properties.put("pgdId", pgdId);

        WebServiceUtils.callWebService(methodName, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //获取单据View 默认值/关联/跳转 内容
    public static void getItemConfigByEmrCode(String episodeId, String emrCode, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("emrCode", emrCode);

        WebServiceUtils.callWebService("getItemConfigByEmrCode", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void GetXmlValues(String emrCode, String id, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("emrCode", emrCode);
        properties.put("id", id);

        WebServiceUtils.callWebService("GetXmlValues", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getNewEmrList(String parr, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);

        WebServiceUtils.callWebService("getNewEmrList", properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    //(guId, episodeId, recordId, parr, groupDesc, locId, wardId, userId)
    public static void saveNewEmrData(String guId, String episodeId, String recordId, String parr, final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("guId", guId);
        properties.put("episodeId", episodeId);
        properties.put("recordId", recordId);
        properties.put("parr", parr);
        properties.put("groupDesc", spUtils.getString(SharedPreference.GROUPDESC));
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));

        WebServiceUtils.callWebService("saveNewEmrData", properties, new WebServiceUtils.WebServiceCallBack() {
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
