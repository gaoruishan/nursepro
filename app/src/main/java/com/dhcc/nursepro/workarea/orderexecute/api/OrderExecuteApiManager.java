package com.dhcc.nursepro.workarea.orderexecute.api;

import android.util.Log;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.nursepro.utils.DateUtils;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecResultBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.OrderExecuteBean;
import com.dhcc.nursepro.workarea.orderexecute.bean.ScanResultBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderSearchApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class OrderExecuteApiManager {
    private static List<Map<String, String>> locsList;
    private static String LocJson;

    /**
     * Description: 皮试计时
     * Input：	oeoriId 执行记录Id
     * Return： w ##class(Nur.PDA.Order).skinTime("194||57||1",1,"50分钟",149)
     */
    public static void skinTime(String oeoriId, String observeTime, String note, com.base.commlibs.http.CommonCallBack<CommResult> callBack) {
        OrderExecuteApiService.skinTime(oeoriId, observeTime, note, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CommResult> parserUtil = new ParserUtil<>();
                CommResult bean = parserUtil.parserResult(jsonStr, callBack, CommResult.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getOrder(String pageNo,String regNo, String sheetCode, String startDate, String startTime, String endDate, String endTime,String screenParts, final GetOrderCallback callback) {
        OrderExecuteApiService.getOrder(pageNo,regNo, sheetCode, startDate, startTime, endDate, endTime,screenParts, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    OrderExecuteBean orderExecuteBean = null;
                    try {
                        orderExecuteBean = gson.fromJson(jsonStr, OrderExecuteBean.class);
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                    }
                    if (!ObjectUtils.isEmpty(orderExecuteBean)) {
                        if ("0".equals(orderExecuteBean.getStatus())) {
                            if (callback != null) {
                                Map bedMapMap = gson.fromJson(jsonStr, Map.class);

                                List<Map<String, String>> patInfoMapList =new ArrayList<>();
                                patInfoMapList = (List<Map<String, String>>) bedMapMap.get("orders");
                                for (int i = 0; i <patInfoMapList.size() ; i++) {
                                    Map patMap = patInfoMapList.get(i);
                                    List<Map<String, String>> patInfoMapListOrder=new ArrayList<>();
                                    patInfoMapListOrder = (List<Map<String, String>>) patMap.get("patOrds");
                                    for (int j = 0; j <patInfoMapListOrder.size() ; j++) {
                                        List<Map<String, Map>> patInfoMapList1 =new ArrayList<>();
                                        patInfoMapList1 = (List<Map<String, Map>>) patInfoMapListOrder.get(j);
                                        Map<String, String> patOrdMap = new HashMap<>();
                                        for (int k = 0; k < patInfoMapList1.size(); k++) {
                                            patOrdMap = patInfoMapList1.get(k).get("orderInfo");
                                            orderExecuteBean.getOrders().get(i).getPatOrds().get(j).get(k).setOrderInfoMap(patOrdMap);
                                        }
                                    }
                                }

                                callback.onSuccess(orderExecuteBean);
                            }
                        } else {
                            if (callback != null) {
                                callback.onFail(orderExecuteBean.getMsgcode(), orderExecuteBean.getMsg());
                            }
                        }
                    }


                }
            }
        });
    }

    public static void execOrSeeOrder(String speed,String barCode,String starttime, String orderDesc, String patInfo, String scanFlag, String batch, String auditUserCode, String auditUserPass, String oeoreId, String execStatusCode, String wayNo, String deviceNo,final ExecOrSeeOrderCallback callback) {

        SPUtils spUtils = SPUtils.getInstance();

        HashMap<String, String> properties = new HashMap<>();
        properties.put("speed",speed);
        properties.put("orderInfo", orderDesc);
        properties.put("patInfo", patInfo);
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
        properties.put("wayNo",wayNo);
        properties.put("deviceNo",deviceNo);


        Log.i("OrderExecute", "execOrSeeOrder: " + properties.toString());
        locsList = new ArrayList<>();
        LocJson = spUtils.getString(SharedPreference.LOCALREQUEST, "");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        locsList = new ArrayList<Map<String, String>>();
        if (LocJson != "") {
            locsList = gson.fromJson(LocJson, type);
        }
        properties.put("soap_method", "execOrSeeOrder");
        properties.put("remarks", "医嘱执行:" + oeoreId);
        properties.put("failreason", "网络异常");
        properties.put("starttime", starttime);


        execOrSeeOrder(speed, barCode, scanFlag, batch, auditUserCode, auditUserPass, oeoreId, execStatusCode, wayNo,deviceNo, callback, properties);
    }

    public static void execOrSeeOrder(String speed, String barCode, String scanFlag, String batch, String auditUserCode, String auditUserPass, String oeoreId, String execStatusCode, String wayNo,String deviceNo, ExecOrSeeOrderCallback callback, HashMap<String, String> properties) {
        OrderExecuteApiService.execOrSeeOrder(speed,barCode,scanFlag, batch, auditUserCode, auditUserPass, oeoreId, execStatusCode, wayNo,deviceNo, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {

                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                    saveRequest(properties, "yes");
                } else {
                    try {
                        OrderExecResultBean orderExecResultBean = gson.fromJson(jsonStr, OrderExecResultBean.class);
                        if (ObjectUtils.isEmpty(orderExecResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                            saveRequest(properties, "yes");
                        } else {
                            saveRequest(properties, "no");
                            if ("0".equals(orderExecResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(orderExecResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(orderExecResultBean.getMsgcode(), orderExecResultBean.getMsg());
                                }
                            }
                        }
                    } catch (Exception e) {
                        callback.onFail("-2", "网络错误，数据解析失败");
                        saveRequest(properties, "yes");
                    }
                }
            }
        });
    }

    public static void saveRequest(HashMap<String, String> properties, String ifSave) {
        if (ifSave.equals("yes")) {
            String[] orders = properties.get("oeoreId").split("\\^");
            String[] orderdescs = properties.get("orderInfo").split("\\^");
            String[] datetimes = properties.get("starttime").split("\\^");
            for (int i = 0; i < orders.length; i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.putAll(properties);
                hashMap.put("oeoreId", orders[i]);
                hashMap.put("orderInfo", orderdescs[i]);
                hashMap.put("remarks", "医嘱：" + orders[i]);
                hashMap.put("starttime", datetimes[i]);
                hashMap.put("execDate", DateUtils.getDateFromSystem());
                hashMap.put("execTime", DateUtils.getTimeFromSystem());
                Gson gson = new Gson();
                SPUtils spUtils = SPUtils.getInstance();
                Boolean b = true;
                for (int j = 0; j < locsList.size(); j++) {
                    if (locsList.get(j).get("oeoreId").equals(orders[i])) {
                        b = false;
                        Log.d("iii", "saveRequest: " + b);
                    }
                }
                if (b) {
                    locsList.add(hashMap);
                }
                LocJson = gson.toJson(locsList);
                spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
            }


        } else {
            SPUtils spUtils = SPUtils.getInstance();
            List<HashMap<String, String>> localList = new ArrayList<>();
            Gson gson1 = new Gson();
            java.lang.reflect.Type type = new TypeToken<List<HashMap<String, String>>>() {
            }.getType();
            String LocalJson = spUtils.getString(SharedPreference.LOCALREQUEST, "");
            if (LocalJson != "") {
                localList = gson1.fromJson(LocalJson, type);
            }
            String[] orders = properties.get("oeoreId").split("\\^");
            int oo = orders.length;
            for (int j = 0; j < orders.length; j++) {
                for (int i = 0; i < localList.size(); i++) {
                    if (localList.get(i).get("oeoreId").equals(orders[j])) {
                        localList.remove(i);
                        Gson gson = new Gson();
                        String LocJson = gson.toJson(localList);
                        spUtils.put(SharedPreference.LOCALREQUEST, LocJson);
                    }
                }

            }
        }


    }

    public static void getScanMsg(String episodeId, String scanInfo, final GetScanCallBack callback) {

        OrderExecuteApiService.getOrdersMsg(episodeId, scanInfo, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanResultBean scanResultBean = gson.fromJson(jsonStr, ScanResultBean.class);
                        if (ObjectUtils.isEmpty(scanResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanResultBean.getMsgcode(), scanResultBean.getMsg());
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

    public static void getScanMsgByMain(String episodeId, String scanInfo, final GetScanCallBack callback) {

        OrderExecuteApiService.getOrdersMsgByMain(episodeId, scanInfo, new OrderExecuteApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ScanResultBean scanResultBean = gson.fromJson(jsonStr, ScanResultBean.class);
                        if (ObjectUtils.isEmpty(scanResultBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(scanResultBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(scanResultBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(scanResultBean.getMsgcode(), scanResultBean.getMsg());
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

    //扫描腕带,医嘱码
    public interface GetScanCallBack extends CommonCallBack {
        void onSuccess(ScanResultBean scanResultBean);
    }

    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface GetOrderCallback extends CommonCallBack {
        void onSuccess(OrderExecuteBean orderExecuteBean);
    }

    public interface ExecOrSeeOrderCallback extends CommonCallBack {
        void onSuccess(OrderExecResultBean orderExecResultBean);
    }
}
