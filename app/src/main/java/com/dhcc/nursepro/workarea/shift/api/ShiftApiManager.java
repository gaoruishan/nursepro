package com.dhcc.nursepro.workarea.shift.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.shift.bean.ShiftBean;
import com.dhcc.nursepro.workarea.shift.bean.ShiftDetailBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * com.dhcc.nursepro.workarea.shift.api
 * <p>
 * author Q
 * Date: 2019/11/12
 * Time:15:42
 */
public class ShiftApiManager {
    public interface CommonCallBack {
        void onFail(String code, String msg);
    }
    public interface GetShiftTopListCallback extends CommonCallBack {
        void onSuccess(ShiftBean shiftBean);
    }

    public interface GetShiftDetailListCallback extends CommonCallBack {
        void onSuccess(ShiftDetailBean shiftBean);
    }

    public static void getShiftList(HashMap<String, String> map, String method, final GetShiftTopListCallback callback){
        ShiftApiService.getShiftList(map, method, new ShiftApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

//                jsonStr = "{\"msg\":\"成功\",\"msgcode\":\"999999\",\"shiftData\":[{\"data\":[{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"72\",\"itemName\":\"入院\",\"row\":\"1\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"8z\",\"itemName\":\"转入\",\"row\":\"1\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"9\",\"itemName\":\"转出\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"10\",\"itemName\":\"出院\",\"row\":\"2\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"12\",\"itemName\":\"分娩完成\",\"row\":\"3\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"13\",\"itemName\":\"今日手术\",\"row\":\"4\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"14\",\"itemName\":\"明日手术\",\"row\":\"4\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"212410\",\"itemName\":\"压疮风险\",\"row\":\"4\"},{\"col\":\"1\",\"count\":\"5\",\"itemDR\":\"294\",\"itemName\":\"原有\",\"row\":\"1\"},{\"col\":\"1\",\"count\":\"5\",\"itemDR\":\"30\",\"itemName\":\"现有\",\"row\":\"5\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"31\",\"itemName\":\"新入\",\"row\":\"1\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"32\",\"itemName\":\"死亡\",\"row\":\"2\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"33\",\"itemName\":\"病危\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"34\",\"itemName\":\"病重\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"35\",\"itemName\":\"特级护理\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"36\",\"itemName\":\"一级护理\",\"row\":\"3\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"37\",\"itemName\":\"新生儿\",\"row\":\"4\"}],\"name\":\"白班\",\"sign\":\"\",\"timeRange\":\"08:00~17:00\",\"type\":\"1||4\"},{\"data\":[{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"7\",\"itemName\":\"入院\",\"row\":\"1\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"8\",\"itemName\":\"转入\",\"row\":\"1\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"9\",\"itemName\":\"转出\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"10\",\"itemName\":\"出院\",\"row\":\"2\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"12\",\"itemName\":\"分娩完成\",\"row\":\"3\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"13\",\"itemName\":\"今日手术\",\"row\":\"4\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"14\",\"itemName\":\"明日手术\",\"row\":\"4\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"20\",\"itemName\":\"压疮风险\",\"row\":\"4\"},{\"col\":\"1\",\"count\":\"5\",\"itemDR\":\"29\",\"itemName\":\"原有\",\"row\":\"1\"},{\"col\":\"1\",\"count\":\"5\",\"itemDR\":\"30\",\"itemName\":\"现有\",\"row\":\"5\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"31\",\"itemName\":\"新入\",\"row\":\"1\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"32\",\"itemName\":\"死亡\",\"row\":\"2\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"33\",\"itemName\":\"病危\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"34\",\"itemName\":\"病重\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"35\",\"itemName\":\"特级护理\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"36\",\"itemName\":\"一级护理\",\"row\":\"3\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"37\",\"itemName\":\"新生儿\",\"row\":\"4\"}],\"name\":\"小夜班\",\"sign\":\"\",\"timeRange\":\"17:01~01:00\",\"type\":\"1||5\"},{\"data\":[{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"7\",\"itemName\":\"入院\",\"row\":\"1\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"8\",\"itemName\":\"转入\",\"row\":\"1\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"9\",\"itemName\":\"转出\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"10\",\"itemName\":\"出院\",\"row\":\"2\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"1234232\",\"itemName\":\"分娩完成\",\"row\":\"3\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"12343\",\"itemName\":\"今日手术\",\"row\":\"4\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"14\",\"itemName\":\"明日手术\",\"row\":\"4\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"20\",\"itemName\":\"压疮风险\",\"row\":\"4\"},{\"col\":\"1\",\"count\":\"5\",\"itemDR\":\"29\",\"itemName\":\"原有\",\"row\":\"1\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"30\",\"itemName\":\"现有\",\"row\":\"5\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"31\",\"itemName\":\"新入\",\"row\":\"1\"},{\"col\":\"3\",\"count\":\"0\",\"itemDR\":\"32\",\"itemName\":\"死亡\",\"row\":\"2\"},{\"col\":\"2\",\"count\":\"0\",\"itemDR\":\"33\",\"itemName\":\"病危\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"34\",\"itemName\":\"病重\",\"row\":\"3\"},{\"col\":\"4\",\"count\":\"0\",\"itemDR\":\"35\",\"itemName\":\"特级护理\",\"row\":\"2\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"36\",\"itemName\":\"一级护理\",\"row\":\"3\"},{\"col\":\"1\",\"count\":\"0\",\"itemDR\":\"37\",\"itemName\":\"新生儿\",\"row\":\"4\"}],\"name\":\"大夜班\",\"sign\":\"\",\"timeRange\":\"01:01~07:59\",\"type\":\"1||6\"}],\"status\":\"0\"}";
                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ShiftBean shiftBean = gson.fromJson(jsonStr, ShiftBean.class);
//                        Map map1 = gson.fromJson(jsonStr,Map.class);

                        if (ObjectUtils.isEmpty(shiftBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(shiftBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(shiftBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(shiftBean.getMsgcode(), shiftBean.getMsg());
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
    public static void getShiftDataDetail(HashMap<String, String> map, String method, final GetShiftDetailListCallback callback){
        ShiftApiService.getShiftList(map, method, new ShiftApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        ShiftDetailBean shiftDetailBean = gson.fromJson(jsonStr, ShiftDetailBean.class);

                        if (ObjectUtils.isEmpty(shiftDetailBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(shiftDetailBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(shiftDetailBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(shiftDetailBean.getMsgcode(), shiftDetailBean.getMsg());
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

}
