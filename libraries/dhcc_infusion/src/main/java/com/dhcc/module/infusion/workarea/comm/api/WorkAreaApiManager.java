package com.dhcc.module.infusion.workarea.comm.api;

import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.comm.bean.OrdInfoBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;

/**
 * @author:gaoruishan
 * @date:202019-05-20/16:50
 * @email:grs0515@163.com
 */
public class WorkAreaApiManager {
    /**
     * Description:  获取医嘱详情
     * Input：       oeoreId:执行记录ID
     * other:		  w ##class(Nur.OPPDA.Order).getOrdInfo("568-3-1")
     */
    public static void getOrdInfo(String oeoreId, final CommonCallBack<OrdInfoBean> callback) {
        WorkAreaApiService.getOrdInfo(oeoreId, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<OrdInfoBean> parser = new ParserUtil<>();
                OrdInfoBean bean = parser.parserResult(jsonStr, callback, OrdInfoBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }

    /**
     * CreatDate:    2019-05-09
     * Description:  获取患者信息
     * Input：       regNo:登记号
     * other:		  w ##class(Nur.OPPDA.Patient).getPatInfo("0000000164")
     */
    public static void getPatInfo(String regNo, final CommonCallBack<PatDetailBean> callback) {
        WorkAreaApiService.getPatInfo(regNo, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<PatDetailBean> parser = new ParserUtil<>();
                PatDetailBean bean = parser.parserResult(jsonStr, callback, PatDetailBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }

}
