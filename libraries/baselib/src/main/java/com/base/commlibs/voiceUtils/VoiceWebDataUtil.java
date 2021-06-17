package com.base.commlibs.voiceUtils;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.api.BaseWebApiManager;
import com.base.commlibs.voiceUtils.bean.VoiceVisalBean;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;

/**
 * com.base.commlibs.voiceUtils
 * <p>
 * author Q
 * Date: 2021/6/15
 * Time:14:43
 */
public class VoiceWebDataUtil {
    public static void getBedMapData() {
        BaseWebApiManager.getPatListToVoiceBean(new BaseWebApiManager.GetVoicePatListCallback() {
            @Override
            public void onSuccess(String bedMapJson) {
                SPUtils.getInstance().put(SharedPreference.VOICE_PAT_LIST,bedMapJson);
            }

            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showLong("error" + code + ":" + msg);
            }
        });
    }

    public static void getVisData(){

        BaseWebApiManager.getVitalSignList("", "", new BaseWebApiManager.GetVitalSignListCallback() {
            @Override
            public void onSuccess(VoiceVisalBean vitalSignBean) {
                Gson gson = new Gson();
                String displayListJsonStr = gson.toJson(vitalSignBean.getPatInfoList());
                SPUtils.getInstance().put(SharedPreference.DISPLAYLIST, displayListJsonStr);
                SPUtils.getInstance().put(SharedPreference.VOICE_DATETIME_POINT,vitalSignBean.getDatePoint()+ " " + vitalSignBean.getTimePoint());
            }

            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showLong("error" + code + ":" + msg);
            }
        });
    }

}
