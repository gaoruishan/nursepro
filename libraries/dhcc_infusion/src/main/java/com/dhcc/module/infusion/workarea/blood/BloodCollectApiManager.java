package com.dhcc.module.infusion.workarea.blood;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-13/15:50
 * @email:grs0515@163.com
 */
public class BloodCollectApiManager {
    /**
     * 获取采血列表
     * @param regNo
     * @param stDate
     * @param enDate
     * @param callBack
     */
    public static void getLabOrdList(String regNo, String stDate, String enDate,String exeFlag, final CommonCallBack<BloodCollectBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addHospitalId(properties);
        properties.put("regNo", regNo);
        properties.put("exeFlag", exeFlag);
        properties.put("startDate", stDate);
        properties.put("endDate", enDate);
        CommWebService.call("getLabOrdList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<BloodCollectBean> parserUtil = new ParserUtil<>();
                BloodCollectBean bean = parserUtil.parserResult(jsonStr, callBack, BloodCollectBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * 执行
     * @param labNo
     * @param callBack
     */
    public static void exeLabOrd(String labNo, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("labNo", labNo);
        CommWebService.call("exeLabOrd", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }

}
