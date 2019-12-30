package com.dhcc.module.health.workarea.orderlist.api;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatListBean;
import com.dhcc.module.health.workarea.orderlist.bean.DocPatOrdersBean;

import java.util.HashMap;

/**
 * com.dhcc.module.health.workarea.patlist.api
 * <p>
 * author Q
 * Date: 2019/11/22
 * Time:14:37
 */
public class OrderListManager {

    /**
     * Gets pat list.
     *
     * @param callBack the call back
     *
     */
    public static void getPatList(final CommonCallBack<DocPatListBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("UserID", SPUtils.getInstance().getString(SharedPreference.USERID));
//        properties.put("LogonID", SPUtils.getInstance().getString(SharedPreference.LOCID));
        properties.put("locId", SPUtils.getInstance().getString(SharedPreference.LOCID));
        CommWebService.callHealth("getRecoverPatList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<DocPatListBean> parserUtil = new ParserUtil<>();
                DocPatListBean bean = parserUtil.parserResult(jsonStr, callBack, DocPatListBean.class);
                if (bean == null){ return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    public static void getPatOrdersList(String appStr,final CommonCallBack<DocPatOrdersBean> callBack) {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("Appstr", appStr);
        CommWebService.callHealth("getRecoverDocCure", properties, new ServiceCallBack() {
                @Override
                public void onResult(String jsonStr) {
                ParserUtil<DocPatOrdersBean> parserUtil = new ParserUtil<>();
                DocPatOrdersBean bean = parserUtil.parserResult(jsonStr, callBack, DocPatOrdersBean.class);
                if (bean == null){ return;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

}
