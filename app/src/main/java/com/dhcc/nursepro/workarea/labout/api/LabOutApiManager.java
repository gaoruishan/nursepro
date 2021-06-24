package com.dhcc.nursepro.workarea.labout.api;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.labout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutDetailBean;
import com.dhcc.nursepro.workarea.labout.bean.LabOutListAllBean;
import com.dhcc.nursepro.workarea.labout.bean.LabUnOutListBean;
import com.google.gson.Gson;

import java.util.HashMap;

public class LabOutApiManager {

    public static void getLabOutListMsg(HashMap<String, String> map, String method, final getLabOutCallBack callback) {

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LabOutListAllBean labOutListAllBean = gson.fromJson(jsonStr, LabOutListAllBean.class);
                        if (ObjectUtils.isEmpty(labOutListAllBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labOutListAllBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labOutListAllBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labOutListAllBean.getMsgcode(), labOutListAllBean.getMsg());
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

    public static void getLabOutDetailMsg(HashMap<String, String> map, String method, final getLabOutDetailCallBack callback) {

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        LabOutDetailBean labOutDetailBean = gson.fromJson(jsonStr, LabOutDetailBean.class);
                        if (ObjectUtils.isEmpty(labOutDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(labOutDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(labOutDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(labOutDetailBean.getMsgcode(), labOutDetailBean.getMsg());
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

    public static void delOrdMsg(HashMap<String, String> map, String method, final delOrdCallBack callback) {

        LabOutApiService.getLabOutMsg(map, method, new LabOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        DelOrderBean delOrderBean = gson.fromJson(jsonStr, DelOrderBean.class);
                        if (ObjectUtils.isEmpty(delOrderBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(delOrderBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(delOrderBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(delOrderBean.getMsgcode(), delOrderBean.getMsg());
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
    //	wardId,
    //	userId,
    //	hospitalId,
    //	groupId,
    //	startDate,
    //	endDate,
    //	locId,
    //	pageNo
    public static void GetUnOutLabData(String startDate, String endDate, String pageNo, com.base.commlibs.http.CommonCallBack<LabUnOutListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        CommWebService.addLocId(properties);
        CommWebService.addGroupId(properties);
        CommWebService.addHospitalId(properties);
        properties.put("startDate", startDate);
        properties.put("endDate", endDate);
        properties.put("pageNo", pageNo);
        CommWebService.call(NurseAPI.GetUnOutLabData,properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<LabUnOutListBean> parserUtil = new ParserUtil<>();
                LabUnOutListBean bean = parserUtil.parserResult(jsonStr, callBack, LabUnOutListBean.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    //新建，查询检验交接单
    public interface getLabOutCallBack extends CommonCallBack {
        void onSuccess(LabOutListAllBean labOutListAllBean);
    }

    //交接单详情，新增删除项目
    public interface getLabOutDetailCallBack extends CommonCallBack {
        void onSuccess(LabOutDetailBean labOutDetailBean);
    }

    //发送，撤销发送
    public interface delOrdCallBack extends CommonCallBack {
        void onSuccess(DelOrderBean delOrderBean);
    }
}
