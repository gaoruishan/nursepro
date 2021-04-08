package com.dhcc.nursepro.workarea.nurrecordnew.api;

import com.base.commlibs.NurseAPI;
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
public class NurRecordNewApiService extends NurseAPI {

    public static void getInWardPatList(final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("wardId", spUtils.getString(SharedPreference.WARDID));
        properties.put("userId", spUtils.getString(SharedPreference.USERID));


        WebServiceUtils.callWebService(getInWardPatList, properties, new WebServiceUtils.WebServiceCallBack() {
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

        WebServiceUtils.callWebService(getModelList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    public static void getNewEmrList(String parr, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("parr", parr);

        WebServiceUtils.callWebService(getNewEmrList, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void GetXmlValues(String episodeId, String emrCode, String id, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("episodeId", episodeId);
        properties.put("emrCode", emrCode);
        properties.put("id", id);

        WebServiceUtils.callWebService(GetXmlValues, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getDataSource(String dataSourceRef, String episodeId, final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("dataSourceRef", dataSourceRef);
        properties.put("episodeId", episodeId);

        WebServiceUtils.callWebService(getDataSource, properties, new WebServiceUtils.WebServiceCallBack() {
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

        WebServiceUtils.callWebService(saveNewEmrData, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }

    public static void getKnowledgeTree(final ServiceCallBack callback) {
        SPUtils spUtils = SPUtils.getInstance();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("locId", spUtils.getString(SharedPreference.LOCID));
        properties.put("hospitalId", spUtils.getString(SharedPreference.HOSPITALROWID));

        WebServiceUtils.callWebService(getKnowledgeTree, properties, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                callback.onResult(result);
            }
        });
    }


    public static void getKnowledgeContent(String knowledgeId,final ServiceCallBack callback) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("knowledgeId", knowledgeId);

        WebServiceUtils.callWebService(getKnowledgeContent, properties, new WebServiceUtils.WebServiceCallBack() {
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
