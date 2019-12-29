package com.dhcc.module.health.workarea.patlist.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.health.workarea.patlist.bean.PatListBean;
import com.dhcc.module.health.workarea.patlist.bean.PatOrdersBean;

import java.util.HashMap;

/**
 * com.dhcc.module.health.workarea.patlist.api
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:14:37
 */
public class PatListManager {

    /**
     * Gets pat list.
     *
     * @param callBack the call back
     *
     */
    public static void getPatList(final CommonCallBack<PatListBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("UserID", SPUtils.getInstance().getString(SharedPreference.USERID));
        properties.put("LogonID", SPUtils.getInstance().getString(SharedPreference.LOCID));
        CommWebService.callHealth("getRecoverPatList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatListBean> parserUtil = new ParserUtil<>();
                PatListBean bean = parserUtil.parserResult(jsonStr, callBack, PatListBean.class);
                if (bean == null){ return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getPatOrdersList(String appStr,final CommonCallBack<PatOrdersBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("Appstr", appStr);
        CommWebService.callHealth("getRecoverDocCureList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatOrdersBean> parserUtil = new ParserUtil<>();
                PatOrdersBean bean = parserUtil.parserResult(jsonStr, callBack, PatOrdersBean.class);
                if (bean == null){ return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

}
