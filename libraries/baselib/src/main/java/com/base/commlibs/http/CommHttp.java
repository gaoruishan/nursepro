package com.base.commlibs.http;

import android.text.TextUtils;
import android.util.Log;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.bean.BroadcastListBean;
import com.base.commlibs.bean.NurseConfig;
import com.base.commlibs.bean.PatientListBean;
import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.utils.HttpUtil;
import com.base.commlibs.utils.SimpleCallBack;
import com.base.commlibs.utils.TransBroadcastUtil;
import com.base.commlibs.wsutils.BaseWebServiceUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * 公共请求
 * @author:gaoruishan
 * @date:202019-09-27/10:21
 * @email:grs0515@163.com
 */
public class CommHttp {

    private static final String TAG = "CommHttp";

    /**
     * 获取PDA在Web目录下配置
     */
    public static void getNurseConfig() {
        HttpUtil.get(BaseWebServiceUtils.getServiceUrl(BaseWebServiceUtils.NUR_CONFIG), new SimpleCallBack<String>() {
            @Override
            public void call(String result, int type) {
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                Field[] fields = NurseConfig.class.getDeclaredFields();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    for (Field field : fields) {
                        //包含配置
                        if (result.contains(field.getName() + "")) {
                            String value = jsonObject.getString(field.getName() + "");
                            SPStaticUtils.put(field.getName(), value);
                        }
                    }

                } catch (Exception e) {
                    Log.e(TAG,"(CommHttp.java:57) "+e.toString());
                    ToastUtils.showShort("检查配置:"+e.toString());
                }

            }
        });
    }

    /**
     * 检查用户/密码
     * @param userCode
     * @param password
     * @param callBack
     */
    public static void checkUser(String userCode, String password, CommonCallBack callBack) {
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("userCode", userCode);
        properties.put("password", password);
        properties.put("logonWardId", SPUtils.getInstance().getString(SharedPreference.WARDID));
        CommWebService.call(NurseAPI.Logon, properties, new ServiceCallBack() {
            @Override
            public void onResult(String result) {
                CommWebService.parserCommResult(result, callBack);
            }
        });
    }

    /**
     * 初始化广播
     */
    public static void initBroadcastList() {
        CommonCallBack<ScanCodeBean> callBack = new CommonCallBack<ScanCodeBean>() {
            @Override
            public void onFail(String code, String msg) {

            }

            @Override
            public void onSuccess(ScanCodeBean bean, String type) {
                TransBroadcastUtil.setScanActionList(bean.broadcastList);
            }
        };
        CommWebService.call(NurseAPI.getBroadcastConfig, new HashMap<String, String>(), new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanCodeBean> parserUtil = new ParserUtil<>();
                ScanCodeBean bean = parserUtil.parserResult(jsonStr, callBack, ScanCodeBean.class, "");
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description: 病区病人列表
     * Creator: JYW
     * Input： wardId 病区Id, userId 用户Id others: (5,3)
     */
    public static void getInWardPatList(final CommonCallBack<PatientListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        CommWebService.callHealth(NurseAPI.getInWardPatList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatientListBean> parserUtil = new ParserUtil<>();
                PatientListBean bean = parserUtil.parserResult(jsonStr, callBack, PatientListBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public class ScanCodeBean extends CommResult {

        public List<BroadcastListBean> broadcastList;

    }


}
