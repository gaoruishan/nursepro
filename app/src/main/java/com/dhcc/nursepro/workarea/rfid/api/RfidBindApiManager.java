package com.dhcc.nursepro.workarea.rfid.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.plyout.api.PlyOutApiManager;
import com.dhcc.nursepro.workarea.plyout.api.PlyOutApiService;
import com.dhcc.nursepro.workarea.plyout.bean.DelOrderBean;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutDetailBean;
import com.dhcc.nursepro.workarea.plyout.bean.PlyOutListAllBean;
import com.dhcc.nursepro.workarea.rfid.bean.RfidPatBean;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.rfid.api
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:42
 */
public class RfidBindApiManager {

    public static void getRfidPatList(HashMap<String, String> map, String method, final getRfidPatListCallBack callback) {

        RfidBindApiService.getRfidPatList(map, method, new PlyOutApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        RfidPatBean rfidPatBean = gson.fromJson(jsonStr, RfidPatBean.class);
                        if (ObjectUtils.isEmpty(rfidPatBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(rfidPatBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(rfidPatBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(rfidPatBean.getMsgcode(), rfidPatBean.getMsg());
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



    public interface CommonCallBack {
        void onFail(String code, String msg);
    }

    public interface getRfidPatListCallBack extends CommonCallBack {
        void onSuccess(RfidPatBean rfidPatBean);
    }

}
