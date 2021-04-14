package com.dhcc.module.infusion.workarea.drugreceive;

import com.base.commlibs.http.CommResult;
import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.base.commlibs.InfusionAPI;

import java.util.HashMap;

/**
 * @author:gaoruishan
 * @date:202021/1/5/16:35
 * @email:grs0515@163.com
 */
public class DrugReceiveApiManager extends InfusionAPI {

    //输液接受列表
    public static void getIFOrdListByBarCode( String sttDate, String endDate, CommonCallBack<IfOrdListBean> callback) {
        HashMap<String, String> properties = CommWebService.addLocId(null);
        properties.put("sttDate", sttDate);
        properties.put("endDate", endDate);

        CommWebService.call(GetReceiveOrdList, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<IfOrdListBean> parser = new ParserUtil<>();
                IfOrdListBean bean = parser.parserResult(jsonStr, callback, IfOrdListBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }

    public static void BatchIFSave(String barcode, CommonCallBack<CommResult> callback) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addLocId(properties);
        properties.put("OeoreId", barcode);

        CommWebService.call(ReceiveOrd, properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
               CommWebService.parserCommResult(jsonStr,callback);
            }
        });
    }
}
