package com.base.commlibs.voiceUtils;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.voiceUtils.api.BaseWebApiManager;
import com.base.commlibs.voiceUtils.bean.VoiceVisalBean;
import com.base.commlibs.voiceUtils.bean.VoiceVitalSignPatBean;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
//                String displayListJsonStr = gson.toJson(vitalSignBean.getPatInfoList());
//                SPUtils.getInstance().put(SharedPreference.DISPLAYLIST, displayListJsonStr);
                List<VoiceVitalSignPatBean> patList = new ArrayList<>();

                for (int i = 0; i < vitalSignBean.getPatInfoList().size(); i++) {
                    VoiceVisalBean.PatInfoListBean patInfoListBeanI = vitalSignBean.getPatInfoList().get(i);
                    VoiceVitalSignPatBean patBean = new VoiceVitalSignPatBean(patInfoListBeanI.getBedCode(), patInfoListBeanI.getName(), patInfoListBeanI.getRegNo(), patInfoListBeanI.getEpisodeId());
                    patList.add(patBean);
                }

                //将列表数据存储在sp，避免Intent传输数据过大报错
                String patListJsonStr = gson.toJson(patList);
                SPUtils.getInstance().put(SharedPreference.DISPLAYLIST, patListJsonStr);

                SPUtils.getInstance().put(SharedPreference.VOICE_DATETIME_POINT,vitalSignBean.getDatePoint()+ " " + vitalSignBean.getTimePoint());
            }

            @Override
            public void onFail(String code, String msg) {
                ToastUtils.showLong("error" + code + ":" + msg);
            }
        });
    }

    public static void getVoicScore() {
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

}
