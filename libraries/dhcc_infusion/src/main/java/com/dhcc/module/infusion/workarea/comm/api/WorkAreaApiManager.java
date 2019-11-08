package com.dhcc.module.infusion.workarea.comm.api;

import com.base.commlibs.http.CommWebService;
import com.base.commlibs.http.CommonCallBack;
import com.base.commlibs.http.ParserUtil;
import com.base.commlibs.http.ServiceCallBack;
import com.dhcc.module.infusion.workarea.comm.bean.MainConfigBean;
import com.dhcc.module.infusion.workarea.comm.bean.OrdInfoBean;
import com.dhcc.module.infusion.workarea.comm.bean.PatDetailBean;

import java.util.HashMap;

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

    /**
     * /// Creator: 		lmm
     * /// CreatDate: 		2019-11-07
     * /// Description: 	主页面模块配置
     * /// Input：
     * /// Return：   	    ##class(Nur.OPPDA.Logon).getMainConfig()
     */
    public static void getMainConfig(final CommonCallBack<MainConfigBean> callback) {
        HashMap<String, String> properties = CommWebService.addUserId(null);
        CommWebService.addGroupId(properties);
        CommWebService.addLocId(properties);
        CommWebService.call("getMainConfig", properties, new ServiceCallBack() {
            @Override
            public void onResult(String jsonStr) {
                ParserUtil<MainConfigBean> parser = new ParserUtil<>();
                MainConfigBean bean = parser.parserResult(jsonStr, callback, MainConfigBean.class);
                if (bean == null) return;
                parser.parserStatus(bean, callback);
            }
        });
    }
}
