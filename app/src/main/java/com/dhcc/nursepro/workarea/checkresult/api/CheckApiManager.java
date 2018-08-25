package com.dhcc.nursepro.workarea.checkresult.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.checkresult.CheckPatsFragment;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckPatsListBean;
import com.dhcc.nursepro.workarea.checkresult.bean.CheckResultListBean;
import com.dhcc.nursepro.workarea.labresult.api.LabApiService;
import com.dhcc.nursepro.workarea.labresult.bean.LabReslutDetailBean;
import com.dhcc.nursepro.workarea.labresult.bean.LabResultListBean;
import com.dhcc.nursepro.workarea.labresult.bean.PatsListBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class CheckApiManager {

    public interface CommonCallBack{
        void onFail(String code, String msg);
    }

    public interface GetCheckResultCallback extends CommonCallBack{
        void onSuccess(CheckPatsListBean checkPatsListBean);
    }

    //获取病人列表
    public static void getPatsList(HashMap<String,String> map,String method,final GetCheckResultCallback callback) {

        LabApiService.getCheckLabMsg(map,method,new LabApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    CheckPatsListBean checkPatsListBean = gson.fromJson(jsonStr, CheckPatsListBean.class);
                    if (ObjectUtils.isEmpty(checkPatsListBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (checkPatsListBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(checkPatsListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(checkPatsListBean.getMsgcode(), checkPatsListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }

    public interface CheckListCallback extends CommonCallBack{
        void onSuccess(CheckResultListBean checkResultListBean);
    }

    public static void getCheckListMsg(HashMap<String,String> map,String method,final CheckListCallback callback) {

        LabApiService.getCheckLabMsg(map,method,new LabApiService.ServiceCallBack(){
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {

                    CheckResultListBean checkResultListBean = gson.fromJson(jsonStr, CheckResultListBean.class);
                    if (ObjectUtils.isEmpty(checkResultListBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (checkResultListBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(checkResultListBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(checkResultListBean.getMsgcode(), checkResultListBean.getMsg());
                            }
                        }
                    }
                }

            }
        });
    }


}
