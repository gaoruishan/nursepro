package com.dhcc.module.nurse.custom;

import com.base.commlibs.NurseAPI;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.utils.ReflectUtil;
import com.dhcc.module.nurse.custom.bean.CustomListData;
import com.dhcc.module.nurse.custom.bean.CustomScanInfo;
import com.dhcc.module.nurse.custom.bean.CustomUiConfigBean;
import com.dhcc.module.nurse.custom.bean.MainSubListBean;

import java.util.HashMap;

/**
 * 接口管理
 * @author:gaoruishan
 * @date:202022/1/28/14:47
 * @email:grs0515@163.com
 */
public class CustomApiManager extends NurseAPI {
    /**
     * 获取自定义UI配置
     * @param json
     * @param callBack
     */
    public static void getUiConfig(MainSubListBean json, CommonCallBack<CustomUiConfigBean> callBack) {
        HashMap<String, String> map = ReflectUtil.getPublicFieldsToMap(json);
        CommWebService.call(GetUIConfig, map, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CustomUiConfigBean> parserUtil = new ParserUtil<>();
                CustomUiConfigBean bean = parserUtil.parserResult(jsonStr, callBack, CustomUiConfigBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getListData(String method, HashMap<String, String> map, CommonCallBack<CustomListData> callBack) {
        CommWebService.call(method, map, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CustomListData> parserUtil = new ParserUtil<>();
                CustomListData bean = parserUtil.parserResult(jsonStr, callBack, CustomListData.class);
                if (bean == null) {
                    return;
                }
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getScanOrdList(String method, HashMap<String, String> map, CommonCallBack<CustomScanInfo> callBack) {
        CommWebService.call(method, map, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CustomScanInfo> parserUtil = new ParserUtil<>();
                CustomScanInfo bean = parserUtil.parserResult(jsonStr, callBack, CustomScanInfo.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
