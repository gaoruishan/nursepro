package com.dhcc.nursepro.workarea.bloodtransfusionsystem.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodInfoBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.BloodOperationResultBean;
import com.dhcc.nursepro.workarea.bloodtransfusionsystem.bean.PatWristInfoBean;
import com.google.gson.Gson;

/**
 * BloodTSApiManager
 *
 * @author DevLix126
 * @date 2018/9/19
 */
public class BloodTSApiManager {

    public static void getPatWristInfo(String regNo, final GetPatWristInfoCallback callback) {
        BloodTSApiService.getPatWristInfo(regNo, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {
                    PatWristInfoBean patWristInfoBean = gson.fromJson(jsonStr, PatWristInfoBean.class);
                    if (ObjectUtils.isEmpty(patWristInfoBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (patWristInfoBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(patWristInfoBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(patWristInfoBean.getMsgcode(), patWristInfoBean.getMsg());
                            }
                        }
                    }

                }
            }
        });
    }

    public static void getBloodInfo(String bloodbagId, String bloodProductId, final GetBloodInfoCallback callback) {
        BloodTSApiService.getBloodInfo(bloodbagId, bloodProductId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {
                    BloodInfoBean bloodInfoBean = gson.fromJson(jsonStr, BloodInfoBean.class);
                    if (ObjectUtils.isEmpty(bloodInfoBean)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if (bloodInfoBean.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess(bloodInfoBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(bloodInfoBean.getMsgcode(), bloodInfoBean.getMsg());
                            }
                        }
                    }

                }
            }
        });
    }
//

    public static void bloodReceive(String bloodbagId, String bloodProductId,String bloodGroup, String patBldGroup,String episodeId, String productDesc,String rowId, final BloodOperationResultCallback callback) {
        BloodTSApiService.bloodReceive(bloodbagId, bloodProductId,bloodGroup,patBldGroup,episodeId,productDesc,rowId, new BloodTSApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络请求失败");
                } else {
                    BloodOperationResultBean bloodOperationResult = gson.fromJson(jsonStr, BloodOperationResultBean.class);
                    if (ObjectUtils.isEmpty( bloodOperationResult)) {
                        callback.onFail("-1", "网络请求失败");
                    } else {
                        if ( bloodOperationResult.getStatus().equals("0")) {
                            if (callback != null) {
                                callback.onSuccess( bloodOperationResult);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail( bloodOperationResult.getMsgcode(),  bloodOperationResult.getMsg());
                            }
                        }
                    }

                }
            }
        });
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetPatWristInfoCallback extends CommonCallBack {
        void onSuccess(PatWristInfoBean patWristInfoBean);
    }

    public interface GetBloodInfoCallback extends CommonCallBack {
        void onSuccess(BloodInfoBean bloodInfoBean);
    }

    public interface BloodOperationResultCallback extends CommonCallBack {
        void onSuccess(BloodOperationResultBean bloodOperationResult);
    }
}
