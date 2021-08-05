package com.dhcc.module.infusion.workarea.blood;

import android.text.TextUtils;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.blood.bean.BloodCollectBean;
import com.dhcc.module.infusion.workarea.comm.BaseApiManager;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202019-11-13/15:50
 * @email:grs0515@163.com
 */
public class BloodCollectApiManager extends BaseApiManager {
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
        CommWebService.call(GetLabOrdList, properties, new ServiceCallBack() {
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
     */
    public static void exeLabOrd(String labNo, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("labNo", labNo);
        CommWebService.call(LabOrd(), properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }
    /**
     * 核对信息
     */
    public static void auditOrd(String barCode,String auditUserId,String auditPassword, final CommonCallBack<CommResult> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("barCode", barCode);
        if(!TextUtils.isEmpty(auditUserId)){
            properties.put("auditUserId", auditUserId);
        }
        if(!TextUtils.isEmpty(auditPassword)){
            properties.put("auditPassword", auditPassword);
        }
        CommWebService.call(auditOrd, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                CommWebService.parserCommResult(jsonStr, callBack);
            }
        });
    }

}
