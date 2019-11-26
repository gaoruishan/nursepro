package com.dhcc.module.health.workarea.orderexecute;

import com.base.commlibs.constant.SharedPreference;
import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.blankj.utilcode.util.SPUtils;
import com.dhcc.module.health.workarea.orderexecute.bean.OrdListBean;

import java.util.HashMap;

/**
 * 医嘱执行
 * @author:gaoruishan
 * @date:202019-11-22/11:18
 * @email:grs0515@163.com
 */
public class OrderExecuteApiManager {

    /**
     * 获取扫码信息
     */
    public static void getHealthOrdInfo(String regNo, String stDate, String enDate,String exeFlag, final CommonCallBack<OrdListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
//        CommWebService.addLocId(properties);
        properties.put("regNo", regNo);
      //  properties.put("exeFlag", exeFlag);
        properties.put("LogonID", SPUtils.getInstance().getString(SharedPreference.WARDID));
//        properties.put("endDate", enDate);
        CommWebService.callHealth("getOrdByRegNo", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrdListBean> parserUtil = new ParserUtil<>();
                OrdListBean bean = parserUtil.parserResult(jsonStr, callBack, OrdListBean.class);
                if (bean == null){ return ;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }


    /**
     * 执行医嘱，返回执行结果
     */
    public static void getHealthExecuResult(String ordStr,final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        properties.put("ordStr","41||84");
        properties.put("userDeptId","150");
        properties.put("execDate","34234");
        properties.put("execTime","2343");
        CommWebService.callHealth("execOrdByordStr", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<CommResult> parserUtil = new ParserUtil<>();
                CommResult bean = parserUtil.parserResult(jsonStr, callBack, CommResult.class);
                if (bean == null){ return ;}
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
