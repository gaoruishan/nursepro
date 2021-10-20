package com.dhcc.nursepro.workarea.rfid.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.nursepro.workarea.rfid.bean.InfusionPatBean;
import com.dhcc.nursepro.workarea.rfid.bean.RfidPatBean;

import java.util.HashMap;

/**
 * com.dhcc.nursepro.workarea.rfid.api
 * <p>
 * author Q
 * Date: 2021/6/23
 * Time:9:42
 */
public class RfidBindApiManager {
//    Parameter BindDevice = "Nur.MNIS.Service.InfusionOrd:BindDevice";
//
//Parameter UnBindDevice = "Nur.MNIS.Service.InfusionOrd:UnBindDevice";
//
//Parameter GetDeviceList = "Nur.MNIS.Service.InfusionOrd:GetDeviceList";
    public static void getDeviceList(HashMap<String, String> map, String method,  CommonCallBack<InfusionPatBean> callBack) {

        CommWebService.call( method,map, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<InfusionPatBean> parserUtil = new ParserUtil<InfusionPatBean>();
                InfusionPatBean bean = parserUtil.parserResult(jsonStr, callBack, InfusionPatBean.class);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }
    public static void getRfidPatList(HashMap<String, String> map, String method,  CommonCallBack<RfidPatBean> callBack) {

        CommWebService.call( method,map, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<RfidPatBean> parserUtil = new ParserUtil<RfidPatBean>();
                RfidPatBean bean = parserUtil.parserResult(jsonStr, callBack, RfidPatBean.class);
                if (bean==null) return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }


}
