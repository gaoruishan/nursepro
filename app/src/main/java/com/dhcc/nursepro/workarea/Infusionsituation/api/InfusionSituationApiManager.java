package com.dhcc.nursepro.workarea.Infusionsituation.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionByWardBean;
import com.dhcc.nursepro.workarea.Infusionsituation.bean.GetInfusionDetailByWardBean;
import com.dhcc.nursepro.workarea.infusiondrugreceive.api.DrugReceiveApiService;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.BatchIFSaveResultBean;
import com.dhcc.nursepro.workarea.infusiondrugreceive.bean.IfOrdListBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * InfusionSituationApiManager
 *
 *
 * @author DevLix126
 * created at 2021/4/19 14:34
 */
public class InfusionSituationApiManager {

    public static void GetInfusionByWard(HashMap<String, String> map, String method, final GetInfusionByWardCallback callback) {

        InfusionSituationApiService.GetInfusionByWard(map, method, new InfusionSituationApiService.serveCallback() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetInfusionByWardBean getInfusionByWardBean = gson.fromJson(jsonStr, GetInfusionByWardBean.class);
                        if (ObjectUtils.isEmpty(getInfusionByWardBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getInfusionByWardBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getInfusionByWardBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getInfusionByWardBean.getMsgcode(), getInfusionByWardBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
                }
            }
        });
    }

    public static void GetInfusionDetailByWard(HashMap<String, String> map, String method, final GetInfusionDetailByWardCallback callback) {

        InfusionSituationApiService.GetInfusionDetailByWard(map, method, new InfusionSituationApiService.serveCallback() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        GetInfusionDetailByWardBean getInfusionDetailByWardBean = gson.fromJson(jsonStr, GetInfusionDetailByWardBean.class);
                        if (ObjectUtils.isEmpty(getInfusionDetailByWardBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(getInfusionDetailByWardBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(getInfusionDetailByWardBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(getInfusionDetailByWardBean.getMsgcode(), getInfusionDetailByWardBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
                }
            }
        });
    }


    public interface GetInfusionByWardCallback extends CommonCallBack{
        void onSuccess(GetInfusionByWardBean getInfusionByWardBean);
    }

    public interface GetInfusionDetailByWardCallback extends CommonCallBack{
        void onSuccess(GetInfusionDetailByWardBean getInfusionDetailByWardBean);
    }
    public interface CommonCallBack {
        void onFail(String code, String msg);
    }
}
