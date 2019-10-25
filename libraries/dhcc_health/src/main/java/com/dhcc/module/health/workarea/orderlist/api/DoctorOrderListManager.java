package com.dhcc.module.health.workarea.orderlist.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.health.workarea.orderlist.bean.DocOrderListBean;
import com.dhcc.module.health.workarea.orderlist.bean.DocOrdersPatsListBean;

import java.util.HashMap;

/**
 * 医嘱单接口
 * @author:gaoruishan
 * @date:202019-10-25/10:37
 * @email:grs0515@163.com
 */
public class DoctorOrderListManager {
    /**
     * Description: 病区病人列表
     * Creator: JYW
     * Input： wardId 病区Id, userId 用户Id others: (5,3)
     */
    public static void getInWardPatList(final CommonCallBack<DocOrdersPatsListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addWardId(properties);
        CommWebService.callHealth("getInWardPatList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<DocOrdersPatsListBean> parserUtil = new ParserUtil<>();
                DocOrdersPatsListBean bean = parserUtil.parserResult(jsonStr, callBack, DocOrdersPatsListBean.class);
                if (bean == null) return;
                parserUtil.parserStatus(bean, callBack);
            }
        });
    }

    /**
     * Description: 医嘱单医嘱列表
     * Creator: JYW
     * Input： episodId 就诊号,wardId 病区Id others: ()
     */
    public static void getDocOrderList(String episodId, final CommonCallBack<DocOrderListBean> callBack) {
        HashMap<String, String> properties = CommWebService.addWardId(null);
        properties.put("episodId", episodId);
        CommWebService.callHealth("getDocOrderList", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<DocOrderListBean> parserUtil = new ParserUtil<>();
                DocOrderListBean bean = parserUtil.parserResult(jsonStr, callBack, DocOrderListBean.class);
                if (bean == null)  return;
                parserUtil.parserStatus(bean,callBack);
            }
        });
    }

}
