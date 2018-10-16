package com.dhcc.nursepro.workarea.bedselect.api;

import com.blankj.utilcode.util.ObjectUtils;
import com.dhcc.nursepro.workarea.bedselect.bean.BedSelectListBean;
import com.google.gson.Gson;

/**
 * BedListApiManager
 *
 * @author DevLix126
 * @date 2018/8/24
 */
public class BedListApiManager {

    public static void getBedList(final GetBedListCallback callback) {
        BedListApiService.getBedList(new BedListApiService.ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                Gson gson = new Gson();

                if (jsonStr.isEmpty()) {
                    callback.onFail("-1", "网络错误，请求数据为空");
                } else {
                    try {
                        BedSelectListBean bedSelectListBean = gson.fromJson(jsonStr, BedSelectListBean.class);
                        if (ObjectUtils.isEmpty(bedSelectListBean)) {
                            callback.onFail("-3", "网络错误，数据解析为空");
                        } else {
                            if ("0".equals(bedSelectListBean.getStatus())) {
                                if (callback != null) {
                                    callback.onSuccess(bedSelectListBean);
                                }
                            } else {
                                if (callback != null) {
                                    callback.onFail(bedSelectListBean.getMsgcode(), bedSelectListBean.getMsg());
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

    public interface GetBedListCallback extends CommonCallBack {
        void onSuccess(BedSelectListBean bedListItem);
    }
}
