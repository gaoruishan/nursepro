package com.dhcc.module.infusion.workarea.comm;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.comm.bean.ScanInfoBean;

import java.util.HashMap;

/**
 * 公共类
 * @author:gaoruishan
 * @date:202019-11-14/14:20
 * @email:grs0515@163.com
 */
public class BaseApiManager {
    /**
     * 获取扫码信息
     * PAT 扫腕带返回患者信息
     * ORD 扫医嘱条码返回医嘱信息
     */
    public static void getScanInfo(String regNo, String barcode,  final CommonCallBack<ScanInfoBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("regNo", regNo);
        properties.put("barcode", barcode);
        CommWebService.call("getScanInfo", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<ScanInfoBean> parserUtil = new ParserUtil<>();
                ScanInfoBean bean = parserUtil.parserResult(jsonStr, callBack, ScanInfoBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }
}
