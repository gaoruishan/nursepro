package com.dhcc.module.infusion.workarea.inject;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.comm.BaseApiManager;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-14/11:35
 * @email:grs0515@163.com
 */
public class InjectApiManager extends BaseApiManager {

    /**
     * 获取注射列表信息
     */
    public static void getInjectOrdList(String regNo, String stDate, String enDate, String exeFlag,String barCode, final CommonCallBack<InjectListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addHospitalId(properties);
        properties.put("regNo", regNo);
        properties.put("exeFlag", exeFlag);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        properties.put("barCode", barCode);
        CommWebService.call("getInjectOrdList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<InjectListBean> parserUtil = new ParserUtil<>();
                InjectListBean bean = parserUtil.parserResult(jsonStr, callBack, InjectListBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 注射-执行
     */
    public static void exeInjectOrd(String oeoreId, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("oeoreId", oeoreId);
        CommWebService.call("exeInjectOrd", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }

}